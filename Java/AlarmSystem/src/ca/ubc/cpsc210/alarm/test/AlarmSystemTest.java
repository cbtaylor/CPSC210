package ca.ubc.cpsc210.alarm.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.alarm.model.AlarmCode;
import ca.ubc.cpsc210.alarm.model.AlarmController;
import ca.ubc.cpsc210.alarm.model.Sensor;
import ca.ubc.cpsc210.alarm.model.exception.CodeException;
import ca.ubc.cpsc210.alarm.model.exception.DuplicateSensorException;
import ca.ubc.cpsc210.alarm.model.exception.LastCodeException;
import ca.ubc.cpsc210.alarm.model.exception.NotValidCodeException;
import ca.ubc.cpsc210.alarm.model.exception.SystemNotReadyException;

/**
 * Unit tests for the AlarmSystem class
 */
public class AlarmSystemTest {
	private AlarmController ac;
	private AlarmCode dc;
	private AlarmCode c;
	private AlarmCode nvc;
	private Sensor s1;
	private Sensor s2;
	
	@Before
	public void runBeforeAllTests() {
		ac = new AlarmController();
		dc = new AlarmCode("1111");
		c = new AlarmCode("1234");
		nvc = new AlarmCode("twelve");
		try {
			s1 = new Sensor("front door", ac);
			s2 = new Sensor("patio door", ac);
		} catch (DuplicateSensorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAlarmController() {
		assertFalse(ac.isArmed());
	}

	@Test
	public void testAddCode() {
		try {
			// add default code into system again to make sure
			// there are no problems with multiple additions
			ac.addCode(dc);
			ac.arm(dc);
			assertTrue(ac.isArmed());
			ac.disarm(dc);
			assertFalse(ac.isArmed());
			
			// add new code into system
			ac.addCode(c);
			ac.arm(c);
			assertTrue(ac.isArmed());
			ac.disarm(c);
			assertFalse(ac.isArmed());
		} catch (NotValidCodeException e) {
			fail(e.getMessage());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		} catch (CodeException e) {
			fail(e.getMessage());
		}
	}

	@Test (expected = NotValidCodeException.class)
	public void testAddCodeNVCException() throws NotValidCodeException {
		ac.addCode(nvc);
		assertFalse(ac.isArmed());
	}
	
	@Test (expected = CodeException.class)
	public void testRemoveCode() throws CodeException {
		try {
			ac.addCode(c);
			ac.arm(c);
			assertTrue(ac.isArmed());
			ac.removeCode(c);
			ac.disarm(c);
			assertTrue(ac.isArmed());
		} catch (NotValidCodeException e) {
			fail(e.getMessage());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		} catch (LastCodeException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = LastCodeException.class)
	public void testRemoveLastCode() throws LastCodeException {
		try {
			ac.addCode(c);
			ac.removeCode(dc);
			ac.removeCode(c);
		} catch (NotValidCodeException e) {
			fail(e.getMessage());
		} catch (CodeException e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void testAddSensor() {
		try {
			Sensor s = new Sensor("window", ac);
			s.open();
			assertFalse(ac.isReady());
			assertFalse(ac.isArmed());
		} catch (DuplicateSensorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = DuplicateSensorException.class)
	public void testAddDuplicateSensor() throws DuplicateSensorException {
		Sensor s1 = new Sensor("window", ac);
		Sensor s2 = new Sensor("window", ac);
	}

	@Test
	public void testIsReady() {
		s1.open();
		s2.open();
		assertFalse(ac.isReady());
		s1.close();
		assertFalse(ac.isReady());
		s2.close();
		assertTrue(ac.isReady());
	}
	
	@Test
	public void testArm() {
		try {
			ac.arm(dc);
			assertTrue(ac.isArmed());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		} catch (CodeException e) {
			fail(e.getMessage());
		}
	}
	
	@Test (expected = CodeException.class)
	public void testArmCNRException() throws CodeException {
		try {
			ac.arm(c);
			assertFalse(ac.isArmed());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		}		
	}
	
	@Test (expected = SystemNotReadyException.class)
	public void testArmSNRException() throws SystemNotReadyException {
		try {
			s1.open();
			ac.arm(dc);
			assertTrue(ac.isArmed());
		} catch (CodeException e) {
			fail(e.getMessage());
		}	
	}

	@Test
	public void testDisarm() {
		try {
			assertFalse(ac.isArmed());
			ac.arm(dc);
			assertTrue(ac.isArmed());
			ac.disarm(dc);
			assertFalse(ac.isArmed());
		} catch (SystemNotReadyException e) {
			fail(e.getMessage());
		} catch (CodeException e) {
			fail(e.getMessage());
		}
	}
}
