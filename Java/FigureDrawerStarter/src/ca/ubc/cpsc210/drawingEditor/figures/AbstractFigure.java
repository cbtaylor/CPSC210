package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents figures in the drawing application.
 */
public abstract class AbstractFigure implements Figure {
	/* offsets for drawing shadow when figure is selected */
	protected static final int DX = 1;
	protected static final int DY = 1;
	/* colour of shadow */
	protected static final Color SHADOW_COLOR = new Color(130, 130, 230);
	/* x and y coordinate of Figure */
	protected int x;
	protected int y;
	/* width and height of Figure */
	protected int width;
	protected int height;
	/* true if figure is selected, false otherwise */
	protected boolean selected = false;

	@Override
	public int getStartX() {
		return x;
	}

	@Override
	public int getStartY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	@Override
	public void setBounds(int w, int h) {
		width = w;
		height = h;		
	}
	
	@Override
	public void setBounds(Point bottomRight) {
		width = bottomRight.x - x;
		height = bottomRight.y - y;
	}
	
	@Override
	public void select() {
		selected = true;
	}
	
	@Override
	public void unselect() {
		selected = false;
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}
}

