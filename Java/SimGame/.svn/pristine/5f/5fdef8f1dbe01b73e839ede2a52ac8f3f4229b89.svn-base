package ca.ubc.cpsc210.simgame;

public class SimPet {
	private static final int ENERGY_TO_MOVE = 1;
	
	private boolean hasHadShots;
	private int energy;
	private int x;
	private int y;
	private int direction;  // 0 - East positive x
							// 1 - North positive y
							// 2 - West negative x
							// 3 - South negative y
	
	// constructor
	// EFFECTS: SimPet has initialEnergy and is at position (x, y) 
	// facing east and has not had its shots
	public SimPet(int x, int y, int initialEnergy) {

	}
	
	// is pet alive?
	// EFFECTS: returns true if this SimPet is alive, false otherwise
	public boolean isAlive() {
		return energy > 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	// EFFECTS: returns direction of this SimPet (0 - East, 1 - North,
	// 2 - West, 3 - South)
	public int getDirection() {
		return direction;
	}
	
	public boolean getHasHadShots() {
		return hasHadShots;
	}

	// move
	// MODIFIES: this
	// EFFECTS: moves this SimPet one unit in current direction, if SimPet is alive
	public void move() {

	}
	
	// feed
	// MODIFIES: this
	// EFFECTS: adds foodEnergy to this SimPet's energy, if SimPet is alive AND foodEnergy > 0
	public void feed(int foodEnergy) {
		if (isAlive() && foodEnergy > 0) 
			energy += foodEnergy;
	}

	// rotate left
	// MODIFIES: this
	// EFFECTS: this SimPet rotates left 90 degrees
	public void rotateLeft() {
		direction = (direction + 1) % 4;
	}
	
	// rotate right
	// MODIFIES: this
	// EFFECTS: this SimPet rotates right 90 degrees
	public void rotateRight() {
		direction--;
		if (direction < 0)
			direction = 3;
	}
}
