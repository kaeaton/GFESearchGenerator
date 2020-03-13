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
	// private LocusModel locusModel = new LocusModel();

	public WhatLocusName() { }

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param whichTabComboBox tells which ActionListener is assigned to it. Some tabs have more than one.
	 * @param version which version are we looking at. Local/legacy data may not have all loci available
	 * @param lociType identifies if it is looking for HLA/KIR/ABO
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatLocusComboBox(String version, String lociType) {
		System.out.println("Generating the what locus name combo box");
		
		// instantiate combobox and its model
		JComboBox whatLocusDropDown = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		whatLocusDropDown.setModel(locusModel.loci(version, lociType));

		// set selected locus and assign listener
		setSelectedLocusIndex(whatLocusDropDown, lociType);
		addWhatLocusListener(whatLocusDropDown);

		return whatLocusDropDown;
	}

	protected void setSelectedLocusIndex(JComboBox whatLocusDropDown, String lociType) {
		try {
			// try using prefs
			whatLocusDropDown.setSelectedIndex(prefs.getInt("GSG_NAME_" + lociType + "_LOCUS", 0));
		} catch (Exception ex) { 
			// if the pref exceeds the length of the model list, reset prefs
			PrefProbException ppex = new PrefProbException();
			System.out.println("whatLocus.setSelectedLocusIndex(): " + ex);
		}
	}

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