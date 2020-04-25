package org.chori.gsg.view.dropdownMenus.whichLociType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.*;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns
 * for the NAME tab.
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLociName extends WhichLoci { 

	public WhichLociName() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu)
	 * for the NAME tab.
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhichLociComboBox() {
		System.out.println("Generating the which loci combo box");
		
		// instantiate combobox and its model
		JComboBox whichLociDropDown = new JComboBox();

		// loci defined in parent class
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		whichLociDropDown.setModel(comboBoxModel);

		whichLociDropDown.setSelectedIndex(prefs.getInt("GSG_NAME_LOCI", 0));

		addWhichLociListener(whichLociDropDown);

		return whichLociDropDown;
	}

	protected void addWhichLociListener(JComboBox whichLociDropDown) {
		whichLociDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String lociType = whichLociDropDown.getSelectedItem().toString();
				System.out.println("WhichLociName listener triggered");

				updateLocusAndVersionDropdowns(lociType);

				prefs.putInt("GSG_NAME_LOCI", whichLociDropDown.getSelectedIndex());
				prefs.put("GSG_NAME_LOCI_STRING", lociType);
			}
		});
	}

	protected void updateLocusAndVersionDropdowns(String lociType) {
		
	}
}