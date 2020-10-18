package org.chori.gsg.gui.dropdownMenus.whatLocus.locusModel;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.data.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;

	

public class LocusModelKir extends LocusModel {

	// class instantiations
	// private WhereTheDataLives wtdl = new WhereTheDataLives();
	private final String[] fullKirLoci = {"KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1"};

	// private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	// private InternetAccess internet = new InternetAccess();
	// private VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();

	public LocusModelKir() { }

	public DefaultComboBoxModel assembleLocusModel(String version) {
		ArrayList<String> availableLoci = new ArrayList<>();
		// String lociType = GSG.whichLociBulk.getSelectedItem().toString();

		// what versions are available online?
		// if(internet.tester() && onlineVersion(version)) {
			ArrayList<String> allLoci = new ArrayList<>(Arrays.asList(fullKirLoci));
			availableLoci = allLoci;
			System.out.println("Reached online version for loci in LocusModel");
		// } else {
		// 	// figure out what datafiles are available for selected version
		// 	availableLoci = versionsAvailableLocally.getLocalDataFiles(version, "KIR");
		// }

		DefaultComboBoxModel model = new DefaultComboBoxModel();

		// return a model available for them
		System.out.println("LocusModelKir.loci(): populating new DefaultComboBoxModel with: " + Arrays.toString(availableLoci.toArray()));
		// System.out.println("LocusModel.loci(): populating new DefaultComboBoxModel with: " + Arrays.toString(availableLoci).toArray());

		model = new DefaultComboBoxModel(availableLoci.toArray());
		return model;
	}

	protected Boolean onlineVersion(String version) {

		String onlineVersions = prefs.get("GSG_KIR_VERSIONS", "");
		String [] parsedOnlineVersions = new String[3];

		if(!onlineVersions.equals(""))
			onlineVersions = onlineVersions.substring(1, onlineVersions.length() - 1);
			parsedOnlineVersions = onlineVersions.split(", ");

		// if the version matches a version available online
		try {
			for(int i = 0; i < parsedOnlineVersions.length; i++) {
				if (parsedOnlineVersions[i].compareTo(version) == 0) {
					return true;
				}
			}

		} catch (Exception ex) { return false; }

		return false;
	}
}