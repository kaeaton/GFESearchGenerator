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
	private Neo4jHttpCall httpCall = new Neo4jHttpCall();
	private IncomingJsonData parser = new IncomingJsonData();

	public CurrentReleaseData() { }

	public void getCurrentVersions(String type) {
		try {
			// to determine the most recent version:
			// create the request and send it
			Neo4jVersionRequest whatVersion = new Neo4jVersionRequest();
			InputStream incomingVersionData = httpCall
				.makeCall(type, whatVersion.formNeo4jVersionRequest(type));

			// recieve the version data and parse it
			versions = parser.parseVersion(incomingVersionData, type);
			System.out.println(versions.toString());

			System.out.println("Versions in Prefs: " + prefs.get("GSG_HLA_VERSIONS", ""));

			// save current versions to preferences
			String storedAvailableVersions = prefs.get("GSG_HLA_VERSIONS", "");
			if (!storedAvailableVersions.equals(versions.toString())) {
				prefs.put("GSG_HLA_VERSIONS", versions.toString());
			}	

			System.out.println("Versions in Prefs: " + prefs.get("GSG_HLA_VERSIONS", ""));

		} catch (Exception ex) { System.out.println("Downloading versions failed: " + ex); }
	}

	public void getRawLocusData(String type, String locus, String version) {
		try{
			// retrieve the data
	        // create the request and send it
	        Neo4jDataRequest neo4jDataRequest = new Neo4jDataRequest();
	        InputStream incomingData = httpCall.makeCall(type, 
	        	neo4jDataRequest.createNeo4jDataRequest(locus, version));
	        
	        // recieve data and parse it
			switch (type) {
				case "GFE":
					parser.parseNeo4jResponse(locus, version, incomingData);
					break;
				default:
					System.out.println(type + " hasn't been added yet.");
			}
		} catch (Exception ex) { System.out.println("Downloading locus data failed: " + ex); }
	}
}