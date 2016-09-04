/*
 * CPSC 211 Example
 */
package ca.ubc.cpsc210.courseRecommender;

import java.util.Scanner;
import java.util.Set;

/**
 * Make the recommender do its thing
 */
public class CourseRecommenderApp {

	// The recommender
	private static CourseRecommender ubcRecommender;

	/**
	 * Load the data in
	 */
	private static void loadData() {
		Course course1 = new Course("CPSC", 110);
		Course course2 = new Course("CPSC", 210);
		Course course3 = new Course("CPSC", 213);
		Course course4 = new Course("CPSC", 221);
		Course course5 = new Course("MATH", 100);
		Course course6 = new Course("MATH", 101);
		Course course7 = new Course("MATH", 200);
		Course course8 = new Course("MATH", 221);
		Course course9 = new Course("MATH", 180);
		ubcRecommender.addRecommendation(course1, course2);
		ubcRecommender.addRecommendation(course2, course3);
		ubcRecommender.addRecommendation(course2, course4);
		ubcRecommender.addRecommendation(course5, course6);
		ubcRecommender.addRecommendation(course6, course7);
		ubcRecommender.addRecommendation(course6, course8);
		ubcRecommender.addRecommendation(course9, course6);
	}

	/**
	 * Allow the user to enter course names after a prompt to see if there is a
	 * recommendation. 
	 */
	private static void processCommands() {
		Scanner input = new Scanner(System.in);
		String command = null;
		boolean keepGoing = true;

		while (keepGoing) {
			System.out
					.println("Enter name of course (DEPT-XXX) for recommendation (or 'all' or 'quit'): ");

			command = input.next();
			if (command.equals("quit"))
				keepGoing = false;
			else if (command.equals("all"))
				ubcRecommender.print();
			else {
				String dept = command.substring(0,Course.DEPT_LENGTH);
				int num = Integer.parseInt(command.substring(Course.DEPT_LENGTH + 1));
				Course courseTaken = new Course(dept, num);
				Set<Course> coursesToTake = ubcRecommender
						.recommended(courseTaken);
				if (coursesToTake != null) {
					System.out.println("Take course(s): ");
					for (Course c : coursesToTake)
						System.out.println("\t" + c);
				} else
					System.out.println("\tNo recommendation.");
			}
		}
	}

	public static void main(String[] args) {
		// Create the recommender
		ubcRecommender = new CourseRecommender();

		// Load it up with data
		loadData();

		// Respond to user commands
		processCommands();
	}
}
