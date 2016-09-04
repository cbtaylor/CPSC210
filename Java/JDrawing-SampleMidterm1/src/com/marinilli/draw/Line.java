/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.awt.geom.GeneralPath;

/**
 * Concrete implementation for the Line symbol, for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Line extends AbstractLine {

  /**
   * Constructor
   */
  public Line() {
    super();
    generalPath = new GeneralPath();//initializes the general path
  }

  /**
   * Used to connect all the control points in the symbol object
   * it specializes the superclass method for drawing a poly-Line.
   * note that a whole family of curves can easily be implemented writing this method
   */
  public void drawTo(float x, float y) {
    generalPath.lineTo(x,y);
  }

  /**
   * Implements the <code>SymbolCreable</code> interface
   * @returns a new Line ready to be put on the <code>Draw</code> canvas
   */
  public AbstractSymbol createNew() {
    return new Line();
  }

}