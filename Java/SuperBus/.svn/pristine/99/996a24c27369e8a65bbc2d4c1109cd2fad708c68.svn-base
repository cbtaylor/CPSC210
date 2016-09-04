package ca.ubc.cpsc.superbus.model;

import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;
import ca.ubc.cpsc.superbus.model.exception.RampException;

/* 
 * A new and improved bus that automates some
 * operations.
 * 
 * Invariant:
 * 	  !isStopped() && !isDoorOpen() && !isRampLowered()
 *	|| isStopped() && !isDoorOpen() && !isRampLowered()
 *	|| isStopped() && isDoorOpen() && !isRampLowered()
 *	|| isStopped() && isDoorOpen() && isRampLowered()
 */
public class AutoBus extends Bus {
	
	public AutoBus() {
		super();
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: if !isStopped() throws MotionException
	 *          o/w door is open and ramp is lowered 
	 */
	@Override
	public void lowerRamp() throws MotionException, DoorException {
		if ( !isStopped() )
			throw new MotionException("Bus is moving.");
		hasValidState();
		openDoor();
		super.lowerRamp();
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: if isRampLowered() ramp is raised and door is closed, otherwise no change
	 */
	@Override
	public void raiseRamp() {
		if (isRampLowered()) {
			super.raiseRamp();
			try {
				closeDoor();
			} catch (RampException e) {
				System.out.println("Ramp is not raised.");
			}
		}
		hasValidState();
	}
}
