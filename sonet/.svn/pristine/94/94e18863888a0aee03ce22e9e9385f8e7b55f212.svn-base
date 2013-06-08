package pt.ist.sonet.service;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.*;

public class VoteService extends SonetService {

	private AgentDto aDto;
	private PublicationDto pubDto;
	private int type;
	
	
	public VoteService(AgentDto agentDto, PublicationDto publicationDto, int type) {
		this.aDto = agentDto;
		this.pubDto = publicationDto;
		this.type = type;
	}

	
	public final void dispatch() throws CantVoteSelfException, CantVoteTwiceException, PolemicPublicationException, PublicationNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agent;
		Publication pub;
		agent = sonet.getAgentByUsername(this.aDto.getUsername());
		pub = sonet.getPublicationById(pubDto.getId());
		if(type==1){
			agent.votePlus(pub,sonet);
		}
		else{
			agent.voteMinus(pub, sonet);
		}
	}
}
