package org.chori.bsg.model;

import java.io.File;
import java.nio.file.*;

public class WhereTheDataLives {

	private final String basePath = (System.getProperty("user.home") 
                + System.getProperty("file.separator") + "Documents"
                + System.getProperty("file.separator") + "BSG");

	private final String rawDataPathAddon = (basePath + System.getProperty("file.separator")
				+ "BSGData" + System.getProperty("file.separator"));

	public WhereTheDataLives() {

	}

	public void storeRawData(String locus, String version, String fileType) {
		String rawDataPath = (basePath + rawDataPathAddon + version
                                    + System.getProperty("file.separator") 
                                    + locus + "_" + version + "_rawData.csv");
		File filePath = new File(rawDataPath);
	} 

	public File getRawData(String locus, String version, String fileType) {
		String rawDataPath = (basePath + rawDataPathAddon + version
                                    + System.getProperty("file.separator") 
                                    + locus + "_" + version + "_rawData.csv");
		File filePath = new File(rawDataPath);
		return filePath;
	}

	public void storeResultsData() {

	}


}