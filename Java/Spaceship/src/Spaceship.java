
/**
 * A spaceship in 2D space that moves within a rectangle with top-left corner 
 * at (0, 0) and bottom-right corner at (500, 500)
 * 
 * @author CPSC 210 Instructor
 * @version Version 1.0 August 2013
 */
public class Spaceship {
	
	// The x,y coordinates of the spaceship as integers
	private int x;
	private int y;
	
	/**
	 * Construct a new spaceship object and place it at (250, 250)
	 */
	public Spaceship() {
		x = 250; // This statement sets the x field of the object to the value 250
		y = 250; // This statement sets the y field of the object to the value 250
	}
	
	/**
	 * Move the spaceship within the rectangle. Never go outside of the rectangle.
	 * @param deltaX The increment for the x direction, can be a negative value to move left
	 * @param deltaY The increment for the y direction, can be a negative value to move up
	 */
	public void move(int deltaX, int deltaY) {
		x = x + deltaX; // Increment x
		if(x > 500)
            x = 500;
        if(x < 0)
            x = 0;
		y = y + deltaY;
		if(y > 500)
            y = 500;
        if(y < 0)
            y = 0;
	}
	
	/*
	 * Return the current x coordinate value of the spaceship
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * Return the current y coordinate value of the spaceship
	 */
	public int getY() { 
		return y;
	}

}
