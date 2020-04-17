package org.chori.gsg.view.dropdownMenus.whatLocus;

// import java.awt.Component;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
import java.util.prefs.Preferences;
// import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
// import javax.swing.JPanel;

// import org.chori.gsg.exceptions.*;
// import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.locusModel.*;



/**
 * Creates the dropdowns to select what locus and associated listeners
 * 
 * @author Katrina Eaton
 * 
 */
public abstract class WhatLocus { 

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	protected LocusModelFactory locusModelFactory = LocusModelFactory.getLocusModelFactoryInstance();

	public WhatLocus() { }

	public abstract JComboBox createWhatLocusComboBox(String version, String lociType);
	protected abstract void addWhatLocusListener(JComboBox whatLocusDropDown);

	protected void setSelectedLocusIndex(JComboBox whatLocusDropDown, String whichTab, String lociType) {
		try {
			// try using prefs
			whatLocusDropDown.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_" + lociType + "_LOCUS", 0));
		} catch (Exception ex) { 
			// if the pref exceeds the length of the model list, reset prefs
			whatLocusDropDown.setSelectedIndex(0);

			// PrefProbException ppex = new PrefProbException();
			System.out.println("whatLocus.setSelectedLocusIndex(): " + ex);
		}
	}
}