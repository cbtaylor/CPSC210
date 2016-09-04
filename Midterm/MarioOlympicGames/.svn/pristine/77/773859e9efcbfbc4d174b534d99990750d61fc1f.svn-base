package sports;

import characters.Birdo;
import characters.Luigi;
import characters.Mario;
import characters.Peach;
import exceptions.GameException;

public class MedlaySwimRelay implements Sport, PoolSport {

	private String name;
	private Peach peach;
	private Luigi luigi;
	private Mario mario;
	private Birdo birdo;

	public void addPeach(Peach p) {
		peach = p;
	}

	public void getWet() {
		try {
			peach.swim();
		} catch (GameException ge) {
			System.out.println("Get the lifeguard, peach is drowning!");
			// Make peach try again!
			peach.swim();
		} finally {
			System.out.println("Don't let peach swim");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

}
