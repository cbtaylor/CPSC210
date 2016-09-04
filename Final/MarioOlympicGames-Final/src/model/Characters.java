package model;

import java.util.LinkedList;
import java.util.List;

import characters.Character;

public class Characters {
	
	private static Characters theCharacters;
	private List<Character> allCharacters;
	
	public static Characters getInstance() {
		if (theCharacters == null) {
			theCharacters = new Characters();
		}
		return theCharacters;
	}
	
	protected Characters() {
		allCharacters = new LinkedList<Character>();
	}

}
