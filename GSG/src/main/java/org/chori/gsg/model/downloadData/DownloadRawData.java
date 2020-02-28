package org.chori.gsg.model.downloadData;

import java.io.InputStream;

import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;

public abstract class DownloadRawData {

	protected Neo4jHttpCall neo4jHttpCall = new Neo4jHttpCall();
	protected IncomingJsonData incomingJsonData = new IncomingJsonData();
	protected Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();

	public DownloadRawData() { }

	public abstract void getRawLocusData(String locus, String version);
	// public void getRawLocusData(String lociType, String locus, String version); //{
		// try{
	 //        // create the request and send it
	 //        InputStream incomingData = neo4jHttpCall.makeCall(lociType, 
	 //        	neo4jGfeDataRequest.createNeo4jGfeDataRequest(lociType, locus, version));
	 //        System.out.println("DownloadRawData: received Input Stream");
	        
	 //        parseIncomingData(incomingData, lociType, locus, version);

		// } catch (Exception ex) { System.out.println("DownloadRawData: Downloading locus data failed: " + ex); }
	// }

	protected abstract void parseIncomingData(InputStream incomingData, String locus, String versio);
	// private abstract void parseIncomingData(InputStream incomingData, String lociType, String locus, String version); // {
		// try{
		// 	switch (lociType) {
		// 		case "HLA":
		// 			incomingJsonData.parseNeo4jResponse(locus, version, incomingData);
		// 			break;
		// 		case "KIR":
		// 			incomingJsonData.parseNeo4jResponse("KIR", version, incomingData);
		// 			break;
		// 		default:
		// 			System.out.println("Current release data: " + lociType + " hasn't been added yet.");
		// 	}
		// } catch (Exception ex) { System.out.println("DownloadRawData: Parsing incoming locus data failed: " + ex); }
	// }
}