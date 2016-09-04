/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Launch class for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Main extends JFrame {
  protected SymbolPalette palette;
  static JMenuBar jMenuBar = new JMenuBar();
  JMenu fileMenu = new JMenu();
  JMenu addMenu = new JMenu();
  static JToolBar toolBar = new JToolBar();
  static Draw draw = new Draw();
  private Director director = new Director();
  protected static boolean showTextOnLabels = false;//XXX
  //a simple-and coarse- implementation, that keeps all the actions wired in the code
  Action newDraw = new ActionRepository.New(draw);
  Action open = new ActionRepository.Open(draw);
  Action save = new ActionRepository.Save(draw);
  Action saveAs = new ActionRepository.SaveAs(draw);
  Action exit = new ActionRepository.Exit(draw);
  Action cut =  new ActionRepository.Cut(draw);
  Action copy = new ActionRepository.Copy(draw);
  Action paste = new ActionRepository.Paste(draw);
  Action undo = new ActionRepository.Undo(draw);
  Action properties = new ActionRepository.Properties(draw);
  Action delete = new ActionRepository.Delete(draw);
  Action rotate = new ActionRepository.Rotate(draw);
  Action info = new ActionRepository.Info(draw);
  Action addSymbol = new ActionRepository.AddSymbol(draw);
  Action aboutMe = new ActionRepository.AboutMe(draw);
  Action print = new ActionRepository.Print(draw);
  Action options = new ActionRepository.Options(draw);

  public Main() {
    palette = new SymbolPalette(draw);
    directorSetUp();
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(draw,BorderLayout.CENTER);
    draw.repaint();
    getContentPane().add(toolBar, BorderLayout.NORTH);
    setJMenuBar(jMenuBar);
    fillMenus();//sorry for this fortran-like style..
    fillToolbar();//as before..
    setIconImage(IconRepository.LOGO.getImage());
    setTitle("JDrawing 1.0 ");

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        draw.exit();
      }
    });
    pack();
    setVisible(true);
    show();
  }

  private void fillMenus() {
    fileMenu.setText("File");
    fileMenu.setMnemonic('f');
    fillMenuBar(fileMenu, new Action[] {newDraw, open, save, saveAs, print, options, exit});

    addMenu.setText("Add");
    addMenu.setMnemonic('a');
    palette.setSymbolsMenu(addMenu);
    jMenuBar.add(addMenu);

    JMenu edit = new JMenu("Edit");
    edit.setMnemonic('e');
    fillMenuBar(edit, new Action[] {cut, copy, paste, delete});

    JMenu help = new JMenu("Help");
    help.setMnemonic('h');
    fillMenuBar(help, new Action[] {info, aboutMe});
  }

  private void fillMenuBar(JMenu menu, Action[] actions) {
    JMenuItem item;
    for (int i=0;i<actions.length;i++) {
      item = menu.add(actions[i]);
      item.setMnemonic(actions[i].getMnemonic());
      item.setToolTipText(actions[i].getToolTipText());
    }
    jMenuBar.add(menu);
  }

  private void fillToolbar() {
    addAction(newDraw);
    addAction(open);
    addAction(save);
    toolBar.addSeparator();
    addAction(print);
    toolBar.addSeparator();
    addAction(cut);
    addAction(copy);
    addAction(paste);
    addAction(undo);
    toolBar.addSeparator();
    addAction(rotate);
    addAction(properties);
    addAction(delete);

    JMenu jm = new JMenu();
    palette.setSymbolsMenu(jm);
    toolBar.add(jm);
    jm.setIcon(IconRepository.ADD_ICON);

    toolBar.addSeparator();
    addAction(info);
  }
  private JButton addAction(Action a) {
    JButton b;//needed to set the showText option
    b = toolBar.add(a);
    if (!showTextOnLabels)
      b.setText("");
    b.setMnemonic(a.getMnemonic());
    b.setToolTipText(a.getToolTipText());
    return b;
  }

  private void directorSetUp() {
    director.addClipboardSensitiveAction(paste);
    director.clipboardChanged(draw.getClipboard());

    director.addSelectedSymbolSensitiveAction(cut);
    director.addSelectedSymbolSensitiveAction(copy);
    director.addSelectedSymbolSensitiveAction(delete);
    director.addSelectedSymbolSensitiveAction(rotate);
    director.addSelectedSymbolSensitiveAction(properties);
    director.selectedSymbolChanged(null);
    director.setActionStack(draw.getActionStack());
    director.addActionArraySensitive(undo);
    director.actionArrayChanged();

    draw.setDirector(director);
  }

  public static void main(String[] args) {
    Main main1 = new Main();
  }

}
