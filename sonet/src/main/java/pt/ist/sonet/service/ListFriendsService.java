package pt.ist.sonet.service;

import java.util.List;
import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;

public class ListFriendsService extends SonetService{
	
	private AgentDto agentToList;
	private List<AgentDto> friendsList;
	
	public ListFriendsService(AgentDto agent) {
		this.agentToList = agent;
	}
	
	public final void dispatch() throws AgentNotFoundException {
		Sonet sonet = FenixFramework.getRoot();
		List<AgentDto> friendsList = new ArrayList<AgentDto>();
		Agent agent = sonet.getAgentByUsername(agentToList.getUsername());
		AgentDto dto;
		if(agent != null){
			for(Agent a : agent.getFriends()) {
				dto = new AgentDto(a.getUsername(), a.getName(),a.getEmail(),a.getCity(),a.getCountry(),a.getPassword(), a.getClass().getSimpleName());
				friendsList.add(dto);
			}
			this.friendsList = friendsList; 
		}
		else
			throw new AgentNotFoundException(agentToList.getUsername());

	}

	public List<AgentDto> getFriends(){
		return this.friendsList;
	}
}