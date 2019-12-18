package org.chori.gsg.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.prefs.*;

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
		prefs.addPreferenceChangeListener(gfeLociChangeListener);
		prefs.addPreferenceChangeListener(nameLociChangeListener);
		prefs.addPreferenceChangeListener(featureLociChangeListener);
		prefs.addPreferenceChangeListener(bulkLociChangeListener);
	}

	private PreferenceChangeListener gfeLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_GFE_LOCI_STRING".equals(pce.getKey())) {
				String whichLoci = pce.getNewValue();
				System.out.println("GFE loci pref listener: GSG_GFE_LOCI_STRING now set to: " + whichLoci);
			}
		}
	};

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

	private PreferenceChangeListener bulkLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_BULK_LOCI_STRING".equals(pce.getKey())) {
				if(pce.getNewValue().equals("HLA")) {
					B12xGui.whatVersionBulk.setVisible(true);
				} else{
					B12xGui.whatVersionBulk.setVisible(false);
				}
				System.out.println("GFE loci pref listener: GSG_GFE_LOCI_STRING now set to: " + pce.getNewValue());
			}
		}
	};
}