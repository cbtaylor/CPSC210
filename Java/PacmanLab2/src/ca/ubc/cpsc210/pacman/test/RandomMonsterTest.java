package ca.ubc.cpsc210.pacman.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.pacman.model.Board;
import ca.ubc.cpsc210.pacman.model.RandomMonster;

public class RandomMonsterTest {
	
	Board b;
	RandomMonster m;
	
	@Before
	public void setup() {
		b = new Board();
		m = new RandomMonster(b, 0, 0);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.CYAN, m.getColor());
	}

	@Test
	public void testMakeMove() {
		// Random move method, not testable.
	}

	@Test
	public void testRandomMonster() {
		assertEquals(0, m.getX());
		assertEquals(0, m.getY());
	}

}
