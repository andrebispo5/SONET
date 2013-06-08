package pt.ist.sonet.exception;

public class PublicationAccessDeniedException extends SonetException {
	
	private static final long serialVersionUID = 1L;

	private int publicationId;
	public PublicationAccessDeniedException(){}
	public PublicationAccessDeniedException(int pubId) {
			this.publicationId = pubId;
	}
	
	public int getpublicationId() {
		return this.publicationId;
	}
	
	@Override
	public String toString(){
		return ("ERROR: Permission denied accessing publication #" + this.publicationId);
	}
}







