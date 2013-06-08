package pt.ist.sonet.service;

import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Organization;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.dto.AgentDto;

public class SearchOrganizationService extends SonetService {

	private String nameToSearch;
	private AgentDto foundAgent;
	
	public SearchOrganizationService(String name){
		this.nameToSearch = name;
	}
	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		List<Agent> orgs = sonet.getAgent();
		for(Agent a : orgs){
			if(a.getClass().getSimpleName().equals("Organization") && a.getUsername().equals(nameToSearch)){
				foundAgent = a.createDto();
				return;
			}
		}
		throw new AgentNotFoundException(nameToSearch);
	}
	
	public AgentDto getOrg(){
		return this.foundAgent;
	}
}
