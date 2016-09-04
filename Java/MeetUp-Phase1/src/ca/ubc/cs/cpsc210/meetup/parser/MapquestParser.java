package ca.ubc.cs.cpsc210.meetup.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalStudentException;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;

/**
 * Parse XML of a Mapquest call (Sax parser)
 */

public class MapquestParser extends DefaultHandler {

	private StringBuffer accumulator;
	private String lastName;
	private String firstName;
	private int id;
	private int studentId;
	private String sectionId;
	private String courseCode;
	private int courseNumber;

	private StudentManager manager;

	public MapquestParser(StudentManager manager) {
		this.manager = manager;
	}

	/**
	 * Called at the start of the document (as the name suggests)
	 */
	@Override
	public void startDocument() {
		accumulator = new StringBuffer();
		lastName = null;
		firstName = null;
		id = 0;
		studentId = 0;
		sectionId = null;
		courseCode = null;
		courseNumber = 0;
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		if (qName.toLowerCase().equals("student")) {
			lastName = null;
			firstName = null;
			id = 0;
		} else if (qName.toLowerCase().equals("schedule")) {
			sectionId = null;
			courseCode = null;
			courseNumber = 0;
		} else if (qName.toLowerCase().equals("studentid")) {
			studentId = 0;
		} else if (qName.toLowerCase().equals("section")) {
			sectionId = atts.getValue("name");
			courseCode = atts.getValue("courseCode");
			courseNumber = Integer.valueOf(atts.getValue("courseNumber"));
			// System.out.println("add section: " + studentId + " " + courseCode + " " + courseNumber + " " + sectionId);
			manager.addSectionToSchedule(studentId, courseCode, courseNumber, sectionId);
		}
		
		accumulator.setLength(0);
	}

	/**
	 * Called for values of elements
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void endElement(String uri, String localName, String qName) 
	throws IllegalStudentException {
		if (qName.toLowerCase().equals("lastname")) {
			lastName = accumulator.toString().trim();
			if (lastName.isEmpty()) {
				throw new IllegalStudentException();
			}
			// System.out.println("lastname: " + lastName);
		}
		if (qName.toLowerCase().equals("firstname")) {
			firstName = accumulator.toString().trim();
			if (firstName.isEmpty()) {
				throw new IllegalStudentException();
			}
			// System.out.println("firstname: " + firstName);
		}
		if (qName.toLowerCase().equals("id")) {
			id = Integer.valueOf(accumulator.toString().trim());
			// System.out.println("id: " + id);
		}
		if (qName.toLowerCase().equals("studentid")) {
			studentId = Integer.valueOf(accumulator.toString().trim());
			// System.out.println("studentid: " + studentId);
		}
		if (qName.toLowerCase().equals("student")) {
			manager.addStudent(lastName, firstName, id);
			// System.out.println("add student: " + lastName + " " + firstName + " " + id);
		}
		
	}

}
