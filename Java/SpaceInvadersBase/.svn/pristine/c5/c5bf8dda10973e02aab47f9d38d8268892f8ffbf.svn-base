package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Tank;

/*
 * Unit tests for the Tank class.
 */
public class TestTank {
	private static final int XLOC = SIGame.WIDTH / 2;
	private Tank t;
	
	@Before
	public void runBefore() {
		t = new Tank(XLOC);
	}
	
	@Test
	public void testGetX() {
		assertEquals(XLOC, t.getX());
	}
	
	@Test
	public void testUpdate() {
		final int NUM_UPDATES = 8;
		
		t.move();
		assertEquals(XLOC + Tank.DX, t.getX());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			t.move();
		}
		
		assertEquals(XLOC + NUM_UPDATES * Tank.DX, t.getX());
	}
	
	@Test
	public void testFlipDirection() {
		t.move();
		assertEquals(XLOC + Tank.DX, t.getX());
		t.faceLeft();
		t.move();
		assertEquals(XLOC, t.getX());
		t.faceRight();
		t.move();
		assertEquals(XLOC + Tank.DX, t.getX());
	}
	
	@Test 
	public void testLeftBoundary() {
		t.faceLeft();
		for(int count = 0; count < SIGame.WIDTH / 2 / Tank.DX + 1; count++)
			t.move();
		assertEquals(0, t.getX());
		t.move();
		assertEquals(0, t.getX());
	}
	
	@Test
	public void testRightBoundary() {
		t.faceRight();
		for(int count = 0; count < SIGame.WIDTH / 2 / Tank.DX + 1; count++)
			t.move();
		assertEquals(SIGame.WIDTH, t.getX());
		t.move();
		assertEquals(SIGame.WIDTH, t.getX());
	}
}
