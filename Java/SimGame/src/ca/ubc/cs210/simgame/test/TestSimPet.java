package ca.ubc.cs210.simgame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.simgame.SimPet;

public class TestSimPet {
	
	private SimPet sp;

	@Before
	public void setUp() {
		sp = new SimPet(0, 0, 10);
	}

	@Test
	public void testConstructor() {
		assertEquals(0, sp.getX());
		assertEquals(0, sp.getY());
		assertEquals(10, sp.getEnergy());
		assertTrue(sp.isAlive());
		assertFalse(sp.getHasHadShots());
	}
	
	@Test
	public void testRotateLeft() {
		sp.rotateLeft();
		assertEquals(1, sp.getDirection());
		sp.rotateLeft();
		assertEquals(2, sp.getDirection());
		sp.rotateLeft();
		assertEquals(3, sp.getDirection());
		sp.rotateLeft();
		assertEquals(0, sp.getDirection());
	}
	
	@Test
	public void testRotateRight() {
		sp.rotateRight();
		assertEquals(3, sp.getDirection());
		sp.rotateRight();
		assertEquals(2, sp.getDirection());
		sp.rotateRight();
		assertEquals(1, sp.getDirection());
		sp.rotateRight();
		assertEquals(0, sp.getDirection());
	}
	
	@Test
	public void testMoveMaxSteps() {
		for (int count = 0; count < 9; count++) {
			sp.move();
		}
		
		assertEquals(1, sp.getEnergy());
		assertTrue(sp.isAlive());
		assertEquals(9, sp.getX());
		assertEquals(0, sp.getY());
	}

	@Test
	public void testMoveAndDie() {
		for (int count = 0; count < 10; count++) {
			sp.move();
		}
		
		assertEquals(0, sp.getEnergy());
		assertFalse(sp.isAlive());
		assertEquals(10, sp.getX());
		assertEquals(0, sp.getY());
	}
	
	@Test
	public void testMoveFurtherAndDie() {
		for (int count = 0; count < 20; count++) {
			sp.move();
		}
		
		assertEquals(0, sp.getEnergy());
		assertFalse(sp.isAlive());
		assertEquals(10, sp.getX());
		assertEquals(0, sp.getY());
	}
	
	@Test
	public void testMoveEast() {
		sp.move();
		assertEquals(1, sp.getX());
		assertEquals(0, sp.getY());
	}
	
	@Test
	public void testMoveNorth() {
		sp.rotateLeft();
		sp.move();
		assertEquals(0, sp.getX());
		assertEquals(1, sp.getY());
	}
	
	@Test
	public void testMoveWest() {
		sp.rotateLeft();
		sp.rotateLeft();
		sp.move();
		assertEquals(-1, sp.getX());
		assertEquals(0, sp.getY());
	}
	
	@Test
	public void testMoveSouth() {
		sp.rotateRight();
		sp.move();
		assertEquals(0, sp.getX());
		assertEquals(-1, sp.getY());
	}
}
