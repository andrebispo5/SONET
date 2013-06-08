package pt.ist.sonet.domain;

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.LimitOfFriendsReachedException;
import pt.ist.sonet.service.dto.IndividualDto;

public class Individual extends Individual_Base {

	public Individual() {
		super();
	}

	public Individual(String username, String name, String email, String city,
			String country, String password) {
		super();
		init(username, name, email, city, country, password);
	}

	public Individual(String username, String name, String email, String city,
			String country, String password, Permission permission) {
		super();
		init(username, name, email, city, country, password, permission);
	}

	// ACCEPT A FRIEND REQUEST FROM AGENT WITH USERNAME username
	public void acceptRequest(String username)
			throws LimitOfFriendsReachedException {
		int maxFriends = this.getSonet().getFriendLimit();
		int curFriends = this.getFriendListCount();
		if (curFriends < maxFriends) {
			for (Agent a : getRequestsSet()) {
				if (a.getUsername().equals(username)) {
					addFriends(a);
					removeRequests(a);
					a.acceptedBy(this);
				}
			}
			System.out.println(this.getUsername() + " ACCEPTED REQUEST FROM "
					+ username);
		} else {
			throw new LimitOfFriendsReachedException(this.getUsername());
		}

	}

	// REMOVE A FRIEND REQUEST FROM AGENT WITH USERNAME username
	public void removeRequest(String username) {
		for (Agent a : getRequestsSet()) {
			if (a.getUsername().equals(username)) {
				removeRequests(a);
			}
		}
		System.out.println(this.getUsername() + " DENIED REQUEST FROM "
				+ username);
	}
	
	public IndividualDto createDto(){
		IndividualDto individual;
		individual = new IndividualDto(this.getUsername(), this.getName(), this.getEmail(), this.getCity(), this.getCountry(), this.getPassword(), this.getPermission().toString());
		return individual;
	}
}
