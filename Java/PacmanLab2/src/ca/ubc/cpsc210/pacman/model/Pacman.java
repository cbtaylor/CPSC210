package ca.ubc.cpsc210.pacman.model;

import java.awt.Color;

public class Pacman {
	
	Board board;
	int x_location;
	int y_location;
	protected char direction = 'L';

	// Requires: b is a valid board
	// Modifies: this
	// Effects:  remembers b
	public Pacman(Board b) {
		board = b;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects: returns the colour of pacman
	public Color getColor() {
		return Color.YELLOW;
	}

	// Requires: c is a valid direction (U, D, L, R)
	// Modifies: this
	// Effects: changes the direction heading of pacman
	public void setDirection(char c) {
		direction = c;
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
	
	// Requires: That this sprite can move to the next square in its current direction
	// Modifies: this
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
	
	public void makeMove() {
		moveInCurrentDirection();
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
