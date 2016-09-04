package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// NOTE TO 210 STUDENTS: You should not have to touch this class.

/**
 * A factory for courses
 */
public class CourseFactory {

	// Remember all the courses that have been defined. The key is the course code (e.g., all CPSC courses)
	private Map<String, Set<Course>> courses;
	
	// Singleton pattern
	private static CourseFactory instance = null;
	
	/**
	 * Return the factory instance
	 * EFFECTS: Returns a factory for looking up a course
	 */
	public static CourseFactory getInstance() {
		if (instance == null)
			instance = new CourseFactory();
		return instance;
	}
	
	/**
	 * Reset for testing
	 */
	public static void reset() {
		instance = null;
	}

	/**
	 * Constructor. Only the singleton needs to create.
	 */
	protected CourseFactory() {
		courses = new HashMap<String, Set<Course>>();
	}

	/**
	 * Retrieve a course, create the course if it hasn't been seen before
	 * REQUIRES: code != null and code.length() > 0 and number > 0
	 * MODIFIES: this
	 * EFFECTS: Returns a non-null course that this object will remember
	 */
	public Course getCourse(String code, int number) {
		// Get the courses with a given code from the map
		Set<Course> coursesWithCode = courses.get(code);

		// The course doesn't exist, add it
		if (coursesWithCode == null)
			return addCourse(code, number);
		
		// Is there a course with the given number?
		Course courseOfInterest = null;
		for (Course aCourse : coursesWithCode) {
			if (aCourse.getNumber() == number)
				courseOfInterest = aCourse;
		}

		if (courseOfInterest != null) {
			// There is a course, return it
			return courseOfInterest;
		} else {
			return addCourse(code, number);
		}

	}
	
	/**
	 * Helper method to add a course
	 */
	private Course addCourse(String code, int number) {
		// Create the Course
		Course course = new Course(code, number);
		
		// See if we need to add it to the set of courses keyed only on four letter code
		if (courses.containsKey(code)) {
			Set<Course> coursesWithCode = courses.get(code);
			coursesWithCode.add(course);
		} else {
			Set<Course> coursesWithCode = new HashSet<Course>();
			coursesWithCode.add(course);
			courses.put(code, coursesWithCode);
		}
		return course;
	}
	
}
