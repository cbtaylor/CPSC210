package ca.ubc.cs.cpsc210.photo.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;

/**
 * MouseListener that adds a right-click popup menu to JList items
 */
public class PopupMouseListener extends MouseAdapter {
	JList list;
	JPopupMenu menu;

	public PopupMouseListener(JList parentList, JPopupMenu popupMenu){
		list = parentList;
		menu = popupMenu;
	}
	
	public void mousePressed(MouseEvent e) {
		maybePopup(e);
	}
	public void mouseReleased(MouseEvent e) {
		maybePopup(e);
	}

	private void maybePopup(MouseEvent e){
		if(e.isPopupTrigger()){
			int index = list.locationToIndex(e.getPoint());
			if(index != -1){
				list.setSelectedIndex(index);
				menu.show(list, e.getX(), e.getY());
			}
		}
	}
}
