package ca.ubc.cpsc210.account.exceptions;

/*
 * Represent when the account does not have enough money to complete 
 * a desired operation.
*/
@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {
	
	public NotEnoughMoneyException() {}
	
	public NotEnoughMoneyException( String msg ) {
		super(msg);
	}
}
