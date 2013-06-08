package pt.ist.sonet.presentation.client.view;


import pt.ist.sonet.service.dto.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.presentation.client.*;

public class ObtainTargetAgentPanel extends DecoratorPanel {

	/* One column only panel*/
	private final VerticalPanel verticalPanel;
	
	/*Box with note caption*/
	private final Label         targetAgentLabel;
	private final TextBox       targetAgentTextBox;
	private Button		  confirmWantedButton;
	private final SonetServletAsync rpcService;
	private SonetGWT rootPage;
	private AgentDto targetAgent;
	
	public ObtainTargetAgentPanel(final SonetServletAsync rpcService, final SonetGWT rootPage) {
	  
		this.rootPage = rootPage;
	    this.rpcService = rpcService;
	    this.setStyleName("inputBox");
		this.verticalPanel = new VerticalPanel();

		this.confirmWantedButton = new Button("Confirmar");
		this.targetAgentLabel = new Label("Wanted Agent:");
		this.targetAgentTextBox = new TextBox();
		this.verticalPanel.add(this.targetAgentLabel);
		this.verticalPanel.add(this.targetAgentTextBox);
		this.verticalPanel.add(this.confirmWantedButton);

		this.add(this.verticalPanel);
		
		GWT.log("presentation.client.view.ObtainTargetAgentPanel::constructor()" + " - done!");
	}

	public void setTarget(AgentDto dto) {
		this.targetAgent = dto;
	}
	
	// returns the caption of the note filled by the agent
	public String getWantedAgent() {
		return this.targetAgentTextBox.getValue();
	}
	
	public Button getWantedButton() {
		return this.confirmWantedButton;
	}

}