package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Set;

/*
 * Represent a course a student may take
 */
public class Course {

	// The four-letter code and number of the course
	private String code;
	private int number;
	
	// All sections offered this term for the course
	private Set<Section> sections;

    /**
     * Constructor
     * @param code The four letter code for a course
     * @param number The number of a course
     */
	public Course(String code, int number) {
		this.code = code;
		this.number = number;
		sections = new HashSet<Section>();
	}

	/**
	 * Add a section to the course
     * @param section The non-null section to add
	 */
	public void addSection(Section section) {
		if (!sections.contains(section))
			sections.add(section);
	}
	
	// ************** Getters ****************

	public String getCode() {
		return code;
	}

	public int getNumber() {
		return number;
	}

	public Building getBuilding(Section section) {
		return null;
	}
	
	public Section getSection(String sectionId) {
		for (Section section: sections) {
			if (section.getName().equals(sectionId))
				return section;
		}
		return null;
	}
	
	// ************** Equals and Hashcode ****************

    @Override
    public String toString() {
        return getCode() + " " + getNumber() + " " + sections.size();
    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Course course = (Course) o;

		if (number != course.number)
			return false;
		if (!code.equals(course.code))
			return false;
		if (!sections.equals(course.sections))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = code.hashCode();
		result = 31 * result + number;
		result = 31 * result + sections.hashCode();
		return result;
	}
}
