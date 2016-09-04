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
 * Repository of ImageIcons for the JDrawing Editor.
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public interface IconRepository {

  public final static String IMAGES_DIR =  System.getProperty("user.dir") + "/images/";//directory in the jar file (must be in classpath)
  // icons
  public static final ImageIcon COPY_ICON = new ImageIcon(IMAGES_DIR + "copy.gif","Copy");
  public static final ImageIcon OPEN_ICON = new ImageIcon(IMAGES_DIR + "open.gif","Open");
  public static final ImageIcon SAVE_ICON = new ImageIcon(IMAGES_DIR + "save.gif","Save");
  public static final ImageIcon SAVEAS_ICON = new ImageIcon(IMAGES_DIR + "saveas.gif","Save As..");
  public static final ImageIcon CUT_ICON = new ImageIcon(IMAGES_DIR + "cut.gif","Cut");
  public static final ImageIcon PASTE_ICON = new ImageIcon(IMAGES_DIR + "paste.gif","Paste");
  public static final ImageIcon DELETE_ICON = new ImageIcon(IMAGES_DIR + "delete.gif","Delete");
  public static final ImageIcon UNDO_ICON = new ImageIcon(IMAGES_DIR + "undo.gif","Undo");
  public static final ImageIcon PROPERTIES_ICON = new ImageIcon(IMAGES_DIR + "properties.gif","Properties");
  public static final ImageIcon INFO_ICON = new ImageIcon(IMAGES_DIR + "info.gif","About");
  public static final ImageIcon ADD_ICON = new ImageIcon(IMAGES_DIR + "add.gif","Add");
  public static final ImageIcon ROTATE_ICON = new ImageIcon(IMAGES_DIR + "rotate.gif","Rotate");
  public static final ImageIcon MY_ICON = new ImageIcon(IMAGES_DIR + "M.gif","(M)");
  public static final ImageIcon LOGO = new ImageIcon(IMAGES_DIR + "logo.gif","");
  public static final ImageIcon NEW_ICON = new ImageIcon(IMAGES_DIR + "new.gif","New");
  public static final ImageIcon PRINT_ICON = new ImageIcon(IMAGES_DIR + "print.gif","Print");
  public static final ImageIcon OPTIONS_ICON = new ImageIcon(IMAGES_DIR + "preferences.gif","Preferences");

}
