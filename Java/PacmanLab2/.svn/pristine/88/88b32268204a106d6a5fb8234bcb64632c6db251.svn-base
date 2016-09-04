package ca.ubc.cpsc210.pacman.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ca.ubc.cpsc210.pacman.model.GridLocation;
import ca.ubc.cpsc210.pacman.model.Pacman;
import ca.ubc.cpsc210.pacman.model.RandomMonster;
import ca.ubc.cpsc210.pacman.model.TrackerMonster;

@SuppressWarnings("serial")
public class GridLocationPanel extends JPanel {
	
	public static final int GRID_HEIGHT = 50;
	public static final int GRID_WIDTH  = 50;
	
	private static final int FOOD_SIZE = 18;
	
	GridLocation gridLocation;

	// Requires: a valid Gridlocation g
	// Modifies: this
	// Effects:  remembers g and sets the prefered size of the grid location for drawing
	public GridLocationPanel(GridLocation g) {
		gridLocation = g;
		setPreferredSize(new Dimension(GRID_HEIGHT, GRID_WIDTH));
	}

	// Requires: nothing
	// Modifies: g
	// Effects:  paints the grid location represented by this object using g and the contents of the board
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// This grid location has a food dot, make sure to draw it
		if (gridLocation.hasFood()) {
			g.setColor(Color.WHITE);

			int x = GRID_WIDTH/2 - FOOD_SIZE/2;
			int y = GRID_HEIGHT/2 - FOOD_SIZE/2;
			
			g.fillOval(x, y, FOOD_SIZE/2, FOOD_SIZE/2);
		}
		
		// This grid location is a wall
		if (!gridLocation.isPassable()) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GRID_WIDTH, GRID_HEIGHT);
		}
		
		if (gridLocation.hasSprite()) {
			Pacman p = gridLocation.getPacman();
			RandomMonster r = gridLocation.getRandomMonster();
			TrackerMonster t = gridLocation.getTrackerMonster();
			
			if (p != null) {
				g.setColor(p.getColor());
			} else if (r != null) {
				g.setColor(r.getColor());
			} else if (t != null) {
				g.setColor(t.getColor());
			}
			g.fillOval(0, 0, GRID_WIDTH-1, GRID_HEIGHT-1);

		}
	}
}
