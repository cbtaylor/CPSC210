package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * Represent a place to eat
 */
public class EatingPlace extends Place {

	/**
	 * Constructor
	 * @param name The name of the eating place
     * @param latLon The latitude and longitude of the eating place
	 */
    public EatingPlace(String name, LatLon latLon) {
        super(name, latLon);
        addTag("Food");

    }

    /**
     * Constructor
     * @param name The name of the eating place
     */
    public EatingPlace(String name) {
        this(name, new LatLon());
    }
}
