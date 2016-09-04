package ca.ubc.cs.cpsc210.meetup.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * Provide a factory for places that have been "seen"
 */
public class PlaceFactory {

	// Remember the places with a given name.
	private Map<String, Set<Place>> places;

	// Singleton
	private static PlaceFactory instance = null;

	/**
	 * Retrieve the one and only one factory instance EFFECTS: The
	 * factory for places
	 */
	public static PlaceFactory getInstance() {
		if (instance == null)
			instance = new PlaceFactory();
		return instance;
	}

	/**
	 * Constructor. Only the singleton need access
	 */
	protected PlaceFactory() {
		places = new HashMap<String, Set<Place>>();
	}

	/**
	 * Add a new place to the factory. 
	 * @param place A place to add, there may be multiple places with the same name
	 */
	public void add(Place place) {
		if (places.keySet().contains(place.getName())) {
			boolean found = false;
			Set<Place> matchingPlaces = places.get(place.getName());
			for (Place aPlace : matchingPlaces) {
				if (aPlace.equals(place)) {
					found = true;
					break;
				}
			}
			if (!found) {
				matchingPlaces.add(place);
				return;
			}
		}

		Set<Place> placesWithName = new HashSet<Place>();
		placesWithName.add(place);
		places.put(place.getName(), placesWithName);

	}

	/**
	 * Retrieve the places with this name
     * @param name The place to look for
     * @return All places with the given name
	 */
	public Set<Place> get(String name) {
		return places.get(name);
	}

    public Set<Place> findPlacesWithinDistance(LatLon latlon, int distance) {
        Set<Place> returnSet = new HashSet<Place>();
        for (Set<Place> aSetOfPlaces: places.values()) {
            for (Place aPlace: aSetOfPlaces) {
                if (LatLon.distanceBetweenTwoLatLon(latlon, aPlace.getLatLon()) <= distance)
                    returnSet.add(aPlace);
            }
        }
        return returnSet;
    }

}
