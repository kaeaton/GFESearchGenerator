package org.chori.gsg.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoInternetException extends Exception {

	// JFrame optionFrame;

	public NoInternetException () {

		// if(optionFrame == null)
		// 	optionFrame = new JFrame();

		// optionFrame.setVisible(true);
		// optionFrame.setLocationRelativeTo(null);
		// optionFrame.setAlwaysOnTop(true);

		// final JOptionPane optionPane = new JOptionPane(
		JOptionPane.showMessageDialog(null, //B12xGui.parentTabbedPane, //optionFrame,
			"You have no internet and cannot download data right now",
			"Houston, we have a problem",
			JOptionPane.ERROR_MESSAGE);
	}
}