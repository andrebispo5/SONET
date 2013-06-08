package pt.ist.sonet.presentation.client;

import java.util.List;

import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.dto.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface SonetServletAsync {
	
	void initServer(String serverType, AsyncCallback<Void> callback);
	
	public void StartLargaCaixaService(AsyncCallback<Void> callback);
	
	void login(AgentLoginDto agent, AsyncCallback<AgentDto> callback);
	
	void listFriends(AgentDto agent, AsyncCallback<List<AgentDto>> callback);
	
	void listPendings(AgentDto agent, AsyncCallback<List<AgentDto>> callback);
	
	void publicationDetails(PublicationDto Dto, AsyncCallback<List<PublicationDto>> callback);

	void addNote(AgentDto agentDto, NoteDto noteDto,AsyncCallback<Void> callback );
	
	void listPublications(AgentDto activeAgent, AgentDto requestedAgent, AsyncCallback<List<PublicationDto>> callback);
	
	void removeComment(PublicationDto pubDto, CommentDto comDto, AsyncCallback<Void> callback );
	
	public void acceptConnection(AgentDto destination, AgentDto source, AsyncCallback<Void> callback);
	
	public void rejectConnection(AgentDto destination, AgentDto source, AsyncCallback<Void> callback);
	
	void addComment( AgentDto agentDto, PublicationDto pubDto, String comment, AsyncCallback<Void> callback);
	
	void listComment(PublicationDto pubDto, AsyncCallback<List<CommentDto>> callback);
	
	void votePub(AgentDto agentDto, PublicationDto pubDto, int type, AsyncCallback<Void> callback);
	
	void transferMoney(AgentDto sender, AgentDto receiver, int amount, String description, AsyncCallback<Void> callback);
	
	void listOrganizations(AgentDto agent, AsyncCallback<List<AgentDto>> callback);

	void listContent(AgentDto requestedAgent,	AsyncCallback<List<UrlDto>> callback);
	
	void buyContent(AgentDto buyer, AgentDto seller, String contentID, String paymentProof, AsyncCallback<Void> callback);
	
	void requestFriend(AgentDto origin, AgentDto requestedAgent, AsyncCallback<Void> callback);
	
	void searchOrganization(String name, AsyncCallback<AgentDto> callback); 
	
	void viewAgentProfile(AgentDto loggedAgent, AgentDto targetAgent, AsyncCallback<AgentDto> callback) throws AgentNotFoundException;
}
