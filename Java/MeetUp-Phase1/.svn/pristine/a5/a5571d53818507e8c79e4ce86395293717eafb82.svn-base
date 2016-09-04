package ca.ubc.cs.cpsc210.meetup.util;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;

/**
 * Represents start and end time of a course.
 */

public class CourseTime implements Comparable<CourseTime> {

	// Start and end time separated into hours and minutes
	private int startHours;
	private int startMinutes;
	private int endHours;
	private int endMinutes;

	/**
	 * Constructor REQUIRES: start as non-null in format HH:MM and end as
	 * non-null in format HH:MM with start before end EFFECTS: object
	 * initialized or IllegalCourseTimeException occurs if strings are
	 * incorrectly formatted
	 */
	public CourseTime(String start, String end)
			throws IllegalCourseTimeException {
		// Make sure start is in HH:MM format
		int indexOfColon = start.indexOf(":");
		if (indexOfColon <= 0)
			throw new IllegalCourseTimeException("Missing a : in a start time.");

		// Convert start to hours and minutes
		startHours = Integer.parseInt(start.substring(0, indexOfColon));
		startMinutes = Integer.parseInt(start.substring(indexOfColon + 1,
				start.length()));

		// Make sure end is in HH:MM format
		indexOfColon = end.indexOf(":");
		if (indexOfColon <= 0)
			throw new IllegalCourseTimeException("Missing a : in an end time.");

		// Convert end to hours and minutes
		endHours = Integer.parseInt(end.substring(0, indexOfColon));
		endMinutes = Integer.parseInt(end.substring(indexOfColon + 1,
				end.length()));

		// Check start is before end
		if (startHours > endHours)
			throw new IllegalCourseTimeException(
					"Start time must be less than end time.");
		else if (startHours == endHours && startMinutes > endMinutes)
			throw new IllegalCourseTimeException(
					"Start time must be less than end time.");
	}

	/**
	 * Provide a re-formatted end time
	 * EFFECTS: Returns end time as HH:MM
	 */
	public String getEndTime() {
		return new String(endHours + ":" + endMinutes);
	}

	@Override
	public int compareTo(CourseTime other) {
		if (startHours > other.startHours)
			return 1;
		else if (startHours == other.startHours) {
			if (startMinutes < other.startMinutes)
				return -1;
			else if (startMinutes == other.startMinutes
					&& endMinutes == other.endMinutes)
				return 0;
			else
				return 1;
		} else
			return -1;
	}

	/**
	 * Provide a nicely formatted CourseTime
	 */
	public String toString() {
		return new String(startHours + ":" + startMinutes + " to " + endHours
				+ ":" + endMinutes);
	}

	// *********** Hashcode and equals ************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endHours;
		result = prime * result + endMinutes;
		result = prime * result + startHours;
		result = prime * result + startMinutes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseTime other = (CourseTime) obj;
		if (endHours != other.endHours)
			return false;
		if (endMinutes != other.endMinutes)
			return false;
		if (startHours != other.startHours)
			return false;
		if (startMinutes != other.startMinutes)
			return false;
		return true;
	}

}
