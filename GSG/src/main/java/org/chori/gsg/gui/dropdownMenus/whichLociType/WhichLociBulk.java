package org.chori.gsg.view.dropdownMenus.whichLociType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;

/**
 * Creates the dropdown to select the set of genes being used, and listener for the dropdown
 * for the bulk download section of the options tab.
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLociBulk extends WhichLoci { 

	protected VersionModel versionModelBulk = versionModelFactory.createVersionModel("BULK");

	public WhichLociBulk() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu)
	 * for the BULK download in the options tab.
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhichLociComboBox() {
		System.out.println("Generating the bulk which loci combo box");
		
		// instantiate combobox and its model
		JComboBox whichLociDropDown = new JComboBox();

		// loci defined in parent class
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		whichLociDropDown.setModel(comboBoxModel);

		whichLociDropDown.setSelectedIndex(prefs.getInt("GSG_BULK_LOCI", 0));

		addWhichLociListener(whichLociDropDown);

		return whichLociDropDown;
	}

	protected void addWhichLociListener(JComboBox whichLociDropDown) {
		whichLociDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = whichLociDropDown.getSelectedItem().toString();
				System.out.println("WhichLociBulk listener triggered");

				updateLocusAndVersionDropdowns(lociType);

				prefs.putInt("GSG_BULK_LOCI", whichLociDropDown.getSelectedIndex());
				prefs.put("GSG_BULK_LOCI_STRING", lociType);
			}
		});
	}

	protected void updateLocusAndVersionDropdowns(String lociType) {		

		DefaultComboBoxModel newVersionModel = versionModelBulk.assembleVersionModel(lociType);
		GSG.whatVersionBulk.setModel(newVersionModel);

		// no locus for bulk download
	}
}