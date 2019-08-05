package org.chori.bsg.model;

import java.io.File;
import java.nio.file.*;
import java.util.prefs.Preferences;

import org.chori.bsg.view.B12xGui;


public class WhereTheDataLives {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	private final String defaultBasePath = (System.getProperty("user.home") 
                + System.getProperty("file.separator") + "Documents"
                + System.getProperty("file.separator") + "BSG");

	private final String defaultRawDataPath = (defaultBasePath + System.getProperty("file.separator")
				+ "BSGData" + System.getProperty("file.separator"));

	private String basePath = prefs.get("BSG_RESULTS_DATA", defaultBasePath);
	private String rawDataPath = prefs.get("BSG_RAW_DATA", defaultRawDataPath);


	public WhereTheDataLives() { }

	public void setRawDataPath(String rawDataFileFolder) {
		prefs.put("BSG_RAW_DATA", rawDataFileFolder);
	}

	public void setResultsDataPath(String resultsFileFolder) {
		prefs.put("BSG_RESULTS_DATA", resultsFileFolder);
	}

	public void storeRawData(String locus, String version, String fileType) {
		File filePath = new File(rawDataPath);
	} 

	public File getRawData(String locus, String version, String fileType) {
		
		File filePath = new File(rawDataPath);
		return filePath;
	}

	public void storeResultsData() {

	}


}