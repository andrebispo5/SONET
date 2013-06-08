package pt.ist.sonet.presentation.client;

import pt.ist.sonet.domain.ExternalServiceConfig;
import pt.ist.sonet.factory.ExternalServiceFactory;
import pt.ist.sonet.factory.LocalExternalServiceFactory;
import pt.ist.sonet.factory.RemoteExternalServiceFactory;
import pt.ist.sonet.presentation.client.LoginPage;
import pt.ist.sonet.presentation.client.ProfilePage;
import pt.ist.sonet.presentation.client.SonetServlet;
import pt.ist.sonet.presentation.client.SonetServletAsync;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.AgentLoginDto;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SonetGWT implements EntryPoint {
	
	private static final String         remoteServerType    = "ES+SD";
	private static final String         localServerType     = "ES-only";
	
	private String serverType;
	
	private final SonetServletAsync 	rpcService			= GWT.create(SonetServlet.class);
	
	private final Label 				serverTypeLabel 	= new Label("Sonet");
	private final Label 				errorMessage 		= new Label("");

	private LoginPage loginPage;
	private ProfilePage profilePage;
	private AgentDto activeAgent;
	
	
	
	public void onModuleLoad() {
		GWT.log("presentation.client.Sonet::onModuleLoad() - begin");
	    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

	        @Override
	        public void onUncaughtException(Throwable e) {
	          Window.alert("uncaught: " + e.getMessage());
	          GWT.log(e.toString());
	          e.printStackTrace();
	        }
	      });

	      new Timer() {

	        @Override
	        public void run() {
	        	serverType = "ES+SD";
	        	start();
	        }
	      }.schedule(1);
       
	}
	
	public void setType(){
		if(serverType.contains("ES+SD")){
			serverType = "ES-only";
		}else{
			serverType = "ES+SD";
		}
		start();
	}
	
	public String getType(){
		return serverType;
	}
	
	void start() {
		
		// create label with mode type
		serverTypeLabel.setStyleName("headerStyle");

		if (serverType.contains("ES+SD")) {
			GWT.log("presentation.client.Sonet::onModuleLoad()" + 
		          " - running Sonet on REMOTE mode");
			
			serverTypeLabel.setText("Sonet - Remote mode");
			serverType = remoteServerType;
			
		} else { // default: local - even if it is misspelled
			GWT.log("presentation.client.Sonet::onModuleLoad()" + 
		          " - running Sonet on LOCAL mode");
			serverTypeLabel.setText("Sonet - Local mode");
			serverType = localServerType;
		}
		
		
		RootPanel typeRootPanel = RootPanel.get("header");
		typeRootPanel.add(serverTypeLabel);
		serverTypeLabel.setWidth("100%");
		// set the mode of the GWT server, local or remote.
		this.rpcService.initServer(serverType, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {}
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.initBridge");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				showErrorMessage("Not able to init aplication server bridge: " + caught.getMessage());
			}
		});
		loginPage = new LoginPage(this, rpcService);
		profilePage = new ProfilePage(this, rpcService);
		
		if(serverType.contains("ES-only")){
			this.rpcService.StartLargaCaixaService(new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					GWT.log("LargaCaixaSetup [OK]");
				}
				@Override
				public void onFailure(Throwable caught) {
					GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.StartLargaCaixaService");
					GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
					showErrorMessage("Not able to init aplication server bridge: " + caught.getMessage());
				}
			});
		}

		GWT.log("presentation.client.Sonet::onModuleLoad() - done!");
		
		RootPanel updateRootPanel = RootPanel.get("mainPanel");
		showLoginPage();
		updateRootPanel.add(errorMessage);
        errorMessage.setStyleName("labelError");
        errorMessage.setWidth("100%");
        updateRootPanel.setStyleName("style-login");
	}
	
	void showProfilePage(AgentDto loggedAgent){
		RootPanel.get("mainPanel").clear();
		RootPanel.get("menu").add(profilePage);
		activeAgent = loggedAgent;
		profilePage.profilePage(activeAgent);
	}
	
	
	void showLoginPage() {
		profilePage.hidePage();
		
		RootPanel.get("mainPanel").add(loginPage);
        errorMessage.setText("");
	}
	
	public AgentDto getActiveAgent(){
		return activeAgent;
	}
	// show the contact page of the logged person
//	void showContactPage(AgentLoginDto loggedPerson) {
//		RootPanel.get("nameFieldContainer").clear();
//		agentPage.showPage(loggedPerson);
//		
//		errorMessage.setText("");
//	}
	
	public void showErrorMessage(String message) {
		errorMessage.setText(message);
	}
}