package org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel;

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
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;


public class VersionModelGfe extends VersionModel {

	public VersionModelGfe() { }

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
		onlineVersions = prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", "");
		System.out.println("Version model: online versions array from prefs: " + onlineVersions);

		// if online versions is empty, and you have internet
		// download the current versions
		if(onlineVersions.equals("") && internet.tester()) {
			versionsAvailableOnline.getCurrentVersionsByLoci(lociType);
			onlineVersions = prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", null);
		}

		// if you have stored versions, parse them
		// it's an array read as a string, so remove the brackets
		// split on the commas, add to the version set.
		versionSet = parseStoredArray(localVersions, versionSet);
		versionSet = parseStoredArray(onlineVersions, versionSet);
		
		model = new DefaultComboBoxModel(versionSet.toArray());
		return model;
	}

	protected Set<String> parseStoredArray(String prefVersions, Set<String> versionSet) {
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
}