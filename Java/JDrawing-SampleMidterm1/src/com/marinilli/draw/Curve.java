/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Concrete implementation of an ellipse symbol, for the JDrawing Editor.
 * @see AbstractCurve
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Curve extends AbstractLine {
  public final static int DEFAULT_LENGTH = 100;//when a new line is created

  public Curve() {
    super();
    generalPath = new GeneralPath();
  }

  public void drawTo(float x, float y){
    Point2D.Float p = (Point2D.Float)generalPath.getCurrentPoint();
    generalPath.quadTo(x,y, x+100,y+100);//XXX a fancy, quick and dirt  implementation..
  }

  public AbstractSymbol createNew() {
    return new Curve();
  }

  public void initializeAt(Point location) {
    setLocation(location);

    float[] point = new float[2];
    point[0] = location.x;
    point[1] = location.y;
    addCtrPoint(point);
    point[0] = location.x+DEFAULT_LENGTH;
    point[1] = location.y+DEFAULT_LENGTH;
    addCtrPoint(point);
    redraw();
  }
}
