package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.Invader;
import ca.ubc.cpsc210.spaceinvaders.model.Missile;

public class TestInvader extends TestSprite {
	
	private Invader invdr;
	
	@Before
	public void runBefore() {
		sprite = new Invader(XLOC, YLOC);
		invdr = (Invader) sprite;
	}
	
	
	@Test
	public void testUpdate() {
		final int NUM_UPDATES = 8;
		
		sprite.move();
		// can't test XLOC due to random jiggle behaviour
		assertEquals(YLOC + Invader.DY, sprite.getY());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			sprite.move();
		}
		
		assertEquals(YLOC + NUM_UPDATES * Invader.DY, sprite.getY());
	}
	
	@Test 
	public void testCollideWith() {
		Missile m = new Missile(0, 0);
		assertFalse(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX(), sprite.getY());
		assertTrue(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2, invdr.getY());
		assertTrue(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX() + Invader.SIZE_X / 2 + Missile.SIZE_X / 2 + 1, invdr.getY());
		assertFalse(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2, invdr.getY());
		assertTrue(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX() - Invader.SIZE_X / 2 - Missile.SIZE_X / 2 - 1, invdr.getY());
		assertFalse(invdr.collidedWith(m));
		
		m = new Missile(invdr.getX(), invdr.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2);
		assertTrue(invdr.collidedWith(m));

		m = new Missile(invdr.getX(), invdr.getY() + Invader.SIZE_Y / 2 + Missile.SIZE_Y / 2 + 1);
		assertFalse(invdr.collidedWith(m));
	}
}
