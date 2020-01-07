package org.chori.gsg.model;

import java.io.File;
import java.nio.file.*;

/**
 * Utilities for processing files
 * 
 * @author Katrina Eaton
 * 
 */

public class FileUtilities {

	private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();

	public FileUtilities() { }


	/**
	 * Returns the csv file of raw data.
	 * First it checks to see if the data has already been downloaded 
	 * (scans for file length to make sure it's not just a header)
	 * If the file does not exist or contains no data, this downloads it.
	 * 
	 * @param locus a string telling which locus for HLA, or just "KIR" for KIR data
	 * @param version a string listing the version.
	 * @return a csv file of raw data.
	 */
	public File getTheRawDataFile(String locus, String version) {
		String specificFile = whereTheDataLives.getRawDataPath() + version + System.getProperty("file.separator") 
								+ "neo4j_" + locus + "_" + version + "_download.csv";

		File theFile = new File(specificFile);
		
		if(theFile.exists() && isTheFileLongEnough(theFile)) {
			System.out.println("Found the raw data file");
			return theFile;
		} else {
			whereTheDataLives.storeResultsData("GFE", locus, version);
			return theFile;
		}
	}

	/**
	 * Sometimes data files end up with only a header. It's typically 18 bytes long.
	 * Just in case there's any variation, the file length cutoff here is 100 bytes
	 * (The shortest datafile is still over 1 kb)
	 * 
	 * @param file a csv file. (Not just the path to the file. The actual file is passed in.)
	 * @return a boolean, true if the file is greater than 100 bytes long, false if not.
	 */
	public boolean isTheFileLongEnough(File file) {
		if (file.exists()) {
			System.out.println("File Utilities: isTheFileLongEnough(): The file exists, we're going to check the length: " 
								+ file.toString());

			if(file.length() > 100)
				return true;
		}

		return false;
	}

	// create the file to store results
	// public void storeResultsData(String type, String locus, String version) {
	// 	try {
	// 		if(!internet.tester()) {
	// 			throw new NoInternetException();
	// 		}

	// 		crd.getRawLocusData(type, locus, version);

	// 	} catch(Exception ex) { System.out.println("WTDL.getRawData is having trouble getting the data: " + ex ); }


	// }
}