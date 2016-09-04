/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import javax.swing.*;

/**
 * Creates the symbol palette for the JDrawing Editor.
 * Conceptually, this is the 'seed' of all the possible symbol
 * that could be add to a drawing.<P>
 * Palettes can be serialized as well (not used in 1.0) and be
 * kept in the serialized form. So, for example, new symbol
 * types could be added at runtime.
 *
 * @see Main
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class SymbolPalette implements java.io.Serializable {
  Draw draw;
  ActionRepository.AddSymbol[] symbols;//coarse implementation

  public SymbolPalette(Draw d) {
    draw = d;
    symbols = new ActionRepository.AddSymbol[4];

    //build it, in the most simple way..
    symbols[0] =
      new ActionRepository.AddSymbol(draw,"bitmap icon");
    symbols[0].setSymbol(new BitmapIcon());

    symbols[1] =
      new ActionRepository.AddSymbol(draw,"simple line");
    symbols[1].setSymbol(new Line());

    symbols[2] =
      new ActionRepository.AddSymbol(draw,"circle");
    symbols[2].setSymbol(new Circle());

    symbols[3] =
      new ActionRepository.AddSymbol(draw,"curve");
    symbols[3].setSymbol(new Curve());
  }

  public void setSymbolsMenu(JMenu menu) {
    if (menu==null)
      return;
    for (int i=0;i<symbols.length;i++)
      menu.add(symbols[i]);
  }

}