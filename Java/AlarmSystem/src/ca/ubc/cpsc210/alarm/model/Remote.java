package ca.ubc.cpsc210.alarm.model;

import ca.ubc.cpsc210.alarm.model.exception.SystemNotReadyException;

/**
 * Represents a remote to arm the alarm system.
 */
public class Remote {
	private AlarmController alarmController;
	
	/**
	 * Constructor
	 * @param controller  the alarm controller
     */
	public Remote(AlarmController controller) {
		alarmController = controller;
	}
	
	/**
	 * Arms the alarm system.
	 * @throws SystemNotReadyException if system cannot be armed
	 */
	public void activate() throws SystemNotReadyException {
		alarmController.arm();
	}
}
