package ca.ubc.cs.cpsc210.meetup.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * A place represents a location at which people can meet
 */
public class Place extends Location {

    // The name of the place
    private String name;

    // Tags describing place
    private Set<String> tags;

    /**
     * Constructor
     * @param placeName tThe name of the place
     */
    public Place(String placeName) {
        this(placeName, new LatLon());
    }

    /**
     * Constructor
     * @param placeName The name of the place
     * @param latLon The latitude and longitude of the place
     */
    public Place(String placeName, LatLon latLon) {
        super(latLon);
        name = placeName;
        displayText = placeName;
        tags = new HashSet<String>();
    }


    /**
     * Add a tag describing what can be done in the place
     * @param tag The tag to add, non-null
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Determine if this place has the specified tag
     * @param tag The tag to look for, non-null
     * @return true if found, false otherwise
     */
    public boolean containsTag(String tag) {
        return tags.contains(tag);
    }


	// ************** Getters ****************

    public String getName() {
        return name;
    }

}
