package ca.ubc.cpsc210.pacman.model;

import java.awt.Color;

public class TrackerMonster {

	Board board;
	int x_location;
	int y_location;
	protected char direction = 'R';
	
	// Requires: b is a valid board
	// Modifies: this
	// Effects:  remembers b
	public TrackerMonster(Board b) {
		board = b;
	}

	// Requires: b is a valid board and the position (x,y) is not a wall on b
	// Modifies: this
	// Effects:  remembers b, x and y
	public TrackerMonster(Board b, int x, int y) {
		this(b);
		x_location = x;
		y_location = y;
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the colour gray for a tracker monster
	public Color getColor() {
		return Color.gray;
	}
	
	// Requires: nothing
	// Modifies: this, board
	// Effects:  moves the tracker monster in the direction of pacman if possible
	public void makeMove() {
		char horiz_direction = getHorizontalDirectionToPacman();
		char vert_direction = getVerticalDirectionToPacman();
		
		int horiz_distance = Math.abs(getX() - board.getPacman().getX());
		int vert_distance  = Math.abs(getY() - board.getPacman().getY());
		
		direction = horiz_direction;
		boolean canMoveHoriz = canMakeMove();
		
		direction = vert_direction;
		boolean canMoveVert = canMakeMove();
		
		if (horiz_distance > vert_distance && canMoveHoriz) {
			direction = horiz_direction;
		} else if (vert_distance > horiz_distance && canMoveVert) {
			direction = vert_direction;
		} else if (canMoveHoriz) {
			direction = horiz_direction;
		} else if (canMoveVert) {
			direction = vert_direction;
		} else {
			// Can't move in the direction of pacman, so choose a random direction to keep moving.
			do {
				double random = Math.random();
				if (random < 0.25) direction = 'L';
				else if (random < 0.5) direction = 'U';
				else if (random < 0.75) direction = 'R';
				else direction = 'D';
			} while (!canMakeMove());
		}
		
		moveInCurrentDirection();
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the vertical direction to pacman on the current board
	private char getVerticalDirectionToPacman() {
		Pacman pacman = board.getPacman();
		int dy = y_location - pacman.getY();

		if (dy < 0) {
			return 'U';
		} else {
			return 'D';
		}
	}

	// Requires: nothing
	// Modifies: nothing
	// Effects:  returns the horizontal direction to pacman on the current board
	private char getHorizontalDirectionToPacman() {
		Pacman pacman = board.getPacman();
		int dx = x_location - pacman.getX();

		if (dx < 0) {
			return 'L';
		} else {
			return 'R';
		}
	}


	// Requires: position (x,y) is a valid position in the remembered board
	// Modifies: this
	// Effects:  remembers new position (x,y) 
	public void setLocation(int x, int y) {
		x_location = x;
		y_location = y;
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
