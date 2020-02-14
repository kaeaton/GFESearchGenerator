package org.chori.gsg.view.dropdownMenus;

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
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;

	

public class LocusModel {

	// class instantiations
	// private WhereTheDataLives wtdl = new WhereTheDataLives();
	private final String[] fullHlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	private final String[] fullKirLoci = {"KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1"};

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private InternetAccess internet = new InternetAccess();
	private DataAvailableLocally dataAvailableLocally = new DataAvailableLocally();

	public LocusModel() { }

	public DefaultComboBoxModel loci(String version) {
		ArrayList<String> availableLoci = new ArrayList<>();

		// what versions are available online?
		if(internet.tester() && onlineVersion(version)) {
			ArrayList<String> allLoci = new ArrayList<>(Arrays.asList(fullHlaLoci));
			availableLoci = allLoci;
			System.out.println("Reached online version for loci in LocusModel");
		} else {
			// figure out what datafiles are available for selected version
			availableLoci = dataAvailableLocally.getLocalDataFiles(version);
		}

		DefaultComboBoxModel model = new DefaultComboBoxModel();

		// return a model available for them
		System.out.println("LocusModel.loci(): populating new DefaultComboBoxModel with: " + availableLoci.toString());
		System.out.println("LocusModel.loci(): populating new DefaultComboBoxModel with: " + availableLoci.toArray().toString());

		model = new DefaultComboBoxModel(availableLoci.toArray());
		return model;
	}

	private Boolean onlineVersion(String version) {

		String onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);
		String [] parsedOnlineVersions = new String[3];

		if(onlineVersions != null)
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