package pt.ist.sonet.exception;

public class CantRequestOwnException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	public CantRequestOwnException() {}
	
	@Override
	public String toString(){
		return ("You cant request your own!");
	}
}

