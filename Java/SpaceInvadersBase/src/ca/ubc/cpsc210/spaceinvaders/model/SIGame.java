package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Represents a space invaders game.
 */
public class SIGame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int MAX_MISSILES = 10;
	public static final Random RND = new Random();
	private static final int INVASION_PERIOD = 250;   // on average, one invader each 250 updates

	private List<Missile> missiles;
	private List<Invader> invaders;
	private Tank tank;
	private boolean isGameOver;
	private int numInvadersDestroyed;
	
	// Constructs a Space Invaders Game
	// effects:  creates empty lists of missiles and invaders, centres tank on screen
	public SIGame() {
		missiles = new ArrayList<Missile>();
		invaders = new ArrayList<Invader>();
		setUp();
	}
	
	// Updates the game on clock tick
	// modifies: this
	// effects:  updates tank, missiles and invaders
	public void update() {
		moveMissiles();
		moveInvaders();
		tank.move();
		
		checkMissiles();
		invade();
		checkCollisions();
		checkGameOver();
	}

	// Responds to key press codes
	// modifies: this
	// effects:  turns tank, fires missiles and resets game in response to 
	//           given key pressed code
	public void keyPressed(int keyCode) {
		if (keyCode == KeyEvent.VK_SPACE)
			fireMissile();
		else if (keyCode == KeyEvent.VK_R && isGameOver)
			setUp();
		else if (keyCode == KeyEvent.VK_X)
			System.exit(0);
		else
			tankControl(keyCode);
	}
	
	// Exercise: fill in the documentation for this method
	public boolean isOver() {
		return isGameOver;
	}
	
	public int getNumMissiles() {
		return missiles.size();
	}
	
	public int getNumInvadersDestroyed() {
		return numInvadersDestroyed;
	}
	
	public List<Invader> getInvaders() {
		return invaders;
	}
	
	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public Tank getTank() {
		return tank;
	}
	
	// Sets / resets the game
	// modifies: this
	// effects:  clears list of missiles and invaders, initializes tank
	private void setUp() {
		invaders.clear();
		missiles.clear();
		tank = new Tank(WIDTH / 2);
		isGameOver = false;
		numInvadersDestroyed = 0;
	}
	
	// Fires a missile
	// modifies: this
	// effects:  fires a missile if max number of missiles in play has
	//           not been exceeded, otherwise silently returns
	private void fireMissile() {
		if (missiles.size() < MAX_MISSILES) {
			Missile m = new Missile(tank.getX(), Tank.Y_POS);
			missiles.add(m);
		}
	}
	
	// Controls the tank
	// modifies: this
	// effects: turns tank in response to key code 
	private void tankControl(int keyCode) {
		if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
			tank.faceLeft();
		else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
			tank.faceRight();
	}
	
	// updates the missiles
	// modifies: this
	// effects: moves the missiles
	private void moveMissiles() {
		for (Missile next : missiles ) {
			next.move();
		}	
	}
	
	// updates the invaders
	// modifies: this
	// effects: moves the invaders
	private void moveInvaders() {
		for (Invader next : invaders) {
			next.move();
		}
	}
	
	// Check missiles
	// modifies: this
	// effects:  removes any missile that has traveled off top of screen
	private void checkMissiles() {
		List<Missile> missilesToRemove = new ArrayList<Missile>();
		
		for (Missile next : missiles) {
			if (next.getY() < 0) {
				missilesToRemove.add(next);
			}
		}
		
		missiles.removeAll(missilesToRemove);
	}
	
	// Exercise: add the documentation for this method
	private void invade() {
		if (RND.nextInt(INVASION_PERIOD) < 1) {
			Invader i = new Invader(RND.nextInt(SIGame.WIDTH), 0);
			invaders.add(i);
		}
	}
	
	// Checks for collisions between an invader and a missile
	// modifies: this
	// effects:  removes any invader that has been shot with a missile
	//           and removes corresponding missile from play
	private void checkCollisions() {
		List<Invader> invadersToRemove = new ArrayList<Invader>();
		List<Missile> missilesToRemove = new ArrayList<Missile>();
		
		for (Invader target : invaders) {
			if (checkInvaderHit(target, missilesToRemove)) {
				invadersToRemove.add(target);
			}
		}
		
		invaders.removeAll(invadersToRemove);
		missiles.removeAll(missilesToRemove);
	}
	
	// Exercise:  fill in the documentation for this method
	private boolean checkInvaderHit(Invader target, List<Missile> missilesToRemove) {
		for (Missile next : missiles) {
			if (target.collidedWith(next)) {
				missilesToRemove.add(next);
				numInvadersDestroyed++;
				return true;
			}
		}
		
		return false;
	}
	
	// Is game over? (Has an invader managed to land?)
	// modifies: this
	// effects:  if an invader has landed, game is marked as
	//           over and lists of invaders & missiles cleared
	private void checkGameOver() {
		for (Invader next : invaders) {
			if (next.getY() > HEIGHT) {
				isGameOver = true;
			}
		}
		
		if (isGameOver) {
			invaders.clear();
			missiles.clear();
		}
	}
}
