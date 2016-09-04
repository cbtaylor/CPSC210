package characters;

import sports.ThrowingSport;
import exceptions.ThrowingException;

public class Bowser extends SharedCharacterBehaviour implements Power, ThrowingSport {
	
	public Bowser() {
		super("Bowser");
	}

	public boolean canRun() {
		return false;
	}
	
	@Override
	public void performThrow(int distance) throws ThrowingException {
		// The % operator is the mod operator, i.e., the remainder of a division
		if (distance % 2 == 0)
			throw new ThrowingException("Bowser can't throw even distances!");
	}
	
	/**
	 * Compute the power of a bowser
	 * @param powerBase The base power value
	 * @return Bowser's power!
	 * 
	 * REQUIRES: powerBase > 20
	 * MODIFIES: Nothing
	 * EFFECTS: Returns a power value that is > 100
	 */
	public double computeBowserPower(int powerBase) {
		return powerBase * 6;
	}
}
