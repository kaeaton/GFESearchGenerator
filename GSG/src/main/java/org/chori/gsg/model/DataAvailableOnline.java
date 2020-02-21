package org.chori.gsg.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;
import org.chori.gsg.view.*;

public class DataAvailableOnline {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);

	// class instantiations
	private Neo4jVersionRequest neo4jVersionRequest = new Neo4jVersionRequest();
	private Neo4jHttpCall neo4jHttpCall = new Neo4jHttpCall();
	private IncomingJsonData incomingJsonData = new IncomingJsonData();
	private Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();

	public DataAvailableOnline() { }

	public void getCurrentVersionsByLoci(String lociType) {
		ArrayList<String> downloadedVersions = new ArrayList<>();

		try {
			// create the request and send it
			InputStream incomingVersionData = neo4jHttpCall
				.makeCall(lociType, neo4jVersionRequest.formNeo4jVersionRequest(lociType));

			// recieve the version data and parse it
			downloadedVersions = incomingJsonData.parseVersion(incomingVersionData, lociType);

		} catch (Exception ex) { System.out.println("DataAvailableOnline: Downloading versions by loci failed: " + ex); }

		System.out.println("DataAvailableOnline: Versions in Prefs: " + prefs.get("GSG_" + lociType + "_VERSIONS", ""));
		storeLociVersions(lociType, downloadedVersions);
	}

	private void storeLociVersions(String lociType, ArrayList<String> downloadedVersions) {
		
		String storedAvailableVersions = prefs.get("GSG_" + lociType + "_VERSIONS", "");

		if (!storedAvailableVersions.equals(downloadedVersions.toString())) {
			prefs.put("GSG_" + lociType + "_VERSIONS", downloadedVersions.toString());
		}	

		System.out.println("DataAvailableOnline: Versions in Prefs: " + prefs.get("GSG_" + lociType + "_VERSIONS", ""));
	}

	public void getRawLocusData(String lociType, String locus, String version) {
		try{
	        // create the request and send it
	        InputStream incomingData = neo4jHttpCall.makeCall(lociType, 
	        	neo4jGfeDataRequest.createNeo4jGfeDataRequest(lociType, locus, version));
	        System.out.println("DataAvailableOnline: received Input Stream");
	        
	        parseIncomingData(incomingData, lociType, locus, version);

		} catch (Exception ex) { System.out.println("DataAvailableOnline: Downloading locus data failed: " + ex); }
	}

	private void parseIncomingData(InputStream incomingData, String lociType, String locus, String version) {
		try{
			switch (lociType) {
				case "HLA":
					incomingJsonData.parseNeo4jResponse(locus, version, incomingData);
					break;
				case "KIR":
					incomingJsonData.parseNeo4jResponse("KIR", version, incomingData);
					break;
				default:
					System.out.println("Current release data: " + lociType + " hasn't been added yet.");
			}
		} catch (Exception ex) { System.out.println("DataAvailableOnline: Parsing incoming locus data failed: " + ex); }
	}
}