package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/**
 * Represent a section for a course
 */
public class Section implements Comparable<Section> {

	// Name of the section (like 201)
	private String name;

	// Day of week the section occurs, either "MWF" or "TR"
	private String dayOfWeek;

	// The time of the course, start and end
	private CourseTime timeOfCourse;

	// The building in which the section occurs
	private Building building;

	// The course for which this is a section
	private Course course;

	/**
	 * Constructor
     * @param name The name of the section, such as "201"
     * @param day The day of Week as in "MWF" or "TR"
     * @param startTime When the section starts as in "HH:MM"
     * @param endTime The end time of the section as in "HH:MM"
     */
	public Section(String name, String day, String startTime, String endTime,
			Building building) {
		this.name = name;
		try {
			timeOfCourse = new CourseTime(startTime, endTime);
		} catch (IllegalCourseTimeException ice) {
			throw new IllegalSectionInitialization("Ill-formed time of course.");
		}
		dayOfWeek = day;
		this.building = building;
	}

	// ********** Getters and Setters **********

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;

	}

	public CourseTime getCourseTime() {
		return timeOfCourse;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	@Override
	public int compareTo(Section o) {
		return timeOfCourse.compareTo(o.timeOfCourse);
	}

	// ************* Equals and hashCode ****************

    @Override
    public String toString() {
        return "Section is " + name;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((building == null) ? 0 : building.hashCode());
		result = prime * result
				+ ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null)
				return false;
		} else if (!dayOfWeek.equals(other.dayOfWeek))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timeOfCourse == null) {
			if (other.timeOfCourse != null)
				return false;
		} else if (!timeOfCourse.equals(other.timeOfCourse))
			return false;
		return true;
	}

}
