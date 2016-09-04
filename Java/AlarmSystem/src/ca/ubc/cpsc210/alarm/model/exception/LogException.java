package ca.ubc.cpsc210.alarm.model.exception;

/**
 * Represents the exception that can occur when
 * printing the event log.
 */
public class LogException extends Exception {
	public LogException() {
		super("Error printing log");
	}
	
	public LogException(String msg) {
		super(msg);
	}
}
