package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class Place extends Location {
	
	protected String name;
	private Set<String> tags = new HashSet<String>();
	
	public Place(String name, LatLon latlon) {
		super();
		this.name = name;
		this.latlon = latlon;
	}
	
	public Place(String name) {
		super();
		this.name = name;
	}
	
	public Place() {
		super();
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public boolean containsTag(String tag) {
		return tags.contains(tag);
	}

	public String getName() {
		return name;
	}

}
