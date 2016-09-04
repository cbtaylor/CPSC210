package ca.ubc.cpsc.superbus.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc.superbus.model.Bus;
import ca.ubc.cpsc.superbus.model.exception.BusException;
import ca.ubc.cpsc.superbus.model.exception.DoorException;
import ca.ubc.cpsc.superbus.model.exception.MotionException;
import ca.ubc.cpsc.superbus.model.exception.RampException;

public class TestBus {

	protected Bus bus;
	
	@Before
	public void runBeforeTests() {
		bus = new Bus();
	}
	
	@Test
	public void testInitializeBus() {
		assertTrue(bus.isStopped());
		assertTrue(!bus.isRampLowered());
		assertTrue(!bus.isDoorOpen());
	}
	
	@Test
	public void testGo() {
		try {
			bus.go();
			assertTrue(!bus.isStopped());
		} catch (BusException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStop() {
		try {
			bus.go();
			bus.stop();
			assertTrue(bus.isStopped());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void testOpenDoor() {
		try {
			bus.openDoor();
			assertTrue(bus.isDoorOpen());
		} catch (MotionException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCloseDoor() {
		try {
			bus.openDoor();
			bus.closeDoor();
			assertTrue(!bus.isDoorOpen());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void testLower() {
		try {
			bus.stop();
			bus.openDoor();
			bus.lowerRamp();
			assertTrue(bus.isRampLowered());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void testRaise() {
		try {
			bus.stop();
			bus.openDoor();
			bus.lowerRamp();
			bus.raiseRamp();
			assertTrue(!bus.isRampLowered());
		} catch (BusException e) {
			fail(e.getMessage());
		} 
	}
	
	@Test
	public void testRaiseWhenRaised() {	
		try {
			bus.stop();
			bus.openDoor();
			bus.lowerRamp();
			bus.raiseRamp();
			bus.raiseRamp();
		} catch (MotionException e) {
			fail(e.getMessage());
		} catch (DoorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = DoorException.class)
	public void testGoWithDoorOpen() throws DoorException {
		try {
			bus.stop();
			bus.openDoor();
			bus.go();
		} catch (MotionException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = MotionException.class)
	public void testOpenDoorWhileMoving() throws MotionException {
		try {
			bus.go();
			bus.openDoor();
		} catch (DoorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = RampException.class)
	public void testCloseDoorWithRampExtended() throws RampException {
		try {
			bus.stop();
			bus.openDoor();
			bus.lowerRamp();
			bus.closeDoor();
		} catch (MotionException e) {
			fail(e.getMessage());
		} catch (DoorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = MotionException.class)
	public void testLowerRampWhileMoving() throws MotionException {
		try {
			bus.go();
			bus.lowerRamp();
		} catch (DoorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = DoorException.class)
	public void testLowerRampWithDoorClosed() throws DoorException {
		try {
			bus.stop();
			bus.closeDoor();
			bus.lowerRamp();
		} catch (RampException e) {
			fail(e.getMessage());
		} catch (MotionException e) {
			fail(e.getMessage());
		}
	}
}
