package org.chori.gsg.model.downloadData;

import java.io.InputStream;

import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;

public class DownloadRawData {

	private Neo4jHttpCall neo4jHttpCall = new Neo4jHttpCall();
	private IncomingJsonData incomingJsonData = new IncomingJsonData();
	private Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();

	public DownloadRawData() { }

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