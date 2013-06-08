package pt.ist.sonet.presentation.client;

import java.util.List;

import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.PublicationDto;
import pt.ist.sonet.service.dto.AgentLoginDto;
import pt.ist.sonet.service.dto.NoteDto;
import pt.ist.sonet.service.dto.OrganizationDto;
import pt.ist.sonet.service.dto.UrlDto;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.exception.SonetException;

import pt.ist.sonet.presentation.client.view.AddNotePanel;
import pt.ist.sonet.presentation.client.view.BuyContentPanel;
import pt.ist.sonet.presentation.client.view.DonationPanel;
import pt.ist.sonet.presentation.client.view.ListFriendsPanel;
import pt.ist.sonet.presentation.client.view.ListPendingsPanel;
import pt.ist.sonet.presentation.client.view.ShowProfilePanel;
import pt.ist.sonet.presentation.client.view.PublicationListPanel;
import pt.ist.sonet.presentation.client.view.ObtainTargetAgentPanel;
import pt.ist.sonet.presentation.client.view.RequestFriendPanel;

import pt.ist.sonet.presentation.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
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
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilePage extends DecoratorPanel {

	private Button        myProfileButton;
	private Button        showFriendsButton;
	private Button        addTextualButton;
	private Button        showPubsButton;
	private Button        donateButton;
	private Button		  requestButton;
	private Button		  pendingsButton;
	private Button		  viewProfileButton;
	private Button		  changeServerType;

	private SonetGWT rootPage;
	private AgentDto activeAgent;
	private SonetServletAsync rpcService;
	
	private final VerticalPanel verticalPanel;
	private final AddNotePanel     addNotePanel  = new AddNotePanel ();
	private final ObtainTargetAgentPanel obtainAgentPanel = new ObtainTargetAgentPanel(rpcService,rootPage);
	//FOR: SEE TARGET AGENT PROFILE PAGE
	private final ObtainTargetAgentPanel getAgentProfilePanel = new ObtainTargetAgentPanel(rpcService,rootPage);
	private final RequestFriendPanel requestFriendPanel = new RequestFriendPanel();


	public ProfilePage(final SonetGWT rootPage, final SonetServletAsync rpcService) {		
		GWT.log("presentation.client.view.AddCommentPanel::constructor()");
		this.rpcService = rpcService;
		this.rootPage = rootPage;
		this.verticalPanel = new VerticalPanel();



		this.myProfileButton 	= 	new Button("O Meu Perfil");
		this.showFriendsButton  = 	new Button("Listar Amigos");
		this.addTextualButton 	= 	new Button("Adicionar Nota Textual");
		this.showPubsButton 	= 	new Button("Ver Publicacoes de Agente");
		this.donateButton 		= 	new Button("Fazer Donativo");
		this.requestButton		= 	new Button("Adicionar Agente");
		this.pendingsButton 	=	new Button("Ver Pedidos");
		this.viewProfileButton 	= 	new Button("Ver Perfil de Agente");
		this.changeServerType = new Button("Mudar Local/Remoto");

		this.verticalPanel.add(this.myProfileButton);
		this.verticalPanel.add(this.showFriendsButton);
		this.verticalPanel.add(this.addTextualButton);
		this.verticalPanel.add(this.showPubsButton);
		this.verticalPanel.add(this.donateButton);
		this.verticalPanel.add(this.requestButton);
		this.verticalPanel.add(this.pendingsButton);
		this.verticalPanel.add(this.viewProfileButton);
		this.verticalPanel.add(this.changeServerType);
		
		this.myProfileButton.setStyleName("buttonNav");
		this.showFriendsButton.setStyleName("buttonNav");
		this.addTextualButton.setStyleName("buttonNav");
		this.showPubsButton.setStyleName("buttonNav");
		this.donateButton.setStyleName("buttonNav");
		this.requestButton.setStyleName("buttonNav");
		this.pendingsButton.setStyleName("buttonNav");
		this.viewProfileButton.setStyleName("buttonNav");
		this.changeServerType.setStyleName("buttonNav");
		
		setWidget(verticalPanel);


		
		this.addNotePanel.getAddNoteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String text = addNotePanel.getText();
				String caption = addNotePanel.getCaption();
				doNote(text, caption,rootPage.getActiveAgent());
			}
		});
		
		this.requestFriendPanel.setClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String requestAgent = requestFriendPanel.getText();
				requestFriend(requestAgent, rootPage, rpcService);
			}
		});
		
		//FOR: SEE TARGET AGENT PROFILE PAGE
		this.getAgentProfilePanel.getWantedButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String wantedAgent = getAgentProfilePanel.getWantedAgent();
				doAgentProfile(wantedAgent);
			}
		});
		
		this.obtainAgentPanel.getWantedButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String wantedAgent = obtainAgentPanel.getWantedAgent();
				doPublicationsList(wantedAgent, rootPage, rpcService);
			}
		});
		
		myProfileButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				profilePage(rootPage.getActiveAgent());
			}
		});
		
		changeServerType.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				changeType();
			}
		});
		
		showPubsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				listPublicationsFirstPage();
				
			}
		});
		
		requestButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				requestFriendPage();
			}
		});
		
		showFriendsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doFriendList(rootPage, rpcService);
			}
		});
		
		pendingsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doRequestsList(rootPage, rpcService);
			}
		});
		
		addTextualButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addNotePage(rootPage.getActiveAgent());
			}
		});
		
		donateButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				doOrganizationList(rootPage, rpcService);
			}
		});
				
		viewProfileButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				viewTargetAgentProfilePage();
			}
		});
				
	}
	
	void changeType(){
		rootPage.setType();
	}
	
	void hidePage() {
		RootPanel.get("menu").clear();
		RootPanel.get("mainPanel").clear();
	}
	
	public void requestFriendPage() {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(requestFriendPanel);
	}
	
	public void profilePage(AgentDto loggedAgent) {
		RootPanel.get("mainPanel").clear();
		ShowProfilePanel profilePanel = new ShowProfilePanel(loggedAgent);
		RootPanel.get("mainPanel").add(profilePanel);
	}
	
	public void friendsPage(List<AgentDto> friendList) {
		RootPanel.get("mainPanel").clear();
		ListFriendsPanel friendPanel = new ListFriendsPanel(rpcService,rootPage);
		friendPanel.init(friendList, rootPage, rpcService);
		RootPanel.get("mainPanel").add(friendPanel);
	}
	
	public void RequestsPage(List<AgentDto> pendingList) {
		RootPanel.get("mainPanel").clear();
		ListPendingsPanel pendingsPanel = new ListPendingsPanel(rpcService,rootPage);
		pendingsPanel.init(pendingList, rootPage, rpcService);
		RootPanel.get("mainPanel").add(pendingsPanel);
	}
	
	public void donatePage(List<AgentDto> organizations){
		RootPanel.get("mainPanel").clear();
		DonationPanel donation = new DonationPanel(organizations);
		donation.init(organizations, rootPage, rpcService);
		RootPanel.get("mainPanel").add(donation);
	}
	
	public void addNotePage(AgentDto loggedAgent) {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(addNotePanel);

	}
	
	public void listPublicationsPage(List<PublicationDto> pubsList, AgentDto requestedAgent) {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(obtainAgentPanel);
		PublicationListPanel publicationListPanel = new PublicationListPanel(rpcService, rootPage);
		publicationListPanel.init(pubsList, requestedAgent);
		RootPanel.get("mainPanel").add(publicationListPanel);
	}
	
	public void listContentPage(List<UrlDto> contentList, AgentDto requestedAgent) {
		RootPanel.get("mainPanel").clear();
		BuyContentPanel buyContentPanel = new BuyContentPanel(contentList);
		buyContentPanel.init(contentList, requestedAgent, rootPage, rpcService);
		RootPanel.get("mainPanel").add(buyContentPanel);
	}
	
	public void listPublicationsFirstPage() {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(obtainAgentPanel);
		PublicationListPanel publicationListPanel = new PublicationListPanel(rpcService, rootPage);
		RootPanel.get("mainPanel").add(publicationListPanel);
	}
	
	public void viewTargetAgentProfilePage() {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(getAgentProfilePanel);
	}
	
	public void doTargetAgentProfilePage(AgentDto agent) {
		ShowProfilePanel profilePanel = new ShowProfilePanel(agent);
		RootPanel.get("mainPanel").remove(getAgentProfilePanel);
		RootPanel.get("mainPanel").add(profilePanel);
		
	}
	
	public final void doFriendList(final SonetGWT rootPage, final SonetServletAsync rpcService) {
		
		AgentDto dto = rootPage.getActiveAgent();
		
		rpcService.listFriends(dto, new AsyncCallback<List<AgentDto>>() {
			public void onSuccess(List<AgentDto> response) {
				
				friendsPage(response);
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doFriendList");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				AgentNotFoundException ex = (AgentNotFoundException) caught;
				Window.alert("The agent '" + ex.getNameNotFound() + "' does not exist.");
			}
		});
	}
	
