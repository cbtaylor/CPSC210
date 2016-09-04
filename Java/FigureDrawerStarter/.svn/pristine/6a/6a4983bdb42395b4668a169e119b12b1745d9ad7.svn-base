package ca.ubc.cpsc210.drawingEditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval extends AbstractFigure {
	
	public Oval(Point topLeft) {
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
			g.drawOval(x - DX, y - DY, width + 2 * DX, height + 2 * DY);
			g.setColor(save);
		}
		g.drawOval(x, y, width, height);
	}

	@Override
	public boolean contains(Point p) {
		final double TOL = 1.0e-6;
		double halfWidth = width / 2.0;
		double halfHeight = height / 2.0;
		double diff = 0.0;
		
		//[NOTE TO CPSC 210 STUDENTS: don't spend ANY time worrying about
		// why this implementation looks the way it does.  The mathematical
		// details of how we determine if an oval contains a point are
		// not important in the context of this course!]
		
		if (halfWidth > 0.0)
			diff = diff + sqrDiff(x + halfWidth, p.x) / (halfWidth * halfWidth);
		else
			diff = diff + sqrDiff(x + halfWidth, p.x);
		
		if (halfHeight > 0.0)
			diff = diff + sqrDiff(y + halfHeight, p.y) / (halfHeight * halfHeight);
		else
			diff = diff + sqrDiff(y + halfHeight, p.y);
		
		return  diff <= 1.0 + TOL;
	}
	
	// Compute square of difference
	// EFFECTS: returns the square of the difference of num1 and num2
	private double sqrDiff(double num1, double num2) {
		return (num1 - num2) * (num1 - num2);
	}
}
