/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.util.*;

/**
 * Simple implementation of the Mediator design pattern, for the JDrawing Editor.
 *
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Director {
  // the state of the Director is made up of the following fields
  private AbstractSymbol clipboard;
  private AbstractSymbol selectedSymbol;
  private ActionStack actionStack;
  private ArrayList<Action> clipboardSensitiveActions = new ArrayList<Action>();
  private ArrayList<Action> selectedSymbolSensitiveActions = new ArrayList<Action>();
  private ArrayList<Action> actionArraySensitive = new ArrayList<Action>();

  public Director() {
  }

  public void addActionArraySensitive(Action a) {
    actionArraySensitive.add(a);
  }

  public void addClipboardSensitiveAction(Action a) {
    clipboardSensitiveActions.add(a);
  }

  public void addSelectedSymbolSensitiveAction(Action a) {
    selectedSymbolSensitiveActions.add(a);
  }

  public void actionArrayChanged() {
    for (Iterator i = actionArraySensitive.iterator(); i.hasNext();)
      ((Action)i.next()).setEnabled( actionStack.size()>0);
  }

  public void clipboardChanged(AbstractSymbol c) {
    clipboard = c;
    for (Iterator i = clipboardSensitiveActions.iterator(); i.hasNext();)
      ((Action)i.next()).setEnabled(clipboard != null);
  }

  public void setActionStack(ActionStack as){
    actionStack = as;
  }

  public void selectedSymbolChanged(AbstractSymbol c) {
    selectedSymbol = c;
    for (Iterator i = selectedSymbolSensitiveActions.iterator(); i.hasNext();)
      ((Action)i.next()).setEnabled(selectedSymbol != null);
  }

  public void setAddMode(boolean mode) {
    //during addMode is all disabled
    if (mode==true) {
      for (Iterator i = actionArraySensitive.iterator(); i.hasNext();)
        ((Action)i.next()).setEnabled(false);
      for (Iterator i = clipboardSensitiveActions.iterator(); i.hasNext();)
        ((Action)i.next()).setEnabled(false);
      for (Iterator i = selectedSymbolSensitiveActions.iterator(); i.hasNext();)
        ((Action)i.next()).setEnabled(false);
    } else {
      //put back the situation previous to adding
      actionArrayChanged();
      selectedSymbolChanged(selectedSymbol);
      clipboardChanged(clipboard);
    }
  }
}