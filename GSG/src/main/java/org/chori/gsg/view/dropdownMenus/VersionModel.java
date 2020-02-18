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

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	// private	static HashMap<Integer, JTextArea> whichTextArea = new HashMap();

	// class instantiations
	private DataAvailableLocally dataAvailableLocally = new DataAvailableLocally();
	private InternetAccess internet = new InternetAccess();
	private ResetPrefsButton resetPrefs = new ResetPrefsButton();
	private DataAvailableOnline dataAvailableOnline = new DataAvailableOnline();

	public VersionModel() { }

	public DefaultComboBoxModel assembleVersionModel(String whichLoci) {
		String onlineVersions = null;
		String[] parsedOnlineVersions = new String[3];
		ArrayList<String> localVersions = new ArrayList<>();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		Set<String> versionSet = new HashSet<String>();

		localVersions = dataAvailableLocally.getLocalVersionsByLoci(whichLoci);
		onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);
		System.out.println("Version model: online versions array from prefs: " + onlineVersions);

		// if online versions equals null, and you have internet
		// download the current versions
		if(onlineVersions == null && internet.tester()) {
			dataAvailableOnline.getCurrentVersionsByLoci("HLA");
			onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);
		}

		// if you have online versions, parse them
		// it's an array read as a string, so remove the brackets
		// split on the commas, add to an ArrayList.
		if(onlineVersions != null) { //&& internet.tester()) {
			onlineVersions = onlineVersions.substring(1, onlineVersions.length() - 1);
			System.out.println("Version model: online versions array: " + onlineVersions);
			parsedOnlineVersions = onlineVersions.split(", ");
			ArrayList<String> onlineVersionsList = new ArrayList<String>(Arrays.asList(parsedOnlineVersions));
			versionSet.addAll(onlineVersionsList);
		}
		
		if (localVersions != null)
			versionSet.addAll(localVersions);

		// if(onlineVersions == null)


		
		// versionSet.addAll(onlineVersions);

		model = new DefaultComboBoxModel(versionSet.toArray());
		return model;
	}



	public DefaultComboBoxModel bulkVersions() {

		DefaultComboBoxModel model = new DefaultComboBoxModel();
		String onlineVersions = "";
		String[] parsedOnlineVersions = new String[3];

		onlineVersions = prefs.get("GSG_HLA_VERSIONS", null);

		// if online versions equals null, and you have internet
		// download the current versions
		if(onlineVersions == null && internet.tester()) {
			dataAvailableOnline.getCurrentVersionsByLoci("HLA");
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