public final void doRequestsList(final SonetGWT rootPage, final SonetServletAsync rpcService) {
		
		AgentDto dto = rootPage.getActiveAgent();
		
		rpcService.listPendings(dto, new AsyncCallback<List<AgentDto>>() {
			public void onSuccess(List<AgentDto> response) {
				
				RequestsPage(response);
			}
			public void onFailure(Throwable caught) {
				AgentNotFoundException ex = (AgentNotFoundException) caught;
				Window.alert("The agent '" + ex.getNameNotFound() + "' does not exist.");
			}
		});
	}
	
	
	private final void doPublicationsList(String agent, final SonetGWT rootPage, final SonetServletAsync rpcService) {
		
		final AgentDto requestedAgent = new AgentDto(agent, "", "", "", "", "");
		AgentDto activeAgent = rootPage.getActiveAgent();
		
		rpcService.listPublications(activeAgent, requestedAgent, new AsyncCallback<List<PublicationDto>>() {
			public void onSuccess(List<PublicationDto> response) {
				
				listPublicationsPage(response, requestedAgent);
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doPublicationsList");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				SonetException ex = (SonetException) caught;
				Window.alert(ex.toString());
			}
		});
	}
	
	
	
	
	public final void requestFriend(String agent, final SonetGWT rootPage, final SonetServletAsync rpcService) {
		
		AgentDto origin = rootPage.getActiveAgent();
		final AgentDto requestedAgent = new AgentDto(agent, "", "", "", "", "");
		
		rpcService.requestFriend(origin, requestedAgent, new AsyncCallback<Void>() {
			public void onSuccess(Void response) {
				Window.alert("Pedido de amizade enviado para " + requestedAgent.getUsername());
				requestFriendPanel.reset();
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doFriendList");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				SonetException ex = (SonetException) caught;
				Window.alert(ex.toString());
				requestFriendPanel.reset();
			}
		});
	}




