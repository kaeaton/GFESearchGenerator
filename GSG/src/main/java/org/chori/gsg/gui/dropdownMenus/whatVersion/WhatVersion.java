package org.chori.gsg.gui.dropdownMenus.whatVersion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
// import javax.swing.JPanel;

// import org.chori.gsg.exceptions.*;
// import org.chori.gsg.gui.gfeTab.gfeSearchPanels.*;
import org.chori.gsg.gui.dropdownMenus.whatLocus.locusModel.*;
import org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel.*;
import org.chori.gsg.gui.GSG;

public abstract class WhatVersion { 

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	protected VersionModelFactory versionModelFactory = VersionModelFactory.getVersionModelFactoryInstance();
	protected LocusModelFactory locusModelFactory = LocusModelFactory.getLocusModelFactoryInstance();

	public WhatVersion() { }

	public abstract JComboBox createWhatVersionComboBox(); 
	protected abstract void addWhatVersionListener(JComboBox whatVersionDropDown);

	protected void setSelectedVersionIndex(JComboBox whatVersionDropDown, String whichTab, String lociType) {
		try {
			whatVersionDropDown.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_" + lociType + "_VERSION", 0));
		} catch (Exception ex) { 
			whatVersionDropDown.setSelectedIndex(0);
			System.out.println("Name whatVersion.setIndex(): setSelectedIndex error: " + ex); 
			// PrefProbException ppex = new PrefProbException();
		}
	}


}