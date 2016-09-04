/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Abstract class for symbols having control points, for the JDrawing Editor.
 * 
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public abstract class AbstractCurve extends AbstractSymbol {
	protected ArrayList<float[]> ctrPts = new ArrayList<float[]>();
	private static final int CTR_PT_SENSITIVE_DISTANCE = 102;// square of max
																// distance from
																// the control
																// point center
																// to be dragged
	private Color color = Color.black;// current color
	private int strokeWidth = 1;// current stroke width

	public AbstractCurve() {
		setLocation(new Point(0, 0));
	}

	public void drawCtrPts(Graphics2D g2) {
		Color c = g2.getColor();
		for (ListIterator i = ctrPts.listIterator(); i.hasNext();) {

			float[] point = (float[]) i.next();
			Point2D.Float p = new Point2D.Float(point[0], point[1]);
			g2.setColor(Color.green);
			g2.drawOval((int) p.x - 2 + getLocation().x, (int) p.y - 2
					+ getLocation().y, 4, 4);
			g2.setColor(Color.black);
			g2.drawOval((int) p.x - 3 + getLocation().x, (int) p.y - 3
					+ getLocation().y, 6, 6);
		}
		g2.setColor(c);
	}

	public void processMouseEvent(MouseEvent me) {
		// mouse dragged translates or modifies ctr points
		if (me.getID() == MouseEvent.MOUSE_DRAGGED) {
			if (editMode) {

				// scans all ctr pts
				Point2D.Float mousePt = new Point2D.Float(me.getPoint().x
						- getLocation().x, me.getPoint().y - getLocation().y);
				// note that for ease of serialization points are kept as float
				// arrays
				Point2D.Float p;
				for (float[] point: ctrPts) {
				//for (ListIterator i = ctrPts.listIterator(); i.hasNext();) {
					//point = (float[]) i.next();
					p = new Point2D.Float(point[0], point[1]);

					if (p.distanceSq(mousePt) <= CTR_PT_SENSITIVE_DISTANCE) {
						point[0] = mousePt.x;
						point[1] = mousePt.y;
						redraw();
						return;
					}
				}
			} else {// delete the 'else' for dragging also with ctr points on
					// (i.e. in editMode)
				setLocation(me.getPoint());// standard (rough!) drag
			}
		}// -end of MOUSE_DRAGGED
	}

	public abstract void redraw();

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void setStrokeWidth(int w) {
		strokeWidth = w;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	protected void drawRectBounds(Graphics2D g2) {
		if (getRectBounds() == null)
			return;
		g2.setColor(Color.black);
		if (editMode)
			g2.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND, 0, new float[] { 3, 3 }, 0));
		else
			g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 0, new float[] { 5, 5 }, 0));
		g2.draw(getRectBounds());
	}

	public void editProperties() {
		PropertiesDialog p = new PropertiesDialog(this);
		redraw();
	}

	public void rotate() {
		super.rotate();
		// XXX NOT WORKING
		// rotate all ctr points around the ctr point at index zero

		// as of version 1.0 not yet implemented!

		/*
		 * float[] point0 = (float[])ctrPts.get(0); Point2D.Float p0 = new
		 * Point2D.Float(point0[0],point0[1]); java.awt.geom.AffineTransform at
		 * = new java.awt.geom.AffineTransform(); at.rotate(
		 * rotationAngle,p0.getX(),p0.getY());
		 * 
		 * float x,y; float x0 = point0[0]; float y0 = point0[1];
		 * System.out.println("rotate x0="+x0+", y0="+y0);
		 * 
		 * // for (int i=1; i<ctrPts.size();i++ ) { //float[] point =
		 * (float[])ctrPts.get(i);
		 * //System.out.println(i+") x="+point[0]+", y="+point[1]); //}
		 * 
		 * 
		 * for (int i=1; i<ctrPts.size();i++ ) { float[] point =
		 * (float[])ctrPts.get(i); x = point[0]; y = point[1];
		 * System.out.println("\t("+i+")rotate x'="+x+", y'="+y); // point[0] =
		 * point[0] + x0; // point[1] = point[1] + y0; point[0] = (float)(x *
		 * Math.cos(rotationAngle)) - (float)(y * Math.sin(rotationAngle));
		 * point[1] = (float)(x * Math.sin(rotationAngle)) + (float)(y *
		 * Math.cos(rotationAngle));
		 * System.out.println("\t("+i+")rotate x''="+point
		 * [0]+", y''="+point[1]); // point[0] = point[0] - x0; // point[1] =
		 * point[1] - y0;
		 * 
		 * // Point2D.Float p = new Point2D.Float(point[0],point[1]); }
		 */
	}

	public void rotateBack() {
		super.rotateBack();
		// rotate back all ctr points around the first one
	}

}