package ca.ubc.cpsc210.account.exceptions;

/*
 * Represents when an illegal value is passed to an operation.
 */
@SuppressWarnings("serial")
public class IllegalValueException extends Exception {
	
	public IllegalValueException() {}
	
	public IllegalValueException( String msg ) {
		super(msg);
	}
}
