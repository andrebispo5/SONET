package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.service.dto.*;

public class RemoveCommentService extends SonetService{

	private PublicationDto pub;
	private CommentDto comment;
	
	public RemoveCommentService(PublicationDto pub, CommentDto comment) {
		this.pub = pub;
		this.comment = comment;
	}
	
	public final void dispatch(){
		Publication publication = getPublication(pub.getId());		
		publication.removeComment(this.comment.getId());
	}
	
	
}
