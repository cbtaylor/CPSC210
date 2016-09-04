package ca.ubc.cs.cpsc210.meetup.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.model.Building;
import ca.ubc.cs.cpsc210.meetup.model.Course;
import ca.ubc.cs.cpsc210.meetup.model.CourseFactory;
import ca.ubc.cs.cpsc210.meetup.model.Schedule;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class ScheduleTest {

	private Schedule schedule;
	private CourseFactory cfactory;

	@Before
	public void runBefore() {
		// Used for most tests but not all
		schedule = new Schedule();

		// Get some courses
		CourseFactory.reset();
		cfactory = CourseFactory.getInstance();
		Course cpscCourse = cfactory.getCourse("CPSC", 210);
		Course bioCourse = cfactory.getCourse("BIOL", 202);

		// Make some sections
		Section section = new Section("202", "MWF", "15:00", "15:50",
				new Building("Elsewhere", new LatLon()));
		section.setCourse(bioCourse);
		bioCourse.addSection(section);
		schedule.add(section);

		section = new Section("201", "MWF", "12:00", "12:50", new Building(
				"Somewhere", new LatLon()));
		section.setCourse(cpscCourse);
		cpscCourse.addSection(section);
		schedule.add(section);
	}

	@Test
	public void testFirstStartTime() {
		try {
			CourseTime startTime = schedule.startTime("MWF");
			CourseTime ct = new CourseTime("12:00", "12:50");
			assertTrue(startTime.equals(ct));
		} catch (IllegalCourseTimeException ice) {
			fail("IllegalCourseTimeException should not have occured.");
		}

	}

	@Test
	public void testLastEndTime() {
		try {
			CourseTime endTime = schedule.endTime("MWF");
			CourseTime ct = new CourseTime("15:00", "15:50");
			assertTrue(endTime.equals(ct));
		} catch (IllegalCourseTimeException ice) {
			fail("IllegalCourseTimeException should not have occured.");
		}
	}


	@Test
	public void findAllBreaks() {
		Set<String> breakTimes = schedule.getStartTimesOfBreaks("MWF");
		assertTrue(breakTimes.size() == 1);
		Iterator<String> iterator = breakTimes.iterator();
		String breakTime = iterator.next();
		assertTrue(breakTime.equals("12:50"));
	}
	
	@Test
	public void testWhereAmI() {
		Building testBuilding = new Building("Elsewhere", new LatLon());
		Building result = schedule.whereAmI("MWF", "16:00");
		assertEquals(testBuilding, result);
	}

}
