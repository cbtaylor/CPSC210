package characters;

import java.util.ArrayList;
import java.util.List;

import sports.Sport;

public class Mario extends SharedCharacterBehaviour {

	private List<Character> friends;
	private List<Sport> bestSports;
	private String myName;

	public Mario(List<Sport> bestSports) {
		friends = new ArrayList<Character>();
		this.bestSports = bestSports;
		myName = "";
	}

	public void addFriend(Character c) {
		friends.add(c);
	}
	
	public void setName(String name) {
		myName = name;
		super.setName(name);
	}

}
