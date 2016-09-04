package ca.ubc.cs.cpsc210.photo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import ca.ubc.cs.cpsc210.photo.Album;
import ca.ubc.cs.cpsc210.photo.Photo;
import ca.ubc.cs.cpsc210.photo.PhotoManager;
import ca.ubc.cs.cpsc210.photo.Tag;
import ca.ubc.cs.cpsc210.photo.TagManager;
import ca.ubc.cs.cpsc210.utility.ThumbnailDoesNotExistException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	// The underlying library
	private PhotoManager photoMan = new PhotoManager();
	private TagManager tagMan = new TagManager();

	/**
	 * Album wrapper to use for JList entries
	 */
	private static class AlbumListEntry {
		public Album album;

		public AlbumListEntry(Album a) {
			album = a;
		}

		public String toString() {
			return album.getName();
		}
	}

	/**
	 * Tag wrapper to use for JList entries
	 */
	private static class TagListEntry {
		public Tag tag;

		public TagListEntry(Tag t) {
			tag = t;
		}

		public String toString() {
			return tag.getName();
		}
	}

	/**
	 * ListModel representing the albums in the system
	 */
	private class AlbumListModel extends DefaultListModel {
		public void refresh() {
			clear();
			Set<Album> albums = new TreeSet<Album>(albumsByName);
			albums.addAll(photoMan.getAlbums());
			for (Album a : albums)
				addElement(new AlbumListEntry(a));
		}
	}

	private AlbumListModel albumsModel = new AlbumListModel();

	/**
	 * ListModel representing the tags in the system
	 */
	private class TagListModel extends DefaultListModel {
		public void refresh() {
			clear();
			Set<Tag> tags = new TreeSet<Tag>(tagsByName);
			tags.addAll(tagMan.getTags());
			for (Tag tag : tags)
				addElement(new TagListEntry(tag));
		}
	}

	private TagListModel tagsModel = new TagListModel();

	// Orders albums by name, case-insensitive
	private static Comparator<Album> albumsByName = new Comparator<Album>() {
		public int compare(Album a, Album b) {
			return a.getName().compareToIgnoreCase(b.getName());
		}
	};

	// Orders tags by name, case-insensitive
	private static Comparator<Tag> tagsByName = new Comparator<Tag>() {
		public int compare(Tag a, Tag b) {
			return a.getName().compareToIgnoreCase(b.getName());
		}
	};

	// Orders photos by date
	private static Comparator<Photo> photosByDate = new Comparator<Photo>() {
		public int compare(Photo a, Photo b) {
			return a.getDateAdded().compareTo(b.getDateAdded());
		}
	};

	// Update the GUI to display the set of photos selected in the browse panel
	private void updateDisplayedPhotoSet() {
		// Ignore events fired during initialisation before the window is set up
		if (browsePanel == null)
			return;

		// Set the window title
		setTitle("Photo Library: " + browsePanel.getSelectionDescription());

		thumbnailsPanel.refresh();
	}

	// UI components

	private BrowsePanel browsePanel = new BrowsePanel();
	private ThumbnailsPanel thumbnailsPanel = new ThumbnailsPanel();
	private PhotoPanel photoPanel = new PhotoPanel();

	private PhotoFileChooser photoFileChooser = new PhotoFileChooser();

	/**
	 * The left-side browse-by-album/tag tabbed panel
	 */
	private class BrowsePanel extends JTabbedPane {

		// Indices of the tabs
		public static final int ALBUM = 0;
		public static final int TAG = 1;
		public static final int DATE = 2;

		private JList albumList = new JList(albumsModel);
		private JList tagList = new JList(tagsModel);

		private Date startDate;
		private Date endDate;

		private JLabel lblStartDate = new JLabel();
		private JLabel lblEndDate = new JLabel();

		/**
		 * @return the index in albumsModel of the currently selected album, or
		 *         -1 if no album is selected. Used by the photo file chooser to
		 *         set the default album selection.
		 */
		private int getSelectedAlbumIndex() {
			return albumList.getSelectedIndex();
		}

		/**
		 * @return the currently selected album, or null if no album is selected
		 */
		private Album getSelectedAlbum() {
			AlbumListEntry ale = (AlbumListEntry) albumList.getSelectedValue();
			return ale == null ? null : ale.album;
		}

		/**
		 * @return the currently selected tags
		 */
		private Set<Tag> getSelectedTags() {
			Set<Tag> tags = new HashSet<Tag>();
			for (Object tle : tagList.getSelectedValues())
				tags.add(((TagListEntry) tle).tag);
			return tags;
		}

		/**
		 * @return a displayable description of the selected album or tags
		 */
		public String getSelectionDescription() {
			switch (getSelectedIndex()) {
			case ALBUM:
				Album album = getSelectedAlbum();
				return album == null ? "No album selected" : "Album: "
						+ album.getName();
			case TAG:
				return tagList.getSelectedValues().length + " tag(s) selected";
			case DATE:
				return "Photos added between "
						+ new SimpleDateFormat().format(startDate) + " and "
						+ new SimpleDateFormat().format(endDate);
			default:
				return "";
			}
		}

		/**
		 * @return the set of photos from the selected album or tags
		 */
		public Set<Photo> getPhotoSelection() {
			Set<Photo> photos = new TreeSet<Photo>(photosByDate);

			switch (getSelectedIndex()) {
			case ALBUM:
				Album album = getSelectedAlbum();
				if (album != null)
					photos.addAll(album.getPhotos());
				break;
			case TAG:
				for (Tag tag : getSelectedTags())
					photos.addAll(tag.getPhotos());
				break;
			case DATE:
				photos.addAll(photoMan
						.findPhotosInDateRange(startDate, endDate));
				break;
			}

			return photos;
		}

		/**
		 * Switch to the Albums tab and select the provided album.
		 */
		public void selectAlbum(Album album) {
			for (int i = 0; i < albumsModel.size(); i++) {
				if (((AlbumListEntry) albumsModel.get(i)).album == album) {
					albumList.setSelectedIndex(i);
					setSelectedIndex(ALBUM);
					return;
				}
			}
		}

		/**
		 * Switch to the Tags tab and select the provided tag.
		 */
		public void selectTag(Tag tag) {
			for (int i = 0; i < tagsModel.size(); i++) {
				if (((TagListEntry) tagsModel.get(i)).tag == tag) {
					tagList.setSelectedIndex(i);
					setSelectedIndex(TAG);
					return;
				}
			}
		}

		public BrowsePanel() {

			// Album right-click popup

			JMenuItem addPhotos = new JMenuItem("Add photos...");
			addPhotos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					photoFileChooser.showAddPhotoDialog();
				}
			});

			JMenuItem renameAlbum = new JMenuItem("Rename");
			renameAlbum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Album album = getSelectedAlbum();
					if (album != null) {
						String newName = JOptionPane
								.showInputDialog("Enter a new name for the album.");
						if (newName != null) {
							album.setName(newName);
							albumsModel.refresh();
						}
					}
				}
			});

			JMenuItem removeAlbum = new JMenuItem("Remove");
			removeAlbum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Album album = getSelectedAlbum();
					if (album != null
							&& confirmPopup("Remove album " + album.getName()
									+ "?")) {
						for (Photo photo : album.getPhotos())
							removePhoto(photo);
						photoMan.removeAlbum(album);
						albumsModel.refresh();
					}
				}
			});

			JPopupMenu albumPopup = new JPopupMenu();
			albumPopup.add(addPhotos);
			albumPopup.add(renameAlbum);
			albumPopup.add(removeAlbum);
			albumList.addMouseListener(new PopupMouseListener(albumList,
					albumPopup));

			// Tag right-click popup

			JMenuItem renameTag = new JMenuItem("Rename");
			renameTag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TagListEntry tle = (TagListEntry) tagList
							.getSelectedValue();
					if (tle != null) {
						String newName = JOptionPane
								.showInputDialog("Enter a new name for the tag.");
						if (newName != null) {

								tagMan.renameTag(tle.tag.getName(), newName);
							tagsModel.refresh();
						}
					}
				}
			});

			JMenuItem removeTag = new JMenuItem("Remove");
			removeTag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TagListEntry tle = (TagListEntry) tagList
							.getSelectedValue();
					if (tle != null
							&& confirmPopup("Remove tag " + tle
									+ " from the system?")) {
						tagMan.removeTag(tle.toString());
						tagsModel.refresh();
					}
				}
			});

			JPopupMenu tagPopup = new JPopupMenu();
			tagPopup.add(renameTag);
			tagPopup.add(removeTag);
			tagList.addMouseListener(new PopupMouseListener(tagList, tagPopup));

			// Update the displayed photo set when the user switches tabs
			addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					updateDisplayedPhotoSet();
				}
			});

			// Update the displayed photo set when the tag or album selection
			// changes
			ListSelectionListener updatePhotoDisplay = new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					updateDisplayedPhotoSet();
				}
			};
			albumList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			albumList.addListSelectionListener(updatePhotoDisplay);
			tagList.addListSelectionListener(updatePhotoDisplay);

			JButton btnNewAlbum = new JButton("New Album");
			btnNewAlbum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String albumName = JOptionPane
							.showInputDialog("Enter a name for the new album.");
					if (albumName != null) {
						Album album = new Album(albumName);
						photoMan.addAlbum(album);
						albumsModel.refresh();
						selectAlbum(album);
					}
				}
			});

			JButton btnNewTag = new JButton("New Tag");
			btnNewTag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tagName = JOptionPane
							.showInputDialog("Enter a name for the new tag.");
					if (tagName != null) {
						Tag tag;
							tag = tagMan.createTag(tagName);
							tagsModel.refresh();
						selectTag(tag);
					}
				}
			});

			// Date range controls

			final JButton btnChangeStartDate = new JButton("Change...");
			final JButton btnChangeEndDate = new JButton("Change...");

			ActionListener changeDate = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean start = e.getSource() == btnChangeStartDate;
					String newDateString = JOptionPane.showInputDialog(
							"Enter a date:", new SimpleDateFormat()
									.format(start ? startDate : endDate));
					if (newDateString != null) {
						Date date;
						DateFormat df = new SimpleDateFormat();
						try {
							date = df.parse(newDateString);
						} catch (ParseException ex) {
							errorPopup("Unrecognized date format.");
							return;
						}
						if (start)
							setStartDate(date);
						else
							setEndDate(date);
						updateDisplayedPhotoSet();
					}
				}
			};

			btnChangeStartDate.addActionListener(changeDate);
			btnChangeEndDate.addActionListener(changeDate);

			setStartDate(new Date(0));
			setEndDate(new Date());

			JPanel albumsPanel = new JPanel(new BorderLayout());
			albumsPanel.add(new JScrollPane(albumList), BorderLayout.CENTER);
			albumsPanel.add(btnNewAlbum, BorderLayout.PAGE_END);

			JPanel tagsPanel = new JPanel(new BorderLayout());
			tagsPanel.add(new JScrollPane(tagList), BorderLayout.CENTER);
			tagsPanel.add(btnNewTag, BorderLayout.PAGE_END);

			JPanel datePanel = new JPanel();
			datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
			datePanel.add(lblStartDate);
			datePanel.add(btnChangeStartDate);
			datePanel.add(Box.createVerticalStrut(5));
			datePanel.add(lblEndDate);
			datePanel.add(btnChangeEndDate);
			datePanel
					.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			// Add the tabs
			add("Albums", albumsPanel);
			add("Tags", tagsPanel);
			add("Date range", datePanel);

			setPreferredSize(new Dimension(200, 300));
		}

		private void setStartDate(Date date) {
			startDate = date;
			lblStartDate.setText("Start date: "
					+ new SimpleDateFormat().format(date));
			// updateDisplayedPhotoSet();
		}

		private void setEndDate(Date date) {
			endDate = date;
			lblEndDate.setText("End date: "
					+ new SimpleDateFormat().format(date));
			// updateDisplayedPhotoSet();
		}

	}

	/**
	 * The JFileChooser for selecting photo files
	 */
	private class PhotoFileChooser extends JFileChooser {

		private JList albumsList = new JList(albumsModel);

		public PhotoFileChooser() {
			JPanel accessory = new JPanel(new BorderLayout());
			accessory.add(new JLabel("Add to album:"), BorderLayout.NORTH);
			accessory.add(new JScrollPane(albumsList), BorderLayout.CENTER);
			accessory.setPreferredSize(new Dimension(150, 275));
			accessory.setBorder(new EmptyBorder(0, 10, 0, 0));
			setAccessory(accessory);

			setMultiSelectionEnabled(true);
			setAcceptAllFileFilterUsed(false);
			setApproveButtonText("Add Photos");

			setFileFilter(new FileFilter() {
				public boolean accept(File f) {
					if (f.isDirectory())
						return true;
					String name = f.getName().toLowerCase();
					return name.endsWith(".jpg") || name.endsWith(".jpeg");
				}

				public String getDescription() {
					return "JPEG images (*.jpg, *.jpeg)";
				}
			});
		}

		/**
		 * Show the dialog to add a photo to the library
		 */
		public void showAddPhotoDialog() {
			if (albumsModel.size() == 0) {
				errorPopup("You must create an album before adding photos.");
				return;
			}

			albumsList.setSelectedIndex(browsePanel.getSelectedAlbumIndex());

			if (photoFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				Album album = ((AlbumListEntry) albumsList.getSelectedValue()).album;

				for (File file : getSelectedFiles()) {
					try {
						Photo photo = new Photo(importPhotoFile(file));
						photo.setDateAdded(new Date());
						photo.setDescription("");
						photo.loadPhoto();

						album.addPhoto(photo);
					} catch (IOException e) {
						errorPopup("Photo " + file.getPath() + " not found.");
					}
				}

				browsePanel.selectAlbum(album);
				updateDisplayedPhotoSet();
			}
		}

		/**
		 * Given any JPEG image file, returns a name suitable for passing to the
		 * Photo constructor.
		 */
		private String importPhotoFile(File file) throws IOException {
			String name = file.getName().substring(0,
					file.getName().lastIndexOf('.'));

			// If the file isn't in the photos folder with the expected
			// filename, copy it there
			if (!file.getCanonicalPath().equals(
					new File("photos" + System.getProperty("file.separator")
							+ name + ".jpg").getCanonicalPath())) {

				// append a number that depends on the whole path, to reduce
				// collisions
				name += file.getCanonicalPath().hashCode() % 5000 + 5000;
				File dest = new File("photos"
						+ System.getProperty("file.separator") + name + ".jpg");

				if (!dest.exists()) {
					InputStream in = new FileInputStream(file);
					OutputStream out = new FileOutputStream(dest);
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0)
						out.write(buf, 0, len);
					in.close();
					out.close();
				}
			}

			return name;
		}
	}

	/**
	 * The top thumbnails bar.
	 */
	private class ThumbnailsPanel extends JPanel {

		/**
		 * A thumbnail image component that calls selectPhoto(photo) when
		 * clicked.
		 */
		private class ThumbnailLabel extends JPanel {
			public Photo photo;

			public ThumbnailLabel(Photo p)
					throws ThumbnailDoesNotExistException {
				super(new BorderLayout());
				photo = p;

				JLabel nameLabel = new JLabel(p.getName());
				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				add(nameLabel, BorderLayout.SOUTH);
				add(new JLabel(new ImageIcon(p.getThumbnailImage())),
						BorderLayout.CENTER);
				setSelectedBorder(false);

				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						// update the thumbnail borders indicating the selected
						// photo
						for (Component c : ThumbnailsPanel.this.getComponents())
							((ThumbnailLabel) c)
									.setSelectedBorder(c == ThumbnailLabel.this);

						// display the photo in the lower area
						photoPanel.selectPhoto(photo);
					}
				});
			}

			public void setSelectedBorder(boolean selected) {
				setBorder(selected ? new CompoundBorder(new LineBorder(
						Color.BLUE, 3), new EmptyBorder(3, 3, 3, 3))
						: new EmptyBorder(6, 6, 6, 6));
			}
		}

		/**
		 * Ensure the photos selected in the browse panel are the ones displayed
		 */
		public void refresh() {
			Set<Photo> photos = browsePanel.getPhotoSelection();

			removeAll();
			if (photos.isEmpty()) {
				JLabel label = new JLabel("No photos to display.");
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setPreferredSize(new Dimension(300, 100));
				add(label);
			}

			ThumbnailLabel lastLabel = null;
			for (Photo p : photos) {
				try {
					add(lastLabel = new ThumbnailLabel(p));
				} catch (ThumbnailDoesNotExistException e) {
					errorPopup("Error getting thumbnail for photo "
							+ p.getName());
				}
			}

			repaint();
			revalidate();

			if (lastLabel != null) {
				lastLabel.setSelectedBorder(true);
				lastLabel.scrollRectToVisible(new Rectangle(0, 0, 5, 5));
			}

			photoPanel.selectPhoto(lastLabel == null ? null : lastLabel.photo);
		}
	}

	/**
	 * The panel for displaying a photo and managing its associated info
	 */
	private class PhotoPanel extends JPanel {

		private Photo selectedPhoto;

		private JPanel imagePanel = new JPanel();
		private JPanel infoPanel = new JPanel();

		private JLabel lblAdded = new JLabel();
		private JLabel lblAlbum = new JLabel();
		private JTextArea txtDescription = new JTextArea();
		private JPanel descriptionBtnPanel = new JPanel();

		private DefaultListModel photoTagsModel = new DefaultListModel();
		private JList tagList = new JList(photoTagsModel);

		public PhotoPanel() {
			super(new BorderLayout());

			JScrollPane scrollPane = new JScrollPane(imagePanel);
			scrollPane.setBackground(Color.WHITE);
			add(scrollPane, BorderLayout.CENTER);

			// Set up the info pane

			JButton btnMove = new JButton("Move to another album");
			btnMove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String albumName = JOptionPane
							.showInputDialog("Enter the name of the destination album:");
					if (albumName != null) {
						Album album = photoMan.findAlbum(albumName);
						if (album == null) {
							errorPopup("The specified album does not exist.");
							return;
						}

						if (selectedPhoto.getAlbum() != null)
							selectedPhoto.getAlbum().removePhoto(selectedPhoto);
						album.addPhoto(selectedPhoto);

						updateDisplayedPhotoSet();
					}
				}
			});

			JButton btnRemove = new JButton("Remove from library");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmPopup("Remove photo " + selectedPhoto.getName()
							+ "?")) {
						removePhoto(selectedPhoto);
						updateDisplayedPhotoSet();
					}
				}
			});

			// Description

			JButton btnSaveDescription = new JButton("Save");
			btnSaveDescription.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedPhoto.setDescription(txtDescription.getText());
					refreshInfoPanel();
				}
			});

			JButton btnResetDescription = new JButton("Reset");
			btnResetDescription.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshInfoPanel();
				}
			});

			descriptionBtnPanel.add(btnSaveDescription);
			descriptionBtnPanel.add(btnResetDescription);

			txtDescription.setLineWrap(true);
			txtDescription.setFont(lblAlbum.getFont());

			txtDescription.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					descriptionBtnPanel.setVisible(true);
				}
			});

			// Tags

			JMenuItem removeTag = new JMenuItem("Remove");
			removeTag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TagListEntry tle = (TagListEntry) tagList
							.getSelectedValue();
					if (tle != null) {
						selectedPhoto.removeTag(tle.tag);
						refreshInfoPanel();
					}
				}
			});

			JPopupMenu tagPopup = new JPopupMenu();
			tagPopup.add(removeTag);
			tagList.addMouseListener(new PopupMouseListener(tagList, tagPopup));

			JButton btnAddTag = new JButton("Add tag");
			btnAddTag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tagName = JOptionPane
							.showInputDialog("Enter the name of the tag to add:");
					if (tagName != null) {
						Tag tag = tagMan.findTag(tagName);
						if (tag == null) {
							errorPopup("The specified tag does not exist.");
							return;
						}
						selectedPhoto.addTag(tag);
						refreshInfoPanel();
					}
				}
			});

			// Add the components to the panel

			infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

			infoPanel.add(lblAdded);
			infoPanel.add(Box.createVerticalStrut(5));
			infoPanel.add(lblAlbum);
			infoPanel.add(Box.createVerticalStrut(5));
			infoPanel.add(btnMove);
			infoPanel.add(btnRemove);
			infoPanel.add(Box.createVerticalStrut(5));

			JPanel descriptionPanel = new JPanel(new BorderLayout());
			descriptionPanel
					.add(new JLabel("Description:"), BorderLayout.NORTH);
			descriptionPanel.add(new JScrollPane(txtDescription),
					BorderLayout.CENTER);
			descriptionPanel.add(descriptionBtnPanel, BorderLayout.SOUTH);
			infoPanel.add(descriptionPanel);

			JPanel tagsPanel = new JPanel(new BorderLayout());
			tagsPanel.add(new JLabel("Tags:"), BorderLayout.NORTH);
			tagsPanel.add(new JScrollPane(tagList), BorderLayout.CENTER);
			tagsPanel.add(btnAddTag, BorderLayout.SOUTH);
			infoPanel.add(tagsPanel);

			// center everything
			for (Component c : infoPanel.getComponents())
				((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

			infoPanel.setPreferredSize(new Dimension(200, 300));
			add(infoPanel, BorderLayout.EAST);
		}

		/**
		 * Display the provided photo and its info pane in the main photo area.
		 */
		private void selectPhoto(Photo photo) {
			selectedPhoto = photo;

			// Unfocus the description textarea
			imagePanel.requestFocusInWindow();

			// Add the image
			imagePanel.removeAll();
			if (photo != null) {
				imagePanel.add(new JLabel(new ImageIcon(photo.getImage())));
			} else {
				imagePanel.add(new JLabel("No photo selected."));
			}

			// Update the info panel
			refreshInfoPanel();

			repaint();
			revalidate();
		}

		private void refreshInfoPanel() {
			if (selectedPhoto != null) {
				infoPanel.setBorder(BorderFactory.createTitledBorder("Photo: "
						+ selectedPhoto.getName()));
				lblAdded.setText("Added: "
						+ new SimpleDateFormat().format(selectedPhoto
								.getDateAdded()));
				Album album = selectedPhoto.getAlbum();
				lblAlbum.setText("Album: "
						+ (album == null ? "(none)" : album.getName()));

				txtDescription.setText(selectedPhoto.getDescription());
				descriptionBtnPanel.setVisible(false);

				photoTagsModel.clear();
				Set<Tag> tags = new TreeSet<Tag>(tagsByName);
				if (selectedPhoto != null)
					;
				tags.addAll(selectedPhoto.getTags());
				for (Tag tag : tags)
					photoTagsModel.addElement(new TagListEntry(tag));

				infoPanel.setVisible(true);
			} else {
				infoPanel.setVisible(false);
			}
		}
	}

	/**
	 * Create and display the main window.
	 */
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(browsePanel, BorderLayout.WEST);

		getContentPane()
				.add(new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(
						thumbnailsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), photoPanel),
						BorderLayout.CENTER);

		setJMenuBar(createMenuBar());

		populateLibrary();

		updateDisplayedPhotoSet();
		setSize(800, 600);
		setVisible(true);
	}

	/**
	 * Build the menu bar
	 */
	private JMenuBar createMenuBar() {

		JMenuItem addPhotoMenuItem = new JMenuItem("Add Photos...");
		addPhotoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photoFileChooser.showAddPhotoDialog();
			}
		});

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(addPhotoMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		return menuBar;
	}

	/**
	 * Add some test data to the library.
	 */
	private void populateLibrary() {
		try {

			Album evens = new Album("evens");
			Album odds = new Album("odds");

			Photo[] photos = new Photo[10];
			for (int i = 1; i <= 10; i++) {
				Photo p = photos[i - 1] = new Photo("" + i);
				p.loadPhoto();
				p.setDateAdded(new Date(new Date().getTime() - 2000000000
						+ 100000000 * i));
				p.setDescription("The number " + i);
				((i & 1) == 1 ? odds : evens).addPhoto(p);
			}

			Tag square = tagMan.createTag("square");
			photos[0].addTag(square);
			photos[3].addTag(square);
			photos[8].addTag(square);

			Tag prime = tagMan.createTag("prime");
			photos[1].addTag(prime);
			photos[2].addTag(prime);
			photos[4].addTag(prime);
			photos[6].addTag(prime);

			photoMan.addAlbum(evens);
			photoMan.addAlbum(odds);

			albumsModel.refresh();
			tagsModel.refresh();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Display an error message box.
	 */
	private void errorPopup(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Display a confirmation box.
	 */
	private boolean confirmPopup(String message) {
		return JOptionPane.showConfirmDialog(this, message, "Confirm action",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}

	/**
	 * Cleanly remove a photo from the library.
	 */
	public void removePhoto(Photo photo) {
		for (Tag tag : photo.getTags())
			photo.removeTag(tag);
		try {
			photo.getAlbum().removePhoto(photo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateDisplayedPhotoSet();
	}
}
