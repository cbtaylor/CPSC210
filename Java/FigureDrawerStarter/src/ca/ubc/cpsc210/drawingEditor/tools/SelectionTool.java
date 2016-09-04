package ca.ubc.cpsc210.drawingEditor.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import ca.ubc.cpsc210.drawingEditor.editor.DrawingEditor;
import ca.ubc.cpsc210.drawingEditor.figures.Figure;

/**
 * Represents selection tools in the drawing application.
 */
public class SelectionTool extends Tool {

	/**
	 * Constructor
	 * 
	 * @param editor
	 *            the drawing editor
	 * @param parent
	 *            the parent component to which this tool will be added
	 */
	public SelectionTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
	}

	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Select");
		addToParent(parent);
	}

	@Override
	protected void addListener() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setActiveTool(SelectionTool.this);
			}
		});
	}

	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {

		Figure figure = editor.getFigureInDrawing(e.getPoint());
		if (figure != null) {
			if (!figure.isSelected())
				figure.select();
			else
				figure.unselect();
		}

	}

	@Override
	public void mouseClickedInDrawingArea(MouseEvent e) {

	}

	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
