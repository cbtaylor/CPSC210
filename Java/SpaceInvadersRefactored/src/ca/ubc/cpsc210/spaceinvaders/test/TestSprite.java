package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Sprite;


public abstract class TestSprite {
	protected static final int XLOC = SIGame.WIDTH / 2;
	protected static final int YLOC = 50;
	protected Sprite sprite;
	
	@Test
	public void testGetX() {
		assertEquals(XLOC, sprite.getX());
	}	
}
