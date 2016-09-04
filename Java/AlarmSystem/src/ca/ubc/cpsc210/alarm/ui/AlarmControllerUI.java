package ca.ubc.cpsc210.alarm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ca.ubc.cpsc210.alarm.model.AlarmCode;
import ca.ubc.cpsc210.alarm.model.AlarmController;
import ca.ubc.cpsc210.alarm.model.EventLog;
import ca.ubc.cpsc210.alarm.model.Remote;
import ca.ubc.cpsc210.alarm.model.Sensor;
import ca.ubc.cpsc210.alarm.model.exception.CodeException;
import ca.ubc.cpsc210.alarm.model.exception.DuplicateSensorException;
import ca.ubc.cpsc210.alarm.model.exception.LastCodeException;
import ca.ubc.cpsc210.alarm.model.exception.LogException;
import ca.ubc.cpsc210.alarm.model.exception.NotValidCodeException;
import ca.ubc.cpsc210.alarm.model.exception.SystemNotReadyException;

/**
 * Represents application's main window frame.
 */
public class AlarmControllerUI extends JFrame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String FILE_DESCRIPTOR = "...file";
	private static final String SCREEN_DESCRIPTOR = "...screen";
	private AlarmController ac;
	private KeyPad kp;
	private JComboBox printCombo;
	private JDesktopPane desktop;
	private JInternalFrame controlPanel;
	
	/**
	 * Constructor sets up button panel, key pad and visual alarm status window.
	 */
	public AlarmControllerUI() {
		ac = new AlarmController();
		ac.addAlarmObserver(new AlarmUI());
		ac.addAlarmObserver(new AlarmSiren());
		
		desktop = new JDesktopPane();
		desktop.addMouseListener(new DesktopFocusAction());
		controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
		controlPanel.setLayout(new BorderLayout());
		
		setContentPane(desktop);
		setTitle("CPSC 210: Alarm System Simulator");
		setSize(WIDTH, HEIGHT);
		
		addButtonPanel();
		addMenu();
		addKeyPad();
		addAlarmDisplayPanel();
		
		Remote r = new Remote(ac);
		addRemote(r);
		
		controlPanel.pack();
		controlPanel.setVisible(true);
		desktop.add(controlPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centreOnScreen();
		setVisible(true);
	}
	
	/**
	 * Helper to set up visual alarm status window
	 */
	private void addAlarmDisplayPanel() {
		AlarmUI alarmUI = new AlarmUI();
		ac.addAlarmObserver(alarmUI);
		controlPanel.add(alarmUI, BorderLayout.NORTH);
	}
	
	/**
	 * Helper to add control buttons.
	 */
	private void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,2));
		buttonPanel.add(new JButton(new AddCodeAction()));
		buttonPanel.add(new JButton(new RemoveCodeAction()));
		buttonPanel.add(new JButton(new ArmAction()));
		buttonPanel.add(new JButton(new DisarmAction()));
		buttonPanel.add(new JButton(new AddSensorAction()));
		buttonPanel.add(new JButton(new ClearLogAction()));
		buttonPanel.add(new JButton(new PrintLogAction()));
		buttonPanel.add(createPrintCombo());
		
		controlPanel.add(buttonPanel, BorderLayout.WEST);
	}
	
	/**
	 * Adds menu bar.
	 */
	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu sensorMenu = new JMenu("Sensor");
		sensorMenu.setMnemonic('S');
		addMenuItem(sensorMenu, new AddSensorAction(), 
				KeyStroke.getKeyStroke("control S"));
		menuBar.add(sensorMenu);
		
		JMenu codeMenu = new JMenu("Code");
		codeMenu.setMnemonic('C');
		addMenuItem(codeMenu, new AddCodeAction(), null);
		addMenuItem(codeMenu, new RemoveCodeAction(), null);
		menuBar.add(codeMenu);
		
		JMenu systemMenu = new JMenu("System");	
		systemMenu.setMnemonic('y');
		addMenuItem(systemMenu, new ArmAction(), 
				KeyStroke.getKeyStroke("control A"));
		addMenuItem(systemMenu, new DisarmAction(), 
				KeyStroke.getKeyStroke("control D"));
		menuBar.add(systemMenu);
		
		setJMenuBar(menuBar);
	}

	/**
	 * Adds an item with given handler to the given menu
	 * @param theMenu  menu to which new item is added
	 * @param action   handler for new menu item
	 * @param accel    keystroke accelerator for this menu item
	 */
	private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accel) {
		JMenuItem menuItem = new JMenuItem(action);
		menuItem.setMnemonic(menuItem.getText().charAt(0));
		menuItem.setAccelerator(accel);
		theMenu.add(menuItem);
	}
	
	/**
	 * Adds user interface for remote to the system.
	 * @param r  the remote control
	 */
	private void addRemote(Remote r) {
		RemoteUI rUI = new RemoteUI(r, this);
		desktop.add(rUI);
	}
	
	/**
	 * Helper to create print options combo box
	 * @return  the combo box
	 */
	private JComboBox createPrintCombo() {
		printCombo = new JComboBox();
		printCombo.addItem(FILE_DESCRIPTOR);
		printCombo.addItem(SCREEN_DESCRIPTOR);
		return printCombo;
	}
	
	/**
	 * Helper to add keypad to main application window
	 */
	private void addKeyPad() {
		kp = new KeyPad();
		addKeyListener(kp);
		controlPanel.add(kp, BorderLayout.CENTER);
	}
	
	/**
	 * Helper to centre main application window on desktop
	 */
	private void centreOnScreen() {
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
	}
	
	/**
	 * Represents action to be taken when user wants to add a new code
	 * to the system.
	 */
	private class AddCodeAction extends AbstractAction {
		
		AddCodeAction() {
			super("Add Code");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			AlarmCode alarmCode = new AlarmCode(kp.getCode());
			kp.clearCode();
			try {
				ac.addCode(alarmCode);		
			} catch (NotValidCodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Represents the action to be taken when the user wants to add a new
	 * sensor to the system.
	 */
	private class AddSensorAction extends AbstractAction {
		
		AddSensorAction() {
			super("Add Sensor");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			String sensorLoc = JOptionPane.showInputDialog(null,
					  "Sensor location?",
					  "Enter sensor location",
					  JOptionPane.QUESTION_MESSAGE);
			try {
				if (sensorLoc != null) {
					Sensor s = new Sensor(sensorLoc, ac);
					desktop.add(new SensorUI(s, AlarmControllerUI.this));
				}
			} catch (DuplicateSensorException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Represents the action to be taken when the user wants to remove
	 * a code from the system.
	 */
	private class RemoveCodeAction extends AbstractAction {
		
		RemoveCodeAction() {
			super("Remove Code");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			AlarmCode alarmCode = new AlarmCode(kp.getCode());
			kp.clearCode();
			try {
				ac.removeCode(alarmCode);
			} catch (NotValidCodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error", 
						JOptionPane.ERROR_MESSAGE);
			} catch (CodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (LastCodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Represents the action to be taken when the user wants to arm
	 * the system.
	 */
	private class ArmAction extends AbstractAction {
		
		ArmAction() {
			super("Arm System");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			AlarmCode alarmCode = new AlarmCode(kp.getCode());
			kp.clearCode();
			try {
				ac.arm(alarmCode);
			} catch (SystemNotReadyException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (CodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Represents the action to be taken when the user wants to 
	 * disarm the system.
	 */
	private class DisarmAction extends AbstractAction {
		
		DisarmAction() {
			super("Disarm System");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			AlarmCode alarmCode = new AlarmCode(kp.getCode());
			kp.clearCode();
			
			try {
				ac.disarm(alarmCode);
			} catch (CodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Represents the action to be taken when the user wants to
	 * print the event log.
	 */
	private class PrintLogAction extends AbstractAction {
		PrintLogAction() {
			super("Print log to...");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			String selected = (String) printCombo.getSelectedItem();
			LogPrinter lp;
			try {
				if (selected.equals(FILE_DESCRIPTOR)) 
					lp = new FilePrinter();
				else {
					lp = new ScreenPrinter(AlarmControllerUI.this); 
					desktop.add((ScreenPrinter) lp);
				}
				
				lp.printLog(EventLog.getInstance());
			} catch (LogException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Represents the action to be taken when the user wants to
	 * clear the event log.
	 */
	private class ClearLogAction extends AbstractAction {
		ClearLogAction() {
			super("Clear log");
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			EventLog.getInstance().clear();
		}
	}
	
	/**
	 * Represents action to be taken when user clicks desktop
	 * to switch focus. (Needed for key handling.)
	 */
	private class DesktopFocusAction extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			AlarmControllerUI.this.requestFocusInWindow();
		}
	}
	
	// starts the application
	public static void main(String[] args) {
		new AlarmControllerUI();
	}
}
