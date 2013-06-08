
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

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.presentation.client.*;

public class ListPendingsPanel extends FlexTable{
	
	
	

	public ListPendingsPanel(SonetServletAsync rpcService, SonetGWT rootPage){
	    GWT.log("presentation.client.view.CommentListPanel::constructor()");
	    setText(0, 0, "Username");
	    setText(0, 1, "Name");
	    setText(0, 2, "Email");
	    setText(0, 3, "City");
	    setText(0, 4, "Country");
	    setText(0, 5, "Aceitar Pedido");
	    setText(0, 6, "Rejeitar Pedido");
	    getRowFormatter().addStyleName(0, "friendListHeader");
	}
	
	
	
	 public void init(final List<AgentDto> requestList,final SonetGWT rootPage, final SonetServletAsync rpcService){
		 if(requestList.size()==0){
			 removeRow(0);
			 setText(0, 2, "No Pending Requests");
		 }
		 else{
			 
			 for(final AgentDto a : requestList ){
				 final int row = this.getRowCount();
				 final Button acceptButton = new Button("Aceitar Pedido");
				 final Button rejectButton = new Button("Rejeitar Pedido");
				 setText(row, 0, a.getUsername());
				 setText(row, 1, a.getName());
				 setText(row, 2, a.getEmail());
				 setText(row, 3, a.getCity());
				 setText(row, 4, a.getCountry());
				 setWidget(row,5,acceptButton);
				 setWidget(row,6,rejectButton); 
				 getRowFormatter().addStyleName(row, "friendListCell"); 
				 
				 acceptButton.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event){
							final AgentDto src = rootPage.getActiveAgent();
							final AgentDto dest = new AgentDto(getText(row,0),"","","","","");
							rpcService.acceptConnection(dest,src, new AsyncCallback<Void>(){
								public void onSuccess(Void response) {
									Window.alert("You and " + dest.getUsername() + " are now friends!");
									removeRow(row);
									requestList.remove(a);
								}
								public void onFailure(Throwable caught) {
									AgentNotFoundException ex = (AgentNotFoundException) caught;
									Window.alert(ex.toString());
								}
							});
							
						}
						}
					);
				 
				 rejectButton.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event){
							final AgentDto src = rootPage.getActiveAgent();
							final AgentDto dest = new AgentDto(getText(row,0),"","","","","");
							rpcService.rejectConnection(dest,src, new AsyncCallback<Void>(){
								public void onSuccess(Void response) {
									Window.alert("Request Removed");
									removeRow(row);
									requestList.remove(a);
								}
								public void onFailure(Throwable caught) {
									AgentNotFoundException ex = (AgentNotFoundException) caught;
									Window.alert(ex.toString());
								}
							});
							
						}
						}
					);
			 
			 }
		 }
	 }
}


