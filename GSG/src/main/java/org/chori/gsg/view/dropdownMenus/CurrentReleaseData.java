package org.chori.gsg.view.dropdownMenus;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;
import org.chori.gsg.view.*;

public class CurrentReleaseData {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	private ArrayList<String> versions = new ArrayList<>();

	// class instantiations
	private Neo4jVersionRequest neo4jVersionRequest = new Neo4jVersionRequest();
	private Neo4jHttpCall neo4jHttpCall = new Neo4jHttpCall();
	private IncomingJsonData incomingJsonData = new IncomingJsonData();

	public CurrentReleaseData() { }

	public void getCurrentVersionsByLoci(String lociType) {
		try {
			// to determine the most recent version:
			// create the request and send it
			InputStream incomingVersionData = neo4jHttpCall
				.makeCall(lociType, neo4jVersionRequest.formNeo4jVersionRequest(lociType));

			// recieve the version data and parse it
			versions = incomingJsonData.parseVersion(incomingVersionData, lociType);
			System.out.println(versions.toString());

			System.out.println("Versions in Prefs: " + prefs.get("GSG_" + lociType + "_VERSIONS", ""));

			// save current versions to preferences
			String storedAvailableVersions = prefs.get("GSG_" + lociType + "_VERSIONS", "");
			if (!storedAvailableVersions.equals(versions.toString())) {
				prefs.put("GSG_" + lociType + "_VERSIONS", versions.toString());
			}	

			System.out.println("Versions in Prefs: " + prefs.get("GSG_" + lociType + "_VERSIONS", ""));

		} catch (Exception ex) { System.out.println("Downloading versions failed: " + ex); }
	}

	public void getRawLocusData(String lociType, String locus, String version) {
		try{
			// retrieve the data
	        // create the request and send it
	        Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();
	        System.out.println("CurrentReleaseData: requesting Input Stream");
	        InputStream incomingData = neo4jHttpCall.makeCall(lociType, 
	        	neo4jGfeDataRequest.createNeo4jGfeDataRequest(lociType, locus, version));
	        System.out.println("CurrentReleaseData: received Input Stream");
	        
	        // recieve data and parse it
			switch (lociType) {
				case "HLA":
					incomingJsonData.parseNeo4jResponse(locus, version, incomingData);
					break;
				default:
					System.out.println("Current release data: " + lociType + " hasn't been added yet.");
			}
		} catch (Exception ex) { System.out.println("Current Release Data: Downloading locus data failed: " + ex); }
	}
}