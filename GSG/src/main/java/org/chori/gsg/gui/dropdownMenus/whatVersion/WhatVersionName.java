package org.chori.gsg.gui.dropdownMenus.whatVersion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

// import org.chori.gsg.exceptions.*;
import org.chori.gsg.gui.dropdownMenus.whatLocus.*;
import org.chori.gsg.gui.dropdownMenus.whatLocus.locusModel.*;
import org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel.*;
import org.chori.gsg.gui.GSG;
import org.chori.gsg.gui.nameTab.NameTab;

public class WhatVersionName extends WhatVersion { 

	protected VersionModel versionModelName = versionModelFactory.createVersionModel("NAME");
	protected LocusModel locusModel;
	protected DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

	public WhatVersionName() { }

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatVersionComboBox() {
		String lociType = prefs.get("GSG_NAME_LOCI_STRING", "HLA");
		
		JComboBox whatVersionDropDown = new JComboBox();
		
		// populate dropdown
		DefaultComboBoxModel defaultComboBoxModel = versionModelName.assembleVersionModel(lociType);
		whatVersionDropDown.setModel(defaultComboBoxModel);

		setSelectedVersionIndex(whatVersionDropDown, "NAME", lociType);

		addWhatVersionListener(whatVersionDropDown);
		// nameListener(whatVersionDropDown);

		return whatVersionDropDown;
	}

	// protected void setSelectedVersionIndex(JComboBox whatVersionDropDown, String lociType) {
	// 	try {
	// 		// try using prefs
	// 		whatVersionDropDown.setSelectedIndex(prefs.getInt("GSG_NAME_" + lociType + "_VERSION", 0));
	// 	} catch (IllegalArgumentException iex) { 
	// 		// if the pref exceeds the length of the model list, reset prefs
	// 		System.out.println("Name whatVersion.setSelectedVersionIndex(): setSelectedIndex error: " + iex); 
	// 		// PrefProbException ppex = new PrefProbException();
	// 	}
	// }

	protected void addWhatVersionListener(JComboBox whatVersionDropDown) {
		whatVersionDropDown.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	// if local version, update locus model to show available loci
				String lociType = NameTab.whichLociName.getSelectedItem().toString(); 
				String version = whatVersionDropDown.getSelectedItem().toString();
                System.out.println("Which name version listener triggered");
                
            	prefs.putInt("GSG_NAME_" + lociType + "_VERSION", whatVersionDropDown.getSelectedIndex());

            	// create and assign appropriate locus model based on loci type
				locusModel = locusModelFactory.createLocusModel(lociType);
				comboBoxModel = locusModel.assembleLocusModel(version);
            	NameTab.whatLocusName.setModel(comboBoxModel);

            	// update the preferences
            	prefs.putInt("GSG_NAME_" + lociType + "_LOCUS", NameTab.whatLocusName.getSelectedIndex());
            	// prefs.put("GSG_NAME_" + lociType + "_LOCUS_STRING", whichLocus);
            }
        });
	}
}