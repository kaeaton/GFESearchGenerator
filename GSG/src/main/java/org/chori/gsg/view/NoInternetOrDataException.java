package org.chori.gsg.view;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoInternetOrDataException extends Exception {

	JFrame optionFrame;

	public NoInternetOrDataException () {

		JOptionPane.showMessageDialog(null,
			"You have no internet and no downloaded data.\nI'm sorry, but this won't work.",
			"Houston, we have a problem",
			JOptionPane.ERROR_MESSAGE,
			createImageIcon("/StrangeloveRound.png", "How I learned to quit worrying and love the bomb."));
	}

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