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
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;
import org.chori.gsg.view.*;

public class WhatVersionGfe extends WhatVersion { 

	protected VersionModel versionModelGfe = versionModelFactory.createVersionModel("GFE");

	public WhatVersionGfe() { }

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

				String lociType = GSG.whichLociGfe.getSelectedItem().toString();
            	String whichVersion = whatVersionDropDown.getSelectedItem().toString();
                System.out.println("Which version listener triggered");

            	prefs.putInt("GSG_GFE_" + lociType + "_VERSION", whatVersionDropDown.getSelectedIndex());

            	LocusModel locusModel = new LocusModel();
            	GSG.whatLocusName.setModel(locusModel.loci(whichVersion, lociType));

            	updateGfePanel(whichVersion, lociType);
            }
        });
	}

	private void updateGfePanel(String whichGfeVersion, String lociType) {
		LocusModel locusModel = new LocusModel();
    	GSG.whatLocusGfe.setModel(locusModel.loci(whichGfeVersion, lociType));

    	// grab the new available default locus
    	String whichLocus = GSG.whatLocusGfe.getSelectedItem().toString();

    	// borrow the set new panel method from WhatLocus
    	WhatLocusGfe whatLocusGfe = new WhatLocusGfe();
    	whatLocusGfe.setNewGfePanel(whichLocus);

    	// update the preferences
    	prefs.putInt("GSG_GFE_" + lociType + "_LOCUS", GSG.whatLocusGfe.getSelectedIndex());
    	prefs.put("GSG_GFE_" + lociType + "_LOCUS_STRING", whichLocus);
	}
}