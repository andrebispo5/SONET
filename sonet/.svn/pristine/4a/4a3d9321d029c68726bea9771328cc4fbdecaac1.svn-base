package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;

public class RejectConnectionService extends SonetService {
	
	//AgentDto source is the agent who want to establish a connection
	private AgentDto source;
	//AgentDto destination is the target of the connection
	private AgentDto destination;
	
	public RejectConnectionService(AgentDto s, AgentDto d) {
		this.source = s;
		this.destination = d;
	}

	
	public final void dispatch() throws AgentNotFoundException {
		Sonet sonet = FenixFramework.getRoot();
		Agent agentSource;
		Agent agentDestination;
		
		agentSource = sonet.getAgentByUsername(this.source.getUsername());
		if(agentSource==null)
			throw new AgentNotFoundException(this.source.getUsername());
		else{
			agentDestination = sonet.getAgentByUsername(this.destination.getUsername());
			if(agentDestination==null)
				throw new AgentNotFoundException(this.destination.getUsername());
			else{
				((Individual) agentDestination).removeRequest(agentSource.getUsername());
			}
		}
	}
}