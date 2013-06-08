package pt.ist.sonet.presentation.client;

import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.AgentLoginDto;
import pt.ist.sonet.exception.SonetException;

import pt.ist.sonet.presentation.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class LoginPage extends DecoratorPanel {
	final Button loginButton = new Button("Login");
	final TextBox nameField = new TextBox();
	final PasswordTextBox passField = new PasswordTextBox();
	
		
	public LoginPage(final SonetGWT rootPage, final SonetServletAsync rpcService) {		
		FlexTable flexTable = new FlexTable();
		this.setStyleName("inputBox");
		Label lblUsername = new Label("Username:");
		flexTable.setWidget(0, 0, lblUsername);
		lblUsername.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(1, 0, nameField);
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		nameField.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					doLogin(rootPage, rpcService);
				}
		}});
		
		Label lblPass = new Label("Password:");
		flexTable.setWidget(2, 0, lblPass);
		lblPass.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(3, 0, passField);
		
		passField.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					doLogin(rootPage, rpcService);
				}
		}});
		
		flexTable.setWidget(4, 0, loginButton);
		
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doLogin(rootPage, rpcService);
			}
		});
		
		
		
		setWidget(flexTable);
	}

	private final void doLogin(final SonetGWT rootPage, final SonetServletAsync rpcService) {
		// First, we validate the input
		String textToServer = nameField.getText();
		if(!FieldVerifier.isValidName(textToServer)) {
			rootPage.showErrorMessage("Please enter at least four characters!");
			return;
		}
		else if(this.passField.getText().equals("")) {
			rootPage.showErrorMessage("Please fill the password of the agent!");
			return;
		}
		
		final AgentLoginDto dto = new AgentLoginDto(this.nameField.getText(), this.passField.getText());
		
		rpcService.login(dto, new AsyncCallback<AgentDto>() {
			public void onSuccess(AgentDto response) {
				
				rootPage.showProfilePage(response);
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.login");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				SonetException ex = (SonetException) caught;
				rootPage.showErrorMessage(ex.toString());
			}
		});
	}
}
