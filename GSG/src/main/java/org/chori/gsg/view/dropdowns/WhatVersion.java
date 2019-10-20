package org.chori.gsg.view.dropdowns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;


import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.*;

public class WhatVersion { 

	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	public WhatVersion() {	}

	public JComboBox createWhatVersionComboBox(String whichTab) {
		System.out.println("Generating the which version combo box");
		
		VersionModel vm = new VersionModel();

		// instantiate combobox and its model
		JComboBox whatVersion = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		// JPanel newlayout = new JPanel();

		// who is this combobox for?
		switch(whichTab) {
			case "HLA":
				comboBoxModel = vm.versions();
				whatVersion.setModel(comboBoxModel);

				System.out.println(vm.localVersionData());
				whatVersion.setSelectedIndex(prefs.getInt("GSG_HLA_VERSION", 0));
				// whatLocus.setName("HLA-dropdown");
				hlaListener(whatVersion);
				break;
			case "NAME":
				comboBoxModel = vm.versions();
				break;
			case "NAME2":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
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

            	// if legacy version, update loci model to show available loci
            	String whichLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
            	LocusModel locusModel = new LocusModel();
            	B12xGui.whatLocusHla.setModel(locusModel.loci("HLA"));

            	// grab the new available default locus and update the GUI accordingly
            	whichLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
				HlaSearchBoxGenerator hlaSBG = new HlaSearchBoxGenerator();
				JPanel newGfePanel = hlaSBG.generateHlaPanel(whichLocus);
            	newGfePanel.setName("HLA-GFE");

            	// borrow the find panel method from WhatLocus
            	WhatLocus whatLocus = new WhatLocus();
            	JPanel oldPanel = whatLocus.findPanel(B12xGui.hlaPanel, "HLA-GFE");
            	B12xGui.hlaPanel.remove(oldPanel);
            	B12xGui.hlaPanel.add(newGfePanel).revalidate();
            	B12xGui.hlaPanel.repaint();

            	// update the preferences
            	prefs.putInt("GSG_HLA_LOCUS", B12xGui.whatLocusHla.getSelectedIndex());
            	prefs.put("GSG_HLA_LOCUS_STRING", whichLocus);
            }
        });
	}
}