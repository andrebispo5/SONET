package pt.ist.sonet.domain;

import java.util.ArrayList;
import java.util.List;

import jvstm.Atomic;
import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.LargaCaixaLocal;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.pagamigo.PagAmigoLocal;
import pt.ist.sonet.exception.*;

public class Sonet extends Sonet_Base {
	private final int friendLimit = 2;
	private int polemicPubLimit = -5;
	private static ExternalServiceConfig services=null;
	

	public Sonet() {
		super();
		services = ExternalServiceConfig.getInstance();
		this.setFriendLimit(friendLimit);
		this.setPolemicPubLimit(polemicPubLimit);
	}

	// ADDS AGENT newAgent TO SONET
	@Override
	public void addAgent(Agent newAgent) {
		
		String name = newAgent.getName();
		String str = newAgent.getClass().getName();
		if (str.equals("pt.ist.sonet.domain.Individual")) {
			if (!hasIndidividual(newAgent.getUsername())) {
				super.addAgent(newAgent);
				System.out.println("ADDED AGENT TO SONET: " + name);
			} else
				throw new UsernameAlreadyExistsException(newAgent.getUsername());

		} else if (str.equals("pt.ist.sonet.domain.Organization")) {
			if (!hasOrganization(newAgent.getName())) {
				super.addAgent(newAgent);
				System.out.println("ADDED AGENT TO SONET: " + name);
			} else
				throw new UsernameAlreadyExistsException(newAgent.getName());
		}

	}

	public PagAmigo getPagAmigo() {
		return ExternalServiceConfig.getFactory().makePagAmigo();
	}

	public LargaCaixa getLargaCaixa() {
		return ExternalServiceConfig.getFactory().makeLargaCaixa();
	}
	
	// CHECKS FOR USER username IN SONET
	public boolean hasIndidividual(String username) {
		return (getAgentByUsername(username) != null);
	}

	// CHECKS FOR USER Name IN SONET
	public boolean hasOrganization(String name) {
		return (getAgentByName(name) != null);
	}

	// GET AGENT WITH USERNAME username
	public Agent getAgentByUsername(String username) {
		for (Agent a : getAgentSet()) {
			if (a.getUsername().equals(username))
				return a;
		}
		return null;
	}

	// GET AGENT WITH NAME name
	public Agent getAgentByName(String name) {
		for (Agent a : getAgentSet()) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}

	// MANAGES THE ID GENERATOR FOR PUBLICATIONS AND COMMENTS
	public int generateID() {
		if(this.getIdCounter() == null)
			this.setIdCounter(0);
		int nextId = this.getIdCounter();
		nextId++;
		this.setIdCounter(nextId);
		return nextId;
	}

	// LIST ALL SONET AGENTS
	public void listAllAgents() {
		String agentClassName[];
		System.out.println("     ");
		System.out
				.println("----------------------[STARTED] LISTING ALL AGENTS:");
		for (Agent agent : getAgentSet()) {
			agentClassName = agent.getClass().getName().split("\\.");
			System.out.println("________[" + agent.getName() + "]");
			System.out.println("\tAgent Type: \t\t" + agentClassName[4] + ";");
			System.out.println("\tAgent Email: \t\t" + agent.getEmail() + ";");
			System.out.println("\tAgent City: \t\t" + agent.getCity() + ";");
			System.out.println("\tAgent Country: \t\t" + agent.getCountry()
					+ ";");
			System.out.println("     ");
			System.out.println("\tAgent Friends:");
			for (Agent friend : agent.getFriendsSet()) {
				if (friend == null) {
					System.out.println("\t\t <EMPTY>");
				} else {
					System.out.println("\t\t[Name]: " + friend.getName()
							+ " [Username]: " + friend.getUsername());
				}
			}
			if (agent.getPublicationCount() == 0) {
				System.out.println("     ");
				System.out.println("\tAgent Publications:");
				System.out.println("\t\t<EMPTY>");
			} else {
				System.out.println("     ");
				System.out.println("\tAgent Publications:");
				for (Publication pub : agent.getPublicationSet()) {
					if (pub instanceof Note) {
						System.out.println("\t\t[PubId]: " + pub.getId()
								+ " [Caption]: " + pub.getCaption()
								+ " [ScorePlus]: " + pub.getScorePlus()
								+ " [ScoreMinus]: " + pub.getScoreMinus()
								+ " [Text]: " + ((Note) pub).getText());
						
					} else {
						System.out.println("[PubId]: " + pub.getId()
								+ " [Caption]: " + pub.getCaption()
								+ " [ScorePlus]: " + pub.getScorePlus()
								+ " [ScoreMinus]: " + pub.getScoreMinus()
								+ " [Text]: " + ((Url) pub).getUrl());
					}
					System.out.println("\t\tPublication Comments:");
					if (pub.getCommentCount() == 0) {
						System.out.println("\t\t\t<EMPTY>");
					} else {
						for (Comment com : pub.getCommentSet()) {
							System.out.println("\t\t\t[ComId]: " + com.getId()
									+ " [Text]: " + com.getText());
						}
					}
				}
			}
			System.out.println("     ");

		}
		System.out.println("----------------------[ENDED] LISTING ALL AGENTS:");
		System.out.println("     ");
	}
	
	
	
	
	public Publication getPublicationById(int id) throws PublicationNotFoundException{
		for(Publication p : getPublicationSet()){
				if (p.getId() == id)
					return p;
		}
		throw new PublicationNotFoundException(id);
	}
	
	public List<Agent> getPublicAgents(){
		ArrayList<Agent> agentList = new ArrayList<Agent>(); 
		for(Agent a : this.getAgentSet()){
			if(a.getPermission() == Permission.PUBLIC){
				agentList.add(a);
			}
		}
		return agentList;
	}
}
