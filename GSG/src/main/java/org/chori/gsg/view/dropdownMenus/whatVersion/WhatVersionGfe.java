package org.chori.gsg.view.dropdownMenus.whatVersion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.locusModel.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;
import org.chori.gsg.view.tabs.GfeTab;
import org.chori.gsg.view.*;

public class WhatVersionGfe extends WhatVersion { 

	protected VersionModel versionModelGfe = versionModelFactory.createVersionModel("GFE");
	// private UpdateGfePanel updateGfePanel = new UpdateGfePanel();
	// protected LocusModelFactory locusModelFactory = LocusModelFactory.getLocusModelFactoryInstance();
	private static GfeTab assembleGfeTab = GfeTab.getGfeTabInstance();
	protected LocusModel locusModel;
	protected DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

	public WhatVersionGfe() { }

	/**
	 * Generates the JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhatVersionComboBox() {
		
		String lociType = prefs.get("GSG_GFE_LOCI_STRING", "HLA");
		JComboBox whatVersionDropDown = new JComboBox();

		// populate dropdown
		DefaultComboBoxModel defaultComboBoxModel = versionModelGfe.assembleVersionModel(lociType);
		whatVersionDropDown.setModel(defaultComboBoxModel);
		
		setSelectedVersionIndex(whatVersionDropDown, "GFE", lociType);

		addWhatVersionListener(whatVersionDropDown);

		return whatVersionDropDown;
	}

	protected void addWhatVersionListener(JComboBox whatVersionDropDown) {
		whatVersionDropDown.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {

				String lociType = GfeTab.whichLociGfe.getSelectedItem().toString();
				// String lociType = GSG.whichLociGfe.getSelectedItem().toString();
            	String version = whatVersionDropDown.getSelectedItem().toString();
                System.out.println("Which gfe version listener triggered");

            	prefs.putInt("GSG_GFE_" + lociType + "_VERSION", whatVersionDropDown.getSelectedIndex());

            	// create and assign appropriate locus model based on loci type
				locusModel = locusModelFactory.createLocusModel(lociType);
				comboBoxModel = locusModel.assembleLocusModel(version);
            	GfeTab.whatLocusGfe.setModel(comboBoxModel);
            	// GSG.whatLocusName.setModel(comboBoxModel);

            	updateGfePanel(version, lociType);
            }
        });
	}

	private void updateGfePanel(String version, String lociType) {


		String whatLocus = prefs.get("GSG_GFE_" + lociType + "_LOCUS_STRING", "HLA-B");
		assembleGfeTab.updateTheGfePanel(whatLocus);

    	// create and assign appropriate locus model based on loci type
		locusModel = locusModelFactory.createLocusModel(lociType);
		comboBoxModel = locusModel.assembleLocusModel(version);
    	GfeTab.whatLocusGfe.setModel(comboBoxModel);
    	// GSG.whatLocusGfe.setModel(comboBoxModel);

    	// grab the new available default locus
    	// String whichLocus = GSG.whatLocusGfe.getSelectedItem().toString();
    	String whichLocus = GfeTab.whatLocusGfe.getSelectedItem().toString();

    	// borrow the set new panel method from WhatLocus
    	WhatLocusGfe whatLocusGfe = new WhatLocusGfe();
    	whatLocusGfe.setNewGfePanel(whichLocus);

    	// update the preferences
    	// prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", GSG.whatLocusGfe.getSelectedIndex());
    	prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", GfeTab.whatLocusGfe.getSelectedIndex());
    	prefs.put("GSG_GFE_" + lociType + "_LOCUS_STRING", whichLocus);
	}
}