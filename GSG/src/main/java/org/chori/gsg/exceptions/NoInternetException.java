package org.chori.gsg.exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoInternetException extends Exception {

	public NoInternetException () {

		JOptionPane.showMessageDialog(null,
			"You have no internet and cannot download data right now",
			"Houston, we have a problem",
			JOptionPane.ERROR_MESSAGE);
	}
}