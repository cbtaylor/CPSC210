package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * Represent a building
 */
public class Building extends Location {

    // The name of the building
    private String name;

    /**
     * Constructor
     * @param name The name of the building
     * @param latLon The latitude and longitude of building
     */
    public Building(String name, LatLon latLon) {
        super(latLon);
        this.name = name;
        displayText = name;
    }

    /**
     * Constructor
     * @param name The name of the building
     */
    public Building(String name) {
        this(name, new LatLon());
    }
    
	// ************** Getters ****************

    public String getName() {
        return name;
    }
    
    // ************** Equals and hashcode **************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Building other = (Building) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    
}
