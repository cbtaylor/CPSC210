package ca.ubc.cpsc210.pacman.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ubc.cpsc210.pacman.model.Board;
import ca.ubc.cpsc210.pacman.model.GridLocation;
import ca.ubc.cpsc210.pacman.model.Pacman;
import ca.ubc.cpsc210.pacman.model.RandomMonster;
import ca.ubc.cpsc210.pacman.model.TrackerMonster;

public class GridLocationTest {
	
	GridLocation wall = new GridLocation('W');
	GridLocation food = new GridLocation('D');
	GridLocation empty = new GridLocation('E');
	
	GridLocation pacman = new GridLocation('E', new Pacman(new Board()));

	@Test
	public void testIsPassable() {
		assertEquals(false, wall.isPassable());
		assertEquals(true,  food.isPassable());
		assertEquals(true,  empty.isPassable());
	}

	@Test
	public void testHasFood() {
		assertFalse(wall.hasFood());
		assertFalse(empty.hasFood());
		assertTrue(food.hasFood());
	}

	@Test
	public void testHasSprite() {
		assertFalse(wall.hasSprite());
		assertFalse(food.hasSprite());
		assertFalse(empty.hasSprite());
		assertTrue(pacman.hasSprite());
	}

	@Test
	public void testRemoveSprite() {
		assertTrue(pacman.hasSprite());
		pacman.removeSprite(pacman.getPacman());
		assertFalse(pacman.hasSprite());
	}

	@Test
	public void testAddSprite() {
		Board b = new Board();
		
		assertFalse(food.hasSprite());
		assertTrue(food.hasFood());
		
		food.addSprite(new RandomMonster(b, 1, 1));
		assertTrue(food.hasFood());
		
		food.addSprite(new TrackerMonster(b, 1, 1));
		assertTrue(food.hasFood());
		
		food.addSprite(new Pacman(b));
		assertTrue(food.hasSprite());
		assertFalse(food.hasFood());
	}

	@Test
	public void testGetSprite() {
		Pacman p2 = new Pacman(new Board());
		assertFalse(food.hasSprite());
		food.addSprite(p2);
		assertTrue(food.hasSprite());
		assertEquals(p2, food.getPacman());
	}

}
