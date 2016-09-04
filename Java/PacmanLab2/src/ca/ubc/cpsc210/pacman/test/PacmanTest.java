package ca.ubc.cpsc210.pacman.test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.pacman.model.Pacman;

public class PacmanTest {
	
	Pacman s;
	
	@Before
	public void runBefore() {
		s = new Pacman(null);
	}
	
	@Test
	public void testColor() {
		assertEquals(Color.YELLOW, s.getColor());
	}
	
	@Test
	public void testSetDirection() {
		((Pacman)s).setDirection('L');
		assertEquals('L', s.getDirection());
		
		((Pacman)s).setDirection('R');
		assertEquals('R', s.getDirection());
	}
}
