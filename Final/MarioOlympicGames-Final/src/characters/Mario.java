package characters;

import sports.FiftyMetreDash;
import sports.PoolSport;
import sports.RunningSport;
import sports.Sport;

public class Mario extends SharedCharacterBehaviour implements Sport, RunningSport {

	/**
	 * Constructor
	 */
	public Mario() {
		addSport(new FiftyMetreDash());
		setName("Mario");
	}

	/**
	 * Set the name of the character
	 * REQUIRES: name is not null 
	 * MODIFIES: this
	 * EFFECTS: Mario's name becomes the given name
	 */
	public void setName(String name) {
		super.setName(name);
	}

	/**
	 * Run!
	 * EFFECTS: Writes information to the console
	 */
	public void performRun() {
		System.out.println("Mario runs.");
	}

	/**
	 * Return the speed at which the running occurs for this character
	 * EFFECTS: Returns a speed that is >= 10
	 */
	public int getSpeed() {
		return 10;
	}
	
	

}
