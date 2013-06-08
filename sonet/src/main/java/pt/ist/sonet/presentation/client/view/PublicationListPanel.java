package pt.ist.sonet.presentation.client.view;

import java.util.List;

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.PublicationNotFoundException;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.presentation.client.SonetGWT;
import pt.ist.sonet.presentation.client.SonetServletAsync;
import pt.ist.sonet.service.dto.AgentDto;

import pt.ist.sonet.service.dto.CommentDto;
import pt.ist.sonet.service.dto.PublicationDto;
import pt.ist.sonet.service.dto.UrlDto;
import pt.ist.sonet.service.dto.NoteDto;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.core.client.GWT;

public class PublicationListPanel extends FlexTable {

	private final SonetServletAsync rpcService;
	private AgentDto loggedAgent;
	private SonetGWT rootPage;
	
	public PublicationListPanel(SonetServletAsync rpcService, SonetGWT rootPage) {
		
		this.rootPage = rootPage;
		this.rpcService = rpcService;
		this.loggedAgent = rootPage.getActiveAgent();
		
	    setText(0, 0, "Caption");
	    setText(0, 1, "Positive Score");
	    setText(0, 2, "Negative Score");
	    setText(0, 3, "Number of Comments");
	    setText(0, 4, "Text");
	    setText(0, 5, "Price");
	    setText(0, 6, "Vote Plus");
	    setText(0, 7, "Vote Minus");
	    setText(0, 8, "Add Comment");
	    setText(0, 9, "Comments");
	    setText(0, 10, "Buy Content");
	    getRowFormatter().addStyleName(0, "friendListHeader");
	}
	
	public final void setLoggedAgent(AgentDto dto) {
		  this.loggedAgent = dto;
	}
	 
