package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/*
 * Represent a student's schedule consisting of all sections they must attend
 */
public class Schedule {

	SortedSet<Section> sections;

	/**
	 * Constructor
	 */
	public Schedule() {
		sections = new TreeSet<Section>();
	}

	/**
	 * Add a section to the student's schedule REQUIRES: section is not null
	 * MODIFIES: this EFFECTS: if section is not well-formed, throws
	 * IllegalSectionInitialization otherwise, section is remembered in the
	 * schedule
	 */
	public void add(Section section) throws IllegalSectionInitialization {
		if (section == null || 
				section.getStartTime().compareTo(section.getEndTime()) >= 0) {
			System.out.println(section);
			throw new IllegalSectionInitialization();
		}
		sections.add(section);
	}

	/**
	 * Retrieve the earliest start time in the schedule on a given day REQUIRES:
	 * dayOfWeek is either "MWF" or "TR" EFFECTS: Returns the start and end
	 * times of the earliest section on that day or null
	 */
	public CourseTime startTime(String dayOfWeek) {
		SortedSet<Section> sectionsOnDay = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek)) {
				sectionsOnDay.add(s);
			}
		}
		if (!sectionsOnDay.isEmpty()) {
			return new CourseTime(sectionsOnDay.first().getStartTime(),
					sectionsOnDay.first().getEndTime());
		}

		return null;
	}

	/**
	 * Retrieve the latest start time in the schedule on a given day REQUIRES:
	 * dayOfWeek is either "MWF" or "TR" EFFECTS: Returns the start and end
	 * times of the latest section on that day or null
	 */
	public CourseTime endTime(String dayOfWeek) {
		SortedSet<Section> sectionsOnDay = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek)) {
				sectionsOnDay.add(s);
			}
		}
		if (!sectionsOnDay.isEmpty()) {
			return new CourseTime(sectionsOnDay.last().getStartTime(),
					sectionsOnDay.last().getEndTime());
		}

		return null;
	}

	/**
	 * Find the start time of all two hour breaks less than the end time
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" EFFECTS: Returns a set of the
	 * end time before any 2 hour break. The end time is in HH:MM format.
	 */
	public Set<String> getStartTimesOfBreaks(String dayOfWeek) {
		SortedSet<Section> sectionsOnDay = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek)) {
				sectionsOnDay.add(s);
			}
		}
		Set<String> breaks = new TreeSet<String>();
		// use iterator
		Iterator<Section> iterator = sectionsOnDay.iterator();
		Section prev = iterator.next();
		while (iterator.hasNext()) {
			Section next = iterator.next();
			System.out.println(prev.getEndTime() + " to " + next.getStartTime());
			CourseTime courseTimePrev = new CourseTime(prev.getStartTime(),
					prev.getEndTime());
			CourseTime courseTimeNext = new CourseTime(next.getStartTime(),
					next.getEndTime());
			if (courseTimeNext.getStartHour() * 60 + courseTimeNext.getStartMinutes()
					- (courseTimePrev.getEndHour() * 60 + courseTimePrev.getEndMinutes()) 
					>= 120) {
				breaks.add(prev.getEndTime());
			}
			prev = next;
		}
		return breaks;
	}

	/**
	 * In which building was I before the given timeOfDay on the given dayOfWeek
	 * REQUIRES: dayOfWeek is "MWF or "TR" and timeOfDay is non-null and of
	 * format HH:MM EFFECTS: The Building in which the student last was before
	 * timeOfDay on dayOfWeek or null
	 */
	public Building whereAmI(String dayOfWeek, String timeOfDay) {
		// TODO: Complete implementation
		SortedSet<Section> sectionsOnDay = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek)) {
				sectionsOnDay.add(s);
			}
		}
		if (sectionsOnDay.isEmpty()) {
			return null;
		}
		
		CourseTime timeToTest = new CourseTime(timeOfDay, timeOfDay);
		// use iterator
		Iterator<Section> iterator = sectionsOnDay.iterator();
		Section first = iterator.next();
		CourseTime courseTimeFirstStart = new CourseTime(first.getStartTime(),
				first.getStartTime());
		if (timeToTest.compareTo(courseTimeFirstStart) == -1) {
			return null;
		}
		Section prev = first;
		
		while (iterator.hasNext()) {
			Section next = iterator.next();
			CourseTime courseTimePrevStart = new CourseTime(prev.getStartTime(),
					prev.getStartTime());
			CourseTime courseTimePrevEnd = new CourseTime(prev.getEndTime(),
					prev.getEndTime());
			CourseTime courseTimeNextStart = new CourseTime(next.getStartTime(),
					next.getStartTime());
			
			if (timeToTest.compareTo(courseTimePrevStart) == 0) {
				return prev.getBuilding();
			}
			if (timeToTest.compareTo(courseTimePrevEnd) == -1) {
				return prev.getBuilding();
			}
			if (timeToTest.compareTo(courseTimeNextStart) == -1) {
				return prev.getBuilding();
			}
			prev = next;
			
		}
		
		return sectionsOnDay.last().getBuilding();

	}

}
