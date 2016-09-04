package ca.ubc.cpsc210.spaceinvaders.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Invader;
import ca.ubc.cpsc210.spaceinvaders.model.Missile;
import ca.ubc.cpsc210.spaceinvaders.model.Tank;

/*
 * The panel in which the game is rendered.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private static final String OVER = "Game Over!";
	private static final String REPLAY = "R to replay";
	private SIGame game;

	// Constructs a game panel
	// effects:  sets size and background colour of panel, 
	//           updates this with the game to be displayed
	public GamePanel(SIGame g) {
		setPreferredSize(new Dimension(SIGame.WIDTH, SIGame.HEIGHT));
		setBackground(Color.GRAY);
		this.game = g;
	}
	
	@Override
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		drawGame(g);
		
		if (game.isOver()) {
			gameOver(g);
		}
	}
	
	// Draws the game
	// modifies: g
	// effects:  draws the game onto g
	private void drawGame(Graphics g) {
		drawTank(g);
		drawInvaders(g);
		drawMissiles(g);
	}
	
	// Draw the tank
	// modifies: g
	// effects:  draws the tank onto g
	private void drawTank(Graphics g) {
		Tank t = game.getTank();
		Color savedCol = g.getColor();
		g.setColor(Tank.COLOR);
		g.fillRect(t.getX() - Tank.SIZE_X / 2, Tank.Y_POS - Tank.SIZE_Y / 2, Tank.SIZE_X, Tank.SIZE_Y);
		g.setColor(savedCol);
	}
	
	// Draw the invaders
	// modifies: g
	// effects:  draws the invaders onto g
	private void drawInvaders(Graphics g) {
		for(Invader next : game.getInvaders()) {
			drawInvader(g, next);
		}
	}
	
	// Draw an invader
	// modifies: g
	// effects:  draws the invader i onto g
	private void drawInvader(Graphics g, Invader i) {
		Color savedCol = g.getColor();
		g.setColor(Invader.COLOR);
		g.fillOval(i.getX() - Invader.SIZE_X / 2, i.getY() - Invader.SIZE_Y / 2, Invader.SIZE_X, Invader.SIZE_Y);
		g.setColor(savedCol);
	}
	
	// Draws the missiles
	// modifies: g
	// effects:  draws the missiles onto g
	private void drawMissiles(Graphics g) {
		for(Missile next : game.getMissiles()) {
			drawMissile(g, next);
		}
	}
	
	// Draws a missile
	// modifies: g
	// effects:  draws the missile m onto g
	private void drawMissile(Graphics g, Missile m) {
		Color savedCol = g.getColor();
		g.setColor(Missile.COLOR);
		g.fillOval(m.getX() - Missile.SIZE_X / 2, m.getY() - Missile.SIZE_Y / 2, Missile.SIZE_X, Missile.SIZE_Y);
		g.setColor(savedCol);
	}
	
	// Draws the "game over" message and replay instructions
	// modifies: g
	// effects:  draws "game over" and replay instructions onto g
	private void gameOver(Graphics g) {
		Color saved = g.getColor();
		g.setColor(new Color( 0, 0, 0));
		g.setFont(new Font("Arial", 20, 20));
		FontMetrics fm = g.getFontMetrics();
		centreString(OVER, g, fm, SIGame.HEIGHT / 2);
		centreString(REPLAY, g, fm, SIGame.HEIGHT / 2 + 50);
		g.setColor(saved);
	}
	
	// Centres a string on the screen
	// modifies: g
	// effects:  centres the string str horizontally onto g at vertical position yPos
	private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
		int width = fm.stringWidth(str);
		g.drawString(str, (SIGame.WIDTH - width) / 2, yPos);
	}
}
