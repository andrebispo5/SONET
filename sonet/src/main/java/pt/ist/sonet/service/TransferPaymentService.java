package pt.ist.sonet.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import pt.ist.sonet.service.dto.AgentDto;
import jvstm.Atomic;
import pt.ist.pagamigo.*;
import pt.pagamigo.ws.*;

public class TransferPaymentService extends SonetService{
	
	private AgentDto senderDto;
	private AgentDto receiverDto;
	private int amount;
	private String description;
	
	
	public TransferPaymentService(AgentDto sender,AgentDto receiver, int amount, String description) {
		this.senderDto = sender;
		this.receiverDto = receiver;
		this.amount = amount;
		this.description=description;
		
	}

	public final void dispatch() throws AgentNotFoundException{
		Sonet sonet = FenixFramework.getRoot();
		PagAmigo pagamigo = this.getPagAmigo();
		Agent sender=sonet.getAgentByUsername(senderDto.getUsername());
		Agent receiver=sonet.getAgentByUsername(receiverDto.getUsername());
		System.out.println(pagamigo);
		try {
				pagamigo.transfer(sender.getUsername(), receiver.getUsername(), this.amount, this.description);
			} catch (InvalidTransferException e) {
				throw new NoMoneyException();
			} catch (ClienteInexistente e) {
				throw new AgentNotFoundException();
			} catch (MontanteInvalido e) {
				throw new NoMoneyException();
			} catch (SaldoInsuficiente e) {
				throw new NoMoneyException();
			}

	}

}
