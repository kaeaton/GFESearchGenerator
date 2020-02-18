package org.chori.gsg.model.utilities;

import java.io.File;
import java.nio.file.*;
import java.util.prefs.Preferences;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.exceptions.*;

public class WhereTheDataLives {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private InternetAccess internet = new InternetAccess();
	private DataAvailableOnline dataAvailableOnline = new DataAvailableOnline();
	// private FileUtilities fileUtilities = new FileUtilities();

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

	// create path to a file to store raw data
	public String createRawDataFilePath(String lociType, String locus, String version) {

		// where the file is going to live and
		// what the file is going to be called
		String path = "";

		switch(lociType) {
			case "HLA":
				path = hlaPath(locus, version);
				break;
			case "KIR":
				path = kirPath(version);
				break;
			default:
				System.out.println("WTDL: createRawDataPath: Haven't set up that path yet");
		}
		

		createPath(path);

	    // return the string of the now existing path
	    // (the IncomingJsonData class will make the actual file
	    //  out of the provided string)
		return path;
	} 

	private String hlaPath(String locus, String version) {

		// sample file name/path: /Documents/GSG/GSGData/3.35.0/neo4j_HLA-A_3.35.0_Download.csv
		String path = (getRawDataPath() 
				+ version + System.getProperty("file.separator")
				+ "neo4j_" + locus + "_" + version
				+ "_Download.csv");

		return path;
	}

	private String kirPath(String version) {

		// sample file name/path: /Documents/GSG/GSGData/2.7.0/neo4j_KIR_2.7.0_Download.csv
		String path = (getRawDataPath() 
				+ version + System.getProperty("file.separator")
				+ "neo4j_KIR_" + version + "_Download.csv");

		return path;
	}

	private void createPath(String pathString) {

		// turn it from string to a path
		Path path = Paths.get(pathString);

		// if the path doesn't exist, make it
		try {
			if (!path.toFile().exists())
            {
                System.out.println("The file does not exist.");
                path.toFile().getParentFile().mkdirs();
            }
	    } catch (Exception ex) { System.out.println("WTDL: Error creating file structure: " + ex); }
	}

	// create the file to store results
	public void storeResultsData(String lociType, String locus, String version) {
		try {
			if(!internet.tester()) {
				throw new NoInternetException();
			}

			dataAvailableOnline.getRawLocusData(lociType, locus, version);

		} catch(Exception ex) { System.out.println("WTDL.getRawData is having trouble getting the data: " + ex ); }
	}
}