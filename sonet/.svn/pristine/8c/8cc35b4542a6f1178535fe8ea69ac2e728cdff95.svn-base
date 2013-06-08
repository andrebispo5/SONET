package pt.ist.sonet.presentation.server;

import java.util.List;

import pt.ist.pagamigo.InvalidTransferException;
import pt.ist.sonet.service.ListPendingsService;
import pt.ist.sonet.service.RemoveCommentService;
import pt.ist.sonet.DataBaseBootstrap;
import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.presentation.client.SonetServlet;
import pt.ist.sonet.presentation.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class SonetServletImpl extends RemoteServiceServlet implements SonetServlet {
	
	private static final long              serialVersionUID = 1L;
	
	private String agentName;
	
	public String loginServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		this.agentName = input;
		
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	@Override
	public void addNote(AgentDto agentDto, NoteDto pubDto) {
		AddNoteService noteService = new AddNoteService(agentDto, pubDto.getText(), pubDto.getCaption());
		noteService.execute();
	}
	
	@Override
	public void StartLargaCaixaService()  throws AgentNotFoundException{
		StartLargaCaixaService LCService = new StartLargaCaixaService();
		LCService.execute();
	}
	
	@Override
	public List<PublicationDto> listPublications(AgentDto activeAgent, AgentDto requestedAgent)  {
		ObtainPublicationsService obtainService = new ObtainPublicationsService(activeAgent, requestedAgent);
		
		obtainService.execute();
		return obtainService.getPublications();
	}
	
	@Override 	
	public List<UrlDto> listContent(AgentDto requestedAgent){
		GetContentService content = new GetContentService(requestedAgent);
		content.execute();
		return content.getContent();
	}
	
	@Override
	public void transferMoney(AgentDto sender, AgentDto receiver, int amount, String description) throws NoMoneyException, AgentNotFoundException{
		TransferPaymentService transfer = new TransferPaymentService(sender, receiver, amount, description);
		transfer.execute();
	}
	@Override
	public void buyContent(AgentDto buyer, AgentDto seller, String contentID, String paymentProof){
		BuyContentService purchase = new BuyContentService(buyer, seller, contentID, paymentProof);
		purchase.execute();
	}
	
	@Override
	public List<CommentDto> publicationDetails(PublicationDto dto) {
		ListCommentsService service = new ListCommentsService(dto);
		service.execute();
		return service.getComment();
	}

	
	
	@Override
	public void initServer(String serverType) {
		DataBaseBootstrap.init(serverType);
	}

	@Override
	public AgentDto login(AgentLoginDto agent) throws AgentNotFoundException {
		LoginService loginService  = new LoginService(agent);
		loginService.execute();
		return loginService.getAgentDto();
		
	}
	
	@Override
	public AgentDto viewAgentProfile(AgentDto loggedAgent, AgentDto targetAgent) throws AgentNotFoundException, PermissionDeniedException {
		ViewAgentProfileService viewAgentService  = new ViewAgentProfileService(loggedAgent,targetAgent);
		viewAgentService.execute();
		return viewAgentService.getAgentDto();
		
	}
	
	@Override
	public List<AgentDto> listFriends(AgentDto agent) throws AgentNotFoundException {
		ListFriendsService listService  = new ListFriendsService(agent);
		listService.execute();
		return listService.getFriends();
		
	}
	
	public AgentDto searchOrganization(String name) throws AgentNotFoundException{
		SearchOrganizationService searchService = new SearchOrganizationService(name);
		searchService.execute();
		return searchService.getOrg();
	}
	
	@Override
	public List<AgentDto> listPendings(AgentDto agent) throws AgentNotFoundException {
		ListPendingsService listService  = new ListPendingsService(agent);
		listService.execute();
		return listService.getRequests();
		
	}
	
	@Override
	public List<AgentDto> listOrganizations(AgentDto agent) throws AgentNotFoundException{
		GetOrganizationFriendsService list = new GetOrganizationFriendsService(agent);
		list.execute();
		return list.getOrganizations();
	}
	
	
	@Override
	public void requestFriend(AgentDto origin, AgentDto requested) throws AgentNotFoundException, LimitOfFriendsReachedException, FriendRequestAlreadyExistsException, CantRequestOwnException, FriendAlreadyExistsException {
		AddFriendService request = new AddFriendService(origin, requested);
		request.execute();
	}
	
	@Override
	public void removeComment(PublicationDto pubDto, CommentDto comDto ){
		RemoveCommentService service = new RemoveCommentService(pubDto, comDto);
		service.execute();
		
	}
	
	@Override
	public void acceptConnection(AgentDto destinationDto, AgentDto sourceDto ) throws AgentNotFoundException{
		AcceptConnectionService service = new AcceptConnectionService(destinationDto, sourceDto);
		service.execute();
		
	}
	
	@Override
	public void rejectConnection(AgentDto destinationDto, AgentDto sourceDto ) throws AgentNotFoundException{
		RejectConnectionService service = new RejectConnectionService(destinationDto, sourceDto);
		service.execute();
		
	}
	
	
	public void addComment(AgentDto agentDto, PublicationDto pubDto, String comment) throws PublicationAccessDeniedException, PolemicPublicationException, PublicationNotFoundException, AgentNotFoundException{
		AddCommentService service = new AddCommentService(agentDto, pubDto, comment);
		service.execute();
	}
	
	public void votePub(AgentDto agentDto, PublicationDto pubDto, int type) throws CantVoteSelfException, CantVoteTwiceException, PublicationAccessDeniedException, PolemicPublicationException, PublicationNotFoundException{
		VoteService service = new VoteService(agentDto, pubDto, type);
		service.execute();
	}
	
	public List<CommentDto> listComment(PublicationDto pubDto) throws PublicationNotFoundException  {
		ListCommentsService commentService = new ListCommentsService(pubDto);
		
		commentService.execute();
		return commentService.getComment();
	}

	
}
