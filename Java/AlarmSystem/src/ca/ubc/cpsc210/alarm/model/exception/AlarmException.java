package ca.ubc.cpsc210.alarm.model.exception;

/**
 * Represents general exceptions that occur in the alarm system.
 */
public class AlarmException extends Exception {
	public AlarmException() {
		
	}
	
	public AlarmException(String msg) {
		super(msg);
	}
}
