package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.Color;
import java.awt.Rectangle;

/*
 * Represents a space invader.
 */
public class Invader {
	
	public static final int SIZE_X = 15;
	public static final int SIZE_Y = 9;
	public static final int DY = 1;
	public static final Color COLOR = new Color(10, 50, 188);
	private static final int JIGGLE_X = 1;

	private int x;
	private int y;
	
	// Constructs an invader
	// effects: invader is positioned at coordinates (x, y)
	public Invader(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	// Updates invader on clock tick
	// modifies: this
	// effects:  invader is moved down the screen DY units and randomly takes
	//           a step of size no greater than JIGGLE_X to the left or right
	public void move() {
		x = x + SIGame.RND.nextInt(2 * JIGGLE_X + 1) - JIGGLE_X;
		y = y + DY;
		
		handleBoundary();
	}
	
	// Determines if this invader has collided with a missile
	// modifies: none
	// effects:  returns true if this invader has collided with missile m,
	//           false otherwise
	public boolean collidedWith(Missile m) {
		Rectangle invaderBoundingRect = new Rectangle(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
		Rectangle missileBoundingRect = new Rectangle(m.getX() - Missile.SIZE_X / 2, m.getY() - Missile.SIZE_Y/ 2,
				Missile.SIZE_X, Missile.SIZE_Y);
		return invaderBoundingRect.intersects(missileBoundingRect);
	}
	
	// Constrains invader so that it doesn't travel off sides of screen
	// modifies: this
	// effects: tank is constrained to remain within horizontal boundaries of game
	private void handleBoundary() {
		if (x < 0)
			x = 0;
		else if (x > SIGame.WIDTH)
			x = SIGame.WIDTH;
	}
}
