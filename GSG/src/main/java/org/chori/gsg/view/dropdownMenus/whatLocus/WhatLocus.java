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
public abstract class WhatLocus { 

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	protected LocusModel locusModel = new LocusModel();

	public WhatLocus() { }

	public abstract JComboBox createWhatLocusComboBox(String version, String lociType);

	protected abstract void setSelectedLocusIndex(JComboBox whatLocusDropDown, String lociType);

	protected abstract void addWhatLocusListener(JComboBox whatLocusDropDown);
}