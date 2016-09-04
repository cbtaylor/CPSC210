package ca.ubc.cpsc210.alarm.model.exception;

import ca.ubc.cpsc210.alarm.model.AlarmCode;

/**
 * Represents exception that occurs when an attempt is made to 
 * disarm the system with a code that is not registered with
 * the system.
 */
public class CodeException extends AlarmException {
	public CodeException() {
		super("Code is not registered in system.");
	}
	
	public CodeException(AlarmCode code) {
		super("Code " + code + " is not registered in system.");
	}
}
