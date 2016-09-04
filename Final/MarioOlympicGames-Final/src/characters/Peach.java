package characters;

import sports.PoolSport;
import sports.ThrowingSport;
import exceptions.DrowningException;
import exceptions.ThrowingException;

public class Peach extends SharedCharacterBehaviour implements PoolSport, ThrowingSport {
	
	public Peach() {
		super("Peach");
	}

	public void swim() {
		throw new DrowningException();
	}
	
	public void setName(String name) {
		this.name = name + " is my name";
	}

	public void performThrow(int distance) throws ThrowingException {
		try {
			throw new ThrowingException("Peach can't throw.");
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
	}

}
