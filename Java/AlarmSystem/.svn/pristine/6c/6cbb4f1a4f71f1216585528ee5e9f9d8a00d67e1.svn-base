package ca.ubc.cpsc210.alarm.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import ca.ubc.cpsc210.alarm.model.Remote;
import ca.ubc.cpsc210.alarm.model.Sensor;
import ca.ubc.cpsc210.alarm.model.exception.SystemNotReadyException;

/**
 * Represents user interface for remote control.
 */
public class RemoteUI extends JInternalFrame {
	private static final int WIDTH = 175;
	private static final int HEIGHT = 75;
	private static final int LOC = 100;
	private static int sensorCount = 0;
	private Sensor theSensor;
	private JButton armBtn;
	private Remote theRemote;
	private Component theParent;
	
	/**
	 * Constructor
	 * @param r   the remote control
	 * @param parent  the parent component
	 */
	public RemoteUI(Remote r, Component parent) {
		super("Remote", false, false, false, false);
		theRemote = r;
		theParent = parent;
		armBtn = new JButton(new ArmAction());
		add(armBtn);
		setSize(WIDTH, HEIGHT);
		setPosition(parent);
		sensorCount++;
		setVisible(true);
	}
	
	/**
	 * Sets the position of this remote control UI relative to parent component
	 * @param parent   the parent component
	 */
	private void setPosition(Component parent) {
		setLocation(parent.getWidth() - getWidth(), 0);
	}
	
	/**
	 * Represents the action to be taken when the system is armed using
	 * the button on the remote control
	 */
	private class ArmAction extends AbstractAction {
		ArmAction() {
			super("Arm");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			try {
				theRemote.activate();
			} catch (SystemNotReadyException e) {
				JOptionPane.showMessageDialog(theParent, e.getMessage());
			}
		}
	}
}