	public void clearPublicationsList() {
		int rowCount = getRowCount();
		for (int i = rowCount - 1; i > 0; i--)
			removeRow(i);
	}
	
	
	 public void init(List<PublicationDto> pubsList, final AgentDto requestedAgent){
		 
		 for(final PublicationDto publication : pubsList ){
			 final int row = this.getRowCount();
			 getRowFormatter().addStyleName(row, "friendListCell");
			 if(publication.getClass().getName().equals("pt.ist.sonet.service.dto.NoteDto")){
				setText(row, 0, ((NoteDto)publication).getCaption());
			 	setText(row, 4, ((NoteDto)publication).getText());
			 }else{
				 setText(row, 0, ((UrlDto)publication).getUrl());
				 setText(row, 4, "url: " + ((UrlDto)publication).getUrl());
				 setText(row, 5, String.valueOf(((UrlDto)publication).getPrice()));
				 final Button buyContentButton = new Button("Buy Content");
				 if(!(requestedAgent.getUsername().equals(loggedAgent.getUsername())))
					 setWidget(row, 10, buyContentButton);
				 
				 buyContentButton.addClickHandler(new ClickHandler(){
					 public void onClick(ClickEvent event){
						 if (((UrlDto) publication).getPrice() > 0){						
								rpcService.transferMoney(rootPage.getActiveAgent(), requestedAgent, ((UrlDto) publication).getPrice(), "Content " + ((UrlDto) publication).getUrl() + " bought for " + ((UrlDto) publication).getPrice(), new AsyncCallback<Void>(){
									public void onSuccess(Void response){
										rpcService.buyContent(rootPage.getActiveAgent(), requestedAgent, ((UrlDto) publication).getUrl(), "Purchase of " + ((UrlDto) publication).getUrl(), new AsyncCallback<Void>(){
											public void onSuccess(Void response){
												Window.alert("Content purchased successfully");
											}
											public void onFailure(Throwable caught){
												GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
												GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
												Window.alert(caught.toString());
											}
										});
									}
									public void onFailure(Throwable caught){
										GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
										GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
										Window.alert(caught.toString());
									}
								});
						} else {
							rpcService.buyContent(rootPage.getActiveAgent(), requestedAgent, ((UrlDto) publication).getUrl(), "Purchase of " + ((UrlDto) publication).getUrl(), new AsyncCallback<Void>(){
								public void onSuccess(Void response){
									Window.alert("Content purchased successfully");
								}
								public void onFailure(Throwable caught){
									GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
									GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
									Window.alert(caught.toString());
								}
							});
						}
					 }
				 });
			 }
			 
			 setText(row, 1, String.valueOf(publication.getScorePlus()));
			 setText(row, 2, String.valueOf(publication.getScoreMinus()));
			 
			 rpcService.listComment(publication,new AsyncCallback<List<CommentDto>>() {

					public void onSuccess(List<CommentDto> response) {
						setText(row, 3, String.valueOf(response.size()));
					}
							
					public void onFailure(Throwable caught) {
						SonetException ex = (SonetException) caught;
						Window.alert(ex.toString());
					}
				});
			 
			 /* How to get number of comments ? */
			 setText(row, 3, String.valueOf(publication.getScorePlus()));
			 
			 final Button viewCommentsButton = new Button("View Comments");
			 setWidget(row,9 , viewCommentsButton);
			 
			 
			 
			 
			 viewCommentsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
		                final int rowIndex = getCellForEvent(event).getRowIndex();
						if(viewCommentsButton.getText().equals("View Comments")){
							viewCommentsButton.setText("Hide Comments");
							rpcService.listComment(publication,new AsyncCallback<List<CommentDto>>() {

								public void onSuccess(List<CommentDto> response) {
									if(response.size()>0){
										int actualRow=2;
										insertRow(rowIndex+1);
										setText(rowIndex+1,0, "ID");
										setText(rowIndex+1,1, "Agent");
										setText(rowIndex+1,2, "Comment");
										getRowFormatter().addStyleName(rowIndex+1, "publicationsTableHeader");


										for(CommentDto comment : response){
											insertRow(rowIndex+actualRow);
											setText(rowIndex+actualRow, 0, String.valueOf(comment.getId()));
											setText(rowIndex+actualRow, 1, comment.getAgentName());
											setText(rowIndex+actualRow, 2, comment.getText());
											
											actualRow++;
										}

									}
								}
								public void onFailure(Throwable caught) {
									SonetException ex = (SonetException) caught;
									Window.alert(ex.toString());
								}
							});
						}
						else{
							viewCommentsButton.setText("View Comments");
							rpcService.listComment(publication,new AsyncCallback<List<CommentDto>>() {

								public void onSuccess(List<CommentDto> response) {
									if(response.size()>0){
										removeRow(rowIndex+1);
										getRowFormatter().removeStyleName(rowIndex+1, "publicationsTableHeader");
										for(int i=0;i<response.size();i++){
											removeRow(rowIndex+1);
										}

									}
								}
								public void onFailure(Throwable caught) {
									SonetException ex = (SonetException) caught;
									Window.alert(ex.toString());
								}
							});
						}
						
					}
			 });
			 
			 
			 Button addVoteMinusButton = new Button("Vote Minus");
			 setWidget(row,7 , addVoteMinusButton);
			 addVoteMinusButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						rpcService.votePub(loggedAgent, publication, -1, new AsyncCallback<Void>(){
							public void onSuccess(Void response) {
								Window.alert("Vote Added Successfully");
								int votes = Integer.parseInt(getText(row,2));
								GWT.log(String.valueOf(votes));
								votes--;
								setText(row, 2,String.valueOf(votes));
							}
							public void onFailure(Throwable caught) {
								SonetException ex = (SonetException) caught;
								Window.alert(ex.toString());
							}
						});
						
					}
			 });
			 
			 Button addVotePlusButton = new Button("Vote Plus");
			 setWidget(row,6 , addVotePlusButton);
			 addVotePlusButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						rpcService.votePub(loggedAgent, publication, 1, new AsyncCallback<Void>(){
							public void onSuccess(Void response) {
								Window.alert("Vote Added Successfully");
								int votes = Integer.parseInt(getText(row,1));
								votes++;
								setText(row, 1,String.valueOf(votes));
							}
							public void onFailure(Throwable caught) {
								SonetException ex = (SonetException) caught;
								Window.alert(ex.toString());
							}
						});
						
					}
			 });
			 
			 
			 
			 final TextBox commentTextBox = new TextBox();
			 setWidget(row,8,commentTextBox);
			 commentTextBox.addKeyUpHandler(new KeyUpHandler() {
					public void onKeyUp(KeyUpEvent event) {
						if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							if(viewCommentsButton.getText().equals("View Comments")){
							if(!(commentTextBox.getText().equals(""))){
								
							
							rpcService.addComment(loggedAgent, publication,commentTextBox.getText() ,new AsyncCallback<Void>() {
								public void onSuccess(Void response) {
									Window.alert("Comment Added Successfully");
									commentTextBox.setText("");
									int newCommentCount = Integer.parseInt(getText(row,2));
									newCommentCount++;
									setText(row,3,String.valueOf(newCommentCount));
									
								}
								public void onFailure(Throwable caught) {
									SonetException ex = (SonetException) caught;
									Window.alert(ex.toString());
								}
							});
							}
							else{
								Window.alert("Cannot Add An Empty Comment");
							}
							}
							else{
								viewCommentsButton.setText("View Comments");
								rpcService.listComment(publication,new AsyncCallback<List<CommentDto>>() {

									public void onSuccess(List<CommentDto> response) {
										if(response.size()>0){
											removeRow(row+1);
											getRowFormatter().removeStyleName(row+1, "publicationsTableHeader");
											for(int i=0;i<response.size();i++){
												removeRow(row+1);
											}

										}
									}
									public void onFailure(Throwable caught) {
										SonetException ex = (SonetException) caught;
										Window.alert(ex.toString());
									}
								});
								GWT.log(commentTextBox.getText());
								rpcService.addComment(loggedAgent, publication,commentTextBox.getText() ,new AsyncCallback<Void>() {
									public void onSuccess(Void response) {
										Window.alert("Comment Added Successfully");
										commentTextBox.setText("");
										
									}
									public void onFailure(Throwable caught) {
										SonetException ex = (SonetException) caught;
										Window.alert(ex.toString());
									}
								});	
							}
						}
					}
			 });
							
							
			 
		 }
	}
	  
}