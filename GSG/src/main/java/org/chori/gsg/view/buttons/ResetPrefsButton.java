package org.chori.gsg.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.*;

public class ResetPrefsButton {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	public JButton resetPrefsButton = new JButton("Reset Preferences");

	public ResetPrefsButton() { }

	public JButton createResetPrefsButton() {
		System.out.println("Generating the preferences reset button");
		resetPrefsButton.addActionListener(resetPrefsListener);

		return resetPrefsButton;
	}

	public ActionListener resetPrefsListener = new ActionListener() {
		@Override
        public void actionPerformed(ActionEvent evt) {
        	Runnable resetPrefs = new Runnable() {
            	public void run() {
	            	flushPrefs();
		        }
		    };
			new Thread(resetPrefs).start();
        }
	};

	public void flushPrefs() {
		try {
    		System.out.println(prefs.absolutePath());
        	System.out.println(prefs.userRoot());
        	prefs.clear();
        	prefs.flush();
    		System.out.println(prefs.absolutePath());

        } catch (Exception ex) { System.out.println("Problem finding preferences to reset: " + ex); }
	}
}
