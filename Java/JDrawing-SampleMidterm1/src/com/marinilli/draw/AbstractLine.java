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
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ListIterator;

/**
 * Abstract class symbol, for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public abstract class AbstractLine extends AbstractCurve {
  protected transient GeneralPath generalPath;
  public final static int DEFAULT_LENGTH = 100;//when a new line is created

  public AbstractLine() {
  }

  public Rectangle2D.Float getRectBounds(){
     if(generalPath==null) redraw();
     Rectangle2D rect = generalPath.getBounds();
     Rectangle2D.Float f =
        new Rectangle2D.Float((float)rect.getX()+getLocation().x,
                              (float)rect.getY()+getLocation().y,
                              (float)rect.getWidth(),
                              (float)rect.getHeight());
     return f;
  }

  public void addCtrPoint(float[] point) {
    point[0] = point[0]-getLocation().x;
    point[1] = point[1]-getLocation().y;
    ctrPts.add(point);
    redraw();
  }


  public void draw(Graphics2D g2){
    if(getRectBounds()==null) return;
    g2.rotate(rotationAngle, getLocation().getX(), getLocation().getY());

    if (isEditMode()) drawCtrPts(g2);

    g2.setStroke(new BasicStroke(getStrokeWidth()));
    g2.setColor(getColor());

    AffineTransform at=new AffineTransform();
    at.translate(getLocation().getX(),getLocation().getY());
    g2.draw(at.createTransformedShape(generalPath));

    g2.setStroke(new BasicStroke());

    if (isSelected())  drawRectBounds(g2);
    g2.rotate(-rotationAngle, getLocation().getX(), getLocation().getY());
  }

  public void redraw() {
    generalPath = new GeneralPath();
    boolean first = true;
    for (float[] point: ctrPts ) {
      Point2D.Float p = new Point2D.Float(point[0],point[1]);
      if (first) {
        //the first point in the line is always the first ctr point
        generalPath.moveTo(p.x,p.y);
        first = false;
      } else
        drawTo(p.x,p.y);
    }
  }

  public abstract void drawTo(float x, float y);

  public void processMouseEvent(MouseEvent me) {
    // right button adds ctr points
    if (((me.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) && isEditMode()) {
      float[] point = new float[2];
      point[0] = me.getPoint().x;
      point[1] = me.getPoint().y;
      addCtrPoint(point);
      return;
    }
    super.processMouseEvent(me);
  }//-processMouseEvent

  public void initializeAt(Point location) {
    setLocation(location);
    float[] point = new float[2];
    point[0] = location.x;
    point[1] = location.y;
    addCtrPoint(point);
    point = new float[2];
    point[0] = location.x+DEFAULT_LENGTH;
    point[1] = location.y+DEFAULT_LENGTH;
    addCtrPoint(point);
    redraw();
  }
}
