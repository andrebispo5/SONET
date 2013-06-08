
package pt.ist.sonet.presentation.client.view;

import java.util.List;

import pt.ist.sonet.service.dto.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import pt.ist.sonet.presentation.client.*;

public class ShowProfilePanel extends FlexTable{
	

	
	
	
	public ShowProfilePanel(AgentDto loggedAgent){
	    GWT.log("presentation.client.view.CommentListPanel::constructor()");

	    
	    // add header row:
	    setText(0, 0, "Username");
	    setText(0, 1, "Name");
	    setText(0, 2, "Email");
	    setText(0, 3, "City");
	    setText(0, 4, "Country");
	    setText(1, 0, loggedAgent.getUsername());
	    setText(1, 1, loggedAgent.getName());
	    setText(1, 2, loggedAgent.getEmail());
	    setText(1, 3, loggedAgent.getCity());
	    setText(1, 4, loggedAgent.getCountry());
	    getRowFormatter().addStyleName(0, "friendListHeader");
	    getRowFormatter().addStyleName(1, "friendListCell");
	    
	    
	 
	
	}

}
