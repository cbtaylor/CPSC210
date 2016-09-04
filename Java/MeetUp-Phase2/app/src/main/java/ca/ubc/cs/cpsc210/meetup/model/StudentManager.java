package ca.ubc.cs.cpsc210.meetup.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage the students who have been "seen"
 */
public class StudentManager {

	// Remember the students, keyed by id
	private Map<Integer, Student> students;

	/**
	 * Constructor
	 */
	public StudentManager() {
		students = new HashMap<Integer, Student>();
	}

	/**
	 * Remember a student
     * @param lastName The lastName of the student, non-null and non-empty
     * @param firstName The firstName of the student, non-null and non-empty
     * @param id Id of student > 0
	 */
	public void addStudent(String lastName, String firstName, int id) {
        if (!students.containsKey(id)) {
			Student newStudent = new Student(lastName, firstName, id);
			students.put(id, newStudent);
		}
	}

	/**
	 * Add a section to a student's schedule
     * @param id The id of the student
     * @param courseCode The four-letter course code
     * @param courseNumber The course number
     * @param sectionId The section which must exist in course
	 */
	public void addSectionToSchedule(int id, String courseCode,
			int courseNumber, String sectionId) {
		CourseFactory courseFactory = CourseFactory.getInstance();
		Course course = courseFactory.getCourse(courseCode, courseNumber);
		Section section = course.getSection(sectionId);
		Student student = students.get(id);
		Schedule schedule = student.getSchedule();
		schedule.add(section);
	}

	
	// ************* Getters ***************
	
	/*
	 * Retrieve a particular student
	 * REQUIRES: id > 0 and student with that id has been remembered
	 * EFFECTS: Return the appropriate student
	 */
	public Student get(int id) {
		return students.get(id);
	}

}
