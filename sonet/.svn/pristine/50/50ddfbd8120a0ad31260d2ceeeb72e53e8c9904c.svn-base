package pt.ist.sonet.service;

import java.util.ArrayList;
import java.util.List;

import jvstm.Atomic;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Publication;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.domain.Url;
import pt.ist.sonet.domain.Note;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.NoteDto;
import pt.ist.sonet.service.dto.UrlDto;
import pt.ist.sonet.service.dto.PublicationDto;

public class ObtainPublicationsService extends SonetService {

	private AgentDto activeAgent;
	private AgentDto requestedAgent;
	private List<PublicationDto> publicationList;
	
	public ObtainPublicationsService(AgentDto activeAgent, AgentDto requestedAgent){
		this.activeAgent = activeAgent;
		this.requestedAgent = requestedAgent;
	}
	
	
	@Override
	public void dispatch() throws AgentNotFoundException {
		Sonet sonet = FenixFramework.getRoot();
		List<PublicationDto> publicationList = new ArrayList<PublicationDto>();

		
		Agent activeAgent = sonet.getAgentByUsername(this.activeAgent.getUsername());
		Agent requestedAgent = sonet.getAgentByUsername(this.requestedAgent.getUsername());
		if(activeAgent == null){
			throw new AgentNotFoundException(this.activeAgent.getUsername());
		}
		if(requestedAgent == null){
			throw new AgentNotFoundException(this.requestedAgent.getUsername());
		}
			for(Publication p : requestedAgent.getPublication()){
				if(requestedAgent.getPermission().canPermission(activeAgent, p) || activeAgent == requestedAgent){					
					publicationList.add(p.createDto());
				}
				

			}
			this.publicationList = publicationList; 
		}
		

	

	public List<PublicationDto> getPublications(){
		return this.publicationList;
	}
	
	public List<UrlDto> getContent(){
		List<UrlDto> content = new ArrayList<UrlDto>();
		for(PublicationDto pub : this.publicationList){
			if(pub.getType() == "url"){
				content.add((UrlDto) pub);
			}
		}
		return content;
	}

}
