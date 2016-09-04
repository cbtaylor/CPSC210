package ca.ubc.cpsc210.pacman.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import ca.ubc.cpsc210.pacman.model.Board;
import ca.ubc.cpsc210.pacman.model.Pacman;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private Board gameBoard;
	
	private BoardPanel gameViewPanel;
	
	private Timer gameTicker;
	
	// Requires: nothing
	// Modifies: this
	// Effects:  Performs all initializing by creating the board model and graphic view of the board as well as the game ticker
	public Game() {
		super("Pacman");
		init();
	}

	// Requires: nothing
	// Modifies: this
	// Effects:  creates the Board for the game and sets up the GUI windows and buttons, the game timer, and begins the game
	private void init() {
		gameBoard = new Board();
		
		createMenus();
		setupViewPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		repaint();
		setVisible(true);
		
		setupGameTicker();
		
		setFocusable(true);
		addKeyListener(new KeyboardController());
	}
	
	// Requires: nothing
	// Modifies: this
	// Effects:  Sets up the game ticker.  If a ticker exists already it is turned off and discarded.
	private void setupGameTicker() {
		if (gameTicker != null) {
			gameTicker.stop();
		}
		gameTicker = new Timer(1000, new GameTickerActionListener());
		gameTicker.start();
	}
	
	// Requires: nothing
	// Modifies: this
	// Effects:  creates the main game panel for board display
	private void setupViewPanel() {
		gameViewPanel = new BoardPanel(gameBoard);
		this.add(gameViewPanel);
	}
	
	// Requires: nothing
	// Modifies: this 
	// Effects:  creates the menubar and menu items (only one, for resetting the game)
	private void createMenus() {
		JMenuBar menuBar = new JMenuBar();
		JMenu drawingMenu = new JMenu("Pacman");
		
		JMenuItem resetGame = new JMenuItem("Reset Game");
		resetGame.addActionListener(new ResetGameAction());
		drawingMenu.add(resetGame);
		
		menuBar.add(drawingMenu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Starts the main application
	 */
	public static void main(String args[]) {
		new Game();
	}
	
	private class ResetGameAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			init();
		}
	}
	
	private class GameTickerActionListener extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!gameBoard.isGameOver()) {
				gameBoard.tickBoard();	
				gameViewPanel.repaint();
			} else {
				gameViewPanel.repaint();
				gameTicker.stop();
			}
		}
	}
	
	private class KeyboardController implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			Pacman p = gameBoard.getPacman();
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: // Left
				p.setDirection('L'); break;
			case KeyEvent.VK_UP: // Up
				p.setDirection('U'); break;
			case KeyEvent.VK_RIGHT: // Right
				p.setDirection('R'); break;
			case KeyEvent.VK_DOWN: // Down
				p.setDirection('D'); break;
			case KeyEvent.VK_SPACE: // Space
				p.setDirection('S'); break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}
