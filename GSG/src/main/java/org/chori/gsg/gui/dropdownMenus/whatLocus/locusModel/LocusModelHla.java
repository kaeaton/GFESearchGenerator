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

import org.chori.gsg.model.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;

	

public class LocusModelHla extends LocusModel {

	// class instantiations
	// private WhereTheDataLives wtdl = new WhereTheDataLives();
	private final String[] fullHlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};

	// private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	// private InternetAccess internet = new InternetAccess();
	// private VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();

	public LocusModelHla() { }

	public DefaultComboBoxModel assembleLocusModel(String version) {
		ArrayList<String> availableLoci = new ArrayList<>();
		// String lociType = GSG.whichLociBulk.getSelectedItem().toString();

		// what versions are available online?
		if(internet.tester() && onlineVersion(version)) {
			ArrayList<String> allLoci = new ArrayList<>(Arrays.asList(fullHlaLoci));
			availableLoci = allLoci;
			System.out.println("Reached online version for loci in LocusModel");
		} else {
			// figure out what datafiles are available for selected version
			availableLoci = versionsAvailableLocally.getLocalDataFiles(version, "HLA");
		}

		DefaultComboBoxModel model = new DefaultComboBoxModel();

		// return a model available for them
		System.out.println("LocusModel.loci(): populating new DefaultComboBoxModel with: " + availableLoci.toString());
		System.out.println("LocusModel.loci(): populating new DefaultComboBoxModel with: " + availableLoci.toArray().toString());

		model = new DefaultComboBoxModel(availableLoci.toArray());
		return model;
	}

	protected Boolean onlineVersion(String version) {

		String onlineVersions = prefs.get("GSG_HLA_VERSIONS", "");
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