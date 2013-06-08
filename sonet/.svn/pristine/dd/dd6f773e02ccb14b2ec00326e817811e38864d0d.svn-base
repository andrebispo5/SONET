package pt.ist.sonet.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.largacaixa.Content;
import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.PersonalContents;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Publication;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.domain.Url;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.UrlDto;

public class StartLargaCaixaService extends SonetService {
	
	
	public StartLargaCaixaService(){
	}
	
	public void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		LargaCaixa largacaixa = this.getLargaCaixa();
		for (Agent agent : sonet.getAgentSet()) {
			for (Publication pub : agent.getPublicationSet()) {
				if(pub.getClass().getSimpleName().equals("Url")){
					PersonalContents contBox = largacaixa.getPersonalContents(agent.getUsername());
					contBox.addContent(new Content(((Url)pub).getUrl()));
				}
			}
		}	

	}

}

