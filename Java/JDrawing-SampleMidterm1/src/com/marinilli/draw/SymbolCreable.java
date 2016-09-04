/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

/**
 * For creating new symbols for the JDrawing Editor.
 * @see AbstractSymbol
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public interface SymbolCreable {

  public AbstractSymbol createNew();

}
