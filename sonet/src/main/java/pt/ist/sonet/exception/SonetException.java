package pt.ist.sonet.exception;

public abstract class SonetException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public SonetException() {}

	public abstract String toString();
}
