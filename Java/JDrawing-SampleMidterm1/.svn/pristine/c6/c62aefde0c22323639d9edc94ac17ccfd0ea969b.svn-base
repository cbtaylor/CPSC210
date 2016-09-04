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
 * Repository of actions for the JDrawing Editor.
 * @see Action
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public interface ActionRepository {

  public final class Print extends Action {
    public Print(Draw d) {
      super(IconRepository.PRINT_ICON,d);
      mnemonic = 'p'; //keyboard shortcut
      toolTipText = "prints the current drawing";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
      job.setPrintable(draw);
      // still to be implemented properly: look at the website for newer releases
    }
  }//-print

  public final class New extends Action {
    public New(Draw d) {
      super(IconRepository.NEW_ICON,d);
      mnemonic = 'n'; //keyboard shortcut
      toolTipText = "creates a new drawing";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      draw.newDraw();
    }
  }//-new

  public final class Save extends Action {
    public Save(Draw d) {
      super(IconRepository.SAVE_ICON,d);
      mnemonic = 's'; //keyboard shortcut
      toolTipText = "saves on file the current drawing";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      draw.save();
    }
  }//-save

  public final class SaveAs extends Action {
    public SaveAs(Draw d) {
      super(IconRepository.SAVEAS_ICON,d);
      mnemonic = 'a';
      toolTipText = "saves in a file the drawing with a different name";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      try {
        JFileChooser jfc = new JFileChooser();
        int userSays =jfc.showSaveDialog(draw);
        if (userSays == JFileChooser.APPROVE_OPTION) {
          draw.setName(jfc.getSelectedFile().getCanonicalPath());
          draw.save();
        }
      } catch (Exception exc) {
        System.out.println("ActionRepository.SaveAs "+exc);
      }
    }
  }//-save as

  public final class Open extends Action {
    public Open(Draw d) {
      super(IconRepository.OPEN_ICON,d);
      mnemonic = 'o';
      toolTipText = "loads a drawing from file ";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      try {
        JFileChooser jfc = new JFileChooser();
        int ret = jfc.showOpenDialog(draw);
        if (ret==JFileChooser.CANCEL_OPTION)
          return;
//        draw.setName(jfc.getSelectedFile().getPath());
//        draw = Main.load(jfc.getSelectedFile().getPath());
//System.out.println("Open (1) draw="+draw);
        draw.load(jfc.getSelectedFile().getPath());
//System.out.println("Open (2) draw="+draw);
      } catch (Exception exc) {
        System.out.println("ActionRepository.Open "+exc);
      }
    }
  }//-Open

  public final class Cut extends Action {
    private AbstractSymbol symbol;
    public Cut(Draw d) {
      super(IconRepository.CUT_ICON,d);
      mnemonic = 'x';
      toolTipText = "cuts the selected symbol";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      draw.cutSelectedSymbol();
//      symbol = (AbstractSymbol)draw.getClipboard().clone();
      symbol = draw.getClipboard();
    }
    public void undo() {
      symbol.setSelected(false);
      draw.add(symbol);
    }
  }//-Cut

  public final class Copy extends Action {
    AbstractSymbol previousClipboard;
    public Copy(Draw d) {
      super(IconRepository.COPY_ICON,d);
      mnemonic = 'c';
      toolTipText = "copies the selected symbol";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      previousClipboard = draw.getClipboard();
      draw.copySelectedSymbol();
    }
    public void undo() {
      draw.setClipboard(previousClipboard);
    }
  }//-Copy


  public final class Paste extends Action {
    private AbstractSymbol symbol;
    public Paste(Draw d) {
      super(IconRepository.PASTE_ICON,d);
      mnemonic = 'v';
      toolTipText = "paste the symbol in clipboard";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      symbol = draw.pasteSymbolInClipboard();
//System.out.println("paste="+symbol);
    }
    public void undo() {
      draw.deleteSymbol(symbol);//won't go in ActionStack..
    }
  }//-Paste

  public final class Rotate extends Action {
    private AbstractSymbol symbol;
    public Rotate(Draw d) {
      super(IconRepository.ROTATE_ICON,d);
      mnemonic = 'r';
      toolTipText = "rotate the current symbol";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      symbol = draw.rotateSymbolInClipboard();
//System.out.println("rot="+symbol);
    }
    public void undo() {
      symbol.rotateBack();//won't go in ActionStack..
      draw.repaint();
    }
  }//-Rotate

  public final class Delete extends Action {
    private AbstractSymbol symbol;
    public Delete(Draw d) {
      super(IconRepository.DELETE_ICON,d);
      mnemonic = 'd';
      toolTipText = "removes the selected symbol";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      symbol = draw.deleteSelectedSymbol();
    }
    public void undo() {
      symbol.setSelected(false);
      draw.add(symbol);//won't go in ActionStack..
    }
  }//-Delete


  public final class Exit extends Action {
    public Exit(Draw d) {
      super("Exit",d);
      mnemonic = 'e';
      toolTipText = "exits from the program";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      draw.exit();
    }
  }//-Exit

  public final class Undo extends Action {
    public Undo(Draw d) {
      super(IconRepository.UNDO_ICON,d);
      mnemonic = 'z';
      toolTipText = "undo the last action";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      draw.undo();
    }
  }//-Undo


  public final class Properties extends Action {
    public Properties(Draw d) {
      super(IconRepository.PROPERTIES_ICON,d);
      mnemonic = 'p';
      toolTipText = "shows the properties of the selected symbol";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
//      super.actionPerformed(e);//to register for undo
      draw.editSelectedSymbolProperties();
    }
  }//-Properties

  public final class AddSymbol extends Action {
    private AbstractSymbol symbol;
    public AddSymbol(Draw d) {
      super(IconRepository.ADD_ICON,d);
      mnemonic = 'a';
      toolTipText = "adds a symbol to the draw";//sets the tooltip
    }
    public AddSymbol(Draw d,String name) {
      super(name, IconRepository.ADD_ICON, d);
    }
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);//to register for undo
      draw.setSymbolToAdd(symbol.createNew());
    }
    public AbstractSymbol getSymbol() {
      return symbol;
    }
    public void setSymbol(AbstractSymbol as) {
      symbol = as;
    }
    public void undo() {
      draw.deleteSymbol(symbol);
    }
  }//-addSymbol

  public final class Info extends Action {
    public Info(Draw d) {
      super(IconRepository.INFO_ICON,d);
      mnemonic = 'i';
      toolTipText = "shows some information about this program";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null,"Java Drawing Editor 1.0\n(c) 2000 mauro Marinilli\n\n http://www.gamelan.com (articles) \n http://www.marinilli.com  (code)","About JDrawing 1.0",JOptionPane.PLAIN_MESSAGE,IconRepository.LOGO);
    }
  }//-info

  public final class AboutMe extends Action {
    public AboutMe(Draw d) {
      super(IconRepository.MY_ICON,d);
      mnemonic = 'm';
      toolTipText = "contact the author";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null,"Please Contact me at:\ninfo@marinilli.com");
    }
  }//-about me

  public final class Options extends Action {
    public Options(Draw d) {
      super(IconRepository.OPTIONS_ICON, d);
      mnemonic = 'r'; //keyboard shortcut
      toolTipText = "shows program preferences";//sets the tooltip
    }
    public void actionPerformed(ActionEvent e) {
      draw.editOptions();
    }
  }//-save


}
