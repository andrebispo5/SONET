package pt.ist.sonet.exception;

public class FriendRequestAlreadyExistsException extends SonetException {
	
	private static final long serialVersionUID = 1L;
	
	public FriendRequestAlreadyExistsException() {}
	
	@Override
	public String toString(){
		return ("You already have a friend request for that agent.");
	}
}

