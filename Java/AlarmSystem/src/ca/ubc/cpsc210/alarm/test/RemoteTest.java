package ca.ubc.cpsc210.alarm.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.alarm.model.AlarmController;
import ca.ubc.cpsc210.alarm.model.Remote;
import ca.ubc.cpsc210.alarm.model.Sensor;
import ca.ubc.cpsc210.alarm.model.exception.DuplicateSensorException;
import ca.ubc.cpsc210.alarm.model.exception.SystemNotReadyException;

public class RemoteTest {
	private Remote r;
	private AlarmController ac;
	private Sensor s;
	
	@Before
	public void runBefore() {
		ac = new AlarmController();
		r = new Remote(ac);
		try {
			s = new Sensor("Door", ac);
		} catch (DuplicateSensorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testArm() {
		try {
			assertFalse(ac.isArmed());
			r.activate();
			assertTrue(ac.isArmed());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = SystemNotReadyException.class)
	public void testSNRArm() throws SystemNotReadyException {
		assertFalse(ac.isArmed());
		s.open();
		r.activate();
		assertTrue(ac.isArmed());
	}
}
