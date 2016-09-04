package ca.ubc.cpsc.superbus.model;

import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;
import ca.ubc.cpsc.superbus.model.exception.RampException;

/*
 * Models a bus
 * 
 * Invariant:
 * 	  !isStopped() && !isDoorOpen() && !isRampLowered()
 *	|| isStopped() && !isDoorOpen() && !isRampLowered()
 *	|| isStopped() && isDoorOpen() && !isRampLowered()
 *	|| isStopped() && isDoorOpen() && isRampLowered()
 */
public class Bus {

	private boolean bStopped;
	private boolean bDoorOpen;
	private boolean bRampExtended;
	
	/*
	 * Constructor
	 * EFFECTS: bus is stopped, ramp is not lowered and door is closed.
	 */
	public Bus() {
		bStopped = true;
		bDoorOpen = false;
		bRampExtended = false;
		hasValidState();
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: bus is moving
	 *          throws DoorException if isDoorOpen()
	 */
	public void go() throws DoorException {
		if ( isDoorOpen() )
			throw new DoorException("Door is open.");
		hasValidState();
		bStopped = false;
		hasValidState();
	}
	
	/*
	 * MODIFIES: this
	 * EFFECTS: bus is stopped
	 */
	public void stop() {
		hasValidState();
		bStopped = true;
		hasValidState();
	}
	
	/*
	 * EFFECTS: returns true if bus is stopped, false otherwise
	 */
	public boolean isStopped() {
		return bStopped;
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: door is open
	 *          throws MotionException if !isStopped()
	 */
	public void openDoor() throws MotionException {
		if ( !isStopped() )
			throw new MotionException("Bus is moving.");
		hasValidState();
		bDoorOpen = true;
		hasValidState();
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: door is closed
	 *          throws RampException if isRampLowered()
	 */
	public void closeDoor() throws RampException {
		if ( isRampLowered() )
			throw new RampException("Ramp is down.");
		hasValidState();
		bDoorOpen = false;
		hasValidState();
	}
	
	/*
	 * EFFECTS: returns true if door is open, false otherwise
	 */
	public boolean isDoorOpen() {
		return bDoorOpen;
	}
	
	/*
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: ramp is lowered, 
	 *          throw DoorException if !isDoorOpen()
	 *          throw MotionException if !isStoopped()
	 */
	public void lowerRamp() throws MotionException, DoorException {
		if ( !isStopped() )
			throw new MotionException("Bus is moving.");
		if ( !isDoorOpen() )
			throw new DoorException("Door is open.");
		hasValidState();
		bRampExtended = true;
		hasValidState();
	}
	
	/*
	 * REQUIRES: nothing 
	 * MODIFIES: this
	 * EFFECTS: if !isRampLowered() no change, otherwise ramp is raised
	 *          throws DoorException if !isDoorOpen()
	 */
	public void raiseRamp() {
		hasValidState();
		if (bRampExtended)
			bRampExtended = false;
		hasValidState();
	}
	
	/*
	 * EFFECTS: returns true if ramp is lowered, false otherwise
	 */
	public boolean isRampLowered() {
		return bRampExtended;
	}
	
	/*
	 * Check invariants.
	 */
	protected void hasValidState() {
		assert(!isStopped() && !isDoorOpen() && !isRampLowered()
				|| isStopped() && !isDoorOpen() && !isRampLowered()
				|| isStopped() && isDoorOpen() && !isRampLowered()
				|| isStopped() && isDoorOpen() && isRampLowered());
	}
}
