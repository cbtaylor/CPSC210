package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class Building extends Location {
	
	private String name;
	private LatLon latlon;
	
	public Building(String name, LatLon latlon) {
		this.name = name;
		this.latlon = latlon;
	}
	
	public Building(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLon getLatlon() {
		return latlon;
	}

	public void setLatlon(LatLon latlon) {
		this.latlon = latlon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latlon == null) ? 0 : latlon.hashCode());
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
		if (latlon == null) {
			if (other.latlon != null)
				return false;
		} else if (!latlon.equals(other.latlon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
