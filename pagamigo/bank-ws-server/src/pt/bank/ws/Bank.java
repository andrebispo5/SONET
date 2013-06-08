package pt.bank.ws;

import java.util.TreeMap;

import javax.jws.*;


/*Interface for Bank implementation anotated to generate a WebService.*/
/*Implented at BankImpl*/
@WebService
public interface Bank {
	@WebMethod public int getBalance(String id);
	@WebMethod public void createAccount(String id, int balance);
	@WebMethod public void deleteAccount(String id);
	@WebMethod public boolean startLocalTransfer(String orig, String dest, int balance);
	@WebMethod public int startDeposit(String id, int balance);
	@WebMethod public int startWithdraw(String id, int balance);
	@WebMethod public boolean endTransaction(boolean decision);
	@WebMethod public void abortTransaction();
}
