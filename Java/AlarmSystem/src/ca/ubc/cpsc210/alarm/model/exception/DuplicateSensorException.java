package ca.ubc.cpsc210.alarm.model.exception;

import ca.ubc.cpsc210.alarm.model.Sensor;

public class DuplicateSensorException extends Exception {

	public DuplicateSensorException(Sensor theSensor) {
		super("There is already a sensor located at: " + theSensor.getLocation());
	}
}
