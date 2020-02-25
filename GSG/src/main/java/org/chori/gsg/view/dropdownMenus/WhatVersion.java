package org.chori.gsg.view.dropdownMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.view.*;

public class WhatVersion { 

	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private final String[] kirLoci = {"KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1"};
	private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	private DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();

	public WhatVersion() { }

	public JComboBox createWhatVersionComboBox(String whichTab, String lociType) {
		System.out.println("Generating the which version combo box");
		
		// instantiate combobox and its model
		JComboBox whatVersionDropDown = new JComboBox();

		// who is this combobox for?
		switch(whichTab) {
			case "GFE":
				setVersionModel(whatVersionDropDown, whichTab, lociType);
				setIndex(whatVersionDropDown, whichTab, lociType);	
				gfeListener(whatVersionDropDown);

				break;
			case "NAME":
				setVersionModel(whatVersionDropDown, whichTab, lociType);
				setIndex(whatVersionDropDown, whichTab, lociType);
				nameListener(whatVersionDropDown);

				break;
			case "BULK":
				setVersionModel(whatVersionDropDown, whichTab, lociType);

				break;

			default:
				System.out.println("Version: Haven't set up that combobox model yet");
		}

		return whatVersionDropDown;
	}

	public void setVersionModel(JComboBox whatVersionDropDown, String whichTab, String lociType) {
		VersionModel versionModel = new VersionModel();

		if(whichTab.equals("BULK")) {
			defaultComboBoxModel = versionModel.bulkVersions();
		} else {
			defaultComboBoxModel = versionModel.assembleVersionModel(lociType);
		}

		whatVersionDropDown.setModel(defaultComboBoxModel);
	}

	private void setIndex(JComboBox whatVersionDropDown, String whichTab, String lociType) {
		try {
			whatVersionDropDown.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_" + lociType + "_VERSION", 0));
		} catch (IllegalArgumentException iex) { 
			System.out.println("Name whatVersion.setIndex(): setSelectedIndex error: " + iex); 
			PrefProbException ppex = new PrefProbException();
		}
	}

	private void gfeListener(JComboBox gfeWhatVersion) {
		gfeWhatVersion.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {

				String lociType = GSG.whichLociGfe.getSelectedItem().toString();
            	String whichGfeVersion = gfeWhatVersion.getSelectedItem().toString();
                System.out.println("Which version listener triggered");

            	prefs.putInt("GSG_GFE_" + lociType + "_VERSION", gfeWhatVersion.getSelectedIndex());

            	updateGfePanel(whichGfeVersion, lociType);
            }
        });
	}

	private void nameListener(JComboBox nameWhatVersion) {
		nameWhatVersion.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	// if local version, update locus model to show available loci
				String lociType = GSG.whichLociName.getSelectedItem().toString(); String whichVersion = nameWhatVersion.getSelectedItem().toString();
                System.out.println("Which name version listener triggered");
            	prefs.putInt("GSG_NAME_" + lociType + "_VERSION", nameWhatVersion.getSelectedIndex());

            	LocusModel locusModel = new LocusModel();
            	GSG.whatLocusName.setModel(locusModel.loci(whichVersion, lociType));

            	// update the preferences
            	prefs.putInt("GSG_NAME_" + lociType + "_LOCUS", GSG.whatLocusName.getSelectedIndex());
            	// prefs.put("GSG_NAME_" + lociType + "_LOCUS_STRING", whichLocus);
            }
        });
	}

	private void updateGfePanel(String whichGfeVersion, String lociType) {
		LocusModel locusModel = new LocusModel();
    	GSG.whatLocusGfe.setModel(locusModel.loci(whichGfeVersion, lociType));

    	// grab the new available default locus
    	String whichLocus = GSG.whatLocusGfe.getSelectedItem().toString();

    	// borrow the set new panel method from WhatLocus
    	WhatLocus whatLocus = new WhatLocus();
    	whatLocus.setNewGfePanel(whichLocus);

    	// update the preferences
    	prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", GSG.whatLocusGfe.getSelectedIndex());
    	prefs.put("GSG_GFE_" + lociType + "_LOCUS_STRING", whichLocus);
	}
}