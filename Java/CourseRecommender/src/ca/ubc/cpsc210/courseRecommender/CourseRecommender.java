/*
 * CPSC 211 Example: Recommender System
 */
package ca.ubc.cpsc210.courseRecommender;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a course, recommend another course or courses to take.
 * 
 * Class Invariants: - recommendations != null
 */
public class CourseRecommender {
	// Remember a course to recommend based on a specified course
	private Map<Course, Set<Course>> recommendations;

	/**
	 * Constructor
	 */
	public CourseRecommender() {
		recommendations = new HashMap<Course, Set<Course>>();
	}

	/**
	 * Remember a recommendation, but only if we haven't yet been given this
	 * particular recommendation for the specified course
	 * 
	 * @pre taken != null && toTake != null
	 * @post recommended( taken ).contains( toTake )
	 * @param taken
	 *            The course that was taken
	 * @param toTake
	 *            The course recommended to take given the course that was taken
	 */
	public void addRecommendation(Course taken, Course toTake) {
		Set<Course> courseSet;
		if (!recommendations.containsKey(taken)) {
			// Need to create a set of courses for the value
			// of the key in the map
			courseSet = new HashSet<Course>();
			courseSet.add(toTake);
			recommendations.put(taken, courseSet);
		} else {
			// The course has recommendations for it,
			// add to the set.
			courseSet = recommendations.get(taken);
			courseSet.add(toTake);
		}
	}

	/**
	 * Recommend a course given a particular course
	 * 
	 * @pre taken != null
	 * @post recommendations = @pre.recommendations
	 * @param taken
	 *            The course that was taken
	 * @return The recommended courses as a Set or null if no courses to
	 *         recommend
	 */
	public Set<Course> recommended(Course taken) {
		return recommendations.get(taken);
	}

	/**
	 * Print out all recommendations
	 * 
	 */
	public void print() {
		for (Map.Entry<Course, Set<Course>> e : recommendations.entrySet()) {
			Set<Course> recommendedCourses = e.getValue();
			for (Course c : recommendedCourses)
				System.out.println(e.getKey() + " -> " + c);
		}
	}
}
