package ca.ubc.cpsc210.alarm.model.exception;

/**
 * Represents the exception that occurs when an attempt is made
 * to arm the system but the system is not ready.
 */
public class SystemNotReadyException extends AlarmException {
	public SystemNotReadyException() {
		super("System not ready");
	}
	
	public SystemNotReadyException(String msg) {
		super(msg);
	}
}
