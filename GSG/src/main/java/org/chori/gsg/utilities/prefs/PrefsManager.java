package org.chori.gsg.utilities.prefs;

import java.util.prefs.Preferences;

import org.chori.gsg.view.*;


public abstract class PrefsManager {

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);

	public PrefsManager() { }

	public abstract static void snapshot();
	public abstract static int getWhichLoci();
	public abstract static int getWhatVersion(String whichLoci);
	public abstract static int getWhatLocus(String whichLoci);





}