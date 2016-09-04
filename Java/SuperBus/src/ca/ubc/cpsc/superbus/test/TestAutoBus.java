package ca.ubc.cpsc.superbus.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc.superbus.model.AutoBus;
import ca.ubc.cpsc.superbus.model.exception.BusException;
import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;


public class TestAutoBus extends TestBus {
	
	@Before 
	@Override
	public void runBeforeTests() {
		bus = new AutoBus();
	}
	
	@Test
	@Override
	public void testLowerRampWithDoorClosed() {
		try {
			bus.stop();
			bus.lowerRamp();
			assertTrue(bus.isDoorOpen());
			assertTrue(bus.isRampLowered());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void testRaiseCheckDoorClosed() {
		try {
			bus.stop();
			bus.openDoor();
			bus.lowerRamp();
			bus.raiseRamp();
			assertTrue(!bus.isRampLowered());
			assertTrue(!bus.isDoorOpen());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}
	
	@Test
	public void testRaiseWhenRaised() {
		try {
			bus.stop();
			bus.lowerRamp();
			bus.raiseRamp();
			bus.raiseRamp();
		} catch (DoorException e) {
			fail(e.getMessage());
		} catch (MotionException e) {
			fail(e.getMessage());
		}
	}
}
