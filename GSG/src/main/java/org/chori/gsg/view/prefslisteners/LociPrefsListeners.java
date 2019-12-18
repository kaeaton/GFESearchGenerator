package org.chori.gsg.view.prefslisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.prefs.*;

import org.chori.gsg.view.B12xGui;

/**
 * Creates listeners for changes to the loci preferences.
 * When changed this will update the appropriate tab to the correct
 * interface.
 * 
 * @author Katrina Eaton
 * 
 */

public class LociPrefsListeners {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	public LociPrefsListeners() { 
		System.out.println("Preference listeners path: " + prefs.absolutePath());
		// try { System.out.println("Preference listeners keys: " + Arrays.toString(prefs.keys())); }
		// catch(Exception ex) { System.out.println("Preference keys: " + ex); }
		prefs.addPreferenceChangeListener(nameLociChangeListener);
		prefs.addPreferenceChangeListener(featureLociChangeListener);
	}

	private PreferenceChangeListener nameLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_NAME_LOCI_STRING".equals(pce.getKey())) {
				String whichLoci = pce.getNewValue();
				System.out.println("GFE loci pref listener: GSG_GFE_LOCI_STRING now set to: " + whichLoci);
			}
		}
	};

	private PreferenceChangeListener featureLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_FEATURE_LOCI_STRING".equals(pce.getKey())) {
				String whichLoci = pce.getNewValue();
				System.out.println("GFE loci pref listener: GSG_GFE_LOCI_STRING now set to: " + whichLoci);
			}
		}
	};

}