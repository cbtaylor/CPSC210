package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.Missile;
import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Invader;

/*
 * Unit tests for the Invader class.
 */
public class TestInvader {
	private static final int XLOC = SIGame.WIDTH / 2;
	private static final int YLOC = 50;
	private Invader i;
	
	@Before
	public void runBefore() {
		i = new Invader(XLOC, YLOC);
	}
	
	@Test
	public void testGetX() {
		assertEquals(XLOC, i.getX());
	}
	
	@Test
	public void testGetY() {
		assertEquals(YLOC, i.getY());
	}
	
	@Test
	public void testUpdate() {
		final int NUM_UPDATES = 8;
		
		i.move();
		// can't test XLOC due to random jiggle behaviour
		assertEquals(YLOC + Invader.DY, i.getY());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			i.move();
		}
		
		assertEquals(YLOC + NUM_UPDATES * Invader.DY, i.getY());
	}
	
	@Test 
	public void testCollideWith() {
		Missile m = new Missile(0, 0);
		assertFalse(i.collidedWith(m));
		
		m = new Missile(i.getX(), i.getY());
		assertTrue(i.collidedWith(m));
		
		m = new Missile(i.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2, i.getY());
		assertTrue(i.collidedWith(m));
		
		m = new Missile(i.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2 + 1, i.getY());
		assertFalse(i.collidedWith(m));
		
		m = new Missile(i.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2, i.getY());
		assertTrue(i.collidedWith(m));
		
		m = new Missile(i.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2 - 1, i.getY());
		assertFalse(i.collidedWith(m));
		
		m = new Missile(i.getX(), i.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2);
		assertTrue(i.collidedWith(m));

		m = new Missile(i.getX(), i.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2 + 1);
		assertFalse(i.collidedWith(m));
	}
	
}
