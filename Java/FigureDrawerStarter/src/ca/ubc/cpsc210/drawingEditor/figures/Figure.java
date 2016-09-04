package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Graphics;
import java.awt.Point;

public interface Figure {

	public int getStartX();

	public int getStartY();

	public int getWidth();

	public int getHeight();
	
	// REQUIRES: w >= 0, h >= 0
	// MODIFIES: this
	// EFFECTS: sets width to w and height to h
	public void setBounds(int w, int h);
	
	// REQUIRES: bottomRight.x >= getStartX(), bottomRight.y >= getStartY()
	// MODIFIES: this
	// EFFECTS: sets width to (bottomRight.x - getStartX()) 
	//          and height to (bottomRight.y - getStartY())
	public void setBounds(Point bottomRight);

	// Translates figure
	// MODIFIES: this
	// EFFECTS: figure is shifted dx units horizontally and dy units vertically
	public void translate(int dx, int dy);

	// Does figure contain a given point?
	// EFFECTS: returns true if the figure contains given point; false otherwise
	public boolean contains(Point p);

	// Draw figure
	// MODIFIES: g
	// EFFECTS: draws the figure on the given graphics object
	public void draw(Graphics g);
	
	// Select figure
	// MODIFIES: this
	// EFFECTS: this figure is marked selected
	public void select();

	// Unselect figure
	// MODIFIES: this
	// EFFECTS: this figure is marked unselected
	public void unselect();

	// Is this figure selected?
	// EFFECTS: returns true if this figure is selected, false otherwise
	public boolean isSelected();

}