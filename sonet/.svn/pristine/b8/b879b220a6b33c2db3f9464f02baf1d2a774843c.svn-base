package pt.ist.sonet.service;

import java.util.List;
import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.*;
import jvstm.Atomic;
import pt.ist.sonet.service.dto.*;

public class ListPendingsService extends SonetService{
	
	private AgentDto agentToSearch;
	private List<AgentDto> pendingsList;
	
	
	public ListPendingsService(AgentDto agent) {
		this.agentToSearch = agent;
	}
	
	public final void dispatch() throws AgentNotFoundException {
		Sonet sonet = FenixFramework.getRoot();
		List<AgentDto> reqList = new ArrayList<AgentDto>();
		Agent agentTest=sonet.getAgentByUsername(agentToSearch.getUsername());
		if(agentTest==null){
			throw new AgentNotFoundException(agentToSearch.getUsername());
		}
		List<Agent> requests = agentTest.getRequests();

		
		if(requests != null) {
			
			for(Agent a : requests){
				AgentDto agnt = new AgentDto(a.getUsername(),a.getName(),a.getEmail(),a.getCity(),a.getCountry()," ");
				reqList.add(0,agnt);
			}
			this.pendingsList = reqList;
			
		}
		
			
	}

	public List<AgentDto> getRequests(){
		return this.pendingsList;
	}
}