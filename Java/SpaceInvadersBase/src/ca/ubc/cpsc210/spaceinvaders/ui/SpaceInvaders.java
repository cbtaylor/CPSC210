package ca.ubc.cpsc210.spaceinvaders.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;

/*
 * Represents the main window in which the space invaders
 * game is played
 */
@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {

	private static final int INTERVAL = 20;
	private SIGame game;
	private GamePanel gp;
	private ScorePanel sp;

	// Constructs main window
	// effects: sets up window in which Space Invaders game will be played
	public SpaceInvaders() {
		super("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		game = new SIGame();
		gp = new GamePanel(game);
		sp = new ScorePanel(game);
		add(gp);
		add(sp, BorderLayout.NORTH);
		addKeyListener(new KeyHandler());
		pack();
		centreOnScreen();
		setVisible(true);
		addTimer();
	}
	
	// Set up timer
	// modifies: none
	// effects:  initializes a timer that updates game each
	//           INTERVAL milliseconds
	private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				game.update();
				gp.repaint(); 
				sp.update();
			}
		});
		
		t.start();
	}
	
	// Centres frame on desktop
	// modifies: this
	// effects:  location of frame is set so frame is centred on desktop
	private void centreOnScreen() {
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}
	
	/*
	 * A key handler to respond to key events
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			game.keyPressed(e.getKeyCode());
		}
	}
	
	/*
	 * Play the game
	 */
	public static void main(String[] args) {
		new SpaceInvaders();
	}
}
