package ca.ubc.cs.cpsc210.meetup.util;

import org.osmdroid.util.GeoPoint;

import java.util.List;
import java.util.SortedSet;

import ca.ubc.cs.cpsc210.meetup.model.Section;

/**
 * Represents all information needed to plot a route for a schedule.
 * Only exists to separate and abstract the Android UI
 */
public class SchedulePlot {

    // The sections in the schedule
    private SortedSet<Section> sections;
    // The colour to use in hex
    private String colourOfLine;
    // The route between the sections
    private List<GeoPoint> route;
    // The icon to use as R.drawable...
    private int iconToUseForBuilding;
    // Who is this schedule for
    private String name;

    /**
     * Constructor
     * @param sections The sections in the schedule, non-null
     * @param name The name of whose schedule this is
     * @param colourOfLine The colour to use
     * @param iconToUseForBuilding The icon for the building
     */
    public SchedulePlot(SortedSet<Section> sections, String name, String colourOfLine, int iconToUseForBuilding) {
        this.sections = sections;
        this.colourOfLine = colourOfLine;
        this.iconToUseForBuilding = iconToUseForBuilding;
        this.name = name;
    }

    public SortedSet<Section> getSections() {
        return sections;
    }

    public String getColourOfLine() {
        return colourOfLine;
    }

    public void setRoute(List<GeoPoint> route) {
        this.route = route;
    }

    public List<GeoPoint> getRoute() {
        return route;
    }

    public int getIcon() { return iconToUseForBuilding; }

    public String getName() { return name; }
}
