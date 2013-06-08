package pt.ist.sonet.exception;

public class WrongPasswordException extends SonetException {

	private static final long serialVersionUID = 1L;

	private String name;
	public WrongPasswordException(){}
	public WrongPasswordException(String AgentName) {
			this.name = AgentName;
	}
	@Override
	public String toString() {
		return "ERROR: Password for agent " + this.name + " is incorrect!";
	}

}
