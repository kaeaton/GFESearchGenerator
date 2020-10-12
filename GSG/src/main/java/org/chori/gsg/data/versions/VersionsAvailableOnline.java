package org.chori.gsg.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.prefs.Preferences;

// import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;

public class VersionsAvailableOnline {

	private Preferences prefs = Preferences.userNodeForPackage(GSG.class);

	// class instantiations
	private InternetAccess internet = new InternetAccess();
	private Neo4jVersionRequest neo4jVersionRequest = new Neo4jVersionRequest();
	private Neo4jHttpCall neo4jHttpCall = new Neo4jHttpCall();
	private IncomingJsonData incomingJsonData = new IncomingJsonData();

	public VersionsAvailableOnline() { }

	public void downloadCurrentVersionsInTheBackground() {

		new Thread(downloadCurrentVersions).start();
	}

	private Runnable downloadCurrentVersions = new Runnable() {
		String lociType = "HLA";
		
		public void run() {
			getCurrentVersionsByLoci(lociType);
		}
	};

	// private String getLociType(String whatTab) {
		
	// }

	public void getCurrentVersionsByLoci(String lociType) {
		ArrayList<String> downloadedVersions = new ArrayList<>();

		try {
			// create the request and send it
			InputStream incomingVersionData = neo4jHttpCall
				.makeCall(lociType, neo4jVersionRequest.formNeo4jVersionRequest(lociType));

			// recieve the version data and parse it
			downloadedVersions = incomingJsonData.parseVersion(incomingVersionData, lociType);

		} catch (Exception ex) { System.out.println("VersionsAvailableOnline: Downloading versions by loci failed: " + ex); }

		storeLociVersions(lociType, downloadedVersions);
	}

	private void storeLociVersions(String lociType, ArrayList<String> downloadedVersions) {
		
		String storedAvailableVersions = prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", "");

		if (!storedAvailableVersions.equals(downloadedVersions.toString())) {
			prefs.put("GSG_" + lociType + "_ONLINE_VERSIONS", downloadedVersions.toString());
		}	

		System.out.println("VersionsAvailableOnline: Versions in Prefs: " + prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", ""));
	}
}