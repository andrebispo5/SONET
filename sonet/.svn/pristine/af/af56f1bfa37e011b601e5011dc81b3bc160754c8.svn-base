package pt.ist.sonet.exception;

public class CantVoteSelfException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	private int publicationId;
	
	public CantVoteSelfException(){}
	
	public CantVoteSelfException(int pubId) {
			this.publicationId = pubId;
	}
	
	public int getPublicationId() {
		return this.publicationId;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Cant vote on your own publication with ID: " + this.publicationId);
	}
}