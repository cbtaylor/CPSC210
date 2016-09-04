package ca.ubc.cpsc210.pacman.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.pacman.model.Board;
import ca.ubc.cpsc210.pacman.model.GridLocation;
import ca.ubc.cpsc210.pacman.model.Pacman;

public class BoardTest {
	
	Board b;

	@Before
	public void testBoard() {
		b = new Board();
	}

	@Test
	public void testGetBoardWidth() {
		assertEquals(13, b.getBoardWidth());
	}

	@Test
	public void testGetBoardHeight() {
		assertEquals(8, b.getBoardHeight());
	}

	@Test
	public void testGetLocation() {
		// This location should be a wall
		assertFalse(b.getLocation(0, 0).isPassable());
		
		// This location should have food
		assertTrue(b.getLocation(1,1).hasFood());
	}
	
	private boolean noSpritesInTheWall() {
		for (List<GridLocation> rows : b.getGridRows()) {
			for (GridLocation g : rows) {
				if (!g.isPassable() && g.hasSprite())
					return false;
			}
		}
		
		return true;
	}

	@Test
	public void testTickBoard() {
		// Make sure no monsters are in the wall
		while (!b.isGameOver()) {
			assertTrue(noSpritesInTheWall());
			b.tickBoard();
		}
	}

	@Test
	public void testIsGameOver() {
		// A brand new board shouldn't be game over
		assertFalse(b.isGameOver());
		
		Pacman p = b.getPacman();
		b.moveTo(p, 2, 1); // This puts pacman on top of a random monster
		assertTrue(b.isGameOver());
		
		b.moveTo(p, 1, 1); // Puts pacman off the monster
		assertFalse(b.isGameOver());
		
		// Consume all the food on the board
		for ( List<GridLocation> rows : b.getGridRows()) {
			for (GridLocation g : rows) {
				g.consumeFood();
			}
		}
		assertTrue(b.isGameOver());
		
		// Add a food piece back to (2,1)
		b.getGridRows().get(1).get(2).addFood();
		assertFalse(b.isGameOver());
	}

	
	@Test
	public void testCanMoveTo() {
		// TASK M1, complete this test method
		assertFalse(b.canMoveTo(13, 8));  //off the board
		assertFalse(b.canMoveTo(12, 9));  //off the board
		assertFalse(b.canMoveTo(-1, 0));  //off the board
		assertFalse(b.canMoveTo(0, -1));  //off the board
		assertFalse(b.canMoveTo(2, 2));   //a wall
		assertTrue(b.canMoveTo(5, 6));
		assertTrue(b.canMoveTo(7, 6));
	}

	@Test
	public void testMoveTo() {
		Pacman p = b.getPacman();
		
		int x = p.getX();
		int y = p.getY();
		
		assertTrue(b.canMoveTo(x+1, y));		
		b.moveTo(p, x+1, y);
		assertEquals(x+1, p.getX());
		assertEquals(y, p.getY());
		
		x = p.getX();
		y = p.getY();
				
		assertTrue(b.canMoveTo(x-1, y));
		b.moveTo(p, x-1, y);
		assertEquals(x-1, p.getX());
		assertEquals(y, p.getY());
		
		// Move pacman to the top left corner
		assertTrue(b.canMoveTo(1,1));
		b.moveTo(p, 1,1);

		assertEquals(1, p.getX());
		assertEquals(1, p.getY());
		
		x = p.getX();
		y = p.getY();
		
		assertTrue(b.canMoveTo(x,y+1));
		b.moveTo(p, x, y+1);
		assertEquals(x, p.getX());
		assertEquals(y+1, p.getY());

		assertFalse(b.canMoveTo(x, y-1));
	}

	@Test
	public void testGetPacman() {
		Pacman p = b.getPacman();
		
		assertTrue(p != null);
		
		// Ensure pacman is on the same board that the board says it is
		assertEquals(b, p.getBoard());
	}

}
