package ca.ubc.cpsc210.trafficlight.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.ubc.cpsc210.trafficlight.model.TrafficLight;

@SuppressWarnings("serial")
public class IntersectionGUI extends JFrame {
	private static final String STATUS_OK = "System OK";
	private TrafficLight light;
	private TrafficLightGUI trafficLightArea;
	private JLabel statusLabel;
	
	public IntersectionGUI() {
		super("Intersection UI");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		statusLabel = new JLabel(STATUS_OK);
		add(statusLabel, BorderLayout.NORTH);

		// Create the intersection and GUI for intersection
		createIntersection();

		JButton advanceButton = new JButton("Advance");
		advanceButton.setActionCommand("advance");
		advanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				light.advance();
				drawLights();
			}
		});
		
		add(advanceButton, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	public void setLight(String colour) { 
		light.setColour(colour);
		drawLights();
	}

	private void createIntersection() {
		light = new TrafficLight();
		trafficLightArea = new TrafficLightGUI(this);
		add(trafficLightArea, BorderLayout.CENTER);
	}

	private void drawLights() {
		trafficLightArea.setLight(light.getColour());
		validate();
		repaint();
	}

	public static void main(String[] args) {
		new IntersectionGUI();
	}
}
