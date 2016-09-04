/*
 * JDrawing 1.0
 *
 * Copyright 2000 by Earthweb Inc., Oct. 2000
 * All rights reserved.
 *
 */
package com.marinilli.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Concrete implementation of a bitmap icon symbol, for the JDrawing Editor.
 * @see AbstractIcon
 * @author Mauro Marinilli
 * @version 1.0 Oct 2000
 */
public class BitmapIcon extends AbstractIcon {
  private ImageIcon image;
  public BitmapIcon() {
    setImage(IconRepository.COPY_ICON);
  }

  public void draw(Graphics2D g2){
    if(getImage()==null) return;
    g2.rotate(rotationAngle, getLocation().getX(), getLocation().getY());
    g2.drawImage(getImage().getImage() ,(int)(getLocation().getX()-width/2),(int)(getLocation().getY()-height/2),null);

      if(isEditMode()||isSelected()){
        AffineTransform at=new AffineTransform();

        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,new float[]{5,5},0));
        g2.draw(at.createTransformedShape(getRectBounds()));
      }
    g2.rotate(-rotationAngle, getLocation().getX(), getLocation().getY());
  }

  public Rectangle2D.Float getRectBounds() {
    return new Rectangle2D.Float(getLocation().x - width/2,
                                 getLocation().y - height/2,
                                 width,
                                 height);
  }

  public void setImage(ImageIcon newImage) {
    image = newImage;
    width = image.getImage().getWidth(null);
    height = image.getImage().getHeight(null);
  }

  public ImageIcon getImage() {
    return image;
  }

  public void editProperties() {
    PropertiesDialog p = new PropertiesDialog(this);
  }

}