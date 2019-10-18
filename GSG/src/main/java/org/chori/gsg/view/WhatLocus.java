package org.chori.gsg.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.view.searchboxes.*;

public class WhatLocus { 

	HlaSearchBoxGenerator hlaSBG = new HlaSearchBoxGenerator();
	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);


	public WhatLocus() {

	}

	public JComboBox createWhatLocusComboBox(String whichComboBox) {
		System.out.println("Generating the which locus combo box");
		
		// instantiate combobox and its model
		JComboBox whatLocus = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		// JPanel newlayout = new JPanel();

		// who is this combobox for?
		switch(whichComboBox) {
			case "HLA":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
				whatLocus.setModel(comboBoxModel);
				whatLocus.setSelectedIndex(prefs.getInt("GSG_HLA_LOCUS", 0));
				hlaListener(whatLocus);
				break;
			case "NAME":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
				whatLocus.setModel(comboBoxModel);
				whatLocus.setSelectedIndex(prefs.getInt("GSG_NAME_LOCUS_1", 0));
				// whatLocus.setName("HLA-dropdown");
				// hlaListener(whatLocus);
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

    	// whatLocus.setModel(comboBoxModel);

		return whatLocus;
	}

	private void hlaListener(JComboBox hlaWhatLocus) {
		hlaWhatLocus.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
				JPanel newGfePanel = hlaSBG.generateHlaPanel(hlaWhatLocus.getSelectedItem().toString());
            	newGfePanel.setName("HLA-GFE");
            	JPanel oldPanel = findPanel(B12xGui.hlaPanel, "HLA-GFE");
            	B12xGui.hlaPanel.remove(oldPanel);
            	B12xGui.hlaPanel.add(newGfePanel).revalidate();
            	B12xGui.hlaPanel.repaint();
                System.out.println("Which Loci listener triggered");
            	System.out.println("Position: " + hlaWhatLocus.getSelectedIndex());
            	prefs.putInt("GSG_HLA_LOCUS", hlaWhatLocus.getSelectedIndex());
            }
        });
	}

	private JPanel findPanel(JPanel whichTab, String whichPanel) {
		Component selectedPanel = B12xGui.hlaPanel;
		for (Component component : whichTab.getComponents()) {
            if (component.getName().equals(whichPanel)){
                selectedPanel = component;
                System.out.println("panel: " + selectedPanel);
            } 
        }
        return (JPanel)selectedPanel;
	}
}