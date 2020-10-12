package org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel;

// import com.fasterxml.jackson.core.JsonFactory;
// import java.io.BufferedReader;
// import java.io.ByteArrayInputStream;
// import java.io.DataInputStream;
// import java.io.File;
// import java.io.FileReader;
// // import java.io.IOException;
// import java.io.InputStream;
// import java.io.ObjectInputStream;
// // import java.net.URL;
// import java.nio.charset.*;
// import java.nio.file.*;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.LinkedHashSet;
import java.util.prefs.Preferences;
// import java.util.Set;
// import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
// import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.GSG;


public abstract class VersionModel {

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);

	// class instantiations
	protected InternetAccess internet = new InternetAccess();
	protected VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();
	protected VersionsAvailableOnline versionsAvailableOnline = new VersionsAvailableOnline();

	public VersionModel() { }

	public abstract DefaultComboBoxModel assembleVersionModel(String lociType);

	public String[] parseArrayFromString(String arrayInStringForm) {
		String[] parsedArray;
		try {
			// remove the end brackets
			arrayInStringForm = arrayInStringForm.substring(1, arrayInStringForm.length() - 1);
			parsedArray = arrayInStringForm.split(", ");

		} catch(Exception ex) {

			// if it's not working, return that info
			// for feedback and to prevent crash
			parsedArray = new String[1];
			parsedArray[0] = "No versions available";

			System.out.println("Version model: parseArrayFromString: " + ex);
		}
		return parsedArray;
	}
}