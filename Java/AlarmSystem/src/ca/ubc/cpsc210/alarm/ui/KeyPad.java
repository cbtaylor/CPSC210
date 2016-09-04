package ca.ubc.cpsc210.alarm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.ubc.cpsc210.alarm.model.AlarmCode;

/**
 * Represents the keypad in the alarm system
 */
public class KeyPad extends JPanel implements KeyListener {
	private static final String CLR_STR = "CLR";
	private JButton[] keys;
	private JLabel label;
	private String code;
	private ClickHandler keyHandler;
	
	/**
	 * Constructor creates keypad and code display area.
	 */
	public KeyPad() {
		code = new String();
		keyHandler = new ClickHandler();
		setLayout(new BorderLayout());
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4,3));
		addButtons(keyPanel);
		add(keyPanel, BorderLayout.CENTER);
		label = new JLabel(getLabel());
		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalGlue());
		hbox.add(label);
		hbox.add(Box.createHorizontalGlue());
		add(hbox, BorderLayout.SOUTH);
	}
	
	/**
	 * Gets code entered.
	 * @return  code entered
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Clears the code entered on the keypad
	 */
	public void clearCode() {
		code = new String();
		label.setText(getLabel());
		label.repaint();
	}
	
	/**
	 * Adds buttons to button panel
	 * @param p  the button panel
	 */
	private void addButtons(JPanel p) {
		keys = new JButton[12];
		
		for(int i = 0; i < 9; i++) {
			keys[i] = new JButton(Integer.toString(i+1));
			keys[i].addActionListener(keyHandler);
			p.add(keys[i]);
		}
		
		keys[9] = new JButton(CLR_STR);
		keys[9].addActionListener(keyHandler);
		p.add(keys[9]);
		keys[10] = new JButton("0");
		keys[10].addActionListener(keyHandler);
		p.add(keys[10]);
		keys[11] = new JButton("#");
		keys[11].addActionListener(keyHandler);
		p.add(keys[11]);
	}
	
	/**
	 * Gets label for code display area
	 * @return  label for code display area
	 */
	private String getLabel() {
		String label = new String();
		label = label + " ";
		for (int i = 0; i < code.length(); i++) {
			label = label + "X ";
		}
		
		for (int i = code.length(); i < AlarmCode.NUM_DIGITS; i++) {
			label = label + "_ ";
		}
		
		return label;
	}
	
	/**
	 * A listener for key events.
	 */
	private class ClickHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			
			if (src.getText().equals(CLR_STR)) {
				code = new String();
			}
			else if (code.length() < AlarmCode.NUM_DIGITS) {
				code = code + src.getText();
			}
			
			label.setText(getLabel());
			label.repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		char key = ke.getKeyChar();
		
		if (key == '0')
			keys[10].doClick();
		else if (key > '0' && key <= '9')
			keys[ke.getKeyChar() - '1'].doClick();
	}
}
