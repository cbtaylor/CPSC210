package ca.ubc.cpsc210.alarm.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.alarm.model.AlarmController;
import ca.ubc.cpsc210.alarm.model.Sensor;
import ca.ubc.cpsc210.alarm.model.exception.DuplicateSensorException;

/**
 * Unit tests for the Sensor class
 */
public class SensorTest {
	private Sensor s;
	private AlarmController ac;
	
	@Before
	public void runBefore() {
		ac = new AlarmController();
		try {
			s = new Sensor("my location", ac);
		} catch (DuplicateSensorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSensor() {
		assertEquals("my location", s.getLocation());
		assertFalse(s.isOpen());
	}

	@Test
	public void testOpen() {
		assertFalse(s.isOpen());
		s.open();
		assertTrue(s.isOpen());
	}

	@Test
	public void testClose() {
		assertFalse(s.isOpen());
		s.open();
		assertTrue(s.isOpen());
		s.close();
		assertFalse(s.isOpen());
	}

	@Test
	public void testIsOpen() {
		assertFalse(s.isOpen());
		s.open();
		assertTrue(s.isOpen());
		s.close();
		assertFalse(s.isOpen());
	}

	@Test
	public void testGetLocation() {
		assertEquals("my location", s.getLocation());
	}
}
