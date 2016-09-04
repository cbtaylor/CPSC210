package ca.ubc.cpsc210.alarm.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.alarm.model.Event;
import ca.ubc.cpsc210.alarm.model.EventLog;

/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
	private Event e1;
	private Event e2;
	private Event e3;
	
	private void loadEvents() {
		e1 = new Event("A1");
		e2 = new Event("A2");
		e3 = new Event("A3");
		EventLog el = EventLog.getInstance();
		el.logEvent(e1);
		el.logEvent(e2);
		el.logEvent(e3);
	}
	
	@Test
	public void testLogEvent() {	
		List<Event> l = new ArrayList<Event>();
		loadEvents();
		
		EventLog el = EventLog.getInstance();
		for (Event next : el) {
			l.add(next);
		}
		
		assertTrue(l.contains(e1));
		assertTrue(l.contains(e2));
		assertTrue(l.contains(e3));
	}

	@Test
	public void testClear() {
		loadEvents();
		EventLog el = EventLog.getInstance();
		el.clear();
		Iterator<Event> itr = el.iterator();
		assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
		assertEquals("Event log cleared.", itr.next().getDescription());
		assertFalse(itr.hasNext());
	}
}
