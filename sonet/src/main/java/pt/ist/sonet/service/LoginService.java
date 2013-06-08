package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.WrongPasswordException;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.AgentLoginDto;
import pt.ist.sonet.service.dto.IndividualDto;

public class LoginService extends SonetService{
	
	// Agent to login
	private AgentLoginDto agentLoginDto;
	private AgentDto result;
	
	public LoginService(AgentLoginDto agent) {
		this.agentLoginDto = agent;		
	}
	
	
	public final void dispatch() throws  AgentNotFoundException, WrongPasswordException{
		Sonet sonet = FenixFramework.getRoot();
		Agent agent;
		agent = sonet.getAgentByUsername(this.agentLoginDto.getUsername());
		if(agent == null)
			throw new AgentNotFoundException(agentLoginDto.getUsername());
		if(!agent.getPassword().equals(agentLoginDto.getPassword()))	
			throw new WrongPasswordException(agentLoginDto.getUsername());
		result = new AgentDto(agent.getUsername(), agent.getName(),agent.getEmail(),agent.getCity(),agent.getCountry(),agent.getPassword());
	}
	public final AgentDto getAgentDto(){
		return this.result;
	}
}