package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Permission;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.PermissionDeniedException;
import pt.ist.sonet.exception.WrongPasswordException;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.AgentLoginDto;
import pt.ist.sonet.service.dto.IndividualDto;

public class ViewAgentProfileService extends SonetService {

	private AgentDto agentLoginDto;
	private AgentDto agentTargetDto;
	private AgentDto result;

	public ViewAgentProfileService(AgentDto loggedAgent, AgentDto dto) {
		this.agentLoginDto = loggedAgent;
		this.agentTargetDto = dto;
	}

	public final void dispatch() throws AgentNotFoundException, PermissionDeniedException {
		Sonet sonet = FenixFramework.getRoot();
		Agent agentLogin = sonet.getAgentByUsername(this.agentLoginDto.getUsername());
		Agent agentTarget = sonet.getAgentByUsername(this.agentTargetDto.getUsername());

		if (agentLogin == null)
			throw new AgentNotFoundException(agentLoginDto.getUsername());
		if (agentTarget == null)
			throw new AgentNotFoundException(agentTargetDto.getUsername());
		//SEE SAME PROFILE
		if (agentLogin.getUsername().equals(agentTarget.getUsername())) {
			result = agentLogin.createDto();
			return;
		}
		
		// IS PUBLIC?
		for (Agent a : sonet.getPublicAgents()) {
			if (a.getUsername() == agentTarget.getUsername()) {
				result = a.createDto();
				return;
			}
		}

		// IS FRIEND or FRIENDoFRIEND?
		if (result == null) {
			for (Agent a : agentLogin.getFriendsSet()) {
				if(agentTarget.getPermission() == Permission.FRIEND){
					if (a.getUsername().equals(agentTarget.getUsername())) {
						result = a.createDto();
						return;
					}
				}
				
				if(agentTarget.getPermission() == Permission.FRIENDOFRIEND){
					for (Agent friend : a.getFriendsSet()) {
						if (friend.getUsername().equals(agentTarget.getUsername())) {
							result = agentTarget.createDto();
							return;
						}
					}
				}
			}
		}

		if (result == null)
			throw new PermissionDeniedException();

	}

	public final AgentDto getAgentDto() {
		return this.result;
	}
}