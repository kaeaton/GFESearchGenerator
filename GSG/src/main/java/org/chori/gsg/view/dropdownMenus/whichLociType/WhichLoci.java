package org.chori.gsg.view.dropdownMenus.whichLociType;

import java.util.prefs.Preferences;
import javax.swing.JComboBox;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.versionModel.*;

/**
 * Creates the dropdowns to select the set of genes being used, and listeners for the dropdowns
 * 
 * @author Katrina Eaton
 * 
 */

public abstract class WhichLoci { 

	protected final String[] loci = {"HLA", "KIR"};
	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	protected VersionModelFactory versionModelFactory = VersionModelFactory.getVersionModelFactoryInstance();

	public WhichLoci() { }

	/**
	 * Generates the whichLoci (currently HLA and KIR) JComboBox (drop down menu)
	 * 
	 * @return a JComboBox with an associated listener
	 */
	public abstract JComboBox createWhichLociComboBox();

	protected abstract void addWhichLociListener(JComboBox whichLociDropDown);
	protected abstract void updateLocusAndVersions();
}