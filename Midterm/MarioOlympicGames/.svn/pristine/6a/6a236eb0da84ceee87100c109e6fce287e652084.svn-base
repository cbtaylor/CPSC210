package characters;

import exceptions.FlyingException;
import exceptions.HeightException;

public class Birdo extends SharedCharacterBehaviour {
	
	private static int TOOSHORT = 5;
	protected int height;

	public Birdo() {		
		height = 0;
	}
	
	public void fly(int time) {
		// Make Birdo fly for given amount of time
		// But if time is too short, unable to fly and throw an exception
		if (time < TOOSHORT)
			throw new FlyingException();
	}
	
	public void fly(int time, int height) {
		// Make Birdo fly for a given amount of time at a given height
		if (height < 0)
			throw new HeightException();
		this.height = height;
		fly(time);
	}
	
}
