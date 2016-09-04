package ca.ubc.cpsc210.todo.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ca.ubc.cpsc210.todo.model.ScheduledTodoItem;
import ca.ubc.cpsc210.todo.model.TodoItem;
import ca.ubc.cpsc210.todo.model.TodoList;
import ca.ubc.cpsc210.todo.model.UnscheduledTodoItem;

/*
 * Renders and handles all interactions between UI and the TodoList
 * 
 * NOTE: This class needs to be broken into more pieces. A common problem is that UI classes
 * can be large and unwieldy.
 * 
 * NOTE: YOU DO NOT NEED TO CHANGE THIS CLASS! ALTHOUGH THERE ARE ERRORS IN THIS CLASS WHEN 
 * YOU CHECK THE PROJECT OUT, THEY WILL BE RESOLVED WHEN YOU IMPLEMENT YOUR CHANGES TO THE
 * TYPE HIERARCHY.
 * 
 */
public class TodoListUI {

	// ---------- FIELDS --------------

	private static Integer APPWIDTH = 400;
	private static Integer APPHEIGHT = 610;

	private TodoList todoList;
	private TodoItem currOpenTodoItem;

	// --- Application Wrapper ---------
	private JFrame applicationFrame;
	private JLayeredPane layeredPane;

	// --- Main View Panel Components ---
	private JPanel mainView;

	private JPanel topNav; // Contains navBar and tabBar

	private JPanel navBar;
	private JPanel rightButtonGroupNavBar; // Contains + and search buttons

	private JPanel tabBar;
	private JButton allTab;
	private JButton incompleteTab;
	private JButton scheduledTab;
	private boolean incompleteTabSelected;
	private boolean scheduledTabSelected;

	private JPanel searchBar;
	private JTextField fieldSearch;

	private JList listView;
	private DefaultListModel todoListModel;
	private JPanel todoListScrollerWrapper;
	private ScrollableTodoListContainer todoListScroller;

	private JPanel messageBar;
	private JLabel messageLabel;

	// --- Add/Edit TodoItem View Panel Components ---
	private JPanel editView;

	private JPanel editBar;
	private JButton setItemCompletedButton;
	private JPanel rightButtonGroupEditBar;

	private JPanel formContainer;
	private JTextField fieldTitle;
	private JTextArea fieldDesc;
	private JRadioButton allowDueDate;
	private JRadioButton highPriority;
	private JLabel labelTime;
	private JSpinner timeSpinner;
	private JLabel labelDay;
	private JSpinner daySpinner;

	// --- Details View Panel Components ---
	private JPanel detailsView;

	private JPanel detailsBar;
	private JPanel rightButtonGroupDetailsBar;

	private JPanel detailsContainer;
	private JLabel detailsTitleLabel;
	private JLabel detailsCompletedIndicator;
	private JLabel detailsDueTime;
	private JLabel detailsDueDate;
	private JTextArea detailsDescriptionDetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodoListUI window = new TodoListUI();
					window.applicationFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TodoListUI() {
		todoList = new TodoList();
		initialize();
	}

	/**
	 * Initialize the contents of the application.
	 */
	private void initialize() {

		initializeApplicationFrame();

		// ------------------------------------------------------------------
		// To-do Item Main Window

		initializeSearchBar();
		initializeNavBar();
		initializeTabBar();
		initializeTopNavWrapper();
		initializeNoItemsMessageBar();
		initializeTodoList();

		// ------------------------------------------------------------------
		// To-do Item Details Window

		initializeDetailsBar();
		initializeDetailsContainer();
		initializeCompletionIndicator();
		initializeDueDate();
		initializeDescription();

		// ------------------------------------------------------------------
		// Add/Edit Window
		initializeEditBar();
		initializeEditContainer();

		// ------------------------------------------------------------------
		// Adds the 3 Windows into Application
		populateApplicationFrame();
	}

	/**
	 * Puts the 3 views (main, edit/add item, details) onto the application
	 * frame
	 */
	private void populateApplicationFrame() {
		layeredPane = new JLayeredPane();

		detailsView = new JPanel();
		detailsView.setSize(new Dimension(APPWIDTH, APPHEIGHT));
		detailsView.setLayout(new BorderLayout());
		detailsView.add(detailsBar, BorderLayout.NORTH);
		detailsView.add(detailsContainer);
		detailsView.setVisible(false);

		editView = new JPanel();
		editView.setSize(new Dimension(APPWIDTH, APPHEIGHT));
		editView.setLayout(new BorderLayout());
		editView.add(editBar, BorderLayout.NORTH);
		editView.add(formContainer);
		editView.setVisible(false);

		mainView = new JPanel();
		mainView.setSize(new Dimension(APPWIDTH, APPHEIGHT));
		mainView.setLayout(new BorderLayout());
		mainView.add(topNav, BorderLayout.NORTH);
		mainView.add(todoListScrollerWrapper);
		mainView.setVisible(true);

		layeredPane.add(detailsView, 3);
		layeredPane.add(editView, 2);
		layeredPane.add(mainView, 1);

		applicationFrame.getContentPane().add(layeredPane);
	}

