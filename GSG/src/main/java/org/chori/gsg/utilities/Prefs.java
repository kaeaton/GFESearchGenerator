package org.chori.gsg.utilities.prefs;

import java.util.prefs.Preferences;

// import org.chori.gsg.view.*;


public class Prefs {

	private static final Preferences preferences = Preferences.userNodeForPackage(Prefs.class);

	public Prefs() { }

	/* for which tab is open in the GUI */

	/**
	 * Gets the id of what tab was last open. Used in Gui to determine which tab 
	 * should be open on start.
	 *
	 * @return The integer associated with the current tab.
	 */
	public static int getCurrentTab() {
		System.out.println("Get current tab: which tab are we storing? " 
							+ preferences.getInt("SELECTED_TAB", 0));
		return preferences.getInt("SELECTED_TAB", 0);
		
	}

	/**
	 * Sets the preference for what tab is currently selected. Used in Gui's ChangeListener.
	 *
	 * @param currentTab The integer associated with the currently open tab.
	 */
	public static void setCurrentTab(int currentTab) {
		preferences.putInt("SELECTED_TAB", currentTab);
		System.out.println("Set current tab: which tab is selected? " 
							+ preferences.getInt("SELECTED_TAB", 0));
	}




}