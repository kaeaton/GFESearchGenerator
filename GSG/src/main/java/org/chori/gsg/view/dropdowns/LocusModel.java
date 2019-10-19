package org.chori.gsg.view.dropdowns;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

	

public class LocusModel {

	// class instantiations
	private WhereTheDataLives rawData = new WhereTheDataLives();

	public LocusModel() { }

	// public ArrayList<String> localDataFiles() {
	// 	ArrayList<String> versions = new ArrayList<>();
		
	// 	// find the BSGData folder
	// 	WhereTheDataLives wtdl = new WhereTheDataLives();
	// 	String rawDataPath = wtdl.getRawDataPath();

	// 	// read the BSGData folder
	// 	File[] directories = new File(rawDataPath).listFiles(File::isDirectory);
	// 	System.out.println(Arrays.toString(directories));
		
	// 	// get the folders for various versions
	// 	int pathLength = rawDataPath.length();
	// 	System.out.println("Path length: " + pathLength);

	// 	int dirLength;
	// 	String dir;
	// 	String versionNumber;
		
	// 	for (File directory:directories) {
	// 		// get directory length
	// 		dirLength = directory.toString().length();
	// 		dir = directory.toString();
			
	// 		// get version number off the end
	// 		versionNumber = dir.substring(pathLength, dirLength);
	// 		System.out.println("subpath: " + versionNumber);
			
	// 		// add to list if not KIR ("2.7.0")
	// 		if(!versionNumber.equals("2.7.0")) {
	// 			versions.add(versionNumber);
	// 		}
	// 	}

	// 	// sort the versions
	// 	Collections.sort(versions, Collections.reverseOrder());

	// 	return versions;
	// }

	public void fileLength(String whatLocus, String whatVersion) {
		File data = rawData.getRawData(whatLocus, whatVersion);
		long fileLength = data.length();
		System.out.println("File length of " + whatVersion 
			+ ", " + whatLocus + ": " + fileLength);
	}
}