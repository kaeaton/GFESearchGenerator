package org.chori.gsg.view.prefslisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.prefs.*;

import org.chori.gsg.view.B12xGui;
import org.chori.gsg.view.dropdowns.*;

/**
 * Creates a listener for changes to the bulk loci preference.
 * 
 * @author Katrina Eaton
 * 
 */

public class GfeLociPreferenceListener {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private WhatLocus whatLocus = new WhatLocus();

	public GfeLociPreferenceListener() { 
		prefs.addPreferenceChangeListener(gfeLociChangeListener);
	}

	private PreferenceChangeListener gfeLociChangeListener = new PreferenceChangeListener() {
		@Override
		public void preferenceChange(PreferenceChangeEvent pce) {
			if("GSG_GFE_LOCI_STRING".equals(pce.getKey())) {
				if(pce.getNewValue().equals("HLA")) {
					String locus = prefs.get("GSG_GFE_HLA_LOCUS_STRING", "HLA-A");
					whatLocus.setNewGfePanel(locus);
				} else {
					
				}
				// System.out.println("GFE loci pref listener: GSG_GFE_LOCI_STRING now set to: " + pce.getNewValue());
			}
		}
	};
}