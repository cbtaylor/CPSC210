package ca.ubc.cs.cpsc210.meetup.test;

import static org.junit.Assert.*;

// NOTE TO 210 Students. Provided for your information. You should not need
// to alter the CourseTime class or this test

import org.junit.Test;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

public class CourseTimeTest {

	@Test
	public void testStartLessThanEnd() {
		try {
			CourseTime ct1 = new CourseTime("16:00", "16:50");
			CourseTime ct2 = new CourseTime("17:00", "17:50");
			System.out.println(ct1.compareTo(ct2));
			assertTrue(ct1.compareTo(ct2) == -1);
		} catch (IllegalCourseTimeException ice) {
			fail("The start and end times are valid.");
		}
	}
	
	@Test
	public void testStartEqualEnd() {
		try {
			CourseTime ct1 = new CourseTime("16:00", "16:50");
			CourseTime ct2 = new CourseTime("16:00", "16:50");
			System.out.println(ct1.compareTo(ct2));
			assertTrue(ct1.compareTo(ct2) == 0);
		} catch (IllegalCourseTimeException ice) {
			fail("The start and end times are valid.");
		}
	}
	
	@Test
	public void testStartMoreThanEnd() {
		try {
			CourseTime ct1 = new CourseTime("17:00", "17:50");
			CourseTime ct2 = new CourseTime("16:00", "16:50");
			System.out.println(ct1.compareTo(ct2));
			assertTrue(ct1.compareTo(ct2) == 1);
		} catch (IllegalCourseTimeException ice) {
			fail("The start and end times are valid.");
		}
	}
	
	@Test (expected=IllegalCourseTimeException.class)
	public void testIllegalStartTime() throws IllegalCourseTimeException {
		CourseTime ct1 = new CourseTime("15", "16:00");
	}
	
	@Test (expected=IllegalCourseTimeException.class)
	public void testIllegalendTime() throws IllegalCourseTimeException {
		CourseTime ct1 = new CourseTime("15:00", "16;00");
	}


}
