/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Application Option (also known as 'Preferences') for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Options extends HashMap implements Serializable, HavingUserProperties, ActionListener {
  public final static transient String SHOW_TEXT_ON_BUTTONS = "showTextOnLabel";
  public final static transient String FILE_NAME = "fileName";
  private transient JButton okButton = new JButton("OK");
  private transient JButton cancelButton = new JButton("Cancel");
  private transient JTextField fileNameTxtField = new JTextField(16);
  private transient JCheckBox textChkBox = new JCheckBox("Show text on buttons");
  private transient JDialog jd;

  public Options() {
    //set up the system options
    put(SHOW_TEXT_ON_BUTTONS, new Boolean(true));
    put(FILE_NAME, "Draw1");

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
  }

  public void editProperties() {
    //a better implementation would use an inner class
    jd = new JDialog();
    jd.setTitle("Preferences Settings");
    JPanel panel = new JPanel(new BorderLayout());
    boolean  b = ((Boolean)get(SHOW_TEXT_ON_BUTTONS)).booleanValue();
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panel3 = new JPanel();
    JPanel panel0 = new JPanel();
    panel0.add(okButton);
    panel0.add(cancelButton);
    textChkBox.setSelected(b);
    panel2.add(textChkBox);
    panel3.add(new JLabel("File Name:"));
    fileNameTxtField.setText((String)get(FILE_NAME));
    panel3.add(fileNameTxtField);
    panel2.add(panel3);
    panel.add(panel2,BorderLayout.CENTER);
    panel.add(panel0,BorderLayout.SOUTH);
    jd.setContentPane(panel);
    jd.setSize(300,200);
    jd.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand()=="OK") {
      put(SHOW_TEXT_ON_BUTTONS, new Boolean(textChkBox.isSelected()));
      put(FILE_NAME, fileNameTxtField.getText());
      Object[] obs = values().toArray();
      jd.dispose();
    } else
    if (e.getActionCommand()=="Cancel") {
      jd.dispose();
    }
  }

}