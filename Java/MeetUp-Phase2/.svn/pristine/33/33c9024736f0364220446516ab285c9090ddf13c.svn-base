package ca.ubc.cs.cpsc210.meetup.util;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;

public class CourseTime implements Comparable<CourseTime> {
	
	private int startHours;
	private int startMinutes;
	private int endHours;
	private int endMinutes;

    /**
     * Represent a course time
     * @param start The "HH:MM" at which a course (or section) starts
     * @param end The "HH:MM" at which a course (or section) starts
     * @throws IllegalCourseTimeException
     */
	public CourseTime(String start, String end) throws IllegalCourseTimeException {
		int indexOfColon = start.indexOf(":");
		if (indexOfColon <= 0)
			throw new IllegalCourseTimeException("Missing a : in a start time.");
		startHours = Integer.parseInt(start.substring(0, indexOfColon));
		startMinutes = Integer.parseInt(start.substring(indexOfColon+1, start.length()));
		indexOfColon = end.indexOf(":");
		if (indexOfColon <= 0)
			throw new IllegalCourseTimeException("Missing a : in an end time.");
		endHours = Integer.parseInt(end.substring(0, indexOfColon));
		endMinutes = Integer.parseInt(end.substring(indexOfColon+1, end.length()));	
		
		if (startHours > endHours)
			throw new IllegalCourseTimeException("Start time must be less than end time.");
		else if (startHours == endHours && startMinutes > endMinutes)
			throw new IllegalCourseTimeException("Start time must be less than end time.");
	}
	
	public String getEndTime() {
		return new String(endHours + ":" + endMinutes);
	}

    public String getStartTime() {
        if (startMinutes == 0)
            return new String(startHours + ":00");
        else
            return new String(startHours + ":" + startMinutes); }

	@Override
	public int compareTo(CourseTime other) {
		if (startHours > other.startHours)
			return 1;
		else if (startHours == other.startHours) {
			if (startMinutes < other.startMinutes)
				return -1;
			else if (startMinutes == other.startMinutes && endMinutes == other.endMinutes)
				return 0;
			else
				return 1;
		}
		else
			return -1;
	}
	
	public String toString() {
		return new String(startHours + ":" + startMinutes + " to " + endHours + ":" + endMinutes);
	}

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
