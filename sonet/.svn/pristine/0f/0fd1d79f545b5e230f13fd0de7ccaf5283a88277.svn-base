package pt.ist.sonet.domain;

import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.exception.CantVoteSelfException;
import pt.ist.sonet.exception.CantVoteTwiceException;
import pt.ist.sonet.service.dto.PublicationDto;

public class Publication extends Publication_Base {
	
	public Publication() {}
	
    public void init(Integer id) {
        setId(id);
        setCaption("No Caption");
        setScorePlus(0);
        setScoreMinus(0);
       }
    public void init(Integer id, String caption) {
        setId(id);
        setCaption(caption);
        setScorePlus(0);
        setScoreMinus(0);
       }
    //POSITIVE VOTE ON A PUBLICATION
	public void votePlus(Agent agent) {
		if(checkVoter(agent)) {
			int i = getScorePlus();
			setScorePlus(++i);
			System.out.println("POSITIVE VOTE ON PUBLICATION WITH ID: " + this.getId() + " BY " + agent.getUsername());
		}
	}
	
	//NEGATIVE VOTE ON A PUBLICATION
	public void voteMinus(Agent agent) {
		if(checkVoter(agent)) {
			int i = getScoreMinus();
			setScoreMinus(++i);
			System.out.println("NEGATIVE VOTE ON PUBLICATION WITH ID: " + this.getId() + " BY " + agent.getUsername());
		}
	}

	//CHECK IF AGENT IS VOTING HIS OWN PUB OR IF ALREADY VOTED
	public boolean checkVoter(Agent agent) throws CantVoteSelfException, CantVoteTwiceException {
			if(getAgentByName(agent.getUsername()) != null)
					throw new CantVoteTwiceException(this.getId());
			this.addVoters(agent);
			return true;
		
	}
    
	//GET AN AGENT FROM THE VOTERSLIST IN A PUBLICATION
	private Agent getAgentByName(String username) {
		for(Agent agent : getVotersSet()) {
		    if(agent.getUsername().equals(username))
		    	return agent;
		}
		return null;
	}
	
	
	
	
    public Comment getCommentById(int id) {
    	for(Comment comment : getCommentSet()) {
    		if(comment.getId().equals(id)) {
    			return comment;
    		}
    	}
    	return null;
    }
	
	
    public void removeComment(int id) {
    	Comment toRemove = getCommentById(id);
    	if (toRemove == null)
    		//throw new ContactDoesNotExistException(contactName);
	
    	super.removeComment(toRemove);
    }
	
	public PublicationDto createDto(){
		PublicationDto publication = new PublicationDto();
		return publication;
	}
	
	
	
}