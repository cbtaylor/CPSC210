package ca.ubc.cpsc210.alarm.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import ca.ubc.cpsc210.alarm.model.Sensor;

/**
 * Represents user interface for sensors.
 */
public class SensorUI extends JInternalFrame {
	private static final int WIDTH = 175;
	private static final int HEIGHT = 75;
	private static final int LOC = 100;
	private static int sensorCount = 0;
	private Sensor theSensor;
	private JButton openClose;
	
	/**
	 * Constructor sets up user interface for a given sensor
	 * @param s   the sensor
	 * @param parent  the parent component
	 */
	public SensorUI(Sensor s, Component parent) {
		super(s.getLocation(), false, false, false, false);
		theSensor = s;
		openClose = new JButton(new OpenCloseAction());
		add(openClose);
		setSize(WIDTH, HEIGHT);
		setPosition(parent);
		sensorCount++;
		setVisible(true);
	}
	
	/**
	 * Sets the position of this sensor UI relative to parent component
	 * @param parent  the parent component
	 */
	private void setPosition(Component parent) {
		setLocation(LOC * sensorCount, parent.getHeight() / 2 + LOC * sensorCount / 5);
	}
	
	/**
	 * Represents the action to be taken when sensor is opened or closed
	 */
	private class OpenCloseAction extends AbstractAction {
		OpenCloseAction() {
			super("Close");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (theSensor.isOpen()) {
				theSensor.close();
				openClose.setText("Close");
			}
			else {
				theSensor.open();
				openClose.setText("Open");
			}
		}
	}
}
