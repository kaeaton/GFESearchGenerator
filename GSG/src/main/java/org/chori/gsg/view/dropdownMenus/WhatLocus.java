package org.chori.gsg.view.dropdownMenus;

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
public class WhatLocus { 

	private GfeSearchPanelAssembler gfeSearchPanelAssembler = new GfeSearchPanelAssembler();
	private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	private String whichLocus = "HLA-A";
	private LocusModel locusModel = new LocusModel();

	public WhatLocus() {

	}

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param whichTabComboBox tells which ActionListener is assigned to it. Some tabs have more than one.
	 * @param version which version are we looking at. Local/legacy data may not have all loci available
	 * @param lociType identifies if it is looking for HLA/KIR/ABO
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatLocusComboBox(String whichTabComboBox, String version, String lociType) {
		System.out.println("Generating the what locus combo box");
		
		// instantiate combobox and its model
		JComboBox whatLocusDropDown = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		whatLocusDropDown.setModel(locusModel.loci(version, lociType));

		// set selected locus and assign listener
		setSelectedLocusIndex(whatLocusDropDown, whichTabComboBox, lociType);
		assignListener(whatLocusDropDown, whichTabComboBox);

		return whatLocusDropDown;
	}

	private void setSelectedLocusIndex(JComboBox whatLocusDropDown, String whichTabComboBox, String lociType) {
		try {
			// try using prefs
			whatLocusDropDown.setSelectedIndex(prefs.getInt("GSG_" + whichTabComboBox + "_" + lociType + "_LOCUS", 0));
		} catch (Exception ex) { 
			// if the pref exceeds the length of the model list, reset prefs
			PrefProbException ppex = new PrefProbException();
			System.out.println("whatLocus.setSelectedLocusIndex(): " + ex);
		}
	}

	private void assignListener(JComboBox whatLocusDropDown, String whichTabComboBox) {
		switch(whichTabComboBox) {
			case "GFE":
				gfeListener(whatLocusDropDown);
				break;
			case "NAME":
				nameListener(whatLocusDropDown);
				break;
			default:
				System.out.println("WhatLocus.assignListener(): Haven't set up that combobox listener yet");
		}
	}

	private void gfeListener(JComboBox gfeWhatLocus) {
		gfeWhatLocus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = GSG.whichLociGfe.getSelectedItem().toString();
				whichLocus = gfeWhatLocus.getSelectedItem().toString();
				setNewGfePanel(whichLocus);
				System.out.println("Which Locus listener triggered");
				prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", gfeWhatLocus.getSelectedIndex());
				prefs.put("GSG_GFE_" + lociType + "_LOCUS_STRING", whichLocus);
			}
		});
	}

	private void nameListener(JComboBox nameWhatLocus) {
		nameWhatLocus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = GSG.whichLociName.getSelectedItem().toString();
				whichLocus = nameWhatLocus.getSelectedItem().toString();
				System.out.println("Which Locus name listener triggered");
				prefs.putInt("GSG_NAME_" + lociType + "_LOCUS", nameWhatLocus.getSelectedIndex());
				prefs.put("GSG_NAME_" + lociType + "_LOCUS_STRING", whichLocus);
			}
		});
	}

	/**
	 * A helper method that sets the new GFE search panel.
	 * The loci are determined from preferences.
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
	 * @param whichTab which tab is should look for the panel in
	 * @param whichPanel the current panel's name the locus it's for
	 * @return the currently displayed JPanel
	 */
	public JPanel findPanel(JPanel whichTab, String whichPanel) {
		Component selectedPanel = GSG.gfePanel;
		for (Component component : whichTab.getComponents()) {
			if (component.getName().equals(whichPanel)){
				selectedPanel = component;
				System.out.println("panel: " + selectedPanel);
			} 
		}
		return (JPanel)selectedPanel;
	}
}