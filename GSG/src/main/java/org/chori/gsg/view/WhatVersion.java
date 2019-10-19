package org.chori.gsg.view;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.view.searchboxes.*;

public class WhatVersion { 

	private final String[] hlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};

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
				System.out.println(vm.localVersionData());
				// whatLocus.setName("HLA-dropdown");
				// hlaListener(whatLocus);
				break;
			case "NAME":
				comboBoxModel = new DefaultComboBoxModel(hlaLoci);
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
}