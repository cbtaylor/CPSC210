package sports;

import java.util.List;

import characters.Birdo;
import characters.Peach;
import exceptions.FlyingException;
import exceptions.GameException;

public class FiftyMetreDash implements Sport, RunningSport {

	private String name;
	private List<Birdo> birdoCharacters;

	public void addBirdos(Birdo b) {
		birdoCharacters.add(b);
	}

	public void performRun() {
		try {
			for (Birdo b : birdoCharacters)
				b.fly(3);
		} catch (FlyingException fe) {
			System.out.println("A Birdo crashed!");
		} catch (GameException ge) {
			System.out.println("The game has problems.");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

}
