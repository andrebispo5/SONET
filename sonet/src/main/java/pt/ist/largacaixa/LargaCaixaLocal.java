package pt.ist.largacaixa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a simplified local version of the external service 
 * LargaCaixa. 
 * 
 * DISCLAIMER: The actual implementation may vary.
 * 
 * The simplifications made are the following ones:
 * - The data is not persistent
 * - Every user has an account.
 * - Each user, represented by a string, 
 *   starts with an empty list of contents
 *   and assumes transferring items are already there.
 * - It just supports the contents copy operation.
 * - Free/paid contents are not verified
 * - Payment proof is not verified
 *
 * @author ES
 **/
public class LargaCaixaLocal extends LargaCaixa {

	/**
	 * Storage of the list of contents for each PagAmigo user. 
	 */
	private Map<String, PersonalContents> contents;

	/**
	 * 
	 */
	public LargaCaixaLocal() {
		this.contents = new HashMap<String, PersonalContents>();
	}

	public void createBox(String agent){
		this.contents.put(agent, new PersonalContents());
	}
	
	public PersonalContents getPersonalContents(String sourceBox) {

		// check if box exists:
		if (this.contents.get(sourceBox) == null) {
			// initialize instead of throwing InexistentBoxException
			this.contents.put(sourceBox, new PersonalContents());
		}

		return this.contents.get(sourceBox);
	}

	public List<String> getAllUsernames() {
		List<String> result = new ArrayList<String>();
		result.addAll(this.contents.keySet());
		return result;
	}

	public void shareContent(String contentID, 
	                         String sourceUser, 
	                         String destinationUser, 
	                         Object paymentProof)
        throws InexistentBoxException, 
	             InexistentBoxContentException,
	             PaymentProofRejectedException {

		// get content to be transferred:
		Content content = getPersonalContents(sourceUser).getContent(contentID);

		// get store destination content:
		PersonalContents destination = getPersonalContents(destinationUser);

		// TODO: check payment proof

		// TODO: actual transfer 
  		destination.addContent(content);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LargaCaixaLocal box = new LargaCaixaLocal();

		String user1 = "zeze", user2 = "maria";

		System.out.println("--- INITIAL STATE ---");
		printAllUsersContent(box);

		System.out.println("--- FREE TRANSFER ---");
		try {
			box.shareContent("Cnt_01", user1, user2, null);
		} catch (InexistentBoxException e) {
			System.out.println("Couldn't transfer content: " + 
					"no such user " + e.getInexistentUsername());
		} catch (InexistentBoxContentException e) {
			System.out.println("Couldn't transfer content: " + 
					"no such content " + e.getInexistentID());
		} catch (PaymentProofRejectedException e) {
			System.out.println("Couldn't transfer content: " + 
					"invalid payment proof");
		}

		printAllUsersContent(box);

		System.out.println("--- PAID TRANSFER ---");
		try {
			box.shareContent("Cnt_02", user1, user2, "MyPagAmigoProof");
		} catch (InexistentBoxException e) {
			System.out.println("Couldn't transfer content: " + 
			  		             "no such user " + e.getInexistentUsername());
		} catch (InexistentBoxContentException e) {
			System.out.println("Couldn't transfer content: " + 
					               "no such content " + e.getInexistentID());
		} catch (PaymentProofRejectedException e) {
			System.out.println("Couldn't transfer content: " + 
					               "invalid payment proof");
		}

		printAllUsersContent(box);

	}

	private static void printAllUsersContent(LargaCaixaLocal box) {
		for (String user : box.getAllUsernames()) {
			System.out.println("Contents of " + user + ": ");
			for (Content c : box.getPersonalContents(user).getAllContents()) {
				System.out.println("[" + user + "]: " + c.getID());			
			}
		}
	}

}
