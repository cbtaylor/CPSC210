/*
 * CPSC 211 Example
 */
package ca.ubc.cpsc210.courseRecommender;

/**
 * A course taken by students
 */
public class Course {
	// Length of dept string
	public static final int DEPT_LENGTH = 4;
	// Constant for generating hash codes
	private static final int HASH_CONSTANT = 13;
	// The dept offering the course
	private String dept;
	// The course number
	private int num;

	/**
	 * Constructor
	 * @param courseDept  the department offering the course
	 * @param courseNum  the course number
	 */
	public Course(String courseDept, int courseNum) {
		dept = courseDept;
		num = courseNum;
	}

	/**
	 * Getter for course department
	 * @return the name of the department that offers this course
	 */
	public String getDept() {
		return dept;
	}
	
	/**
	 * Getter for course number
	 * @return  the course number
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Helper to print out the course
	 */
	public String toString() {
		return dept + "-" + num;
	}
}
