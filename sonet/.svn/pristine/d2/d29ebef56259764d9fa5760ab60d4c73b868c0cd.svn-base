package pt.ist.sonet.domain;

import jvstm.Atomic;
import pt.ist.sonet.exception.CantRequestOwnException;
import pt.ist.sonet.exception.FriendAlreadyExistsException;
import pt.ist.sonet.exception.FriendRequestAlreadyExistsException;
import pt.ist.sonet.exception.LimitOfFriendsReachedException;
import pt.ist.sonet.exception.PolemicPublicationException;
import pt.ist.sonet.exception.PublicationAccessDeniedException;
import pt.ist.sonet.exception.PublicationNotFoundException;
import pt.ist.sonet.exception.UsernameAlreadyExistsException;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.CantVoteSelfException;
import pt.ist.sonet.service.dto.AgentDto;

public class Agent extends Agent_Base {

	public Agent() {
	}

	// SETS DEFAULT AGENT PERMISSION TO amigo
	public void init(String username, String name, String email, String city,
			String country, String password) {
		setPermission(Permission.FRIEND);
		setUsername(username);
		setName(name);
		setEmail(email);
		setCity(city);
		setCountry(country);
		setPassword(password);
	}

	// ADD A CUSTOM PERMISSION TO THE AGENT PROFILE
	public void init(String username, String name, String email, String city,
				String country, String password, Permission permission) {
		setPermission(permission);
		setUsername(username);
		setName(name);
		setEmail(email);
		setCity(city);
		setCountry(country);
		setPassword(password);
	}

	// CHECK IF ALREADY IN REQUESTLIST AND IF NOT ADD IT
	public void addToRequests(Agent r) throws FriendRequestAlreadyExistsException, CantRequestOwnException, FriendAlreadyExistsException {
		String name = r.getUsername();
		if(this.getUsername().equals(name))
			throw new CantRequestOwnException();
		for (Agent a : getFriends()){
			if(a.getUsername().equals(name))
				throw new FriendAlreadyExistsException(name);
		}
		for (Agent a : getRequestsSet()) {
			if (a.getUsername().equals(name))
				throw new FriendRequestAlreadyExistsException();
		}
		this.addRequests(r);
		System.out.println("REQUEST FROM " + r.getUsername() + " TO "
				+ this.getUsername());
	}

	
	// AGENT IS DIRECTLY PASSED AS AN ARGUMENTE
	// THIS WAY DONT NEED TO SEARCH ON SONET FOR AGENT
	public void addFriend(Agent agent) throws AgentNotFoundException,LimitOfFriendsReachedException {
		String newFriendClass = agent.getClass().getSimpleName();
		if(newFriendClass.equals("Organization")){
			if(!(this.getFriendListCount() < this.getSonet().getFriendLimit())){
				throw new LimitOfFriendsReachedException(this.getUsername());
			}	
		}
		agent.addToRequests(this);		
	}

	// RESPONSE METHOD TO A FRIEND RESQUEST FROM newFriend
	public void acceptedBy(Agent newFriend) {
		addFriends(newFriend);
	}
	
	// ADD PUBLICATION TO AGENT PUB LIST
	public void addPub(Publication pub) {
		this.addPublication(pub);
		Sonet sonet = this.getSonet();
		sonet.addPublication(this.getPublicationById(pub.getId()));
		System.out.println("ADDED PUBLICATION TO AGENT " + this.getName() +" WITH ID "+pub.getId());
	}

	// ADD COMMENT com TO PUBLICATION pub
	public void commentPub(Publication pub, Comment com) throws PublicationAccessDeniedException, PolemicPublicationException{
		if(!isPolemic(pub)){
			if (hasPermissionComment(pub)) {
				
				pub.addComment(com);
				System.out.println("ADDED COMMENT TO PUBLICATION WITH ID ["
						+ pub.getId() + "] BY AGENT ["+this.getName() +"]");
			}else{
				throw new PublicationAccessDeniedException(pub.getId());
			}
		}else{
			throw new PolemicPublicationException(); 
		}
	}

	public boolean hasPermissionComment(Publication pub) {
		Agent pubOwner = pub.getAgent();
		if(this.getUsername().equals(pubOwner.getUsername()))
			return true;
		if (pubOwner.getPermission().canPermission(this, pub))
			return true;
		return false;
	}

	// TESTS PERMISSION AGENT-PUBLICATION
	public boolean hasPermission(Publication pub) throws CantVoteSelfException, PolemicPublicationException {
		if(!isPolemic(pub)){
			Agent pubOwner = pub.getAgent();
			if (pubOwner.getUsername().equals(this.getUsername()))
				throw new CantVoteSelfException(pub.getId());
			if (pubOwner.getPermission().canPermission(this, pub))
				return true;
			return false;
		}else{
			throw new PolemicPublicationException();
		}
	}

	// ADD POSITIVE VOTE TO PUBLICATION pub
	public void votePlus(Publication pub, Sonet sonet) {
		if (hasPermission(pub)) {
			pub.votePlus(this);
		}
	}

	// ADD NEGATIVE VOTE TO PUBLICATION pub
	public void voteMinus(Publication pub, Sonet sonet) {
		if (hasPermission(pub)) {
			pub.voteMinus(this);
		}
	}

	// TRY TO GET A PUBLICATION WITH THE SPECIFIED ID
	public Publication getPublicationById(int id) throws PublicationNotFoundException{
		for (Publication p : getPublicationSet()) {
			if (p.getId() == id)
				return p;
		}
		throw new PublicationNotFoundException(id);
	}
	public boolean isPolemic(Publication pub){
		int positive = pub.getScorePlus();
		int negative = pub.getScoreMinus();
		int score = positive - negative;
		Sonet sonet = pub.getSonet();
		if(score>sonet.getPolemicPubLimit()){
			return false;
		}else{
			return true;
		}
	}
	
	public AgentDto createDto(){
		AgentDto agent = new AgentDto(this.getUsername(), this.getName(), this.getEmail(), this.getCity(), this.getCountry(), this.getPassword(),this.getPermission().toString());
		return agent;
	}
}
