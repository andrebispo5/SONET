package pt.ist.sonet.exception;

public class PermissionDeniedException extends SonetException {
	
	private static final long serialVersionUID = 1L;

	public PermissionDeniedException(){}
	

	
	@Override
	public String toString(){
		return ("You don't have permission to see that agent profile!");
	}
}







