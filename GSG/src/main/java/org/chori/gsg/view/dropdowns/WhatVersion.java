package org.chori.gsg.view.dropdowns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.dropdowns.*;
import org.chori.gsg.view.*;

public class WhatVersion { 

	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	public WhatVersion() { }

	public JComboBox createWhatVersionComboBox(String whichTab) {
		System.out.println("Generating the which version combo box");
		
		VersionModel vm = new VersionModel();

		// instantiate combobox and its model
		JComboBox whatVersion = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

		// who is this combobox for?
		switch(whichTab) {
			case "HLA":
				comboBoxModel = vm.versions();
				whatVersion.setModel(comboBoxModel);

				// System.out.println(vm.localVersionData());
				try {
					whatVersion.setSelectedIndex(prefs.getInt("GSG_HLA_VERSION", 0));
				} catch (IllegalArgumentException iex) { 
					System.out.println("HLA whatVersion setSelectedIndex error: " + iex); 
					PrefProbException ppex = new PrefProbException();
				}
		
				// whatLocus.setName("HLA-dropdown");
				hlaListener(whatVersion);
				break;
			case "NAME":
				comboBoxModel = vm.versions();
				whatVersion.setModel(comboBoxModel);
				try {
					whatVersion.setSelectedIndex(prefs.getInt("GSG_NAME_VERSION_1", 0));
				} catch (IllegalArgumentException iex) { 
					System.out.println("Name whatVersion setSelectedIndex error: " + iex); 
					PrefProbException ppex = new PrefProbException();
				}

				nameListener(whatVersion);
				break;
			case "NAME2":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
				break;
			case "BULK":
				comboBoxModel = vm.bulkVersions();
				whatVersion.setModel(comboBoxModel);
				break;

			default:
				System.out.println("Version: Haven't set up that combobox model yet");
		}

    	whatVersion.setModel(comboBoxModel);

		return whatVersion;
	}

	private void hlaListener(JComboBox hlaWhatVersion) {
		hlaWhatVersion.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	String whichVersion = hlaWhatVersion.getSelectedItem().toString();
                System.out.println("Which version listener triggered");
            	prefs.putInt("GSG_HLA_VERSION", hlaWhatVersion.getSelectedIndex());
            	// prefs.put("GSG_HLA_LOCUS_STRING", whichLocus);

            	// if local version, update loci model to show available loci
            	LocusModel locusModel = new LocusModel();
            	B12xGui.whatLocusHla.setModel(locusModel.loci(whichVersion));

            	// grab the new available default locus
            	String whichLocus = B12xGui.whatLocusHla.getSelectedItem().toString();

            	// borrow the set new panel method from WhatLocus
            	WhatLocus whatLocus = new WhatLocus();
            	whatLocus.setNewHlaPanel(whichLocus);

            	// update the preferences
            	prefs.putInt("GSG_HLA_LOCUS", B12xGui.whatLocusHla.getSelectedIndex());
            	prefs.put("GSG_HLA_LOCUS_STRING", whichLocus);
            }
        });
	}

	private void nameListener(JComboBox nameWhatVersion) {
		nameWhatVersion.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	String whichVersion = nameWhatVersion.getSelectedItem().toString();
                System.out.println("Which name version listener triggered");
            	prefs.putInt("GSG_NAME_VERSION_1", nameWhatVersion.getSelectedIndex());
            	// prefs.put("GSG_NAME_LOCUS_STRING_1", whichLocus);

            	// if local version, update loci model to show available loci
            	LocusModel locusModel = new LocusModel();
            	B12xGui.whatLocusName.setModel(locusModel.loci(whichVersion));

            	// grab the new available default locus
            	// String whichLocus = B12xGui.whatLocusName.getSelectedItem().toString();

            	// update the preferences
            	prefs.putInt("GSG_NAME_LOCUS_1", B12xGui.whatLocusName.getSelectedIndex());
            	// prefs.put("GSG_NAME_LOCUS_STRING_1", whichLocus);
            }
        });
	}
}