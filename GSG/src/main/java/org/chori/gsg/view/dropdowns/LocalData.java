package org.chori.gsg.view.dropdowns;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.swing.JComboBox;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

public class LocalData {

	private WhereTheDataLives wtdl = new WhereTheDataLives();
	private InternetAccess internet = new InternetAccess();

	public LocalData () { }

	/* what versions do we have? */
	public ArrayList<String> localVersionData() {
		ArrayList<String> versions = new ArrayList<>();
		
		// find the GSGData folder
		String rawDataPath = wtdl.getRawDataPath();

		// read the GSGData folder
		File[] directories = new File(rawDataPath).listFiles(File::isDirectory);
		System.out.println("What folders are we looking at? " + Arrays.toString(directories));
		
		// get the folders for various versions
		int pathLength = rawDataPath.length();
		// System.out.println("Path length: " + pathLength);

		int dirLength;
		String dir;
		String versionNumber;
		
		try {
			for (File directory:directories) {
				// get directory length
				dirLength = directory.toString().length();
				dir = directory.toString();
				System.out.println("We're currently looking at folder: " + dir);
				
				// get version number off the end
				versionNumber = dir.substring(pathLength, dirLength);
				ArrayList<String> localDataFiles = getLocalDataFiles(versionNumber);
				System.out.println("subpath: " + versionNumber);
				
				// add to list if not KIR ("2.7.0")
				if(!versionNumber.equals("2.7.0") && localDataFiles != null) {
					versions.add(versionNumber);
				}
			}
		} catch(Exception ex) { System.out.println("LocalData: " + ex + " (Probably no folder found)"); }

		// sort the versions
		if(!versions.isEmpty()) {
			Collections.sort(versions, Collections.reverseOrder());

			return versions;
		}
		return null;
	}

	/* what data files are there? */
	public ArrayList<String> getLocalDataFiles(String version) {
		ArrayList<String> availableLoci = new ArrayList<>();
		
		// find the BSGData folder
		String rawDataPath = wtdl.getRawDataPath();
		rawDataPath = rawDataPath + version;
		// System.out.println("Locus Model file path: " + rawDataPath);

		// read the BSGData folder
		File[] files = new File(rawDataPath).listFiles();
		System.out.println("LocalData.getLocalDataFiles(): Printing out the files array: " + Arrays.toString(files));
		 
		// get the folders for various versions
		int pathLength = (rawDataPath.length() + 7);
		// System.out.println("Path length: " + pathLength);

		int filePathLength;
		String file;
		String protoLocus;
		String locus = "";

		// if you have no data
		if (files == null) {
			return null;
		}
		
		for (File aFile:files) {

			// get file path length
			filePathLength = aFile.toString().length();
			file = aFile.toString();
			
			// get locus out of file name
			protoLocus = file.substring(pathLength, filePathLength);
			// System.out.println("subpath: " + protoLocus);

			// get file suffix, if csv, and more than just a header, extract locus name
			String fileSuffix = protoLocus.substring(protoLocus.length() - 3);
			if (fileSuffix.compareTo("csv") == 0 && wtdl.fileLength(aFile)) {
				locus = protoLocus.substring(0, protoLocus.length() - 20);
				availableLoci.add(locus);
				// System.out.println("locus name: " + locus);
			}
		}

		// sort the loci
		Collections.sort(availableLoci);

		// System.out.println("LocalData.getLocalDataFiles: available loci: " + availableLoci.toString());

		if (!availableLoci.isEmpty())
			return availableLoci;

		return null;
	}



	
}
