/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Dialog that shows the user properties for the <code>AbstractCurve</code>
 * and the <code>BitmapIcon</code> classes
 * @see BitmapIcon
 * @see AbstractCurve
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class PropertiesDialog extends JDialog implements ActionListener {
  //some widgets, left untidy
  JPanel mainPanel = new JPanel();
  JLabel nameLabel = new JLabel();
  JPanel jp1 = new JPanel();
  JPanel jp2 = new JPanel();
  JLabel colorLabel = new JLabel();
  JButton colorButton = new JButton();
  JPanel jp3 = new JPanel();
  JLabel strokeLabel = new JLabel();
  JTextField StrokeTxtField = new JTextField(4);
  private   JButton okButton = new JButton("OK");
  private   JButton cancelButton = new JButton("Cancel");
  JPanel jp4 = new JPanel();
  private AbstractCurve abstractCurve;
  private BitmapIcon bitmapIcon;
  private final static String OK_CURVE = "ok curve";
  private final static String OK_BITMAP = "ok bitmap";
  JButton iconButton = new JButton();


/**
 * Construct a property dialog for a <code>AbstractCurve</code> instance.
 *
 */
  public PropertiesDialog(AbstractCurve ac) {
    setTitle("Symbol Properties");
//    setModal(true);
    setVisible(true);
    abstractCurve = ac;
    initForAbstractCurve();
    pack();
    setSize(256,192);
  }

/**
 * Construct a property dialog for a <code>BitmapIcon</code> instance.
 *
 */
  public PropertiesDialog(BitmapIcon bi) {
    setTitle("Bitmap Icon Properties");
//    setModal(true);
    setVisible(true);
    bitmapIcon = bi;
    initForBitmapIcon();
    pack();
    setSize(256,192);
  }

  private void initForBitmapIcon() {
    mainPanel.setLayout(new BorderLayout());
    nameLabel.setText("Symbol: "+bitmapIcon.getClass());
    mainPanel.add(jp4, BorderLayout.SOUTH);
    jp4.add(okButton, null);
    jp4.add(cancelButton, null);
    jp1.setPreferredSize(new Dimension(256, 120));
    jp1.setLayout(new BorderLayout());
    jp1.add(iconButton,BorderLayout.CENTER);
    mainPanel.add(jp1, BorderLayout.CENTER);
    getContentPane().add(mainPanel);
    cancelButton.addActionListener(this);
    okButton.setActionCommand(OK_BITMAP);
    okButton.addActionListener(this);
    iconButton.setActionCommand("changeIcon");
    iconButton.setIcon(bitmapIcon.getImage());
    iconButton.addActionListener(this);
  }

  private void initForAbstractCurve(){
    mainPanel.setLayout(new BorderLayout());
    nameLabel.setText("Symbol: "+abstractCurve.getClass());
    colorLabel.setText("Color:");
    colorButton.setBackground(abstractCurve.getColor());
    colorButton.setToolTipText("edit symbol color");
    colorButton.setActionCommand("chooseColor");
    colorButton.setText("Current Color");
    strokeLabel.setText("stroke dimension:");
    StrokeTxtField.setText(""+abstractCurve.getStrokeWidth());
    setResizable(false);
    jp1.setPreferredSize(new Dimension(256, 112));
    getContentPane().add(mainPanel);
    mainPanel.add(nameLabel, BorderLayout.NORTH);
    mainPanel.add(jp1, BorderLayout.CENTER);
    jp1.add(jp2, null);
    jp2.add(colorLabel, null);
    jp2.add(colorButton, null);
    jp1.add(jp3, null);
    jp3.add(strokeLabel, null);
    jp3.add(StrokeTxtField, null);
    mainPanel.add(jp4, BorderLayout.SOUTH);
    jp4.add(okButton, null);
    jp4.add(cancelButton, null);
    cancelButton.addActionListener(this);
    okButton.setActionCommand(OK_CURVE);
    okButton.addActionListener(this);
    colorButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand()=="chooseColor") {
      Color newColor = JColorChooser.showDialog(
                           this,
                           "Choose Background Color",
                           colorButton.getBackground());
      colorButton.setBackground(newColor);
    } else
    if (e.getActionCommand()==OK_CURVE) {
      abstractCurve.setColor(colorButton.getBackground());
      abstractCurve.setStrokeWidth(Integer.parseInt(StrokeTxtField.getText()));
      dispose();
    } else
    if (e.getActionCommand()==OK_BITMAP) {
      bitmapIcon.setImage((ImageIcon)iconButton.getIcon());
      dispose();
    } else
    if (e.getActionCommand()=="changeIcon") {
      JFileChooser jf = new JFileChooser();
      jf.setDialogTitle("input bitmap file");
      int returnVal = jf.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        java.io.File file = jf.getSelectedFile();
        ImageIcon ii = new ImageIcon(file.getAbsolutePath());
        if (ii!=null) {
          iconButton.setIcon(ii);
          iconButton.revalidate();
        }
      }
    } else
    if (e.getActionCommand()=="Cancel") {
      dispose();
    }
  }
}

