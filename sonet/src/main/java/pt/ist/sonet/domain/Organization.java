package pt.ist.sonet.domain;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.UsernameAlreadyExistsException;
import pt.ist.sonet.service.dto.IndividualDto;
import pt.ist.sonet.service.dto.OrganizationDto;


public class Organization extends Organization_Base {
    
    public  Organization() {
        super();
    }
    
    public Organization(String username, String name, String email, String city, String country, String password) {
        super();
        init(username, name, email, city, country, password);
    }
    
    public Organization(String username, String name, String email, String city, String country, String password, Permission permission) {
        super();
        init(username, name, email, city, country, password, permission);
    }
   
    //ACCEPTS A FRIEND REQUEST AND COMMUNICATE IT TO AGENT r
    public void addToRequests(Agent newFriend) throws UsernameAlreadyExistsException{
    	addFriends(newFriend);
    	newFriend.acceptedBy(this);
    	System.out.println("REQUEST FROM " + newFriend.getUsername() + " TO " + this.getUsername() );
    	System.out.println(this.getUsername() + " ACCEPTED REQUEST FROM " + newFriend.getUsername());
    }
    
    public void addFriend(Agent agent) throws AgentNotFoundException{
		if(agent != null)
			agent.addToRequests(this);
		else
			throw new AgentNotFoundException(agent.getUsername());
			
    	
    }
    
    public OrganizationDto createDto(){
		OrganizationDto organization = new OrganizationDto(this.getUsername(), this.getName(), this.getEmail(), this.getCity(), this.getCountry(), this.getPassword(),this.getPermission().toString());	
		return organization;
    }
}
