/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Concrete implementation of an ellipse symbol, for the JDrawing Editor.
 * 
 * @see AbstractCurve
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Circle extends AbstractCurve {

	public static final int DEFAULT_RADIUS = 50;

	public Circle() {
		super();
	}

	public void draw(Graphics2D g2) {
		if (getRectBounds() == null)
			return;
		if (isEditMode())
			drawCtrPts(g2);

		g2.setStroke(new BasicStroke());
		g2.setColor(getColor());

		float[] point = (float[]) ctrPts.get(0);
		Point2D.Float p = new Point2D.Float(point[0], point[1]);
		float[] point1 = (float[]) ctrPts.get(1);
		Point2D.Float p1 = new Point2D.Float(point1[0], point1[1]);
		int h = 2 * (int) (Math.abs(p.y - p1.y));
		int w = 2 * (int) (Math.abs(p.x - p1.x));

		g2.drawOval(getLocation().x, getLocation().y, w, h);
		if (isSelected())
			drawRectBounds(g2);
	}

	public AbstractSymbol createNew() {
		return new Circle();
	}

	public void initializeAt(Point location) {
		setLocation(location);
		float[] point = new float[2];
		point[0] = location.x + DEFAULT_RADIUS;
		point[1] = location.y;
		addCtrPoint(point);
		point = new float[2];
		point[0] = location.x;
		point[1] = location.y + DEFAULT_RADIUS;
		addCtrPoint(point);
		redraw();
	}

	public void redraw() {
		// useless in this concrete class
	}

	public void addCtrPoint(float[] point) {
		if (ctrPts.size() < 2) {
			point[0] = point[0] - getLocation().x;
			point[1] = point[1] - getLocation().y;
			ctrPts.add(point);
		}
		redraw();
	}

	public Rectangle2D.Float getRectBounds() {
		float[] point = (float[]) ctrPts.get(0);
		Point2D.Float p = new Point2D.Float(point[0], point[1]);
		float[] point1 = (float[]) ctrPts.get(1);
		Point2D.Float p1 = new Point2D.Float(point1[0], point1[1]);
		int h = 2 * (int) (Math.abs(p.y - p1.y));
		int w = 2 * (int) (Math.abs(p.x - p1.x));

		Rectangle2D.Float f = new Rectangle2D.Float(getLocation().x,
				getLocation().y, w, h);
		return f;
	}
}
