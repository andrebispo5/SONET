package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.largacaixa.Content;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.domain.Url;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.dto.AgentDto;

public class AddUrlService extends SonetService {
	
	private AgentDto agent;
	private String caption;
	private String url;
	private int price;
	
	public AddUrlService(AgentDto agent, String url, String caption, int price) {
		this.agent = agent;
		this.caption = caption;
		this.url = url;
		this.price = price;
	}
	
	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		Url url = new Url(sonet.generateID(), this.url, this.caption, this.price);
		Agent agent = sonet.getAgentByUsername(this.agent.getUsername());
		if(agent==null)
			throw new AgentNotFoundException(this.agent.getUsername());
		agent.addPub(url);
		Content content = new Content(this.url);
		sonet.getLargaCaixa().getPersonalContents(agent.getUsername()).addContent(content);
	}

}
