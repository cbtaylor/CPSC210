package sports;

import java.util.HashSet;
import java.util.Set;

import exceptions.GameException;

public class MedlaySwimRelay implements PoolSport {

	private String name;
	private Set<PoolSport> characters;
	
	public MedlaySwimRelay() {
		characters = new HashSet<PoolSport>();
	}

	public void addCharacter(PoolSport character) {
		characters.add(character);
	}

	public void swim() {
		try {
			for (PoolSport character: characters)
				character.swim();
		} catch (GameException ge) {
			for (PoolSport character: characters)
				character.swim();
		} finally {
			System.out.println("The swimmers are done or drowned.");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

}
