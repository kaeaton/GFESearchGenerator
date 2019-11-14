package org.chori.gsg.model;

import java.io.File;
import java.nio.file.*;
import java.util.prefs.Preferences;

import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdowns.*;


public class WhereTheDataLives {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private InternetAccess internet = new InternetAccess();
	private CurrentReleaseData crd = new CurrentReleaseData();

	private final String defaultBasePath = (System.getProperty("user.home") 
				+ System.getProperty("file.separator") + "Documents"
				+ System.getProperty("file.separator") + "GSG");

	private final String defaultRawDataPath = (defaultBasePath + System.getProperty("file.separator")
				+ "GSGData" + System.getProperty("file.separator"));

	private String basePath = prefs.get("GSG_RESULTS_DATA", defaultBasePath);
	private String rawDataPath = prefs.get("GSG_RAW_DATA", defaultRawDataPath);


	public WhereTheDataLives() { }

	// will be set in options
	public void setRawDataPath(String rawDataFileFolder) {
		prefs.put("GSG_RAW_DATA", rawDataFileFolder);
	}

	public String getRawDataPath() {
		return prefs.get("GSG_RAW_DATA", defaultRawDataPath);
	}

	// will be set in options
	public void setResultsDataPath(String resultsFileFolder) {
		prefs.put("GSG_RESULTS_DATA", resultsFileFolder);
	}

	public String getResultsDataPath() {
		return prefs.get("GSG_RESULTS_DATA", defaultBasePath);
	}

	// create file to store raw data
	public String storeRawData(String type, String locus, String version) {

		// where the file is going to live and
		// what the file is going to be called
		String path = (rawDataPath 
				+ version + System.getProperty("file.separator")
				+ "neo4j_" + locus + "_" + version
				+ "_Download.csv");

		// turn it from string to a path
		Path rawDataPath = Paths.get(path);

		// if the path don't exist, make it
		try {
			if (!rawDataPath.toFile().exists())
            {
                System.out.println("The file does not exist.");
                rawDataPath.toFile().getParentFile().mkdirs();
                // path.toFile().createNewFile();
            }
	    } catch (Exception ex) { System.out.println("Error creating file structure: " + ex); }

	    // return the string of the now existing path
	    // (the IncomingJsonData class will make the actual file
	    //  out of the provided string)
		return path;
	} 

	// get the data file
	public File getRawData(String locus, String version) {
		String specificFile = rawDataPath + version + System.getProperty("file.separator") + "neo4j_" + locus + "_" + version
								+ "_download.csv";
		File file = new File(specificFile);
		
		if(file.exists() && fileLength(file)) {
			System.out.println("Found the raw data file");
			return file;
		} else {
			storeResultsData("HLA", locus, version);
			return file;
		}
		// System.out.println("Didn't find the raw data file.");

		// System.out.println("looking for the file at " + specificFile);

		// return null;
	}

	// create the file to store results
	public void storeResultsData(String type, String locus, String version) {
		try {
			if(!internet.tester()) {
				throw new NoInternetException();
			}

			crd.getRawLocusData(type, locus, version);

		} catch(Exception ex) { System.out.println("WTDL.getRawData is having trouble getting the data: " + ex ); }


	}

	// sometimes files have nothing but a header in them
	// makes sure the file is big enough to actually contain data
	public boolean fileLength(File file) {//String whatLocus, String whatVersion) {
		//
		// File data = getRawData(whatLocus, whatVersion);
		if (file.exists()) {
			System.out.println("wtdl.fileLength(): The file exists, we're going to check the length: " + file.toString());
			long fileLength = file.length();
			// System.out.println("File length of " + whatVersion 
				// + ", " + whatLocus + ": " + fileLength);
		
			// (a header is about 18 bytes. This is giving a bit of a cushion)
			if(file.length() > 100)
				return true;
		}

		return false;
	}

}