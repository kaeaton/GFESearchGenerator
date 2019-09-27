package org.chori.gsg.model;

import java.io.File;
import java.nio.file.*;
import java.util.prefs.Preferences;

import org.chori.gsg.view.B12xGui;


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

	// will be set in options
	public void setRawDataPath(String rawDataFileFolder) {
		prefs.put("BSG_RAW_DATA", rawDataFileFolder);
	}

	// will be set in options
	public void setResultsDataPath(String resultsFileFolder) {
		prefs.put("BSG_RESULTS_DATA", resultsFileFolder);
	}

	// create file to store raw data
	public File storeRawData(String locus, String version) {
		String file = rawDataPath + "GFE_" + locus + "_" + version
						+ "_download.csv";
		File dataFile = new File(rawDataPath);
		// if (!path.toFile().exists())
  //       {
  //           System.out.println("The file does not exist.");
  //           path.toFile().getParentFile().mkdirs();
  //           path.toFile().createNewFile();
  //           dataUpdate();
  //       }
        return dataFile;
	} 

	// get the data file
	public File getRawData(String locus, String version) {
		String specificFile = rawDataPath + "3.34.0" + System.getProperty("file.separator") + "neo4j_" + locus + "_" + version
								+ "_download.csv";
		File file = new File(specificFile);
		if(file.exists()) {
			System.out.println("Found the raw data file");
			return file;
		}
		System.out.println("Didn't find the raw data file.");
		System.out.println("looking for the file at " + specificFile);

		return null;
	}

	// create the file to store results
	public void storeResultsData() {

	}


}