package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;

public class AddFriendService extends SonetService{

	//AgentDto adder is the agent who want to add
	private AgentDto adder;
	//AgentDto requested is the agent who gets the request
	private AgentDto requested;


	//constructor
	public AddFriendService(AgentDto adder, AgentDto requested){
		this.adder = adder;
		this.requested = requested;
	}

	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agentAdder = sonet.getAgentByUsername(this.adder.getUsername());
		if(agentAdder==null)
			throw new AgentNotFoundException(adder.getUsername());
		Agent agentRequested = sonet.getAgentByUsername(this.requested.getUsername());
		if(agentRequested==null)
			throw new AgentNotFoundException(requested.getUsername());	
		agentAdder.addFriend(agentRequested);
		
		
	}
}
