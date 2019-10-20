package org.chori.gsg.view.dropdowns;

// import com.fasterxml.jackson.core.JsonFactory;
// import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
// import java.io.IOException;
import java.io.InputStream;
// import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
// import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

public class VersionModel {

	public VersionModel() {	}

	/* what versions do we have? */
	public ArrayList<String> localVersionData() {
		ArrayList<String> versions = new ArrayList<>();
		
		// find the BSGData folder
		WhereTheDataLives wtdl = new WhereTheDataLives();
		String rawDataPath = wtdl.getRawDataPath();

		// read the BSGData folder
		File[] directories = new File(rawDataPath).listFiles(File::isDirectory);
		System.out.println(Arrays.toString(directories));
		
		// get the folders for various versions
		int pathLength = rawDataPath.length();
		System.out.println("Path length: " + pathLength);

		int dirLength;
		String dir;
		String versionNumber;
		
		for (File directory:directories) {
			// get directory length
			dirLength = directory.toString().length();
			dir = directory.toString();
			
			// get version number off the end
			versionNumber = dir.substring(pathLength, dirLength);
			System.out.println("subpath: " + versionNumber);
			
			// add to list if not KIR ("2.7.0")
			if(!versionNumber.equals("2.7.0")) {
				versions.add(versionNumber);
			}
		}

		// sort the versions
		Collections.sort(versions, Collections.reverseOrder());

		return versions;
	}

	// public static String[] getVersionData(String versionType) throws IOException {
	// 	Path versionPath = Paths.get(Neo4j.dataFilesPath 
	// 								+ "neo4j_" + versionType + "_version.txt");
	// 	File file = versionPath.toFile();
	// 	if (file.exists()){
	// 		BufferedReader br = new BufferedReader(new FileReader(file));
			
	// 		// date stamp
	// 		String line = br.readLine();
			
	// 		// versions
	// 		line = br.readLine();
			
	// 		String[] versions = line.split(",");    
	// 		System.out.println("Versions array: " + Arrays.toString(versions));
	// 		br.close();
	// 		return versions;
	// 	} else {
	// 		return null;
	// 	}
	// }

	// public static List<String> downloadVersionData(String versionType) throws IOException {
	// 	List<String> versions;
	// 	// set up the call
	// 	Neo4jHttp neo4jHttp = new Neo4jHttp();

	// 	// set up for parsing the incoming data
	// 	Neo4jIncomingData parser = new Neo4jIncomingData();

	// 	// create the request and send it
	// 	JsonFactory factory = Neo4j.factory;
		
	// 	// incoming data
	// 	InputStream incomingVersionData;
		
	// 	Neo4jVersionRequest whatVersion = new Neo4jVersionRequest();


	// 	incomingVersionData = neo4jHttp
	// 		.makeCall(versionType, whatVersion.formNeo4jVersionRequest(versionType, factory));

	// 	// recieve the version data and parse it
	// 	versions = parser.parseVersion(incomingVersionData, factory, versionType);

	// 	return versions;
	// }
	
	public DefaultComboBoxModel versions() { //String versionType) {

		ArrayList<String> versions = localVersionData();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		// String[] versionData = getVersionData(versionType);
		
	// 	// is there a data file to read from?
	// 	if (versionData != null) 
	// 	{
			model = new DefaultComboBoxModel(versions.toArray());
	// 	}
		
	// 	// no? create one
	// 	else 
	// 	{
			
	// 		System.out.println("Realized there's no version data file.");
			
	// 		versions = downloadVersionData(versionType);
	// 		model = new DefaultComboBoxModel(versions.toArray());

	// 	}
		
		return model;
	}
	
	// public static DefaultComboBoxModel comparisonVersions(String versionType) {
	// 	ArrayList<String> versions;
	// 	DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		// String[] versionData = getVersionData(versionType);
		
		/* what versions do we have? */
		// read the BSGData folder
		// File[] directories = new File(Neo4j.dataFilesPath).listFiles(File::isDirectory);
		// System.out.println(Arrays.toString(directories));
		
		// // get the folders for various versions
		// int pathLength = Neo4j.dataFilesPath.length();
		// System.out.println("Path length: " + pathLength);
		
		// int dirLength;
		// String dir;
		// String versionNumber;
		// List<String> downloadedVersions = new ArrayList();
		
		// for (File directory:directories) {
		// 	// get directory length
		// 	dirLength = directory.toString().length();
		// 	dir = directory.toString();
			
		// 	// get version number off the end
		// 	versionNumber = dir.substring(pathLength, dirLength);
		// 	System.out.println("subpath: " + versionNumber);
			
		// 	// add to list if not KIR ("2.7.0")
		// 	if(!versionNumber.equals("2.7.0")) {
		// 		downloadedVersions.add(versionNumber);
		// 	}
		// }
		
		// add available versions in case none have been downloaded before
		// if the file already exists
		// if (versionData != null) {
		// 	downloadedVersions.addAll(Arrays.asList(versionData));
			
		// if the file does not already exist
		// } else {
		// 	System.out.println("Realized there's no version data file.");
		// 	versions = downloadVersionData(versionType);
		// 	downloadedVersions.addAll(versions);
		// }
		
		// eliminate duplicates and convert to array
	// 	List<String> finalVersionList = downloadedVersions.stream() 
	// 								  .distinct()
	// 								  .collect(Collectors.toList());
	// 	String[] finalVersionArray = finalVersionList.toArray(new String[finalVersionList.size()]);
		
	// 	model = new DefaultComboBoxModel(finalVersionArray);

	// 	return model;
	// }
	
	// what versions are available to download? Passed on to comparison
	// public List<String> availableVersions(String versionType) {
	// 	List<String> versions = new ArrayList();
		// String[] versionData = getVersionData(versionType);
		
		// is there a data file to read from?
		// if (versionData != null) 
		// {
		// 	versions.addAll(Arrays.asList(versionData));
		// }
		
		// no? create one
		// else 
		// {
		// 	System.out.println("Realized there's no version data file.");
		// 	versions = downloadVersionData(versionType);
		// }
		
		// return versions;
	// }
}