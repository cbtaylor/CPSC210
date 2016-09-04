/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Abstract superclass of all the symbols in the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public abstract class AbstractSymbol implements java.io.Serializable, Cloneable, SymbolCreable, HavingUserProperties{
  static final long serialVersionUID = 11111111L;
  protected boolean editMode = false;
  protected float rotationAngle = 0f;
  private boolean selected = false;
  private Point location;
  private static float ROTATION_STEP = 0.1f;

  public AbstractSymbol() {
  }

  public void setEditMode(boolean em) {
    editMode = em;
  }

  public boolean isEditMode() {
    return editMode;
  }

  public void setLocation(Point l) {
    location = l;
  }

  public Point getLocation() {
    return location;
  }

  public boolean contains(Point p, int tolerance) {
    Rectangle2D.Float r = getRectBounds();
    r.setRect(r.x-tolerance,r.y-tolerance,r.width+tolerance*2,r.height+tolerance*2);
    return r.contains(p);//XXX to coarse in general!
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean s) {
    selected = s;
    if (!s) setEditMode(false);//when deselect always editMode goes off 
  }

  public abstract void draw(java.awt.Graphics2D g);

  public abstract Rectangle2D.Float getRectBounds();

  public Object clone() {
    try {
      return super.clone();
    } catch (Exception exc) {
      System.out.println("AbstractSymbol.clone() "+exc);
    }
    return null;
  }

  public void processMouseEvent(MouseEvent me) {
  }

  public void initializeAt(Point location) {
    setLocation(location);
  }

  public void rotate() {
    rotationAngle += ROTATION_STEP;
  }

  public void rotateBack() {
    rotationAngle -= ROTATION_STEP;
  }

}