package org.chori.gsg.view.dropdownMenus.whichLociType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLociGfe extends WhichLoci { 

	protected VersionModel versionModelGfe = VersionModelFactory.getVersionModelFactoryInstance().createVersionModel("GFE");

	public WhichLociGfe() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu)
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhichLociComboBox() {
		System.out.println("Generating the which loci combo box");
		
		// instantiate combobox and its model
		JComboBox whichLociDropDown = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		whichLociDropDown.setModel(comboBoxModel);

		whichLociDropDown.setSelectedIndex(prefs.getInt("GSG_GFE_LOCI", 0));

		addWhichLociListener(whichLociDropDown);

		return whichLociDropDown;
	}

	protected void addWhichLociListener(JComboBox whichLociDropDown) {
		whichLociDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = whichLociDropDown.getSelectedItem().toString();
				System.out.println("WhichLociGfe listener triggered");

				updateLocusAndVersions();

				prefs.putInt("GSG_GFE_LOCI", whichLociDropDown.getSelectedIndex());
				prefs.put("GSG_GFE_LOCI_STRING", lociType);
			}
		});
	}

	protected void updateLocusAndVersions() {
		String lociType = prefs.get("GSG_GFE_LOCI_STRING", "HLA");

		DefaultComboBoxModel newVersionModel = versionModelGfe.assembleVersionModel(lociType);
		GSG.whatVersionBulk.setModel(newVersionModel);

	}
}