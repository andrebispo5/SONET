package pt.ist.largacaixa;

import java.util.List;

public abstract class LargaCaixa {

	public abstract PersonalContents getPersonalContents(String sourceBox);
	public abstract List<String> getAllUsernames();
	public abstract void createBox(String agent);
	public abstract void shareContent(String contentID, 
            String sourceUser, 
            String destinationUser, 
            Object paymentProof)
throws InexistentBoxException, 
InexistentBoxContentException,
PaymentProofRejectedException;
}
