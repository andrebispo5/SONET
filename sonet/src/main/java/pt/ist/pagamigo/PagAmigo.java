package pt.ist.pagamigo;

import pt.pagamigo.ws.*;


public abstract class PagAmigo {
    
	
    public abstract void transfer(String first, String second, int amount, String description) throws InvalidTransferException, ClienteInexistente, MontanteInvalido, SaldoInsuficiente;
	public abstract int getBalance(String user);
	protected abstract void setBalance(String user, int amount);
	
}