package ca.ubc.cs.cpsc210.photo.gui;


import javax.swing.UIManager;

public class PhotoLibraryApp {
	
	/**
	 * Run the application.
	 */
	public static void main(String[] args){
		
		// Enable the native look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Start the main window
		new MainFrame();
	}
}
