/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import javax.swing.*;
import java.awt.event.*;

/**
 * The concrete Action suited for the JDrawing Editor.
 */
public class Action extends AbstractAction {

  private ActionListener actionListener;//not used
  protected char mnemonic;
  protected Draw draw;
  protected String toolTipText;

  /**
   * Creates an Action suited for the Drawing Editor.
   * @param name the name
   * @param icon the icon
   */
  public Action(String name, ImageIcon icon, Draw d) {
    super(name,icon);
    draw = d;
  }

  /**
   * Creates an Action suited for the Drawing Editor, taking the name from the icon itself.
   * @param icon the icon to be shown
   */
  public Action(ImageIcon icon, Draw d) {
    super(icon.getDescription(),icon);
    draw = d;
  }

  /**
   * Creates an Action suited for the Drawing Editor.
   * @param icon the icon to be shown
   */
  public Action(String name, Draw d) {
    super(name);
    draw = d;
  }

  /**
   * Invoked when an action occurs.
   */
  public void actionPerformed(ActionEvent e) {
    draw.getActionStack().addAction(this);
    draw.getDirector().actionArrayChanged();
  }

  /**
   * Sets the ActionListener (as of 1.0 not used)
   */
  public void setActionListener(ActionListener newActionListener) {
    actionListener = newActionListener;
  }

  /**
   * @return the ActionListener (as of 1.0 not used)
   */
  public ActionListener getActionListener() {
    return actionListener;
  }

  /**
   * the undo support
   */
  public void undo() {
    java.awt.Toolkit.getDefaultToolkit().beep();
    JOptionPane.showMessageDialog(null,"Undo Not Supported For This Action");
  }

  public char getMnemonic() {
    return mnemonic;
  }

  public String getToolTipText() {
    return toolTipText;
  }
}