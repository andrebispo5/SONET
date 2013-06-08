package pt.ist.sonet.service;

import java.util.List;
import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.*;
import jvstm.Atomic;
import pt.ist.sonet.service.dto.*;

public class ListCommentsService extends SonetService{
	
	private PublicationDto publicationToList;
	private List<CommentDto> commentsList;
	
	public ListCommentsService(PublicationDto pub) {
		this.publicationToList = pub;
	}
	
	public final void dispatch() throws PublicationNotFoundException {
		Sonet sonet = FenixFramework.getRoot();
		List<CommentDto> commentsList = new ArrayList<CommentDto>();
		Publication publication = sonet.getPublicationById(publicationToList.getId());
		
		if(publication != null) {
			
			for(Comment c : publication.getComment()){
				CommentDto com = new CommentDto(c.getId(),c.getAgentName(), c.getText());
				commentsList.add(0,com);
			}
			this.commentsList = commentsList; 
		}
		else{
			throw new PublicationNotFoundException(publicationToList.getId());
		}
			
	}

	public List<CommentDto> getComment(){
		return this.commentsList;
	}
}