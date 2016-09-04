package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.Color;

public class Tank {
	
	public static final int SIZE_X = 15;
	public static final int SIZE_Y = 8;
	public static final int DX = 2;
	public static final int Y_POS = SIGame.HEIGHT - 40;
	public static final Color COLOR = new Color(250, 128, 20);

	private int direction;
	private int x;
	
	// Construct a tank.
	// effects: places tank at position (x, Y_POS) moving right.
	public Tank(int x) {
		this.x = x;
		direction = 1;
	}
	
	public int getX() {
		return x;
	}
	
	// Faces tank to the right
	// modifies: this
	// effects: tank is facing right
	public void faceRight() {
		direction = 1;
	}
	
	// Faces tank to the left
	// modifies: this
	// effects: tank is facing left
	public void faceLeft() {
		direction = -1;
	}
	
	// Updates the tank on clock tick
	// modifies: this
	// effects:  tank is moved DX units in whatever direction it is facing and is
	//           constrained to remain within vertical boundaries of game
	public void move() {
		x = x + direction * DX;
		
		handleBoundary();
	}
	
	// Constrains tank so that it doesn't travel of sides of screen
	// modifies: this
	// effects: tank is constrained to remain within vertical boundaries of game
	private void handleBoundary() {
		if (x < 0)
			x = 0;
		else if (x > SIGame.WIDTH)
			x = SIGame.WIDTH;
	}
}
