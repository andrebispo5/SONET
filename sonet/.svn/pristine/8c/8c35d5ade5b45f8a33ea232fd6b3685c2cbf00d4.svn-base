package pt.ist.sonet.service;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.*;

public class AddCommentService extends SonetService {

	private AgentDto aDto;
	private PublicationDto pubDto;
	private String commDto;
	
	
	public AddCommentService(AgentDto agentDto, PublicationDto publicationDto, String commentDto) {
		this.aDto = agentDto;
		this.pubDto = publicationDto;
		this.commDto = commentDto;
	}

	
	public final void dispatch() throws PublicationAccessDeniedException, PolemicPublicationException, PublicationNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agent;
		Publication pub;
		Comment com = new Comment(sonet.generateID(), this.aDto.getUsername(), this.commDto);
		agent = sonet.getAgentByUsername(this.aDto.getUsername());
		pub = sonet.getPublicationById(pubDto.getId());
		agent.commentPub(pub,com);
		
	}
}
