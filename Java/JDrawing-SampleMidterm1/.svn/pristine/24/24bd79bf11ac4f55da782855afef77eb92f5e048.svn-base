/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Abstract class for icon symbols, for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public abstract class AbstractIcon extends AbstractSymbol {
  protected int width;
  protected int height;

  public AbstractIcon() {
  }

  public Rectangle2D.Float getRectBounds() {
    return new Rectangle2D.Float(-width/2,-height/2,width,height);
  }

  public void processMouseEvent(MouseEvent me) {
    if (me.getID()==MouseEvent.MOUSE_DRAGGED) {
      setLocation(me.getPoint());//standard drag
    }//-end of MOUSE_DRAGGED
  }

  public AbstractSymbol createNew() {
    //subclasses will override as needed
    AbstractSymbol a = (AbstractSymbol)clone();
    return a;
  }

  public void editProperties() {
    //not implemented
  }
}