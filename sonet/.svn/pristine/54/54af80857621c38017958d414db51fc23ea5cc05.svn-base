package pt.ist.sonet.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.largacaixa.Content;
import pt.ist.largacaixa.LargaCaixa;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Publication;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.UrlDto;

public class GetContentService extends SonetService {
	
	private AgentDto seller;
	private List<UrlDto> contentList;
	
	public GetContentService(AgentDto seller){
		this.seller = seller;
	}
	
	public void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		LargaCaixa largacaixa = this.getLargaCaixa();
		Agent seller = sonet.getAgentByUsername(this.seller.getUsername());
		if(seller == null){
			throw new AgentNotFoundException(this.seller.getUsername());
		}
		List<UrlDto> contentList = new ArrayList<UrlDto>();
		List<Publication> pubs = seller.getPublication();		
		for(Content content : largacaixa.getPersonalContents(seller.getUsername()).getAllContents()){
			contentList.add((UrlDto) pubs.get(pubs.indexOf(content.getID())).createDto());
		}
		this.contentList = contentList;	
	}
	
	public List<UrlDto> getContent(){
		return this.contentList;
	}
}

