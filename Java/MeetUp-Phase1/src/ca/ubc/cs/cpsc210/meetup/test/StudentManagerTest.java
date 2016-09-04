package ca.ubc.cs.cpsc210.meetup.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.model.Building;
import ca.ubc.cs.cpsc210.meetup.model.Course;
import ca.ubc.cs.cpsc210.meetup.model.CourseFactory;
import ca.ubc.cs.cpsc210.meetup.model.Schedule;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Student;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class StudentManagerTest {

	private StudentManager manager;

	@Before
	public void runBefore() {
		initializeCourseFactory();

		manager = new StudentManager();
	}

	@Test
	public void testAStudent() {
		try {
			manager.parseSchedules("data/Schedules.xml");
			Student student = manager.get(123456);
			// Spot check some data parsed
			assertTrue(student.getLastName().equals("Seed"));
			assertTrue(student.getFirstName().equals("Apple"));
			Schedule schedule = student.getSchedule();
			assertTrue(schedule.startTime("MWF").equals(new CourseTime("10:00","10:50")));
		} catch (IOException ioe) {
			fail("Unhandled IOException");
		} catch (IllegalCourseTimeException cte) {
			fail("Unhandled course time exception");
		}
	}

	private void initializeCourseFactory() {
		CourseFactory.reset();
		CourseFactory cfactory = CourseFactory.getInstance();
		Course course;
		Section section;

		course = cfactory.getCourse("CPSC", 210);
		section = new Section("201", "MWF", "16:00", "16:50", new Building(
				"DMP", new LatLon(49.261267, -123.248066)));
		course.addSection(section);
		section.setCourse(course);
		
		course = cfactory.getCourse("BIOL", 201);
		section = new Section("201", "MWF", "10:00", "10:50", new Building(
				"Wesbrook", new LatLon(49.265551, -123.249256)));
		course.addSection(section);
		section.setCourse(course);
	}

}
