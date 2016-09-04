package ca.ubc.cpsc210.pacman.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.pacman.model.Board;
import ca.ubc.cpsc210.pacman.model.Pacman;
import ca.ubc.cpsc210.pacman.model.TrackerMonster;

public class TrackerMonsterTest {
	
	Board b;
	TrackerMonster m;
	
	@Before
	public void setup() {
		b = new Board();
		m = new TrackerMonster(b, 1, 1);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.GRAY, m.getColor());
	}

	@Test
	public void testTrackerMonster() {
		assertEquals(1, m.getX());
		assertEquals(1, m.getY());
	}
	
	@Test 
	public void makeMove() {
		Pacman p = b.getPacman();
		TrackerMonster mOnBoard = b.getTrackerMonsters().get(0);
		
		double smallestDistance;
		
		// The tracker monster should move closer to pacman on each step
		int dx = p.getX() - mOnBoard.getX();
		int dy = p.getY() - mOnBoard.getY();
		
		smallestDistance = Math.sqrt(dx*dx + dy*dy);
		
		while (!b.isGameOver()) {
			dx = p.getX() - mOnBoard.getX();
			dy = p.getY() - mOnBoard.getY();
			double currentDistance = Math.sqrt(dx*dx + dy*dy);
			
			if (currentDistance < smallestDistance)
				smallestDistance = currentDistance;
			// The tracker monster should never move too far away from pacman.  In the worst case
			// it can move at most 2 squares out from its smallest distance ever from pacman.  This
			// is the case where it gets stuck in a corner and moves directly opposite from pacman.
			// This holds because there is no where to get "stuck" in our default game board.
			assertTrue(currentDistance <= (smallestDistance + 2)); 
			
			b.tickBoard();
		}
	}

}
