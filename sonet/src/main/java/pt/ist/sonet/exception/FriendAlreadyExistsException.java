package pt.ist.sonet.exception;

public class FriendAlreadyExistsException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	private String usernameFriend;
	
	
	
	
	public FriendAlreadyExistsException(){}
	
	public FriendAlreadyExistsException(String username) {
		this.usernameFriend=username;
	}
	
	public String getUsernameFriend() {
		return this.usernameFriend;
	}
	
	@Override
	public String toString(){
		return ("You are already friend with agent " + usernameFriend);
	}
}

