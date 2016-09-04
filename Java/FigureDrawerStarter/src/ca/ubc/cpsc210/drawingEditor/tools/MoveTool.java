package ca.ubc.cpsc210.drawingEditor.tools;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import ca.ubc.cpsc210.drawingEditor.editor.DrawingEditor;
import ca.ubc.cpsc210.drawingEditor.figures.Figure;

/**
 * Represents tools for moving a figure in the drawing editor
 */
public class MoveTool extends Tool {

	private Figure figureToMove;
	private Point start;

	/**
	 * Constructor
	 * @param editor  the drawing editor
	 * @param parent  the parent component to which this tool will be added
	 */
	public MoveTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		figureToMove = null;
		start = null;
	}

	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Move");
		addToParent(parent);
	}

	@Override
	protected void addListener() {
		button.addActionListener(new MoveToolClickHandler());
	}

	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		figureToMove = editor.getFigureInDrawing(e.getPoint());
		if (figureToMove != null) {
			figureToMove.select();
			start = e.getPoint();
		}
	}

	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (figureToMove != null) {
			figureToMove.unselect();
			figureToMove = null;
		}
	}

	@Override
	public void mouseClickedInDrawingArea(MouseEvent e) {
	}

	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (figureToMove != null) {
			int dx = (int) (e.getPoint().getX() - start.getX());
			int dy = (int) (e.getPoint().getY() - start.getY());
			start = e.getPoint();
			figureToMove.translate(dx, dy);
		}
	}
	
	/**
	 * Listener for click events on this move tool
	 */
	private class MoveToolClickHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(MoveTool.this);
		}
	}
}
