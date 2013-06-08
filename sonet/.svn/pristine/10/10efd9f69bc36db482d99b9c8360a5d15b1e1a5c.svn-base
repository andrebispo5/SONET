package pt.ist.sonet.presentation.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.ist.pagamigo.InvalidTransferException;
import pt.ist.sonet.service.dto.*;

import pt.ist.sonet.exception.*;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface SonetServlet extends RemoteService {
	
	public void initServer(String serverType);
	
	public void StartLargaCaixaService()  throws AgentNotFoundException;
	
	public AgentDto login(AgentLoginDto agent) throws AgentNotFoundException, WrongPasswordException;
	
	public List<CommentDto> publicationDetails(PublicationDto Dto) 
		throws PublicationAccessDeniedException, PublicationNotFoundException;
	
	public void addNote(AgentDto agentDto, NoteDto noteDto)throws PublicationNotFoundException,AgentNotFoundException;
	
	public List<PublicationDto> listPublications(AgentDto activeAgent, AgentDto requestedAgent)throws AgentNotFoundException;
	
	public void removeComment(PublicationDto pubDto, CommentDto comDto);
	
	public void acceptConnection(AgentDto source, AgentDto destination) throws AgentNotFoundException;
	
	public void rejectConnection(AgentDto source, AgentDto destination) throws AgentNotFoundException;

	public List<AgentDto> listFriends(AgentDto agent) throws AgentNotFoundException;
	
	public List<AgentDto> listPendings(AgentDto agent) throws AgentNotFoundException;
	
	public void addComment(AgentDto agentDto,PublicationDto pubDto, String comment) throws PublicationAccessDeniedException, PolemicPublicationException, AgentNotFoundException, PublicationNotFoundException;
	
	public void votePub(AgentDto agentDto, PublicationDto pubDto, int type) throws CantVoteSelfException, CantVoteTwiceException, PublicationAccessDeniedException, PolemicPublicationException, PublicationNotFoundException;
	
	public List<AgentDto> listOrganizations(AgentDto agent) throws AgentNotFoundException;
	
	public List<UrlDto> listContent(AgentDto requestedAgent) throws AgentNotFoundException;
	
	public void transferMoney(AgentDto sender, AgentDto receiver, int amount, String description) throws NoMoneyException, AgentNotFoundException;
	
	public void buyContent(AgentDto buyer, AgentDto seller, String contentID, String paymentProof);
	
	public List<CommentDto> listComment(PublicationDto pubDto) throws PublicationNotFoundException;

	public void requestFriend(AgentDto origin, AgentDto requestedAgent) throws AgentNotFoundException, LimitOfFriendsReachedException, FriendRequestAlreadyExistsException, CantRequestOwnException, FriendAlreadyExistsException;
	
	public AgentDto searchOrganization(String name) throws AgentNotFoundException;
	
	public AgentDto viewAgentProfile(AgentDto loggedAgent, AgentDto targetAgent) throws AgentNotFoundException, PermissionDeniedException;
}
