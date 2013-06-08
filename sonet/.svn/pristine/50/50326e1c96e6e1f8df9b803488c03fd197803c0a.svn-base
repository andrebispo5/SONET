package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.largacaixa.InexistentBoxContentException;
import pt.ist.largacaixa.InexistentBoxException;
import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.PaymentProofRejectedException;
import pt.ist.sonet.domain.Agent;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.service.dto.AgentDto;

public class BuyContentService extends SonetService {
	
	private AgentDto buyer;
	private AgentDto seller;
	private String contentId;
	private String paymentProof;
	
	public BuyContentService(AgentDto buyer, AgentDto seller, String contentId,
			String paymentProof) {
		this.buyer = buyer;
		this.seller = seller;
		this.contentId = contentId;
		this.paymentProof = paymentProof;
	}
	
	public final void dispatch(){
		Sonet sonet = FenixFramework.getRoot();
		LargaCaixa largacaixa = sonet.getLargaCaixa();
		Agent buyer = sonet.getAgentByUsername(this.buyer.getUsername());
		Agent seller = sonet.getAgentByUsername(this.seller.getUsername());
		
		try{
			largacaixa.shareContent(this.contentId, seller.getUsername(), buyer.getUsername(), this.paymentProof);
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
	}

	
	

}
