package ca.ubc.cpsc210.pacman.model;

import java.util.LinkedList;
import java.util.List;

public class GridLocation {
	
	private List<TrackerMonster> trackerMonsterSprites = new LinkedList<TrackerMonster>();
	private List<RandomMonster> randomMonsterSprites = new LinkedList<RandomMonster>();
	private Pacman pacman = null;
	//private Sprite sprite = null;
	private boolean hasFood = false;
	private boolean isWall = false;
	
	// Requires: type is a valid grid location type (W, D, or E)
	// Modifies: this
	// Effects: sets up this as the proper type, a Wall, Food Location, or Empty
	public GridLocation(char type) {
		switch (type) {
		case 'W':
			isWall = true; break;
		case 'D':
			addFood(); break;
		case 'E': 
			break;
		default:
			throw new Error("Unknown grid location type.");
		}
	}
	
	// Requires: a valid grid type type, and a Pacman s
	// Modifies: this
	// Effects:  remembers s, and sets up this location given by the type type
	public GridLocation(char type, Pacman s) {
		this(type);
		addSprite(s);
	}
	
	// Requires: a valid grid type type, and a TrackerMonster s
	// Modifies: this
	// Effects:  remembers s, and sets up this location given by the type type
	public GridLocation(char type, TrackerMonster s) {
		this(type);
		addSprite(s);
	}
	
	// Requires: a valid grid type type, and a RandomMonster s
	// Modifies: this
	// Effects:  remembers s, and sets up this location given by the type type
	public GridLocation(char type, RandomMonster s) {
		this(type);
		addSprite(s);
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns false if this location is a wall
	public boolean isPassable() {
		return !isWall;
	}
	
	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns true if this location has food
	public boolean hasFood() {
		return hasFood;
	}
	
	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns true if this location has a TrackerMonster, RandomMonster or Pacman in it
	public boolean hasSprite() {
		return !randomMonsterSprites.isEmpty() || !trackerMonsterSprites.isEmpty() || pacman != null;
	}
	
	// Requires: nothing
	// Modifies: this
	// Effects:  removes s as a RandomMonster of this location if it was here in the first place
	public void removeSprite(RandomMonster s) {
		randomMonsterSprites.remove(s);	
	}
	
	// Requires: s is a valid RandomMonster on the board
	// Modifies: this
	// Effects:  adds s to this location
	public void addSprite(RandomMonster s) {
		randomMonsterSprites.add(s);
		
		// Sprite eats the food
		consumeFood();
	}
	
	// Requires: nothing
	// Modifies: this
	// Effects:  removes s from this location
	public void removeSprite(TrackerMonster s) {
		trackerMonsterSprites.remove(s);	
	}
	
	// Requires: s is a valid sprite on the board
	// Modifies: this
	// Effects:  adds s to this location
	public void addSprite(TrackerMonster s) {
		trackerMonsterSprites.add(s);
		
		// Sprite eats the food
		consumeFood();
	}
	
	// Requires: nothing
	// Modifies: this
	// Effects:  removes s from this location
	public void removeSprite(Pacman s) {
		pacman = null;
	}
	
	// Requires: s is a valid sprite on the board
	// Modifies: this
	// Effects:  adds s to this location and consumes the food since s is Pacman
	public void addSprite(Pacman s) {
		pacman = s;
		
		// Sprite eats the food
		consumeFood();
	}

	// Requires: nothing 
	// Modifies: nothing
	// Effects:  returns Pacman (if any) at this grid location (null if none)
	public Pacman getPacman() {
		return pacman;
	}
	
	// Requires: nothing 
	// Modifies: nothing
	// Effects:  returns the top sprite of RandomMonsters (if any) at this grid location (null if none)
	public RandomMonster getRandomMonster() {
		return randomMonsterSprites.isEmpty() ? null : randomMonsterSprites.get(0);
	}

	
	// Requires: nothing 
	// Modifies: nothing
	// Effects:  returns the top sprite of TrackerMonsters (if any) at this grid location (null if none)
	public TrackerMonster getTrackerMonster() {
		return trackerMonsterSprites.isEmpty() ? null : trackerMonsterSprites.get(0);
	}


	public void consumeFood() {
		hasFood = false;
	}

	public void addFood() {
		hasFood = true;		
	}
}
