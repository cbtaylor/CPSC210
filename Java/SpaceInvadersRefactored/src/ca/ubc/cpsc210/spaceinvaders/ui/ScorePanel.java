package ca.ubc.cpsc210.spaceinvaders.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;

/*
 * Represents the panel in which the scoreboard is displayed.
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	private static final String INVADERS_TXT = "Invaders shot down: ";
	private static final String MISSILES_TXT = "Missiles remaining: ";
	private static final int LBL_WIDTH = 200;
	private static final int LBL_HEIGHT = 30;
	private SIGame game;
	private JLabel invadersLbl;
	private JLabel missilesLbl;
	
	// Constructs a score panel
	// effects: sets the background colour and draws the initial labels;
	//          updates this with the game whose score is to be displayed
	public ScorePanel(SIGame g) {
		game = g;
		setBackground(new Color(180, 180, 180));
		invadersLbl = new JLabel(INVADERS_TXT + game.getNumInvadersDestroyed());
		invadersLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		missilesLbl = new JLabel(MISSILES_TXT + SIGame.MAX_MISSILES);
		missilesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		add(invadersLbl);
		add(Box.createHorizontalStrut(10));
		add(missilesLbl);
	}
	
	// Updates the score panel
	// modifies: this
	// effects:  updates number of invaders shot and number of missiles
	//           remaining to reflect current state of game
	public void update() {
		invadersLbl.setText(INVADERS_TXT + game.getNumInvadersDestroyed());
		missilesLbl.setText(MISSILES_TXT + (SIGame.MAX_MISSILES - game.getNumMissiles()));
		repaint();
	}
}
