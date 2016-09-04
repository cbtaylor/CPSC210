package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Set;

/*
 * Provide a factory for places that have been "seen"
 */
public class PlaceFactory {
	
	// NOTE: A place may not have a unique name. The combination
	// of name, lat and lon is unique.
	
	// Remember all the places that have been seen. 
	private Set<Place> places;
	
	// Singleton pattern
	private static PlaceFactory instance = null;
	
	/**
	 * Return the factory instance
	 * EFFECTS: Returns a factory for looking up a place
	 */
	public static PlaceFactory getInstance() {
		if (instance == null)
			instance = new PlaceFactory();
		return instance;
	}
	
	/**
	 * Reset for testing
	 */
	public static void reset() {
		instance = null;
	}

	/**
	 * Constructor. Only the singleton needs to create.
	 */
	protected PlaceFactory() {
		places = new HashSet<Place>();
	}

	

	public Set<Place> get(String name) {
		// Get the places with a given name 
		Set<Place> placesWithName = new HashSet<Place>();
		for (Place p : places) {
			if (p.getName().equals(name)) {
				placesWithName.add(p);
			}
		}
		return placesWithName;

		// The place doesn't exist, add it
		/*
		if (placesWithName == null)
			return addPlace(name, latlon);
		
		// Is there a place with the given name?
		Place placeOfInterest = null;
		for (Place aPlace : placesWithName) {
			if (aPlace.getName() == name)
				placeOfInterest = aPlace;
		}

		if (placeOfInterest != null) {
			// There is a place, return it
			return placeOfInterest;
		} else {
			return addPlace(name, latlon);
		}
		*/
	}
	
	/**
	 * Helper method to add a place
	 */
	public void add(Place place) {
		places.add(place);
		
	}
	
}
