package pt.ist.pagamigo;

/**
 * This class represents a simplified local version of the external service PagaAmigo.
 * The simplifications made are the following ones:
 * - The data is not persistent
 * - Every user has an account.
 * - Each user, represented by a string, starts with a initial amount of money equal to 100.
 * - It just support the money transfer operation.
 *
 * @author ES
 **/

import java.util.Map;
import java.util.HashMap;

public class PagAmigoLocal extends PagAmigo {
    private static final int INITIAL_AMOUNT_MONEY = new Integer(100);

    private Map<String, Integer> accounts;

    public PagAmigoLocal() {
	this.accounts = new HashMap<String, Integer>();
    }

    /**
     * Transfer the specified amount of money from the the first user's account to the second user's account.
     *
     * @param first the identifier of the first user
     * @param second the identifier of the second user
     * @param amount the amount of money to transfer.
     * @param description description of the transfer
     *
     * @throws InvalidTransferException if the account of the first user has less money than the specified amount of money to transfer.
     **/
    public void transfer(String first, String second, int amount, String description) throws InvalidTransferException {
	int firstBalance = getBalance(first);

	if (firstBalance < amount)
	    throw new InvalidTransferException();

	setBalance(first, firstBalance - amount);
	setBalance(second, getBalance(second) + amount);

	System.out.println("Transfer of " + amount + " from " + first + " to " + second + " with description " + description);
    }

    /**
     * Returns the balance of the user's account.
     *
     * @param user the identifier of the user
     *
     * @return the balance of the account of the specified user.
     **/
    public int getBalance(String user) {
	Integer amount = this.accounts.get(user);
	
	if (amount == null) { // first time for the specified user
	    setBalance(user, INITIAL_AMOUNT_MONEY);
	    amount = INITIAL_AMOUNT_MONEY;
	}

	return amount;
    }

    protected void setBalance(String user, int amount) {
	this.accounts.put(user, amount);
    }
}