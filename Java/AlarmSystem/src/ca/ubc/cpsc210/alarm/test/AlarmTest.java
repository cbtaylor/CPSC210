package ca.ubc.cpsc210.alarm.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.alarm.model.Alarm;

/**
 * Unit tests for the Alarm class
 */
public class AlarmTest {
	private Alarm a;
	
	@Before
	public void runBefore() {
		a = new Alarm();
	}
	
	@Test
	public void testAlarm() {
		assertFalse(a.isSounding());
	}

	@Test
	public void testSound() {
		assertFalse(a.isSounding());
		a.sound();
		assertTrue(a.isSounding());
	}

	@Test
	public void testSilence() {
		a.sound();
		assertTrue(a.isSounding());
		a.silence();
		assertFalse(a.isSounding());
	}

	@Test
	public void testIsSounding() {
		assertFalse(a.isSounding());
		a.sound();
		assertTrue(a.isSounding());
		a.silence();
		assertFalse(a.isSounding());
	}
}
