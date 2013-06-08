package pt.ist.sonet.exception;

public class UsernameAlreadyExistsException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	private String conflictName;
	
	public UsernameAlreadyExistsException() {}
	
	public UsernameAlreadyExistsException(String conflictingName) {
			this.conflictName = conflictingName;
	}
	
	public String getConflictingName() {
		return this.conflictName;
	}
	
	@Override
	public String toString(){
		return ("ERROR: There is already a user " + this.conflictName);
	}
}

