package pt.ist.sonet.service;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;

public class CreateAgentService extends SonetService {

	private AgentDto dto;
	
	public CreateAgentService(AgentDto agentDto) {
		this.dto = agentDto;
	}

	
	public final void dispatch(){
		Sonet sonet = FenixFramework.getRoot();
		String str = this.dto.getClass().getName();
		if(str.equals("pt.ist.sonet.service.dto.IndividualDto")){
			sonet.addAgent(new Individual(this.dto.getUsername(), this.dto.getName(), this.dto.getEmail(), this.dto.getCity(), this.dto.getCountry(), this.dto.getPassword(), Permission.generatePermission(this.dto.getPermission())));
		}else if (str.equals("pt.ist.sonet.service.dto.OrganizationDto")){
			sonet.addAgent(new Organization(this.dto.getUsername(), this.dto.getName(), this.dto.getEmail(), this.dto.getCity(), this.dto.getCountry(), this.dto.getPassword(), Permission.generatePermission(this.dto.getPermission())));
		}
	}
}
