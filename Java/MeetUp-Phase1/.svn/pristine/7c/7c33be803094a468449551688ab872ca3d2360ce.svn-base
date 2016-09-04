package ca.ubc.cs.cpsc210.meetup.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.meetup.model.Course;
import ca.ubc.cs.cpsc210.meetup.model.CourseFactory;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Building;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class CourseTest {

	private CourseFactory courseFactory;
	private Course course210;

	@Before
	public void runBefore() {
		// Make sure the CourseFactory is empty and then initialize it with known courses
		CourseFactory.reset();
		courseFactory = CourseFactory.getInstance();

		// Create a course 210 with two sections, just like now!
		course210 = courseFactory.getCourse("CPSC", 210);
		course210.addSection(new Section("201", "MWF", "12:00", "12:50", new Building("I", new LatLon())));
		course210.addSection(new Section("202", "MWF", "13:00", "13:50", new Building("J", new LatLon())));
	}

	// Test that two courses that should be equal are equal
	@Test
	public void testTwoCoursesEqual() {
		Course course210another = new Course("CPSC", 210);
		course210another.addSection(new Section("201", "MWF", "12:00", "12:50", new Building("I", new LatLon())));
		course210another.addSection(new Section("202", "MWF", "13:00", "13:50", new Building("J", new LatLon())));
		assertTrue(course210.equals(course210another));
	}

	// Test that two courses that should be NOT be equal are NOT equal
	@Test
	public void testTwoCoursesNotEqual() {
		Course course210another = new Course("CPSC", 210);
		course210another.addSection(new Section("201", "MWF", "12:00", "12:50", new Building("I", new LatLon())));
		course210another.addSection(new Section("202", "MWF", "12:00", "12:50", new Building("I", new LatLon())));
		course210another.addSection(new Section("BCS", "MWF", "12:00", "12:50", new Building("I", new LatLon())));
		assertFalse(course210.equals(course210another));
	}

	// Make sure the factory retrieves equivalent objects
	@Test
	public void testCourseFactoryReturnsSame() {
		Course course1 = courseFactory.getCourse("CPSC", 210);
		Course course2 = courseFactory.getCourse("CPSC", 210);
		if (course1 != course2)
			fail("testCourseFactoryReturnsSame returned different objects");
	}
}
