package ca.ubc.cpsc210.pacman.model;

import java.awt.Color;

public class RandomMonster {

	Board board;
	int x_location;
	int y_location;
	protected char direction = 'R';
	
	// Requires: b is a valid board
	// Modifies: this
	// Effects:  remembers b
	public RandomMonster(Board b) {
		board = b;
	}

	// Requires: b is a valid board and the position (x,y) is not a wall on b
	// Modifies: this
	// Effects:  remembers b, x and y
	public RandomMonster(Board b, int x, int y) {
		this(b);
		x_location = x;
		y_location = y;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the CYAN colour for a random moving monster
	public Color getColor() {
		return Color.CYAN;
	}

	// Requires: That this sprite can move to the next square in its current direction
	// Modifies: this, board
	// Effects:  this location is updated based on its current heading, and board is updated to reflect the move
	public void moveInCurrentDirection() {
		int new_x = x_location;
		int new_y = y_location;
		
		switch (direction) {
		case 'S': break;
		case 'U':
			new_y = new_y - 1; break;
		case 'D':
			new_y = new_y + 1; break;
		case 'L':
			new_x = new_x - 1; break;
		case 'R':
			new_x = new_x + 1; break;
		default:
			throw new Error("Unknown direction: " + direction);
		}
		
		if (board.canMoveTo(new_x, new_y)) {
			board.moveTo(this, new_x, new_y);
		}
	}
	
	// Requires: nothing
	// Modifies: this, board
	// Effects:  moves this sprite in a random direction on the board
	public void makeMove() {
		
		do {
			double random = Math.random();
			if (random < 0.25) direction = 'L';
			else if (random < 0.5) direction = 'U';
			else if (random < 0.75) direction = 'R';
			else direction = 'D';
		} while (!canMakeMove());
		
		moveInCurrentDirection();
	}

	// Requires: position (x,y) is a valid position in the remembered board
	// Modifies: this
	// Effects:  remembers new position (x,y) 
	public void setLocation(int x, int y) {
		x_location = x;
		y_location = y;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns true if this sprite can move to the next square in its current heading
	public boolean canMakeMove() {
		int new_x = x_location;
		int new_y = y_location;
		
		switch (direction) {
		case 'S': break;
		case 'U':
			new_y = new_y - 1; break;
		case 'D':
			new_y = new_y + 1; break;
		case 'L':
			new_x = new_x - 1; break;
		case 'R':
			new_x = new_x + 1; break;
		default:
			throw new Error("Unknown direction: " + direction);
		}
		
		return board.canMoveTo(new_x, new_y);
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the x position of this sprite
	public int getX() {
		return x_location;
	}

	// Requires: nothing
	// Modifies: nothing 
	// Effects:  returns the y position of this sprite
	public int getY() {
		return y_location;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the current heading of this sprite
	public char getDirection() {
		return direction;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the board that this sprite is active on
	public Board getBoard() {
		return board;
	}
}
