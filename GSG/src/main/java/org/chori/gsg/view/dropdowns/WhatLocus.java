package org.chori.gsg.view.dropdowns;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.*;


/**
 * Creates the dropdowns to select the locus and listeners for the dropdowns
 * 
 * @author Katrina Eaton
 * 
 */
public class WhatLocus { 

	private HlaSearchBoxAssembler hlaSBA = new HlaSearchBoxAssembler();
	private final String[] fullHlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private String whichLocus = "HLA-A";
	private LocusModel locusModel = new LocusModel();

	public WhatLocus() {

	}

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param whichComboBox tells which ActionListener is assigned to it. Some tabs have more than one.
	 * @param version which version are we looking at. Local/legacy data may not have all loci available
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatLocusComboBox(String whichComboBox, String version) {
		System.out.println("Generating the which locus combo box");
		
		// instantiate combobox and its model
		JComboBox whatLocus = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		// comboBoxModel = new DefaultComboBoxModel(fullHlaLoci);
		whatLocus.setModel(locusModel.loci(version));// comboBoxModel);
		// JPanel newlayout = new JPanel();

		// who is this combobox for?
		switch(whichComboBox) {
			case "HLA":
				try {
					// try using prefs
					whatLocus.setSelectedIndex(prefs.getInt("GSG_HLA_LOCUS", 0));
				} catch (Exception ex) { 
					// if the pref exceeds the length of the model list, reset prefs
					PrefProbException ppex = new PrefProbException();
					System.out.println("HLA whatLocus set selected index: " + ex);
				}
				hlaListener(whatLocus);
				break;
			case "NAME":
				try {
					whatLocus.setSelectedIndex(prefs.getInt("GSG_NAME_LOCUS_1", 0));
				} catch (Exception ex) {
					// if the pref exceeds the length of the model list, reset prefs 
					PrefProbException ppex = new PrefProbException();
					System.out.println("Name whatLocus set selected index: " + ex);
				}
				nameListener(whatLocus);
				break;
			default:
				System.out.println("Locus: Haven't set up that combobox model yet");
		}

		// whatLocus.setModel(comboBoxModel);

		return whatLocus;
	}

	private void hlaListener(JComboBox hlaWhatLocus) {
		hlaWhatLocus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				whichLocus = hlaWhatLocus.getSelectedItem().toString();
				setNewHlaPanel(whichLocus);
				System.out.println("Which Locus listener triggered");
				prefs.putInt("GSG_HLA_LOCUS", hlaWhatLocus.getSelectedIndex());
				prefs.put("GSG_HLA_LOCUS_STRING", whichLocus);
			}
		});
	}

	private void nameListener(JComboBox nameWhatLocus) {
		nameWhatLocus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				whichLocus = nameWhatLocus.getSelectedItem().toString();
				System.out.println("Which Locus name listener triggered");
				prefs.putInt("GSG_NAME_LOCUS_1", nameWhatLocus.getSelectedIndex());
				prefs.put("GSG_NAME_LOCUS_STRING_1", whichLocus);
			}
		});
	}

	/**
	 * A helper method that allows the program to find the currently displayed search panel
	 * 
	 * @param whichTab which tab is should look for the panel in
	 * @param whichPanel the current panel's name the locus it's for
	 * @return the currently displayed JPanel
	 */
	public JPanel findPanel(JPanel whichTab, String whichPanel) {
		Component selectedPanel = B12xGui.hlaPanel;
		for (Component component : whichTab.getComponents()) {
			if (component.getName().equals(whichPanel)){
				selectedPanel = component;
				System.out.println("panel: " + selectedPanel);
			} 
		}
		return (JPanel)selectedPanel;
	}

	/**
	 * A helper method that sets the new HLA GFE search panel.
	 * 
	 * @param locus what HLA locus should be displayed
	 */
	public void setNewHlaPanel(String locus) {
		System.out.println("Triggered setNewHlaPanel");
		JPanel newGfePanel = hlaSBA.assembleHlaPanel(locus);
		newGfePanel.setName("HLA-GFE");
		JPanel oldPanel = findPanel(B12xGui.hlaPanel, "HLA-GFE");
		B12xGui.hlaPanel.remove(oldPanel);
		B12xGui.hlaPanel.add(newGfePanel).revalidate();
		B12xGui.hlaPanel.repaint();
	}
}