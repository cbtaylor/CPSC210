package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


// Represents a space invaders game
public class SIGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final Random RND = new Random();
	public static final int MAX_MISSILES = 10;
	
	private List<Sprite> sprites;
	private Tank tank;
	private boolean isGameOver;
	private int numMissilesRemaining;
	private int numInvadersDestroyed;
	
	// Constructor
	// Effects: sets up the game
	public SIGame() {
		sprites = new ArrayList<Sprite>();
		setUp();
	}
	
	// Updates the game on clock tick
	// modifies: this
	// effects:  updates tank, missiles and invaders
	public void update() {
		moveSprites();
		checkMissiles();
		invade();
		checkCollisions();
		checkGameOver();
	}


	// Responds to key press codes
	// modifies: this
	// effects:  turns tank, fires missiles and resets game in response to 
	//           given key pressed code
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT)
			tank.faceLeft();
		else if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			tank.faceRight();
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
			fireMissile();
		else if (e.getKeyCode() == KeyEvent.VK_R && isGameOver)
			setUp();
		else if (e.getKeyCode() == KeyEvent.VK_X)
			System.exit(0);
	}
	
	public void draw(Graphics g) {
		for (Sprite aSprite: sprites)
			aSprite.draw(g);
	}
	
	// Is game over?
	// Effects: returns true if game is over, false otherwise
	public boolean isOver() {
		return isGameOver;
	}
	
	public int getNumMissiles() {
		return numMissilesRemaining;
	}
	
	public int getNumInvadersDestroyed() {
		return numInvadersDestroyed;
	}
	
	// moves the sprites
	// modifies: this
	// effects: moves sprites to location at next time
	private void moveSprites() {
		for (Sprite next : sprites) {
			next.move();
		}	
	}
	
	// Sets / resets the game
	// modifies: this
	// effects:  clears list of missiles and invaders, initializes tank
	private void setUp() {
		sprites.clear();
		tank = new Tank(WIDTH / 2);
		sprites.add(tank);
		isGameOver = false;
		numMissilesRemaining = 0;
		numInvadersDestroyed = 0;
	}

	
	// Fires a missile
	// modifies: this
	// effects:  fires a missile if max number of missiles in play has
	//           not been exceeded, otherwise silently returns
	private void fireMissile() {
		if (numMissilesRemaining < MAX_MISSILES) {
			Missile m = new Missile(tank.getX(), tank.getY());
			sprites.add(m);
			numMissilesRemaining++;
		}
	}
	
	// Check missiles
	// modifies: this
	// effects:  removes any missile that has traveled off top of screen
	private void checkMissiles() {
		Iterator<Sprite> itr = sprites.iterator();
		
		while (itr.hasNext()) {
			Sprite next = itr.next();
			if (next.getY() < 0) {
				itr.remove();
				numMissilesRemaining--;
			}
		}
	}
	
	// Invade!
	// modifies: this
	// effects: randomly generates new invader at top of screen with random x coordinate.
	private void invade() {
		if (RND.nextInt(250) < 1) {
			Invader i = new Invader(RND.nextInt(WIDTH), 20);
			sprites.add(i);
		}
	}
	
	// Checks for collisions between an invader and a missile
	// modifies: this
	// effects:  removes any invader that has been shot with a missile
	//           and removes corresponding missile from play
	private void checkCollisions() {
		List<Sprite> toBeRemoved = new ArrayList<Sprite>();
		for (Sprite next : sprites) {
			if (next instanceof Invader) {
				checkInvaderHit((Invader) next, toBeRemoved);
			}
		}
		
		sprites.removeAll(toBeRemoved);
	}
	
	// Has a given invader been hit by a missile?
	// modifies: this, missilesToRemove
	// effects: if target has been hit by a missile, removes target and missile from play;
	// increments number of invaders destroyed.
	private void checkInvaderHit(Invader target, List<Sprite> missilesToRemove) {
		for (Sprite next : sprites) {
			if (next instanceof Missile) {
				if (target.collidedWith(next)) {
					missilesToRemove.add(target);
					missilesToRemove.add(next);
					numMissilesRemaining--;
					numInvadersDestroyed++;
				}
			}
		}
	}
	
	// Is game over? (Has an invader managed to land?)
	// modifies: this
	// effects:  if an invader has landed, game is marked as
	//           over and lists of invaders & missiles cleared
	private void checkGameOver() {
		for (Sprite next : sprites) {
			if (next.getY() > HEIGHT)
				isGameOver = true;
		}
		
		if (isGameOver)
			sprites.clear();
	}
}
