package org.chori.gsg.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.chori.gsg.view.buttons.ResetPrefsButton;

public class PrefProbException extends Exception{

	ResetPrefsButton resetPreferences = new ResetPrefsButton();

	public PrefProbException () {

		int reply = JOptionPane.showConfirmDialog(null,
			"There's a good chance your preferences are \npointing to something they shouldn't,\nwould you like to try reseting them?",
			"Possibly a preferences problem", JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(null, "Preferences flushed, \nplease restart the program");
          resetPreferences.flushPrefs();
          System.exit(0);
        }
	}
}