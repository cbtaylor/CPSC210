


/**
 * A class that uses a LocationService to determine the current continent ---
 * via the whichContinent method.
 */
public class WhereAmI {

    /*
     * This method queries locator for Internet distances to the 7 continents
     * around the globe and returns the current continent name as a string.
     * 
     * EFFECTS: returns the current continent name as a string; one of the following
     *          seven strings: "North America", "South America", "Europe",
     *          "Asia", "Africa", "Australia", "Antarctica"
     */
    public String whichContinent(ILocationService locator) {
    		// Replace this stub with a full implementation of the method!
    	String continent;
    	continent = "Unknown Continent";
    	int time = Integer.MAX_VALUE;
    	int timeToNA = locator.timeTo(Continent.N_AMERICA);
    	if (timeToNA < time) {
    		time = timeToNA;
    		continent = "North America";
    	}
    	int timeToSA = locator.timeTo(Continent.S_AMERICA);
    	if (timeToSA < time) {
    		time = timeToSA;
    		continent = "South America";
    	}
    	int timeToEU = locator.timeTo(Continent.EUROPE);
    	if (timeToEU < time) {
    		time = timeToEU;
    		continent = "Europe";
    	}
    	int timeToAS = locator.timeTo(Continent.ASIA);
    	if (timeToAS < time) {
    		time = timeToAS;
    		continent = "Asia";
    	}
    	int timeToAF = locator.timeTo(Continent.AFRICA);
    	if (timeToAF < time) {
    		time = timeToAF;
    		continent = "Africa";
    	}
    	int timeToAU = locator.timeTo(Continent.AUSTRALIA);
    	if (timeToAU < time) {
    		time = timeToAU;
    		continent = "Australia";
    	}
    	int timeToAR = locator.timeTo(Continent.ANTARCTICA);
    	if (timeToAR < time) {
    		time = timeToAR;
    		continent = "Antarctica";
    	}
    	return continent;
    }

}
