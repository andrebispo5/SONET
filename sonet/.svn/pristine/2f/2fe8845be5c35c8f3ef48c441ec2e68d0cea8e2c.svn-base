package pt.ist.sonet.exception;

public class AgentNotFoundException extends SonetException {
	
	private static final long serialVersionUID = 1L;

	private String NameNotFound;
	public AgentNotFoundException(){}
	public AgentNotFoundException(String AgentName) {
			this.NameNotFound = AgentName;
	}
	
	public String getNameNotFound() {
		return this.NameNotFound;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Cant find user with username: " + this.NameNotFound);
	}
}







