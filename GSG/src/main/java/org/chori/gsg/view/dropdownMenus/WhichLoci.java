package org.chori.gsg.view.dropdownMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.*;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns keaton01 706m
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLoci { 

	private final String[] loci = {"HLA", "KIR"};
	private Preferences prefs = Preferences.userNodeForPackage(GSG.class);

	public WhichLoci() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu)
	 * 
	 * @param whichTab is passed to the ActionListener so it changes the correct preferences.
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhichLociComboBox(String whichTab) {
		System.out.println("Generating the which loci combo box");
		
		// instantiate combobox and its model
		JComboBox whichLociDropDown = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		whichLociDropDown.setModel(comboBoxModel);

		whichLociDropDown.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_LOCI", 0));

		whichLociListener(whichLociDropDown, whichTab);
		return whichLociDropDown;
	}

	private void whichLociListener(JComboBox whichLoci, String whichTab) {
		whichLoci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = whichLoci.getSelectedItem().toString();
				System.out.println("Which " + whichTab + " Loci listener triggered");

				updateLocusAndVersions(whichTab, lociType);

				prefs.putInt("GSG_" + whichTab + "_LOCI", whichLoci.getSelectedIndex());
				prefs.put("GSG_" + whichTab + "_LOCI_STRING", lociType);
			}
		});
	}

	private void updateLocusAndVersions(String whichTab, String lociType) {
		
	}
}