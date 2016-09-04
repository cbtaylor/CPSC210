/**
 * A location service that reports the current Internet distance (latency in
 * milliseconds) to the 7 continents around the globe.
 */
public interface ILocationService {
	
	/* 
	 * EFFECTS: returns milliseconds between current location and continent c (or -1 if an invalid continent name was given.)
	 */
	int timeTo(String continent);

}
