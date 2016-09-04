package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.Missile;


public class TestMissile extends TestSprite {

	@Before
	public void runBefore() {
		sprite = new Missile(XLOC, YLOC);
	}
	
	@Test
	public void testUpdate() {
		final int NUM_UPDATES = 8;
		
		sprite.move();
		assertEquals(XLOC, sprite.getX());
		assertEquals(YLOC + Missile.DY, sprite.getY());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			sprite.move();
		}
		
		assertEquals(XLOC, sprite.getX());
		assertEquals(YLOC + NUM_UPDATES * Missile.DY, sprite.getY());
	}
}
