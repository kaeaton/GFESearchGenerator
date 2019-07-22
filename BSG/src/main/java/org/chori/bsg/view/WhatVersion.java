package org.chori.bsg.view;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class WhatVersion { 

	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};

	public WhatVersion() {

	}

	public JComboBox createWhatVersionComboBox(String whichTab) {
		System.out.println("Generating the which version combo box");
		
		// instantiate combobox and its model
		JComboBox whatVersion = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		// JPanel newlayout = new JPanel();

		// who is this combobox for?
		switch(whichTab) {
			case "HLA":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
				// whatLocus.setName("HLA-dropdown");
				// hlaListener(whatLocus);
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

    	whatVersion.setModel(comboBoxModel);

		return whatVersion;
	}
}