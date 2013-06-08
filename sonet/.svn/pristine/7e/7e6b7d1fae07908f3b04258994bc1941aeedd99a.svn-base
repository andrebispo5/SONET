package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;

public class AcceptConnectionService extends SonetService {
	
	private AgentDto requestor;
	private AgentDto acceptor;
	
	public AcceptConnectionService(AgentDto s, AgentDto d) {
		this.requestor = s;
		this.acceptor = d;
	}

	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agentSource;
		Agent agentDestination;
		
		agentSource = sonet.getAgentByUsername(this.requestor.getUsername());
		if(agentSource == null)
			throw new AgentNotFoundException(requestor.getUsername());
		else{
		
		
			
			agentDestination = sonet.getAgentByUsername(this.acceptor.getUsername());
			
			if(agentDestination == null)
				throw new AgentNotFoundException(acceptor.getUsername());
			else
				((Individual) agentDestination).acceptRequest(agentSource.getUsername());
		}
		
		
		
	}
}