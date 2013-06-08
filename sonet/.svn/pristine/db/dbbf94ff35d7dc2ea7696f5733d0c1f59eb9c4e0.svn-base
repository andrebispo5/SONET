package pt.ist.sonet.exception;

public class CantVoteTwiceException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	private int publicationId;
	
	public CantVoteTwiceException() {}
	
	public CantVoteTwiceException(int pubId) {
			this.publicationId = pubId;
	}
	
	public int getPublicationId() {
		return publicationId;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Cant vote twice on publication with ID: " + this.publicationId);
	}
}