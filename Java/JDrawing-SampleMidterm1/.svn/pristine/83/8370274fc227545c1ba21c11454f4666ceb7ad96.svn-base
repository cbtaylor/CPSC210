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
 * Stack specialized for actions, for the JDrawing Editor.
 * @see Action
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class ActionStack extends Stack {
  private int MAX_SIZE = 10;//remember only MAX_SIZE Actions

  public ActionStack() {
  }

  public void addAction(Action a) {
    if (size()>MAX_SIZE)
      remove(0);
    super.push(a);
//System.out.println("ActionStack.addAction="+a+"--"+a.NAME);
  }

  public Action popLastAction() {
    if (size()==0) return null;
    return (Action)super.pop();
  }

  public Object push(Object item) {
    //do nothing
    return null;
  }


  public synchronized Object pop() {
    //do nothing
    return null;
  }

}