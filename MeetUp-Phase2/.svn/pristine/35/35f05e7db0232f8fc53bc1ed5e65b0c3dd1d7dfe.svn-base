package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * Represent a location that an individual can go to
 */
public class Location {

	// The latitude and longitude of the location
	private LatLon latLon;

	// Some text to use to identify the location
	protected String displayText;

	/**
	 * Constructor
     * @param latLon The latitude and longitude of the location
	 */
	public Location(LatLon latLon) {
		this.latLon = latLon;
		displayText = "default";
	}

	// ************** Getters ****************

	public LatLon getLatLon() {
		return latLon;
	}

	public String getDisplayText() {
		return displayText;
	}

	// ************* Hashcode and equals **************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayText == null) ? 0 : displayText.hashCode());
		result = prime * result + ((latLon == null) ? 0 : latLon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (displayText == null) {
			if (other.displayText != null)
				return false;
		} else if (!displayText.equals(other.displayText))
			return false;
		if (latLon == null) {
			if (other.latLon != null)
				return false;
		} else if (!latLon.equals(other.latLon))
			return false;
		return true;
	}

}
