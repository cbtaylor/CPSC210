package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents rectangle figures
 */
public class Rectangle extends AbstractFigure {
	
	/**
	 * Constructor
	 * @param topLeft  the location of the top-left corner of rectangle
	 */
	public Rectangle(Point topLeft) {
		x = (int) topLeft.getX();
		y = (int) topLeft.getY();
		width = 0;
		height = 0;
	}
	

	@Override
	public void draw(Graphics g) {
		if (selected) {
			Color save = g.getColor();
			g.setColor(SHADOW_COLOR);
			g.drawRect(x - DX, y - DY, width + 2 * DX, height + 2 * DY);
			g.setColor(save);
		}
		g.drawRect(x, y, width, height);
	}

	@Override
	public boolean contains(Point point) {
		int point_x = (int) point.getX();
		int point_y = (int) point.getY();
		if ( (x <= point_x) && (point_x <= x+width) &&
				(y <= point_y) && (point_y <= y+height))
			return true;
		return false;
	}
}
