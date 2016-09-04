package characters;

import exceptions.FlyingException;
import exceptions.HeightException;

public class Birdo extends SharedCharacterBehaviour {
	
	private static int TOOSHORT = 3;
	protected int height;
	
	/**
	 * Fly!
	 * @param time The amount of time to be in the air
	 * REQUIRES: time > 0
	 * EFFECTS: May throw a FlyingException if time < TOOSHORT
	 */
	public void fly(int time) {
		if (time < TOOSHORT)
			throw new FlyingException();
	}
	
	/**
	 * Fly!
	 * @param time The amount of time to be in the air
	 * @param height The height at which to reach when flying
	 * REQUIRES: time > 0 
	 * EFFECTS: May throw a HeightException
	 */
	public void fly(int time, int height) {
		if (height < 0)
			throw new HeightException();
		fly(time-1);
		fly(time-1, height-1);
	}
	
}
