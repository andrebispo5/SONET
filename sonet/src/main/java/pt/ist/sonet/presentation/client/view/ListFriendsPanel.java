
package pt.ist.sonet.presentation.client.view;

import java.util.List;

import pt.ist.sonet.service.dto.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.ist.sonet.presentation.client.*;

public class ListFriendsPanel extends FlexTable{
	
	
	

	public ListFriendsPanel(SonetServletAsync rpcService, SonetGWT rootPage){
	    GWT.log("presentation.client.view.CommentListPanel::constructor()");
	    setText(0, 0, "Username");
	    setText(0, 1, "Name");
	    setText(0, 2, "Email");
	    setText(0, 3, "City");
	    setText(0, 4, "Country");
	    setText(0,5, "Amount");
	    setText(0,6,"Donate");
	    
	    getRowFormatter().addStyleName(0, "friendListHeader");
	}
	
	
	
	 public void init(List<AgentDto> friendList,final SonetGWT rootPage, final SonetServletAsync rpcService){
		 for(AgentDto a : friendList ){
			 final Button donateButton = new Button("Donate");
			 final TextBox amountTextBox = new TextBox();
			 final int row = this.getRowCount();
			 setText(row, 0, a.getUsername());
			 setText(row, 1, a.getName());
			 setText(row, 2, a.getEmail());
			 setText(row, 3, a.getCity());
			 setText(row, 4, a.getCountry());
			 if(a.getType().equals("Organization")){
				 setWidget(row,5,amountTextBox);
				 setWidget(row,6,donateButton); 
			 }
			 getRowFormatter().addStyleName(row, "friendListCell");
			 
			 donateButton.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event){
						try{
						final int amount=Integer.parseInt(amountTextBox.getText());
						if (amount>0){
						final AgentDto agent = new AgentDto(getText(row,0),"","","","","");
						rpcService.transferMoney(rootPage.getActiveAgent(), agent, amount, "Donation: " + amount, new AsyncCallback<Void>(){
							public void onSuccess(Void response) {
								Window.alert("Donated:" + amount + " to " + getText(row,0) + " successfully!");
								amountTextBox.setText("");
							}
							public void onFailure(Throwable caught) {
								GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.Donate");
								GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
								Exception ex = (Exception) caught;
								Window.alert(ex.toString());
								amountTextBox.setText("");
							}
						});
						
					}else
						Window.alert("Insert a positive amount of euros");
						amountTextBox.setText("");
						}
						catch(NumberFormatException e){
							Window.alert("Insert a valid amount of euros");
							amountTextBox.setText("");
						}
					}
				});
			 
		 }
	}

}
