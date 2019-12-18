package org.chori.gsg.view.dropdowns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.*;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLoci { 

	private final String[] loci = {"HLA", "KIR"};
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	public WhichLoci() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR)
	 * JComboBox (drop down menu) and associates the appropriate listener
	 * 
	 * @param whichTab is passed to the ActionListener so it changes the correct prefs.
	 * @return a JComboBox with an associated listener
	 */
	public JComboBox createWhichLociComboBox(String whichTab) {
		System.out.println("Generating the which loci combo box");
		
		// instantiate combobox and its model
		JComboBox whichLoci = new JComboBox();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(loci);
		// comboBoxModel = new DefaultComboBoxModel(fullHlaLoci);
		whichLoci.setModel(comboBoxModel);
		// JPanel newlayout = new JPanel();

		// who is this combobox for?
		// switch(whichTab) {
		// 	case "GFE":
				try {
					// try using prefs
					whichLoci.setSelectedIndex(prefs.getInt("GSG_" + whichTab + "_LOCI", 1));
				} catch (Exception ex) { 
					// if the pref exceeds the length of the model list, reset prefs
					PrefProbException ppex = new PrefProbException();
					System.out.println("GFE whichLoci set selected index: " + ex);
				}
				whichLociListener(whichLoci, whichTab);
			// 	break;
			// case "NAME":
				// try {
				// 	whichLoci.setSelectedIndex(prefs.getInt("GSG_NAME_LOCI", 1));
				// } catch (Exception ex) {
				// 	// if the pref exceeds the length of the model list, reset prefs 
				// 	PrefProbException ppex = new PrefProbException();
				// 	System.out.println("Name whichLoci set selected index: " + ex);
				// }
				// nameListener(whichLoci);
		// 		break;
		// 	default:
		// 		System.out.println("WhichLoci: Haven't set up that combobox model yet");
		// }

		// whatLocus.setModel(comboBoxModel);

		return whichLoci;
	}

	private void whichLociListener(JComboBox whichLoci, String whichTab) {
		whichLoci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String whichLociName = whichLoci.getSelectedItem().toString();
				System.out.println("Which " + whichTab + " Loci listener triggered");
				prefs.putInt("GSG_" + whichTab + "_LOCI", whichLoci.getSelectedIndex());
				prefs.put("GSG_" + whichTab + "_LOCI_STRING", whichLociName);
			}
		});
	}
}