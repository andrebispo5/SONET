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

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.presentation.client.*;
import pt.ist.sonet.presentation.shared.FieldVerifier;

public class RequestFriendPanel extends DecoratorPanel{
	
	private final VerticalPanel verticalPanel;

	private final Label         textLabel;
	private final TextBox       textTextBox;

	private final Button        requestFriendButton;

	public RequestFriendPanel(){
		this.verticalPanel = new VerticalPanel();
		this.addStyleName("inputBox");
		this.textLabel = new Label("Agent:");
		this.textTextBox = new TextBox();

		this.requestFriendButton = new Button("Request");

		this.verticalPanel.add(this.textLabel);
		this.verticalPanel.add(this.textTextBox);
		this.verticalPanel.add(this.requestFriendButton);

		this.add(this.verticalPanel);
	}
	
	
	public String getText() {
		return this.textTextBox.getValue();
	}
	
	public void setClickHandler(ClickHandler handler) {
		this.requestFriendButton.addClickHandler(handler);
	}
	
	public void reset() {
		textTextBox.setText("");
	}
	
	public Button getRequestFriendButton() {
		return requestFriendButton;
	}

}
