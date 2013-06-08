package pt.ist.sonet.exception;

public class PublicationNotFoundException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	private int publicationId;
	public PublicationNotFoundException(){}
	public PublicationNotFoundException(int pubId) {
			this.publicationId = pubId;
	}
	
	public int getPublicationId() {
		return this.publicationId;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Publication with ID: " + this.publicationId + " Not Found!");
	}
}