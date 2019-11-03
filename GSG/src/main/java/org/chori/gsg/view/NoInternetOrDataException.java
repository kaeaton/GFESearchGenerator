package org.chori.gsg.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoInternetOrDataException extends Exception {

	JFrame optionFrame;

	public NoInternetOrDataException () {

		if(optionFrame == null)
			optionFrame = new JFrame();

		optionFrame.setVisible(true);
		optionFrame.setLocationRelativeTo(null);
		optionFrame.setAlwaysOnTop(true);

		// final JOptionPane optionPane = new JOptionPane(
		JOptionPane.showMessageDialog(optionFrame,
			"You have no internet and no downloaded data.\nI'm sorry, but this won't work.",
			"Houston, we have a problem",
			JOptionPane.ERROR_MESSAGE);
	}



}