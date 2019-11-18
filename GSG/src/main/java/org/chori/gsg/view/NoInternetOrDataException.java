package org.chori.gsg.view;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoInternetOrDataException extends Exception {

	JFrame optionFrame;

	public NoInternetOrDataException () {

		// if(optionFrame == null)
		// 	optionFrame = new JFrame();

		// optionFrame.setVisible(true);
		// optionFrame.setLocationRelativeTo(null);
		// optionFrame.setAlwaysOnTop(true);



		// final JOptionPane optionPane = new JOptionPane(
		JOptionPane.showMessageDialog(null,
			"You have no internet and no downloaded data.\nI'm sorry, but this won't work.",
			"Houston, we have a problem",
			JOptionPane.ERROR_MESSAGE,
			createImageIcon("/StrangeloveRound.png", "How I learned to quit worrying and love the bomb."));
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
    	URL imgURL = getClass().getResource(path);
    	if (imgURL != null) {
        	return new ImageIcon(imgURL, description);
    	} else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

}