package org.chori.gsg.view.dropdownMenus.whichLociType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.locusModel.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.tabs.GfeTab;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLociGfe extends WhichLoci { 

	protected VersionModel versionModelGfe = VersionModelFactory.getVersionModelFactoryInstance().createVersionModel("GFE");
	protected LocusModelFactory locusModelFactory = LocusModelFactory.getLocusModelFactoryInstance();
	protected LocusModel locusModelHla = LocusModelFactory.getLocusModelFactoryInstance().createLocusModel("HLA");
	protected LocusModel locusModelKir = LocusModelFactory.getLocusModelFactoryInstance().createLocusModel("KIR");
	
	// private UpdateGfePanel updateGfePanel = new UpdateGfePanel();
	private DefaultComboBoxModel newVersionModel = new DefaultComboBoxModel();
	private DefaultComboBoxModel newLocusModel = new DefaultComboBoxModel();

	public WhichLociGfe() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu) 
	 * for the GFE tab.
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

				updateLocusAndVersionDropdowns(lociType);

				prefs.putInt("GSG_GFE_LOCI", whichLociDropDown.getSelectedIndex());
				prefs.put("GSG_GFE_LOCI_STRING", lociType);
			}
		});
	}

	protected void updateLocusAndVersionDropdowns(String lociType) {
		GfeTab assembleGfeTab = GfeTab.getGfeTabInstance();


		String whatLocus = "HLA-B";
		newVersionModel = versionModelGfe.assembleVersionModel(lociType);
		System.out.println("WhichLociGfe: updateLocusAndVersionDropdowns");
		assembleGfeTab.updateTheGfePanel(whatLocus);
		// GfeTab.whatVersionBulk.setModel(newVersionModel);
		// GSG.whatVersionBulk.setModel(newVersionModel);
		// newLocusModel = 
		// updateGfePanel.updateTheGfePanel("HLA-B");
	}
}