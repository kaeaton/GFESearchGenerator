package org.chori.gsg.view.prefslisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.prefs.*;

import org.chori.gsg.view.B12xGui;

/**
 * Creates a listener for changes to the bulk loci preference.
 * 
 * @author Katrina Eaton
 * 
 */

public class BulkLociPreferenceListener {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	public BulkLociPreferenceListener() { 
		prefs.addPreferenceChangeListener(bulkLociChangeListener);
	}

	private PreferenceChangeListener bulkLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_BULK_LOCI_STRING".equals(pce.getKey())) {
				if(pce.getNewValue().equals("HLA")) {
					B12xGui.whatVersionBulk.setVisible(true);
				} else {
					B12xGui.whatVersionBulk.setVisible(false);
				}
				// System.out.println("Bulk loci pref listener: GSG_BULK_LOCI_STRING now set to: " + pce.getNewValue());
			}
		}
	};
}