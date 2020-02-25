package org.chori.gsg.model;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.swing.JComboBox;

// import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
// import org.chori.gsg.view.*;


/**
 * This class determines what versions are available locally 
 * and can return an ArrayList of the versions available.
 * First it rounds up any available data folders, and extracts
 * the version string from the path to each folder.
 * It then checks the contents of each folder.
 * There must be at least one data file in the folder, and it 
 * must contain data beyond just a header for the version 
 * to be added to the returned ArrayList of versions. This is 
 * determined by checking the file length; a file with only 
 * headers is only 18 bytes long.
 * 
 * @author Katrina Eaton
 * 
 */
public class VersionsAvailableLocally {

	private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	// private InternetAccess internet = new InternetAccess();
	private FileUtilities fileUtilities = new FileUtilities();

	public VersionsAvailableLocally () { }

	/**
	 * Checks for local data from any supported loci. Used by the GUI at launch
	 * to determine if it should start. (If there's no local data and no internet
	 * GSG throws an error instead of starting.)
	 * 
	 * @return boolean: true if there is data available for any loci
	 */
	public Boolean isThereAnyLocalData() {
		ArrayList<String> loci = new ArrayList<String>() {
			{
				add("HLA");
				add("KIR");
				add("ABO"); 
            } 
        };

        for(String lociGroup: loci) {
        	ArrayList<String> versionsByLoci = getLocalVersionsByLoci(lociGroup);
        	if(!versionsByLoci.isEmpty())
        		return true;
        }

        return false;
	}

	/**
	 * Searches the local harddrive for raw GFE data for the 
	 * provided loci type. It gets a list of local directories,
	 * and checks the contents of each folder to make sure
	 * there is a viable data file inside before adding a version
	 * to the ArrayList.
	 * 
	 * @param lociType which group of loci are we checking for data?
	 * @return an ArrayList of locally available versions, will be empty if no local data
	 */
	public ArrayList<String> getLocalVersionsByLoci(String lociType) {
		ArrayList<String> localVersions = new ArrayList<>();

		String rawDataPath = whereTheDataLives.getRawDataPath() + lociType
							 + System.getProperty("file.separator");

		File[] localDirectories = gatherLocalDirectories(rawDataPath);

		localVersions = getVersionsFromDirectories(localDirectories, rawDataPath, lociType);
		
		sortTheVersionsList(localVersions);
		return localVersions;
	}

	private File[] gatherLocalDirectories(String rawDataPathByLoci) {

		// read the GSGData/loci folder
		File[] directories = new File(rawDataPathByLoci).listFiles(File::isDirectory);

		return directories;
	}

	private ArrayList<String> getVersionsFromDirectories(File[] directories, String rawDataPathByLoci, 
														 String lociType) {
		int pathLength = rawDataPathByLoci.length();
		ArrayList<String> versions = new ArrayList<>();

		for (File directory:directories) {
			String folderVersion = getFolderVersion(directory, pathLength, lociType);
			if (!folderVersion.equals("")) {
				versions.add(folderVersion);
			}
		}

		return versions;
	}

	private String getFolderVersion(File directory, int pathLength, String lociType) {
		try {
			// get the directory length
			int dirLength = directory.toString().length();
			String dir = directory.toString();

			// get version number off end
			String version = dir.substring(pathLength, dirLength);

			//gather files within the specific version folder, check to see if they have data
			ArrayList<String> localDataFiles = getLocalDataFiles(version, lociType);

			// if at least one file has data, return the version number
			if(!localDataFiles.isEmpty()) {
				return version;
			}
		} catch(Exception ex) { System.out.println("LocalData.getFolderVersion(): " + ex + " (Probably no folder found)"); }

		return "";
	}

	private ArrayList<String> sortTheVersionsList(ArrayList<String> versions) {
		
		Collections.sort(versions, Collections.reverseOrder());
		return versions;
	}

	/* what versions do we have? */
	public ArrayList<String> getLocalVersionData() {
		ArrayList<String> versions = new ArrayList<>();
		// find the GSGData folder
		String rawDataPath = whereTheDataLives.getRawDataPath();

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
				ArrayList<String> localDataFiles = getLocalDataFiles(versionNumber, "HLA");
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
	public ArrayList<String> getLocalDataFiles(String version, String lociType) {
		ArrayList<String> availableLoci = new ArrayList<>();
		
		// find the BSGData folder
		String rawDataPath = whereTheDataLives.getRawDataPath()+ lociType
							 + System.getProperty("file.separator");
		rawDataPath = rawDataPath + version;
		System.out.println("Locus Model file path: " + rawDataPath);

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
			if (fileSuffix.compareTo("csv") == 0 && fileUtilities.isTheFileLongEnough(aFile)) {
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
