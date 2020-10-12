package org.chori.gsg.gui.dropdownMenus.whatVersion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.gui.*;
import org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel.*;

public class WhatVersionBulk extends WhatVersion { 

	protected VersionModel versionModelBulk = versionModelFactory.createVersionModel("BULK");

	public WhatVersionBulk() { }

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatVersionComboBox() {

		String lociType = prefs.get("GSG_BULK_LOCI_STRING", "HLA");
		
		JComboBox whatVersionDropDown = new JComboBox();

		// populate dropdown
		DefaultComboBoxModel defaultComboBoxModel = versionModelBulk.assembleVersionModel(lociType);
		whatVersionDropDown.setModel(defaultComboBoxModel);

		return whatVersionDropDown;
	}

	protected void addWhatVersionListener(JComboBox whatVersionDropDown) {
		// no listener necessary, passively read to get version to download
	}

}