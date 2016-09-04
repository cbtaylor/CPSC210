package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/**
 * Represent a section for a course
 */
public class Section implements Comparable<Section> {

	private Course course = null;
	
	private String name;
	private String day;
	private String startTime;
	private String endTime;
	private Building building;
	
	// Time of course is provided to implement comparable
	private CourseTime timeOfCourse;

	/**
	 * Constructor 
	 * REQUIRES: name is not null, day is "MWF" or "TR", startTime
	 *   is before endTime and building is not null 
	 * EFFECTS: object is initialized
	 *   or the exception IllegalSectionInitialization has occurred
	 */
	public Section(String name, String day, String startTime, String endTime,
			Building building) {
		this.name = name;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.building = building;
		this.timeOfCourse = new CourseTime(startTime, endTime);
	}

	@Override
	public int compareTo(Section o) {
		return timeOfCourse.compareTo(o.timeOfCourse);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setCourse(Course theCourse) {
		if (course != theCourse) {
			course = theCourse;
			course.addSection(this);
		}
	}
	
	public Course getCourse() {
		return course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result
				+ ((timeOfCourse == null) ? 0 : timeOfCourse.hashCode());
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
		Section other = (Section) obj;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (timeOfCourse == null) {
			if (other.timeOfCourse != null)
				return false;
		} else if (!timeOfCourse.equals(other.timeOfCourse))
			return false;
		return true;
	}

}
