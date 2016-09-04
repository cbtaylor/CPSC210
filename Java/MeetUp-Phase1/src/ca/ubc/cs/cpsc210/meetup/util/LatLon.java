package ca.ubc.cs.cpsc210.meetup.util;

/**
 * Represents a point on earth by its latitude and longitude
 */
public class LatLon {

	private double lat;
	private double lon;

	private static final double ANTARCTICA_LAT = 90.0000;
	private static final double ANTARCTICA_LON = 0.0000;

	/**
	 * Constructor
	 * REQUIRES: lat between 0 and 90 and lon between -180 and 180
	 */
	public LatLon(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * Default the lat lon to ANTARTICA
	 */
	public LatLon() {
		this.lat = ANTARCTICA_LAT;
		this.lon = ANTARCTICA_LON;
	}

	/**
	 * Is this a valid lat lon?
	 * EFFECTS: True if valid point on earth and false otherwise
	 */
	public boolean isIllegal() {
		if (lat < 0 || lat > 90 || lon < -180 || lon > 180)
			return false;
		return true;
	}

	// ************ Getters ***************
	
	public double getLongitude() {
		return lon;
	}

	public double getLatitude() {
		return lat;
	}

	/**
	 * Compute the distance between two points
	 * REQUIRES: point1 and point2 are valid points on earth
	 * EFFECTS: Distance in metres between the points
	 */
	public static double distanceBetweenTwoLatLon(LatLon point1, LatLon point2) {
		double d2r = Math.PI / 180;

		double dlong = (point2.getLongitude() - point1.getLongitude()) * d2r;
		double dlat = (point2.getLatitude() - point1.getLatitude()) * d2r;
		double a = Math.pow(Math.sin(dlat / 2.0), 2)
				+ Math.cos(point1.getLatitude() * d2r)
				* Math.cos(point2.getLatitude() * d2r)
				* Math.pow(Math.sin(dlong / 2.0), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = 6367 * c;
		d = d * 1000;
		return d;
	}

	// *************** Hashcode and equals ***************
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LatLon other = (LatLon) obj;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		return true;
	}

}
