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
public class WhatLocusGfe extends WhatLocus { 

	private GfeSearchPanelAssembler gfeSearchPanelAssembler = new GfeSearchPanelAssembler();
	private LocusModel locusModel;
	private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

	public WhatLocusGfe() {	}

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param version which version are we looking at. Local/legacy data may not have all loci available
	 * @param lociType identifies if it is looking for HLA/KIR
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatLocusComboBox(String version, String lociType) {
		System.out.println("Generating the what locus gfe combo box");
		
		// instantiate combobox and its model
		JComboBox whatLocusDropDown = new JComboBox();
		
		// create and assign appropriate locus model based on loci type
		locusModel = locusModelFactory.createLocusModel(lociType);
		comboBoxModel = locusModel.assembleLocusModel(version);
		whatLocusDropDown.setModel(comboBoxModel);

		// set selected locus and assign listener
		setSelectedLocusIndex(whatLocusDropDown, "GFE", lociType);
		addWhatLocusListener(whatLocusDropDown);

		return whatLocusDropDown;
	}

	// protected void setSelectedLocusIndex(JComboBox whatLocusDropDown, String lociType) {
	// 	try {
	// 		// try using prefs
	// 		whatLocusDropDown.setSelectedIndex(prefs.getInt("GSG_GFE_" + lociType + "_LOCUS", 0));
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
				String lociType = GSG.whichLociGfe.getSelectedItem().toString();
				String whatLocus = whatLocusDropDown.getSelectedItem().toString();
				setNewGfePanel(whatLocus);
				System.out.println("Which Locus listener triggered");
				prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", whatLocusDropDown.getSelectedIndex());
				prefs.put("GSG_GFE_" + lociType + "_LOCUS_STRING", whatLocus);
			}
		});
	}

	/**
	 * A helper method that sets the new GFE search panel.
	 * 
	 * @param whatLocus what locus should be displayed
	 */
	public void setNewGfePanel(String whatLocus) {
		System.out.println("Triggered setNewGfePanel");
		JPanel newPanel = createNewGfePanel(whatLocus);
		JPanel oldPanel = findPanel(GSG.gfePanel, "GFE");

		GSG.gfePanel.remove(oldPanel);
		GSG.gfePanel.add(newPanel).revalidate();
		GSG.gfePanel.repaint();
	}

	private JPanel createNewGfePanel(String whatLocus) {
		JPanel newPanel = new JPanel();

		newPanel = gfeSearchPanelAssembler.getGfePanel(whatLocus);
		newPanel.setName("GFE");

		return newPanel;
	}

	/**
	 * A helper method that allows the program to find the currently displayed search panel
	 * 
	 * @param whichTabPanel which tab is should look for the panel in
	 * @param whichPanel the current panel's name the locus it's for
	 * @return the currently displayed JPanel
	 */
	public JPanel findPanel(JPanel whichTabPanel, String whichPanel) {
		Component selectedPanel = GSG.gfePanel;
		for (Component component : whichTabPanel.getComponents()) {
			if (component.getName().equals(whichPanel)){
				selectedPanel = component;
				System.out.println("panel: " + selectedPanel);
			} 
		}
		return (JPanel)selectedPanel;
	}
}