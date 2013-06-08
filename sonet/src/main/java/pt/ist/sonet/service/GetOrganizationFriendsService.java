package pt.ist.sonet.service;

import java.util.ArrayList;
import java.util.List;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.*;

public class GetOrganizationFriendsService extends SonetService {

	private AgentDto agentToList;
	private List<AgentDto> organizations;
	
	public GetOrganizationFriendsService(AgentDto agent){
		this.agentToList = agent;
	}
	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		List<AgentDto> organizations = new ArrayList<AgentDto>();
		Agent agent = sonet.getAgentByUsername(agentToList.getUsername());
		AgentDto dto;
		if(agent != null){
			for(Agent a : agent.getFriends()){
				if(a.getClass().getSimpleName().equals("Organization")){
					dto = a.createDto();
					organizations.add(dto);
				} 
			}
			this.organizations = organizations;
		} else {
			throw new AgentNotFoundException(agentToList.getUsername());
		}
	}
	
	public List<AgentDto> getOrganizations(){
		return this.organizations;
	}
}
