package pt.ist.sonet.exception;

public class PolemicPublicationException extends SonetException {

	private static final long serialVersionUID = 1L;
	
	public PolemicPublicationException() {}

	@Override
	public String toString() {
		return "ERROR: Publication is polemic and can not be voted nor commented ";
	}

}
