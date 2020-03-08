package org.chori.gsg.view.dropdownMenus;

// import com.fasterxml.jackson.core.JsonFactory;
// import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
// import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
// import java.net.URL;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.prefs.Preferences;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.buttons.*;


public class VersionModel {

	private Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	// private	static HashMap<Integer, JTextArea> whichTextArea = new HashMap();

	// class instantiations
	private InternetAccess internet = new InternetAccess();
	private ResetPrefsButton resetPrefs = new ResetPrefsButton();
	private VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();
	private VersionsAvailableOnline versionsAvailableOnline = new VersionsAvailableOnline();

	public VersionModel() { }

	public DefaultComboBoxModel assembleVersionModel(String lociType) {
		String onlineVersions = "";
		String[] parsedOnlineVersions = new String[3];
		String[] parsedLocalVersions = new String[1];
		String localVersions = "";
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		Set<String> versionSet = new HashSet<String>();

		// localVersions = versionsAvailableLocally.getLocalVersionsByLoci(lociType);
		localVersions = prefs.get("GSG_" + lociType + "_LOCAL_VERSIONS", "");
		System.out.println("VersionModel.assembleVersionModel: avaliable version data: " + localVersions);
		onlineVersions = prefs.get("GSG_" + lociType + "_VERSIONS", "");
		System.out.println("Version model: online versions array from prefs: " + onlineVersions);

		// if online versions is empty, and you have internet
		// download the current versions
		if(onlineVersions.equals("") && internet.tester()) {
			versionsAvailableOnline.getCurrentVersionsByLoci(lociType);
			onlineVersions = prefs.get("GSG_" + lociType + "_VERSIONS", null);
		}

		// if you have stored versions, parse them
		// it's an array read as a string, so remove the brackets
		// split on the commas, add to the version set.
		versionSet = parseStoredArray(localVersions, versionSet);
		versionSet = parseStoredArray(onlineVersions, versionSet);
		
		model = new DefaultComboBoxModel(versionSet.toArray());
		return model;
	}

	private Set<String> parseStoredArray(String prefVersions, Set<String> versionSet) {
		String[] parsedVersions = new String[1];
		ArrayList<String> versionsList = new ArrayList<>();

		if(!prefVersions.equals("")) { 
			prefVersions = prefVersions.substring(1, prefVersions.length() - 1);
			System.out.println("Version model: stored versions array: " + prefVersions);
			parsedVersions = prefVersions.split(", ");
			versionsList = new ArrayList<String>(Arrays.asList(parsedVersions));
			versionSet.addAll(versionsList);
		}

		return versionSet;
	}



	public DefaultComboBoxModel bulkVersions() {

		DefaultComboBoxModel model = new DefaultComboBoxModel();
		String onlineVersions = "";
		String[] parsedOnlineVersions = new String[3];

		// if you have internet download the current versions
		if(internet.tester()) {
			versionsAvailableOnline.getCurrentVersionsByLoci("HLA");
			onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);
		} else if(onlineVersions != null) {
			onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);
		}

		// if you have online versions, parse them
		// it's an array read as a string, so remove the brackets
		// and split on the commas
		if(onlineVersions != null && internet.tester()) {
			onlineVersions = onlineVersions.substring(1, onlineVersions.length() - 1);
			System.out.println("Version model: bulkVersions: online versions array: " + onlineVersions);
			parsedOnlineVersions = onlineVersions.split(", ");
		}

		model = new DefaultComboBoxModel(parsedOnlineVersions);
		return model;
	}



}