//	private final void doContentList(String agent, final SonetGWT rootPage, final SonetServletAsync rpcService){
//		
//		final AgentDto requestedAgent = new AgentDto(agent, "", "", "", "", "");
//		
//		rpcService.listContent(requestedAgent, new AsyncCallback<List<UrlDto>>() {
//			public void onSuccess(List<UrlDto> response) {
//				listContentPage(response, requestedAgent);
//				for(UrlDto u : response){
//					GWT.log(u.getUrl());
//				}
//			}
//			public void onFailure(Throwable caught) {
//				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doContentList");
//				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
//				AgentNotFoundException ex = (AgentNotFoundException) caught;
//				Window.alert("The agent '" + ex.getNameNotFound() + "' does not exist.");
//			}
//		});
//	}
	

	
	
	public final void doNote(String text, String caption,AgentDto agentLogged) {
		// First, we validate the input
				if(text.equals("")) {
					addNotePanel.setMessageLabel("Please fill the text of the note!");
					return;
				}
				else if(caption.equals("")) {
					addNotePanel.setMessageLabel("Please fill the caption of the note!");
					return;
				}
				
				final NoteDto dto = new NoteDto(0,text, caption);
//				final AgentDto dto2 = new AgentDto(activeAgent.getUsername(),activeAgent.getName(),activeAgent.getEmail(),activeAgent.getCity(),activeAgent.getCountry(),activeAgent.getPassword());
				
				this.rpcService.addNote(agentLogged, dto, new AsyncCallback<Void>() {
					public void onSuccess(Void response) {
						addNotePanel.reset();
						addNotePanel.setMessageLabel("Note Added With Success!");
					}
					public void onFailure(Throwable caught) {
						GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doNote");
						GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
						SonetException ex = (SonetException) caught;
						addNotePanel.setMessageLabel(ex.toString());
					}
				});
		
	}
	
	private final void doOrganizationList(final SonetGWT rootPage, final SonetServletAsync rpcService){
		
		AgentDto dto = rootPage.getActiveAgent();
		
		this.rpcService.listOrganizations(dto, new AsyncCallback<List<AgentDto>>(){
			public void onSuccess(List<AgentDto> response){
				donatePage(response);
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doOrganizationList");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				SonetException ex = (SonetException) caught;
				Window.alert(ex.toString());
			}
		});
		
	}
	
	private final void doAgentProfile(String username){
		
		AgentDto loggedAgent = rootPage.getActiveAgent();
		AgentDto dto = new AgentDto(username, "", "", "", "", "");
		this.rpcService.viewAgentProfile(loggedAgent, dto, new AsyncCallback<AgentDto>(){
			public void onSuccess(AgentDto response){
				doTargetAgentProfilePage(response);
			}
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.doAgentProfile");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				SonetException ex = (SonetException) caught;
				Window.alert(ex.toString());
			}
		});
	}


}
