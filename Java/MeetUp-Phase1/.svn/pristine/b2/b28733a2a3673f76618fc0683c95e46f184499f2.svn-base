package ca.ubc.cs.cpsc210.meetup.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ca.ubc.cs.cpsc210.meetup.parser.ScheduleParser;

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
	 * REQUIRES: lastName is not null, firstName is not null
	 *    and id > 0 
	 * EFFECTS: Remember the student
	 */
	public void addStudent(String lastName, String firstName, int id) {
		if (!students.containsKey(id)) {
			Student newStudent = new Student(lastName, firstName, id);
			students.put(id, newStudent);
		}
	}

	/**
	 * Add a section to a student's schedule 
	 * REQUIRES: id > 0 and student has been added to this manager and courseCode is not null and courseNumber >
	 *   0 and sectionId is not null 
	 * MODIFIES: this 
	 * EFFECTS: Remember the section for the course
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

	/**
	 * Parse a schedule provided as an XML file 
	 * REQUIRES: filename is a valid file
	 * MODIFIES: this 
	 * sEFFECTS: student's and their schedules are remembered
	 */
	public void parseSchedules(String filename) throws IOException {
		ScheduleParser parser = new ScheduleParser(this);
		try {
			FileReader input = new FileReader(filename);
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(parser);
			reader.parse(new InputSource(input));
		} catch (SAXException e) {
			System.err.println("Unable to parse schedule: <" + e.getMessage() + ">");
			e.printStackTrace();
		}
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
