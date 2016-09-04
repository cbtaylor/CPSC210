/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Implementation of the canvas that holds together all the symbols for the JDrawing Editor.
 * it's the heart of drawing editor class architecture
 * @see AbstractCurve
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class Draw extends JPanel implements java.io.Serializable, MouseListener, MouseMotionListener, Printable {
  private ArrayList<AbstractSymbol> symbols = new ArrayList<AbstractSymbol>();
  private Options options = new Options();
  private transient AbstractSymbol selectedSymbol;
  private transient AbstractSymbol clipboard;//single selection only
  private transient AbstractSymbol symbolToAdd;//symbol going to be added
  private transient Director director;
  private transient ActionStack actionStack = new ActionStack();
  public final static int DEFAULT_TOLERANCE = 20;//rise if you run fast with the mouse
  public final static int NO_TOLERANCE = 0;//for containment

  public Draw() {
    setLayout(null);
    setBackground(Color.white);

    addMouseListener(this);
    addMouseMotionListener(this);
    setPreferredSize(new Dimension(400,300));
  }

  public void cutSelectedSymbol() {
    symbols.remove(selectedSymbol);
    clipboard = selectedSymbol;
    selectedSymbol = null;
    director.clipboardChanged(clipboard);//notify to director
    director.selectedSymbolChanged(selectedSymbol);//notify to director
    repaint();
  }

  public void copySelectedSymbol() {
    clipboard = (AbstractSymbol) selectedSymbol.clone();
    clipboard.setSelected(false);
    director.clipboardChanged(clipboard);//notify to director
    repaint();//only for usability: the blinking will signal the successfull copy to the user
  }

  public AbstractSymbol deleteSelectedSymbol() {
    deleteSymbol(selectedSymbol);
    return selectedSymbol;
  }

  public void deleteSymbol(AbstractSymbol s) {
    symbols.remove(s);
    if (clipboard!=null && clipboard.equals(s)) {//check if it's the same in clipboard
//System.out.println("s="+s+" == cl="+clipboard);
      setClipboard(null);
    }

    s = null;
    director.selectedSymbolChanged(s);//notify to director
    repaint();
  }

  public AbstractSymbol pasteSymbolInClipboard() {
    //to avoid perfect graphical overlapping with copied symbol
    clipboard.setLocation(new Point(clipboard.getLocation().x+8, clipboard.getLocation().y+8));
    AbstractSymbol a = (AbstractSymbol) clipboard.clone();
    a.setSelected(false);
    add(a);
    return a;
  }

  public void add(AbstractSymbol s) {
    symbols.add(s);
    repaint();
  }

  public void paint(Graphics g){
    super.paint(g);
    try{
      Graphics2D g2=(Graphics2D)g;
      Hashtable<Key, Object> h= new Hashtable<Key, Object>();
      h.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
      g2.setRenderingHints(h);
      //for all the symbols, draws them
      for(int i=0;i<symbols.size();i++){
        ((AbstractSymbol)symbols.get(i)).draw(g2);
      }
     } catch (Exception e) {
      System.out.println("Draw.paint() "+e);
     }
  }

  public void mouseClicked(MouseEvent e) {
    //case of left button clicked
    if (((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)) {
      //adding new symbols
      if (symbolToAdd!=null) {
        //when adding to the draw
        AbstractSymbol as = (AbstractSymbol)symbolToAdd.createNew();
        as.initializeAt(e.getPoint());
        add(as);
        setSymbolToAdd(null);//only one at time
        return;
      }

      //handling double clicks
      if (e.getClickCount()==2) {
        if (selectedSymbol!=null) {
          selectedSymbol.setSelected(false);//deselect the old one
        }
        selectedSymbol = getSymbolAt(e.getPoint(),NO_TOLERANCE);
        if (selectedSymbol!=null) {
          selectedSymbol.setSelected(true);
          selectedSymbol.setEditMode(true);
          repaint();
          return;
        }
      }//-2-clicks

      if (selectedSymbol==null) {
        selectedSymbol = getSymbolAt(e.getPoint(),NO_TOLERANCE);
      } else {
        selectedSymbol.setSelected(false);//deselect the old one
        selectedSymbol = getSymbolAt(e.getPoint(),NO_TOLERANCE);
      }

      if (selectedSymbol!=null) {
        selectedSymbol.setSelected(true);
        selectedSymbol.processMouseEvent(e);
      }
      director.selectedSymbolChanged(selectedSymbol);//expensive! please, arrange it better ;-)
      repaint();
    }//-left mouse button clicked
      else {
      if (selectedSymbol!=null) {
        selectedSymbol.setSelected(true);
        selectedSymbol.processMouseEvent(e);
        repaint();
      }
    }//other than left button clicked
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {
    if (selectedSymbol==null) return;
    if (selectedSymbol.contains(e.getPoint(),DEFAULT_TOLERANCE)) {
      selectedSymbol.processMouseEvent(e);
      repaint();
    }
  }

  public void mouseMoved(MouseEvent e) {
  }

  public String getName(){
    return (String)options.get(Options.FILE_NAME);
  }

  public void setName(String n){
    options.put(Options.FILE_NAME,n);
  }

  public AbstractSymbol getSymbolAt(Point p, int tolerance) {
    for (int i=0;i<symbols.size();i++) {
      //check the first symbol where the cursor lies
      if ( ((AbstractSymbol)symbols.get(i)).contains(p,tolerance) )
        return (AbstractSymbol)symbols.get(i);
    }
    return null;//mouse clicked on area without symbols
  }

  public AbstractSymbol getClipboard() {
    return clipboard;
  }

  public void setClipboard(AbstractSymbol c) {
    clipboard = c;
    director.clipboardChanged(clipboard);
  }

  public void undo() {
    // retrieves the last action performed
    Action last = actionStack.popLastAction();
    if (last!=null)
      last.undo();
    director.actionArrayChanged();
  }

  public void setDirector(Director d) {
    director = d;
  }

  public Director getDirector() {
    return director;
  }

  public ActionStack getActionStack() {
    return actionStack;
  }

  public void setSymbolToAdd(AbstractSymbol as) {
    symbolToAdd = as;
    director.setAddMode(as==null);//when adding all is disabled
    if (as!=null)
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    else
      setCursor(Cursor.getDefaultCursor());
  }

  public void editSelectedSymbolProperties() {
    selectedSymbol.editProperties();
    repaint();
  }

  public AbstractSymbol rotateSymbolInClipboard() {
    selectedSymbol.rotate();
    repaint();
    return selectedSymbol;
  }

  public void exit() {
    int userSays = JOptionPane.showConfirmDialog(null,"save before exiting ?","Exits from the program",JOptionPane.YES_NO_CANCEL_OPTION);
    if (userSays==JOptionPane.CANCEL_OPTION) return;
    else if (userSays==JOptionPane.YES_OPTION)
      save();
    System.exit(0);
  }

  public void save() {
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (selectedSymbol!=null)
      selectedSymbol.setSelected(false);//deselect the old one before saving the bunch
    try {
      java.io.ObjectOutputStream out =
        new java.io.ObjectOutputStream(new java.io.FileOutputStream(getName()));
      out.writeObject(symbols);
      out.writeObject(options);
    } catch (Exception exc) {
      System.out.println("ActionRepository.Save() "+exc);
    }
    repaint();
    setCursor(Cursor.getDefaultCursor());
//System.out.println("Draw.save() draw="+symbols);
  }

  public String toString() {
    return getClass().getName()+", #symbols="+symbols.size();
  }

  @SuppressWarnings("unchecked")
public void load(String fileName) {
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      java.io.ObjectInputStream in =
        new java.io.ObjectInputStream(new java.io.FileInputStream(fileName));
      Object ob = in.readObject();
      symbols = (ArrayList<AbstractSymbol>) ob;
      ob = in.readObject();
      options = (Options) ob;
    } catch (Exception exc) {
      System.out.println("Draw.load "+exc);
    }
    actionStack.removeAllElements();
    director.actionArrayChanged();
    repaint();
    setCursor(Cursor.getDefaultCursor());
  }

  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex!=0) return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D)graphics;
    g2.drawString("JDrawing 1.0-" + getName(),16,16);
    paint(g2);
    return PAGE_EXISTS;
  }

  public void newDraw() {
    //you can decide whether clean up this Draw or create another Frame.. here we use the same Draw
    symbols = new ArrayList<AbstractSymbol>();
    setClipboard(null);
    setSymbolToAdd(null);
    repaint();
  }

  public void editOptions() {
    options.editProperties();
  }

  public Options getOptions() {
    return options;
  }
}
