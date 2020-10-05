package org.chori.gsg.view.dropdownMenus.whatLocus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.locusModel.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.*;


/**
 * Creates the dropdowns to select what locus and associated listeners
 * 
 * @author Katrina Eaton
 * 
 */
public class WhatLocusName extends WhatLocus { 

	// private GfeSearchPanelAssembler gfeSearchPanelAssembler = new GfeSearchPanelAssembler();
	// private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	// private String whichLocus = "HLA-A";
	protected LocusModel locusModel;
	protected DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

	public WhatLocusName() { }

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param version which version are we looking at. Local/legacy data may not have all loci available
	 * @param lociType identifies if it is looking for HLA/KIR/ABO
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatLocusComboBox(String version, String lociType) {
		System.out.println("Generating the what locus name combo box");
		
		// instantiate combobox
		JComboBox whatLocusDropDown = new JComboBox();

		// create and assign appropriate locus model based on loci type
		locusModel = locusModelFactory.createLocusModel(lociType);
		comboBoxModel = locusModel.assembleLocusModel(version);
		whatLocusDropDown.setModel(comboBoxModel);

		// set selected locus and assign listener
		setSelectedLocusIndex(whatLocusDropDown, "NAME", lociType);
		addWhatLocusListener(whatLocusDropDown);

		return whatLocusDropDown;
	}

	// protected void setSelectedLocusIndex(JComboBox whatLocusDropDown, String lociType) {
	// 	try {
	// 		// try using prefs
	// 		whatLocusDropDown.setSelectedIndex(prefs.getInt("GSG_NAME_" + lociType + "_LOCUS", 0));
	// 	} catch (Exception ex) { 
	// 		// if the pref exceeds the length of the model list, reset prefs
	// 		PrefProbException ppex = new PrefProbException();
	// 		System.out.println("whatLocus.setSelectedLocusIndex(): " + ex);
	// 	}
	// }

	protected void addWhatLocusListener(JComboBox whatLocusDropDown) {
		whatLocusDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = GSG.whichLociName.getSelectedItem().toString();
				String whatLocus = whatLocusDropDown.getSelectedItem().toString();
				System.out.println("Which Locus name listener triggered");
				prefs.putInt("GSG_NAME_" + lociType + "_LOCUS", whatLocusDropDown.getSelectedIndex());
				prefs.put("GSG_NAME_" + lociType + "_LOCUS_STRING", whatLocus);
			}
		});
	}
}