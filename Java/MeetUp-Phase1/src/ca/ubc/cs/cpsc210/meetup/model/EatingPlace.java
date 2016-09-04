package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class EatingPlace extends Place {
	
	public EatingPlace(String name, LatLon latlon) {
		super();
		this.name = name;
		this.latlon = latlon;
		this.addTag("food");
	}
	
	public EatingPlace(String name) {
		super();
		this.name = name;
		super.addTag("food");
	}

}
