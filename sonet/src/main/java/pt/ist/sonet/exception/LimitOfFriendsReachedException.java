package pt.ist.sonet.exception;

public class LimitOfFriendsReachedException extends SonetException {
	
	private static final long serialVersionUID = 1L;

	private String Name;
	
	public LimitOfFriendsReachedException() {}
	
	public LimitOfFriendsReachedException(String AgentName) {
			this.Name = AgentName;
	}
	
	public String getName() {
		return this.Name;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Agent [" + this.Name +"] has reached the limit of friends");
	}
}







