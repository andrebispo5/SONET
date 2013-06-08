package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;

public class AddNoteService extends SonetService{
	
	private AgentDto dto;
	private String notedto;
	private String captiondto;
	
	public AddNoteService(AgentDto agent, String text, String caption) {
		this.dto = agent;
		this.notedto = text;
		this.captiondto = caption;
		
	}

	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agent;
		Note note = new Note (sonet.generateID() ,this.notedto,this.captiondto);
		
		//UNIQUE PROPERTY: USERNAME FOR INDIVIDUAL OR NAME FOR ORGANIZATION
		agent = sonet.getAgentByUsername(this.dto.getUsername());
		if(agent==null)
			throw new AgentNotFoundException(dto.getUsername());
		agent.addPub(note);

	}

}
