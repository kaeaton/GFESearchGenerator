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
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

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
		JComboBox whichLoci = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		whichLoci.setModel(comboBoxModel);

		whichLoci.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_LOCI", 0));

		whichLociListener(whichLoci, whichTab);
		return whichLoci;
	}

	private void whichLociListener(JComboBox whichLoci, String whichTab) {
		whichLoci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String whichLociName = whichLoci.getSelectedItem().toString();
				System.out.println("Which " + whichTab + " Loci listener triggered");

				updateLocusAndVersions(whichTab, whichLociName);

				prefs.putInt("GSG_" + whichTab + "_LOCI", whichLoci.getSelectedIndex());
				prefs.put("GSG_" + whichTab + "_LOCI_STRING", whichLociName);
			}
		});
	}

	private void updateLocusAndVersions(String whichTab, String whichLoci) {
		
	}
}