	/**
	 * Creates the edit container in the edit/add item view
	 */
	private void initializeEditContainer() {
		formContainer = new JPanel();
		formContainer.setBackground(Color.WHITE);
		formContainer.setAlignmentX(SwingConstants.LEFT);
		formContainer.setPreferredSize(new Dimension(APPWIDTH, 1000));
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));

		JPanel formPurposeContainer = new JPanel();
		formPurposeContainer.setLayout(new BoxLayout(formPurposeContainer,
				BoxLayout.Y_AXIS));
		formPurposeContainer.setBackground(new Color(230, 230, 230));
		formPurposeContainer.setAlignmentX(SwingConstants.LEFT);
		formPurposeContainer.setPreferredSize(new Dimension(APPWIDTH, 50));

		JLabel titleBar = new JLabel("Add Item", JLabel.LEFT);
		titleBar.setFont(new Font("Helvetica", Font.PLAIN, 19));
		titleBar.setForeground(new Color(165, 165, 165));
		titleBar.setBorder(new EmptyBorder(10, 10, 10, 0));
		titleBar.setMaximumSize(new Dimension(APPWIDTH, 50));
		formPurposeContainer.add(titleBar);
		formContainer.add(formPurposeContainer);

		JPanel formTitleContainer = new JPanel();
		formTitleContainer.setLayout(new BoxLayout(formTitleContainer,
				BoxLayout.Y_AXIS));
		formTitleContainer.setBackground(Color.WHITE);
		formTitleContainer.setAlignmentX(SwingConstants.LEFT);
		formTitleContainer.setBorder(new EmptyBorder(0, 10, 10, 10));
		formTitleContainer.setPreferredSize(new Dimension(APPWIDTH - 10, 60));

		JLabel labelTitle = new JLabel("TASK NAME", JLabel.LEFT);
		labelTitle.setForeground(new Color(7, 90, 124));
		labelTitle.setFont(new Font("Helvetica", Font.PLAIN, 14));
		labelTitle.setAlignmentX(SwingConstants.LEFT);
		labelTitle.setMaximumSize(new Dimension(APPWIDTH, 40));
		labelTitle.setBorder(new EmptyBorder(10, 10, 0, 10));

		fieldTitle = new JTextField("");
		fieldTitle.setFont(new Font("Helvetica", Font.PLAIN, 12));
		fieldTitle.setMaximumSize(new Dimension(APPWIDTH - 10, 40));
		formTitleContainer.add(fieldTitle);

		JPanel formDescContainer = new JPanel();
		formDescContainer.setLayout(new BoxLayout(formDescContainer,
				BoxLayout.Y_AXIS));
		formDescContainer.setBackground(Color.WHITE);
		formDescContainer.setAlignmentX(SwingConstants.LEFT);
		formDescContainer.setBorder(new EmptyBorder(0, 10, 10, 10));
		formDescContainer.setPreferredSize(new Dimension(APPWIDTH - 10, 140));

		JLabel labelDesc = new JLabel("DESCRIPTION", JLabel.LEFT);
		labelDesc.setForeground(new Color(7, 90, 124));
		labelDesc.setFont(new Font("Helvetica", Font.PLAIN, 14));
		labelDesc.setAlignmentX(SwingConstants.LEFT);
		labelDesc.setMaximumSize(new Dimension(APPWIDTH, 40));
		labelDesc.setBorder(new EmptyBorder(10, 10, 0, 10));

		fieldDesc = new JTextArea(3, 30);
		JScrollPane fieldDescScroll = new JScrollPane(fieldDesc);
		fieldDescScroll.setMaximumSize(new Dimension(APPWIDTH - 10, 80));
		fieldDesc.setFont(new Font("Helvetica", Font.PLAIN, 12));
		formDescContainer.add(fieldDescScroll);

		JPanel formTimeContainer = new JPanel();
		formTimeContainer.setLayout(new BoxLayout(formTimeContainer,
				BoxLayout.Y_AXIS));
		formTimeContainer.setBackground(Color.WHITE);
		formTimeContainer.setAlignmentX(SwingConstants.LEFT);
		formTimeContainer.setBorder(new EmptyBorder(0, 10, 10, 10));
		formTimeContainer.setPreferredSize(new Dimension(APPWIDTH - 10, 50));

		highPriority = new JRadioButton("High priority");
		highPriority.setForeground(new Color(7, 90, 124));
		highPriority.setBackground(Color.WHITE);
		highPriority.setFont(new Font("Helvetica", Font.PLAIN, 14));
		highPriority.setAlignmentX(SwingConstants.LEFT);
		highPriority.setMaximumSize(new Dimension(APPWIDTH, 40));
		highPriority.setBorder(new EmptyBorder(10, 10, 0, 10));

		allowDueDate = new JRadioButton("Set completion time and date");
		allowDueDate.setForeground(new Color(7, 90, 124));
		allowDueDate.setBackground(Color.WHITE);
		allowDueDate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		allowDueDate.setAlignmentX(SwingConstants.LEFT);
		allowDueDate.setMaximumSize(new Dimension(APPWIDTH, 40));
		allowDueDate.setBorder(new EmptyBorder(10, 10, 0, 10));
		allowDueDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (allowDueDate.isSelected()) {
					timeSpinner.setEnabled(true);
					labelTime.setEnabled(true);
					daySpinner.setEnabled(true);
					labelDay.setEnabled(true);
					highPriority.setSelected(false);
					highPriority.setEnabled(false);
				} else {
					timeSpinner.setEnabled(false);
					labelTime.setEnabled(false);
					daySpinner.setEnabled(false);
					labelDay.setEnabled(false);
					highPriority.setEnabled(true);
					highPriority.setSelected(false);
				}

			}
		});

		labelTime = new JLabel("COMPLETION TIME", JLabel.LEFT);
		labelTime.setForeground(new Color(7, 90, 124));
		labelTime.setFont(new Font("Helvetica", Font.PLAIN, 14));
		labelTime.setAlignmentX(SwingConstants.LEFT);
		labelTime.setMaximumSize(new Dimension(APPWIDTH, 40));
		labelTime.setBorder(new EmptyBorder(10, 10, 0, 10));

		timeSpinner = new JSpinner(new SpinnerDateModel());
		timeSpinner.setMaximumSize(new Dimension(APPWIDTH - 10, 40));
		timeSpinner.setPreferredSize(new Dimension(APPWIDTH - 10, 40));
		timeSpinner.setFont(new Font("Helvetica", Font.PLAIN, 14));
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
		timeSpinner.setEnabled(false);

		formTimeContainer.add(timeSpinner);

		JPanel formDayContainer = new JPanel();
		formDayContainer.setLayout(new BoxLayout(formDayContainer,
				BoxLayout.Y_AXIS));
		formDayContainer.setBackground(Color.WHITE);
		formDayContainer.setAlignmentX(SwingConstants.LEFT);
		formDayContainer.setBorder(new EmptyBorder(0, 10, 10, 10));
		formDayContainer.setPreferredSize(new Dimension(APPWIDTH - 10, 60));

		daySpinner = new JSpinner(new SpinnerDateModel());
		daySpinner.setMaximumSize(new Dimension(APPWIDTH - 10, 40));
		daySpinner.setFont(new Font("Helvetica", Font.PLAIN, 14));
		JSpinner.DateEditor dayEditor = new JSpinner.DateEditor(daySpinner,
				"dd MMMM yyyy");
		daySpinner.setEditor(dayEditor);
		daySpinner.setValue(new Date()); // will only show the current time
		daySpinner.setEnabled(false);

		labelDay = new JLabel("COMPLETION DATE", JLabel.LEFT);
		labelDay.setForeground(new Color(7, 90, 124));
		labelDay.setFont(new Font("Helvetica", Font.PLAIN, 14));
		labelDay.setAlignmentX(SwingConstants.LEFT);
		labelDay.setMaximumSize(new Dimension(APPWIDTH, 40));
		labelDay.setBorder(new EmptyBorder(10, 10, 0, 10));

		formDayContainer.add(daySpinner);

		formContainer.add(labelTitle);
		formContainer.add(formTitleContainer);

		formContainer.add(labelDesc);
		formContainer.add(formDescContainer);

		formContainer.add(highPriority);

		formContainer.add(allowDueDate);

		formContainer.add(labelTime);
		formContainer.add(formTimeContainer);

		formContainer.add(labelDay);
		formContainer.add(formDayContainer);
	}

	/**
	 * Creates the top edit bar in the edit/add view (ie. Save button)
	 */
	private void initializeEditBar() {
		editBar = new JPanel();
		editBar.setBackground(new Color(7, 90, 124));
		editBar.setPreferredSize(new Dimension(APPWIDTH, 53));
		editBar.setAlignmentX(SwingConstants.LEFT);
		editBar.setLayout(new BorderLayout());

		try {
			// Tries to create and add the 'back' button to top details

			BufferedImage imgBack = ImageIO.read(new File("img/ic_back.png"));
			JButton backEditButton = new JButton("Back", new ImageIcon(imgBack));
			backEditButton.setBorder(BorderFactory.createEmptyBorder());
			backEditButton.setContentAreaFilled(false);
			backEditButton.setBorder(new EmptyBorder(10, 0, 10, 0));
			backEditButton.setForeground(Color.WHITE);
			backEditButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
			backEditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			editBar.add(backEditButton, BorderLayout.WEST);
			backEditButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Back button pressed");

					detailsView.setVisible(false);
					editView.setVisible(false);
					mainView.setVisible(true);
					applicationFrame.repaint();

					fieldTitle.setText("");
					fieldTitle.repaint();
					fieldDesc.setText("");
					fieldDesc.repaint();
				}
			});

		} catch (IOException e) {
			System.err.println("Could not add back button");
		}

		// Creates the button group that will be appended to the top editbar
		rightButtonGroupEditBar = new JPanel();
		rightButtonGroupEditBar.setLayout(new FlowLayout(FlowLayout.LEADING,
				10, 0));
		rightButtonGroupEditBar.setBackground(new Color(7, 90, 124));

		try {
			// Tries to create and add the 'check' button to top editbar
			Image imgCheck = ImageIO.read(new File("img/ic_save.png"));
			ImageIcon iconCheck = new ImageIcon(imgCheck);
			JButton finishedIcon = new JButton("Save", iconCheck);
			finishedIcon.setBackground(new Color(7, 90, 124));
			finishedIcon.setForeground(Color.WHITE);
			finishedIcon.setFont(new Font("Helvetica", Font.PLAIN, 14));
			finishedIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
			finishedIcon.setBorderPainted(false);
			finishedIcon.setContentAreaFilled(false);
			finishedIcon.setFocusPainted(false);
			finishedIcon.setOpaque(false);

			rightButtonGroupEditBar.add(finishedIcon);
			finishedIcon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Done button pressed");

					String title = fieldTitle.getText();
					String description = fieldDesc.getText();

					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
					SimpleDateFormat dayFormat = new SimpleDateFormat(
							"dd MMMM yyyy");
					SimpleDateFormat fullDayFormat = new SimpleDateFormat(
							"HH:mm dd MMMM yyyy");
					Date hourDueRaw = (Date) timeSpinner.getValue();
					String hourDue = timeFormat.format(hourDueRaw);
					Date dayDueRaw = (Date) daySpinner.getValue();
					String dayDue = dayFormat.format(dayDueRaw);
					Date dueDate;
					try {
						dueDate = fullDayFormat.parse(hourDue + " " + dayDue);
					} catch (ParseException e1) {
						System.out.println("Error parsing due date");
						dueDate = new Date(System.currentTimeMillis()); // Placeholder
					}

					if (currOpenTodoItem == null) {
						if (allowDueDate.isSelected()) {
							ScheduledTodoItem scheduledItem = new ScheduledTodoItem(title, description, dueDate);
							todoList.addTodoItem(scheduledItem);
						} else {
							UnscheduledTodoItem item = new UnscheduledTodoItem(title, description, highPriority.isSelected());
							todoList.addTodoItem(item);
						}
					} else {
						if (allowDueDate.isSelected()) {
							ScheduledTodoItem scheduledItem = (ScheduledTodoItem) todoList.getTodoItemById(currOpenTodoItem.getId());
							scheduledItem.setTitle(title);
							scheduledItem.setDescription(description);
							scheduledItem.setDueDate(dueDate);
			
						} else {
							UnscheduledTodoItem item = (UnscheduledTodoItem) todoList.getTodoItemById(currOpenTodoItem.getId());
							item.setTitle(title);
							item.setDescription(description);
							item.setHighPriority(highPriority.isSelected());
						}
					}

					if (incompleteTabSelected) {
						displayIncompleteItems();
					} else if (scheduledTabSelected) {
						displayScheduledItems();
					} else {
						displayAllItems();
					}

					fieldTitle.setText("");
					fieldDesc.setText("");

					detailsView.setVisible(false);
					editView.setVisible(false);
					mainView.setVisible(true);
					applicationFrame.repaint();
				}
			});
		} catch (IOException e) {
			System.err.println("Could not add done button");
		}

		editBar.add(rightButtonGroupEditBar, BorderLayout.EAST);
	}

	/**
	 * Finds and renders the description of an item
	 */
	private void initializeDescription() {
		// ------- Description Row ----------------
		JPanel detailsDescriptionContainer = new JPanel();
		detailsDescriptionContainer.setBackground(Color.WHITE);
		detailsDescriptionContainer.setLayout(new BorderLayout());
		detailsDescriptionContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
		detailsDescriptionContainer.setPreferredSize(new Dimension(250, 200));

		JLabel detailsDescriptionLeft = new JLabel("details");
		detailsDescriptionLeft.setFont(new Font("Helvetica", Font.PLAIN, 16));
		detailsDescriptionLeft.setVerticalAlignment(SwingConstants.TOP);
		detailsDescriptionLeft.setForeground(new Color(200, 200, 200));
		detailsDescriptionLeft.setBorder(BorderFactory.createEmptyBorder(20,
				40, 0, 0));

		detailsDescriptionDetails = new JTextArea(0, 19);
		JScrollPane detailsDescriptionRightScroll = new JScrollPane(
				detailsDescriptionDetails);
		detailsDescriptionDetails
				.setFont(new Font("Helvetica", Font.PLAIN, 14));
		detailsDescriptionDetails.setForeground(new Color(7, 90, 124));
		detailsDescriptionDetails.setBackground(Color.WHITE);
		detailsDescriptionDetails.setEditable(false);
		detailsDescriptionDetails.setCursor(null);
		detailsDescriptionDetails.setOpaque(false);
		detailsDescriptionDetails.setFocusable(false);
		detailsDescriptionDetails.setLineWrap(true);
		detailsDescriptionDetails.setWrapStyleWord(true);
		detailsDescriptionDetails.setCaretPosition(0);

		detailsDescriptionRightScroll.setBorder(BorderFactory
				.createEmptyBorder(20, 0, 35, 0));

		detailsDescriptionContainer.add(detailsDescriptionLeft,
				BorderLayout.WEST);
		detailsDescriptionContainer.add(detailsDescriptionRightScroll,
				BorderLayout.EAST);

		detailsContainer.add(detailsDescriptionContainer);
	}

	/**
	 * Finds and renders the due date of the particular item
	 */
	private void initializeDueDate() {
		JPanel detailsDueDateContainer = new JPanel();
		detailsDueDateContainer.setBackground(Color.WHITE);
		detailsDueDateContainer.setMaximumSize(new Dimension(APPWIDTH, 40));
		detailsDueDateContainer.setLayout(new BorderLayout());
		detailsDueDateContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

		JLabel detailsDueDateLeft = new JLabel("due on", JLabel.LEFT);
		detailsDueDateLeft.setFont(new Font("Helvetica", Font.PLAIN, 16));
		detailsDueDateLeft.setForeground(new Color(200, 200, 200));
		detailsDueDateLeft.setBorder(BorderFactory.createEmptyBorder(5, 40, 0,
				0));

		detailsDueTime = new JLabel("12:09pm", JLabel.LEFT);
		detailsDueTime.setFont(new Font("Helvetica", Font.PLAIN, 16));
		detailsDueTime.setForeground(new Color(7, 90, 124));
		detailsDueTime.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 180));

		detailsDueDate = new JLabel("May 17, 2013", JLabel.LEFT);
		detailsDueDate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		detailsDueDate.setForeground(new Color(190, 190, 190));
		detailsDueDate
				.setBorder(BorderFactory.createEmptyBorder(10, 133, 0, 0));

		detailsDueDateContainer.add(detailsDueDateLeft, BorderLayout.WEST);
		detailsDueDateContainer.add(detailsDueTime, BorderLayout.EAST);
		detailsDueDateContainer.add(detailsDueDate, BorderLayout.SOUTH);

		detailsContainer.add(detailsDueDateContainer);
	}

	/**
	 * Creates the indicator in details view of whether an item is completed or
	 * not
	 */
	private void initializeCompletionIndicator() {

		JPanel completedTextContainer = new JPanel();
		completedTextContainer.setBackground(new Color(0, 173, 239));
		completedTextContainer.setMaximumSize(new Dimension(APPWIDTH, 60));
		completedTextContainer.setLayout(new BorderLayout());

		detailsCompletedIndicator = new JLabel("Not Completed", JLabel.CENTER);
		detailsCompletedIndicator.setForeground(Color.WHITE);
		detailsCompletedIndicator
				.setFont(new Font("Helvetica", Font.PLAIN, 16));
		detailsCompletedIndicator.setBorder(new EmptyBorder(10, 0, 10, 0));
		detailsCompletedIndicator.setBackground(new Color(0, 173, 239));
		detailsCompletedIndicator.setOpaque(true);
		detailsCompletedIndicator
				.setToolTipText("Click the check icon above to mark this item as completed");
		completedTextContainer.add(detailsCompletedIndicator);

		detailsContainer.add(completedTextContainer);

		JPanel detailsTitleContainer = new JPanel();
		detailsTitleContainer.setLayout(new BorderLayout());
		detailsTitleContainer.setBackground(Color.WHITE);
		detailsTitleContainer.setMaximumSize(new Dimension(APPWIDTH, 60));
		detailsTitleContainer.setBorder(new EmptyBorder(40, 0, 0, 0));

		detailsTitleLabel = new JLabel("Item Title #1", JLabel.LEFT);
		detailsTitleLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		detailsTitleLabel.setForeground(new Color(7, 90, 124));

		detailsTitleContainer.add(detailsTitleLabel, BorderLayout.WEST);

		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border listItemSeparator = BorderFactory.createLineBorder(new Color(
				233, 233, 233));
		detailsTitleContainer.setBorder(new CompoundBorder(listItemSeparator,
				paddingBorder));

		detailsContainer.add(detailsTitleContainer);
	}

	/**
	 * Creates and initializes the details view (look at todo item details)
	 */
	private void initializeDetailsContainer() {
		detailsContainer = new JPanel();
		detailsContainer.setBackground(Color.WHITE);
		detailsContainer
				.setMaximumSize(new Dimension(APPWIDTH, APPHEIGHT - 53));
		detailsContainer.setLayout(new BoxLayout(detailsContainer,
				BoxLayout.Y_AXIS));
	}

	/**
	 * Creates and initializes the top details bar
	 */
	private void initializeDetailsBar() {
		detailsBar = new JPanel();
		detailsBar.setBackground(new Color(7, 90, 124));
		detailsBar.setPreferredSize(new Dimension(APPWIDTH, 53));
		detailsBar.setBorder(new EmptyBorder(10, 0, 0, 0));
		detailsBar.setLayout(new BorderLayout());

		try {
			// Tries to create and add the 'back' button to top details

			BufferedImage imgBack = ImageIO.read(new File("img/ic_back.png"));
			JButton backDetailsButton = new JButton("Back", new ImageIcon(
					imgBack));
			backDetailsButton.setBorder(BorderFactory.createEmptyBorder());
			backDetailsButton.setContentAreaFilled(false);
			backDetailsButton.setBorder(new EmptyBorder(0, 0, 10, 0));
			backDetailsButton.setForeground(Color.WHITE);
			backDetailsButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
			backDetailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			detailsBar.add(backDetailsButton, BorderLayout.WEST);
			backDetailsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Back button pressed");

					todoListModel = new DefaultListModel();
					listView.setModel(todoListModel);

					List<TodoItem> todoItems = todoList.getAllTodoItems();
					for (TodoItem item : todoItems) {
						todoListModel.addElement(item);
					}

					listView.repaint();

					if (todoItems.size() == 0) {
						messageBar.setVisible(true);
						messageBar.repaint();
					} else {
						messageBar.setVisible(false);
						messageBar.repaint();
					}

					detailsView.setVisible(false);
					editView.setVisible(false);
					mainView.setVisible(true);
					applicationFrame.repaint();

				}
			});

		} catch (IOException e) {
			System.err.println("Could not add back button");
		}

		// ------------------------------------------------------------------
		// Creates the button group that will be appended to the top edit
		// bar
		rightButtonGroupDetailsBar = new JPanel();
		rightButtonGroupDetailsBar.setLayout(new FlowLayout(FlowLayout.LEADING,
				10, 0));
		rightButtonGroupDetailsBar.setBackground(new Color(7, 90, 124));

		try {
			// Tries to create and add the 'completed' button to top editbar

			BufferedImage imgCompleted = ImageIO.read(new File(
					"img/ic_check.png"));
			setItemCompletedButton = new JButton(new ImageIcon(imgCompleted));
			setItemCompletedButton.setBorder(BorderFactory.createEmptyBorder());
			setItemCompletedButton.setContentAreaFilled(false);
			setItemCompletedButton.setBounds(265, 2, 48, 48);
			setItemCompletedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			setItemCompletedButton
					.setToolTipText("Select the checkmark to mark this item as completed");

			rightButtonGroupDetailsBar.add(setItemCompletedButton);

			setItemCompletedButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Completed button pressed");
					if (currOpenTodoItem.isCompleted()) {
						currOpenTodoItem.setCompleted(false);
						try {
							BufferedImage imgCheck = ImageIO.read(new File(
									"img/ic_check.png"));
							setItemCompletedButton.setIcon(new ImageIcon(
									imgCheck));
							setItemCompletedButton
									.setToolTipText("Click to mark this item as completed");
							setItemCompletedButton.repaint();
						} catch (IOException f) {
							System.err.println("Could not add undo button");
						}
						detailsCompletedIndicator.setText("Not Completed");
						detailsCompletedIndicator.setBackground(new Color(0,
								173, 239));
						detailsCompletedIndicator.repaint();
					} else {
						currOpenTodoItem.setCompleted(true);
						try {
							BufferedImage imgUndo = ImageIO.read(new File(
									"img/ic_undo.png"));
							setItemCompletedButton.setIcon(new ImageIcon(
									imgUndo));
							setItemCompletedButton
									.setToolTipText("Click to mark this item as not completed");
							setItemCompletedButton.repaint();
						} catch (IOException f) {
							System.err.println("Could not add undo button");
						}
						detailsCompletedIndicator.setText("Completed");
						detailsCompletedIndicator.setBackground(new Color(157,
								242, 87));
						detailsCompletedIndicator.repaint();
					}
				}
			});

		} catch (IOException e) {
			System.err.println("Could not add completed button");
		}

		try {
			// Tries to create and add the 'edit' button to top editbar

			BufferedImage imgEdit = ImageIO.read(new File("img/ic_edit.png"));
			JButton editButton = new JButton(new ImageIcon(imgEdit));
			editButton.setBorder(BorderFactory.createEmptyBorder());
			editButton.setContentAreaFilled(false);
			editButton.setBounds(265, 2, 48, 48);
			editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			rightButtonGroupDetailsBar.add(editButton);
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Edit button pressed");

					detailsView.setVisible(false);
					editView.setVisible(true);
					mainView.setVisible(false);
					applicationFrame.repaint();

					fieldTitle.setText(currOpenTodoItem.getTitle());
					fieldTitle.repaint();
					fieldDesc.setText(currOpenTodoItem.getDescription());
					fieldDesc.repaint();

					// We are looking for a particular method to see if a to do
					// item
					// has scheduled information. This is ugly.
					highPriority.setSelected(currOpenTodoItem.isHighPriority());
					boolean scheduled = false;
					Class todoItemClass = currOpenTodoItem.getClass();
					try {
						Method getDueDateMethod = todoItemClass.getMethod(
								"getDueDate", null);
						scheduled = true;
					} catch (NoSuchMethodException e1) {
						// Nothing to do
					}
					highPriority.setEnabled(!scheduled);
					allowDueDate.setSelected(scheduled);
					allowDueDate.setVisible(scheduled);
					allowDueDate.setEnabled(false);
					labelDay.setEnabled(scheduled);
					labelDay.setVisible(scheduled);
					labelTime.setEnabled(scheduled);
					labelTime.setVisible(scheduled);
					timeSpinner.setEnabled(scheduled);
					timeSpinner.setVisible(scheduled);
					daySpinner.setEnabled(scheduled);
					daySpinner.setVisible(scheduled);
				}
			});
		} catch (IOException e) {
			System.err.println("Could not add edit button");
		}

		detailsBar.add(rightButtonGroupDetailsBar, BorderLayout.EAST);
	}

	/**
	 * Creates and populates the scrollable list
	 */
	private void initializeTodoList() {
		todoListModel = new DefaultListModel();
		listView = new JList();

		List<TodoItem> todoItems = todoList.getAllTodoItems();
		for (TodoItem item : todoItems) {
			todoListModel.addElement(item);
		}

		listView.setModel(todoListModel);
		TodoListCellRenderer renderer = new TodoListCellRenderer();
		listView.setCellRenderer(renderer);

		todoListScroller = new ScrollableTodoListContainer(listView);
		todoListScroller.getVerticalScrollBar().setPreferredSize(
				new Dimension(8, 0));
		todoListScroller.setBorder(new EmptyBorder(0, 0, 0, 0));

		todoListScrollerWrapper = new JPanel();
		todoListScrollerWrapper.setLayout(new BorderLayout());
		todoListScrollerWrapper.add(messageBar, BorderLayout.NORTH);
		todoListScrollerWrapper.add(todoListScroller);

		listView.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point pointClicked = e.getPoint();
				int index = listView.locationToIndex(pointClicked);
				System.out.println("Changed:" + index);

				if (index == -1) {
					return;
				}

				if (index > (todoList.getAllTodoItems().size() - 1)) {
					return;
				}

				Rectangle cellBounds = listView.getCellBounds(index, index);
				if (cellBounds.getLocation().getX() < pointClicked.getX()
						&& cellBounds.getLocation().getY() < pointClicked
								.getY()
						&& cellBounds.getLocation().getX()
								+ cellBounds.getWidth() > pointClicked.getX()
						&& cellBounds.getLocation().getY()
								+ cellBounds.getHeight() > pointClicked.getY()) {

					tabBar.setVisible(true);
					navBar.setVisible(true);
					searchBar.setVisible(false);

					detailsView.setVisible(true);
					editView.setVisible(false);
					mainView.setVisible(false);
					applicationFrame.repaint();

					currOpenTodoItem = (TodoItem) listView.getModel().getElementAt(
							index);

					if (currOpenTodoItem.isCompleted()) {
						detailsCompletedIndicator.setText("Completed");
						detailsCompletedIndicator.setBackground(new Color(157,
								242, 87));
						detailsCompletedIndicator.repaint();
					} else {
						detailsCompletedIndicator.setText("Not Completed");
						detailsCompletedIndicator.setBackground(new Color(0,
								173, 239));
						detailsCompletedIndicator.repaint();
					}

					detailsTitleLabel.setText(currOpenTodoItem.getTitle());
					detailsTitleLabel.repaint();

					// We are looking for a particular method to see if a to do
					// item
					// has scheduled information. This is ugly.
					Class todoItemClass = currOpenTodoItem.getClass();
					try {
						Method getDueDateMethod = todoItemClass.getMethod(
								"getDueDate", null);
						TodoItem item = currOpenTodoItem;
						Date dueDate = (Date) getDueDateMethod.invoke(item, null);

						SimpleDateFormat hourFormat = new SimpleDateFormat(
								"HH:mm", Locale.CANADA);
						SimpleDateFormat dayFormat = new SimpleDateFormat(
								"EEE dd MMMM yyyy", Locale.CANADA);

						detailsDueTime.setText(String.valueOf(hourFormat
								.format(dueDate)));
						detailsDueTime.repaint();

						detailsDueDate.setText(String.valueOf(dayFormat
								.format(dueDate)));
						detailsDueDate.repaint();

						setItemCompletedButton.setEnabled(false);
					} catch (NoSuchMethodException e1) {
						detailsDueTime.setText("");
						detailsDueTime.repaint();

						detailsDueDate.setText("");
						detailsDueDate.repaint();

						setItemCompletedButton.setEnabled(true);
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					detailsDescriptionDetails.setText(currOpenTodoItem
							.getDescription());
					detailsDescriptionDetails.repaint();

				}
			}
		});
	}

	/**
	 * Creates the message to display when there are no to-do items
	 */
	private void initializeNoItemsMessageBar() {

		messageBar = new JPanel();
		messageBar.setBackground(Color.WHITE);
		messageBar.setBounds(0, 150, APPWIDTH, 100);
		messageBar.setLayout(new BorderLayout());
		messageBar.setBorder(null);
		messageBar.setVisible(true);

		messageLabel = new JLabel("No Todo Items Yet", JLabel.CENTER);
		messageLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		messageLabel.setForeground(new Color(175, 175, 175));
		messageLabel.setBorder(new EmptyBorder(50, 0, 0, 0));

		messageBar.add(messageLabel);
	}

	/**
	 * Creates a container for the top navigation bar and tabbed bar
	 */
	private void initializeTopNavWrapper() {
		topNav = new JPanel();
		topNav.setBorder(null);
		topNav.setLayout(new BorderLayout());
		topNav.setBounds(0, 0, APPWIDTH, 100);
		topNav.add(navBar, BorderLayout.NORTH);
		topNav.add(searchBar, BorderLayout.CENTER);
		topNav.add(tabBar, BorderLayout.SOUTH);
	}

	/**
	 * Creates and initializes the tabbed bar below the navigation bar
	 */
	private void initializeTabBar() {
		tabBar = new JPanel();
		tabBar.setForeground(Color.WHITE);
		tabBar.setBackground(Color.WHITE);
		tabBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabBar.setLayout(new BorderLayout());
		tabBar.setPreferredSize(new Dimension(APPWIDTH, 53));

		allTab = new JButton("All");
		allTab.setContentAreaFilled(false);
		allTab.setForeground(Color.WHITE);
		allTab.setFont(new Font("Helvetica", Font.PLAIN, 20));
		allTab.setBackground(new Color(0, 173, 239));
		allTab.setOpaque(true);
		allTab.setBorder(new EmptyBorder(0, 20, 0, 10));
		allTab.setPreferredSize(new Dimension(APPWIDTH / 3, 48));
		allTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tabBar.add(allTab, BorderLayout.WEST);
		allTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Switches to the all todo item list view
				allTab.setBackground(new Color(0, 173, 239));
				incompleteTab.setBackground(new Color(184, 228, 244));
				incompleteTabSelected = false;
				scheduledTab.setBackground(new Color(184, 228, 244));
				scheduledTabSelected = false;

				displayAllItems();
			}
		});

		incompleteTabSelected = false;
		incompleteTab = new JButton("Incomplete");
		incompleteTab.setContentAreaFilled(false);
		incompleteTab.setForeground(Color.WHITE);
		incompleteTab.setFont(new Font("Helvetica", Font.PLAIN, 20));
		incompleteTab.setOpaque(true);
		incompleteTab.setBorder(new EmptyBorder(0, 10, 0, 20));
		incompleteTab.setBackground(new Color(184, 228, 244));
		incompleteTab.setPreferredSize(new Dimension(APPWIDTH / 3, 48));
		incompleteTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tabBar.add(incompleteTab, BorderLayout.CENTER);
		incompleteTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Switch to the incomplete list view
				allTab.setBackground(new Color(184, 228, 244));
				incompleteTab.setBackground(new Color(0, 173, 239));
				incompleteTabSelected = true;
				scheduledTab.setBackground(new Color(184, 228, 244));
				scheduledTabSelected = false;

				displayIncompleteItems();
			}
		});

		scheduledTabSelected = false;
		scheduledTab = new JButton("Scheduled");
		scheduledTab.setContentAreaFilled(false);
		scheduledTab.setForeground(Color.WHITE);
		scheduledTab.setFont(new Font("Helvetica", Font.PLAIN, 20));
		scheduledTab.setBackground(new Color(184, 228, 244));
		scheduledTab.setOpaque(true);
		scheduledTab.setBorder(new EmptyBorder(0, 20, 0, 10));
		scheduledTab.setPreferredSize(new Dimension(APPWIDTH / 3, 48));
		scheduledTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tabBar.add(scheduledTab, BorderLayout.EAST);
		scheduledTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Switches to the schedule todo item list view
				System.out.println("Switch to scheduled tab");
				scheduledTab.setBackground(new Color(0, 173, 239));
				scheduledTabSelected = true;
				allTab.setBackground(new Color(184, 228, 244));
				incompleteTab.setBackground(new Color(184, 228, 244));
				incompleteTabSelected = false;

				displayScheduledItems();
			}
		});

	}

	private void displayAllItems() {
		todoListModel = new DefaultListModel();
		listView.setModel(todoListModel);

		List<TodoItem> todoItems = todoList.getAllTodoItems();
		for (TodoItem item : todoItems) {
			todoListModel.addElement(item);
		}

		listView.repaint();

		if (todoItems.size() == 0) {
			messageBar.setVisible(true);
			messageBar.repaint();
		} else {
			messageBar.setVisible(false);
			messageBar.repaint();
		}
	}

	private void displayIncompleteItems() {
		todoListModel = new DefaultListModel();
		listView.setModel(todoListModel);

		List<TodoItem> todoItems = todoList.filterTodoItemsByIncompletion();
		for (TodoItem item : todoItems) {
			todoListModel.addElement(item);
		}
		listView.repaint();

		if (todoItems.size() == 0) {
			messageBar.setVisible(true);
			messageBar.repaint();
		} else {
			messageBar.setVisible(false);
			messageBar.repaint();
		}
	}

	private void displayScheduledItems() {

		todoListModel = new DefaultListModel();
		listView.setModel(todoListModel);

		List<TodoItem> todoItems = todoList.getScheduledItems();
		for (TodoItem item : todoItems) {
			todoListModel.addElement(item);
		}

		listView.repaint();

		if (todoItems.size() == 0) {
			messageBar.setVisible(true);
			messageBar.repaint();
		} else {
			messageBar.setVisible(false);
			messageBar.repaint();
		}
	}

	/**
	 * Creates and initializes the top navigation bar
	 */
	private void initializeNavBar() {
		navBar = new JPanel();
		navBar.setBackground(new Color(7, 90, 124));
		navBar.setPreferredSize(new Dimension(APPWIDTH, 53));
		navBar.setAlignmentX(SwingConstants.LEFT);
		navBar.setLayout(new BorderLayout());

		// Creates the button group that will be appended to the top navbar
		rightButtonGroupNavBar = new JPanel();
		rightButtonGroupNavBar.setLayout(new FlowLayout(FlowLayout.LEADING, 10,
				0));
		rightButtonGroupNavBar.setBorder(new EmptyBorder(5, 0, 0, 0));
		rightButtonGroupNavBar.setBackground(new Color(7, 90, 124));

		try {
			// Tries to create and add logo to top navbar
			BufferedImage logoImg = ImageIO.read(new File("img/logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
			logoLabel.setBounds(5, 0, 100, 50);
			logoLabel.setPreferredSize(new Dimension(100, 50));
			navBar.add(logoLabel, BorderLayout.WEST);
		} catch (IOException e) {
			System.err.println("Could not add logo");
		}

		try {
			// Tries to create and add the 'add' button to top navbar
			BufferedImage addIconImg = ImageIO.read(new File("img/ic_add.png"));
			JButton addButton = new JButton(new ImageIcon(addIconImg));
			addButton.setBorder(BorderFactory.createEmptyBorder());
			addButton.setContentAreaFilled(false);
			addButton.setBounds(265, 2, 48, 48);
			addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			rightButtonGroupNavBar.add(addButton);
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Add button pressed");

					currOpenTodoItem = null;

					detailsView.setVisible(false);
					editView.setVisible(true);
					mainView.setVisible(false);
					highPriority.setEnabled(true);
					highPriority.setSelected(false);
					allowDueDate.setSelected(false);
					allowDueDate.setEnabled(true);
					allowDueDate.setVisible(true);
					timeSpinner.setVisible(true);
					timeSpinner.setEnabled(false);
					labelTime.setEnabled(false);
					labelTime.setVisible(true);
					daySpinner.setEnabled(false);
					daySpinner.setVisible(true);
					labelDay.setEnabled(false);
					labelDay.setVisible(true);
					applicationFrame.repaint();

				}
			});
		} catch (IOException e) {
			System.err.println("Could not add + button");
		}

		try {
			// Tries to create and add the 'search' button to top navbar
			BufferedImage searchIconImg = ImageIO.read(new File(
					"img/ic_action_search.png"));
			JButton searchButton = new JButton(new ImageIcon(searchIconImg));
			searchButton.setBorder(BorderFactory.createEmptyBorder());
			searchButton.setContentAreaFilled(false);
			searchButton.setBounds(265, 2, 48, 48);
			searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			rightButtonGroupNavBar.add(searchButton);

			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					fieldSearch.setText("");

					navBar.setVisible(false);
					tabBar.setVisible(false);
					searchBar.setVisible(true);
					applicationFrame.repaint();

				}
			});
		} catch (IOException e) {
			System.err.println("Could not add search button");
		}

		navBar.add(rightButtonGroupNavBar, BorderLayout.EAST);
	}

	/**
	 * Creates and initializes the top search bar
	 */
	private void initializeSearchBar() {
		searchBar = new JPanel();
		searchBar.setBackground(new Color(7, 90, 124));
		searchBar.setPreferredSize(new Dimension(APPWIDTH, 53));
		searchBar.setAlignmentX(SwingConstants.LEFT);
		searchBar.setLayout(new BorderLayout());
		searchBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 20));
		searchBar.setVisible(false);

		try {
			// Tries to create and add the 'search' button to top searchbar
			BufferedImage cancelSearchImg = ImageIO.read(new File(
					"img/ic_cancel.png"));
			JButton cancelSearchButton = new JButton(new ImageIcon(
					cancelSearchImg));
			cancelSearchButton.setBorder(BorderFactory.createEmptyBorder());
			cancelSearchButton.setContentAreaFilled(false);
			cancelSearchButton.setBounds(265, 2, 48, 48);
			cancelSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			searchBar.add(cancelSearchButton, BorderLayout.EAST);
			cancelSearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Cancel button pressed");

					todoListModel = new DefaultListModel();
					listView.setModel(todoListModel);

					List<TodoItem> todoItems = todoList.getAllTodoItems();
					for (TodoItem item : todoItems) {
						todoListModel.addElement(item);
					}
					listView.setBorder(new EmptyBorder(0, 0, 0, 0));
					listView.repaint();

					if (todoItems.size() == 0) {
						messageBar.setVisible(true);
						messageBar.repaint();
					} else {
						messageBar.setVisible(false);
						messageBar.repaint();
					}

					tabBar.setVisible(true);
					navBar.setVisible(true);
					searchBar.setVisible(false);
					detailsView.setVisible(false);
					editView.setVisible(false);
					mainView.setVisible(true);
					applicationFrame.repaint();
				}
			});
		} catch (IOException f) {
			System.err.println("Could not add search cancel button");
		}

		fieldSearch = new JTextField("");
		fieldSearch.setFont(new Font("Helvetica", Font.PLAIN, 14));
		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();

				todoListModel = new DefaultListModel();
				listView.setModel(todoListModel);

				List<TodoItem> todoItems = todoList
						.filterTodoItemsBySearchTerm(text);
				for (TodoItem item : todoItems) {
					todoListModel.addElement(item);
				}

				listView.repaint();

				if (todoItems.size() == 0) {
					messageBar.setVisible(true);
					messageBar.repaint();
				} else {
					messageBar.setVisible(false);
					messageBar.repaint();
				}
			}
		});

		searchBar.add(fieldSearch);
	}

	// Creates the wrapper for the application
	private void initializeApplicationFrame() {
		applicationFrame = new JFrame();
		applicationFrame.getContentPane().setForeground(Color.WHITE);
		applicationFrame.getContentPane().setBackground(Color.WHITE);
		applicationFrame.setSize(new Dimension(APPWIDTH, APPHEIGHT));
		applicationFrame.setResizable(false);
		applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Handles the rendering/format of a to-do list item.
	 */
	class TodoListCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -7799441088157759804L;

		private JPanel listItemParent;
		private JPanel listItemContainer;
		private JPanel listItemLeft;

		private JPanel leftHighlight; // Colored bar to easily identify element
		private JLabel titleLabel;
		private JLabel descLabel;
		private JLabel dateLabel;

		public TodoListCellRenderer() {
			// ------------------------------------------------------------------
			// Initializes to-do list item components
			listItemParent = new JPanel();
			listItemContainer = new JPanel();
			listItemLeft = new JPanel();
			leftHighlight = new JPanel();
			listItemParent.setLayout(new BorderLayout());
			listItemContainer.setLayout(new BorderLayout());
			listItemLeft.setLayout(new BorderLayout());
			titleLabel = new JLabel();
			descLabel = new JLabel();
			dateLabel = new JLabel();

			// ------------------------------------------------------------------
			// Sets styles of components

			listItemParent.setBackground(Color.WHITE);
			listItemContainer.setBackground(Color.WHITE);
			listItemContainer.setBorder(new EmptyBorder(0, 15, 0, 10));
			listItemContainer.setSize(new Dimension(280, 100));

			leftHighlight.setBackground(new Color(113, 42, 42));
			leftHighlight.setPreferredSize(new Dimension(6, 48));
			leftHighlight.setOpaque(true);

			titleLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
			titleLabel.setForeground(new Color(51, 51, 51));

			descLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
			descLabel.setForeground(new Color(120, 120, 120));

			dateLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
			dateLabel.setForeground(new Color(100, 100, 100));

			listItemLeft.add(leftHighlight, BorderLayout.WEST);
			listItemLeft.setBackground(Color.WHITE);

			listItemContainer.add(titleLabel, BorderLayout.WEST);
			listItemContainer.add(descLabel, BorderLayout.SOUTH);
			listItemContainer.add(dateLabel, BorderLayout.EAST);

			Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10,
					10);
			Border listItemSeparator = BorderFactory
					.createLineBorder(new Color(233, 233, 233));
			listItemParent.setBorder(new CompoundBorder(listItemSeparator,
					paddingBorder));
			listItemParent.add(listItemContainer);
			listItemParent.add(listItemLeft, BorderLayout.WEST);

		}

		@Override
		// Renders a to-do list item
		public Component getListCellRendererComponent(JList list,
				Object listItem, int index, boolean selected, boolean expanded) {

			TodoItem item = (TodoItem) listItem;
			Class todoItemClass = item.getClass();
			try {
				Method getDueDateMethod = todoItemClass.getMethod(
						"getDueDate", null);
				Date dueDate = (Date) getDueDateMethod.invoke(item, null);
				Date todayDate = new Date(System.currentTimeMillis());
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.setTime(dueDate);
				cal2.setTime(todayDate);
				boolean sameDay = cal1.get(Calendar.YEAR) == cal2
						.get(Calendar.YEAR)
						&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
								.get(Calendar.DAY_OF_YEAR);

				SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm aa",
						Locale.ENGLISH);
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy",
						Locale.ENGLISH);
				if (sameDay) {
					dateLabel.setText(hourFormat.format(dueDate));
				} else {
					dateLabel.setText(dayFormat.format(dueDate));
				}
			} catch (NoSuchMethodException e1) {
				dateLabel.setText("");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
			titleLabel.setText(item.getTitle());
			titleLabel.setToolTipText(item.getTitle());
			descLabel.setText(item.getDescription());

			if (item.isCompleted()) {
				leftHighlight.setBackground(new Color(120, 190, 22));
			} else {
				leftHighlight.setBackground(new Color(113, 42, 42));
			}

			if (item.isHighPriority()) {
				listItemContainer.setBackground(new Color(250, 110, 100));
			} else {
				listItemContainer.setBackground(Color.WHITE);
			}

			return listItemParent;
		}
	}

	/*
	 * Custom scrollable container for the to-do list (allows scrolling and
	 * enables scrollbars)
	 */
	class ScrollableTodoListContainer extends JScrollPane {

		private static final long serialVersionUID = -103572977104124066L;

		public ScrollableTodoListContainer(Component component) {
			super(component);
			final JScrollBar verticalScrollBar = getVerticalScrollBar();
			setWheelScrollingEnabled(false);

			addMouseWheelListener(new MouseAdapter() {
				public void mouseWheelMoved(MouseWheelEvent evt) {
					if (evt.getWheelRotation() == 1) {
						int scrollAmount = evt.getScrollAmount();
						int newScrollPosition = verticalScrollBar.getValue()
								+ verticalScrollBar.getBlockIncrement()
								* scrollAmount;
						if (newScrollPosition <= verticalScrollBar.getMaximum()) {
							verticalScrollBar.setValue(newScrollPosition);
						}
					} else if (evt.getWheelRotation() == -1) {
						int scrollAmount = evt.getScrollAmount();
						int newScrollPosition = verticalScrollBar.getValue()
								- verticalScrollBar.getBlockIncrement()
								* scrollAmount;
						if (newScrollPosition >= 0) {
							verticalScrollBar.setValue(newScrollPosition);
						}
					}
				}
			});

		}
	}

}
