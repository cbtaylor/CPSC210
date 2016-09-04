package ca.ubc.cs.cpsc210.meetup.model;

import java.util.Collections;
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

	// Remember sections on each kind of day
	private SortedSet<Section> MWFSections;
	private SortedSet<Section> TRSections;

	/**
	 * Constructor
	 */
	public Schedule() {
		MWFSections = new TreeSet<Section>();
		TRSections = new TreeSet<Section>();
	}

	/**
	 * Add a section to the student's schedule
     * @param section The section to add to the schedule.
	 */
	public void add(Section section) throws IllegalSectionInitialization {
		SortedSet<Section> sections = getSectionsForDayOfWeek(section.getDayOfWeek());
		if (section.getCourse() == null)
			throw new IllegalSectionInitialization(
					"Course link is not set for " + section.toString());

		sections.add(section);
	}


	/**
	 * Retrieve the earliest start time in the schedule on a given day
     * @param dayOfWeek The day of the week, either "MWF" or "TR"
     * @return The CourseTime of the earliest section or null
	 */
	public CourseTime startTime(String dayOfWeek) {
		SortedSet<Section> sections = getSectionsForDayOfWeek(dayOfWeek);
		Section earliestSection = sections.first();
		if (earliestSection == null)
			return null;
		else
			return earliestSection.getCourseTime();
	}

	/**
	 * Retrieve the latest start time in the schedule on a given day 
	 * @param dayOfWeek The day of the week, either "MWF" or "TR"
     * @return The CourseTime of the latest section of the day or null
	 */
	public CourseTime endTime(String dayOfWeek) {
		SortedSet<Section> sections = getSectionsForDayOfWeek(dayOfWeek);
		Section latestSection = sections.last();
		if (latestSection == null)
			return null;
		else
			return latestSection.getCourseTime();
	}

	/**
	 * Find the start time of all two hour breaks less than the end time
	 * @param dayOfWeek The day of the week
     * @return The times in HH:MM of the start time of each two-hour break
	 */
	public Set<String> getStartTimesOfBreaks(String dayOfWeek) {
		SortedSet<Section> sections = getSectionsForDayOfWeek(dayOfWeek);
		Set<String> startTimes = new HashSet<String>();
		if (sections.size() == 1) {
			Section section = sections.first();
			CourseTime courseTime = section.getCourseTime();
			startTimes.add(courseTime.getEndTime());
		} else if (sections.size() > 1) {
			Iterator<Section> it = sections.iterator();
			Section section = it.next();
			String lastTime = section.getCourseTime().getEndTime();
			while (it.hasNext()) {
				section = it.next();
				String nextTime = section.getCourseTime().getEndTime();

				if (calculateBreakTimeInMinutes(nextTime, lastTime) >= 120) {
					startTimes.add(lastTime);
				}
				lastTime = nextTime;
			}

		}

		return startTimes;
	}

	/**
	 * In which building was I before the given timeOfDay on the given dayOfWeek
	 * @param dayOfWeek The day of week of interest, "MWF" or "TR"
     * @param timeOfDay The time of day as "HH"
     * @return The building where the student was last or null if nowhere
	 */
	public Building whereAmI(String dayOfWeek, String timeOfDay) {
		SortedSet<Section> sections = getSectionsForDayOfWeek(dayOfWeek);
		// Find which section ended just before timeOfDay
		Section lastSection = null;
		for (Section section : sections) {
			if (section.getCourseTime().getEndTime().compareTo(timeOfDay) <= 0) {
				lastSection = section;
			}
		}
		if (lastSection != null)
			return lastSection.getBuilding();
		return null;
	}

    /**
     * Retrieve the sets for a particular day of the week
     * @param dayOfWeek The day of week of interest, "MWF" or "TR"
     * @return The sections on a given day of Week
     */
    public SortedSet<Section> getSections(String dayOfWeek) {
        if (dayOfWeek.equals("MWF")) {
            return Collections.unmodifiableSortedSet(MWFSections);
        }
        else {
            return Collections.unmodifiableSortedSet(TRSections);
        }
    }

	/**
	 * Compute the break between two HH:MM strings in minutes
	 * @param second The later time
     * @param first The earlier time
     * @return minutes between
	 */
	private int calculateBreakTimeInMinutes(String second, String first) {
		int secondInMinutesIntoDay = calculateMinutesIntoDay(second);
		int firstInMinutesIntoDay = calculateMinutesIntoDay(first);
		System.out.println("minutes is "
				+ (secondInMinutesIntoDay - firstInMinutesIntoDay));
		return secondInMinutesIntoDay - firstInMinutesIntoDay;
	}

	/**
	 * Transform a HH:MM time into minutes into the day
     * @param aTime HH:MM time
     * @return Minutes since midnight
	 */
	private int calculateMinutesIntoDay(String aTime) {
		int colonIndex = aTime.indexOf(":");
		int hours = Integer.parseInt(aTime.substring(0, colonIndex));
		int minutes = Integer.parseInt(aTime.substring(colonIndex + 1,
				aTime.length()));
		return (hours * 60) + minutes;
	}

	/**
	 * Retrieve the sets for a particular day of the week
     * @param dayOfWeek The day of week of interest, "MWF" or "TR"
     * @return The sections on that day of week
	 */
	private SortedSet<Section> getSectionsForDayOfWeek(String dayOfWeek) {
		if (dayOfWeek.equals("MWF"))
			return MWFSections;
		else
			return TRSections;
	}

}
