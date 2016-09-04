package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Represents an invader
public class Invader extends Sprite {
	
	public static final int DY = 1;
	public static final int SIZE_X = 15;
	public static final int SIZE_Y = 9;
	private static final Color COLOR = new Color(10, 50, 188);
	private static final int JIGGLE_X = 1;

	// Constructor
	// Effects: invader is at position (x, y)
	public Invader(int x, int y) {
		super(x, y, SIZE_X, SIZE_Y);
	}
	
	@Override
	public void draw(Graphics g) {
		Color savedCol = g.getColor();
		g.setColor(COLOR);
		g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
		g.setColor(savedCol);
	}
	
	@Override
	public void move() {
		x = x + SIGame.RND.nextInt(2 * JIGGLE_X + 1) - JIGGLE_X;
		y = y + DY;
		
		super.move();
	}
	
	// Has invader collided with another sprite?
	// Effects: returns true if this Invader has collided with other Sprite; false otherwise
	public boolean collidedWith(Sprite other) {
		Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
		Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2, other.getY() - other.getHeight() / 2,
				other.getWidth(), other.getHeight());
		return thisBoundingRect.intersects(otherBoundingRect);
	}
}
