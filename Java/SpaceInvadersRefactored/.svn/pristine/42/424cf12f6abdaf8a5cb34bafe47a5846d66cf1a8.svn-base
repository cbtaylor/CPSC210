package ca.ubc.cpsc210.spaceinvaders.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import ca.ubc.cpsc210.spaceinvaders.model.Sprite;

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
	// effects:  the game is drawn onto the Graphics object g
	private void drawGame(Graphics g) {
		game.draw(g);
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
