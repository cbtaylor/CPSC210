package ca.ubc.cpsc210.spaceinvaders.test;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Invader;
import ca.ubc.cpsc210.spaceinvaders.model.Missile;
import ca.ubc.cpsc210.spaceinvaders.model.Tank;

/*
 * Unit tests for the Game class.
 */
public class TestGame {
	private SIGame g;
	
	@Before
	public void runBefore() {
		g = new SIGame();
	}
	
	@Test
	public void testInit() {
		Tank t = g.getTank();
		assertEquals(SIGame.WIDTH / 2, t.getX());
		List<Invader> invaders = g.getInvaders();
		assertEquals(0, invaders.size());
		List<Missile> missiles = g.getMissiles();
		assertEquals(0, missiles.size());
	}
	
	@Test
	public void testUpdate() {
		Tank t = g.getTank();
		assertEquals(SIGame.WIDTH / 2, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2 + Tank.DX, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2 + 2 * Tank.DX, t.getX());
		assertEquals(0, g.getMissiles().size());
	}
	
	@Test
	public void testNonKeyPadKeyEvent() {
		Tank t = g.getTank();
		g.keyPressed(KeyEvent.VK_LEFT);
		g.update();
		assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2 - 2 * Tank.DX, t.getX());
		g.keyPressed(KeyEvent.VK_RIGHT);
		g.update();
		assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2, t.getX());		
	}
	
	@Test
	public void testKeyPadKeyEvent() {
		Tank t = g.getTank();
		g.keyPressed(KeyEvent.VK_KP_LEFT);
		g.update();
		assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2 - 2 * Tank.DX, t.getX());
		g.keyPressed(KeyEvent.VK_KP_RIGHT);
		g.update();
		assertEquals(SIGame.WIDTH / 2 - Tank.DX, t.getX());
		g.update();
		assertEquals(SIGame.WIDTH / 2, t.getX());		
	}
	
	@Test
	public void testSpaceKeyEvent() {
		g.keyPressed(KeyEvent.VK_SPACE);
		assertEquals(1, g.getMissiles().size());
		g.keyPressed(KeyEvent.VK_SPACE);
		g.keyPressed(KeyEvent.VK_SPACE);
		assertEquals(3, g.getMissiles().size());
	}
}
