package org.chori.gsg.model.downloadData;

import java.io.InputStream;

public class DownloadRawHlaData extends DownloadRawData {
	public DownloadRawHlaData() { }

	public void getRawLocusData(String locus, String version) {
		try{
			// create the request and send it
			InputStream incomingData = neo4jHttpCall.makeCall("HLA", 
				neo4jGfeDataRequest.createNeo4jGfeDataRequest("HLA", locus, version));
			System.out.println("DownloadRawData: received Input Stream");
			
			parseIncomingData(incomingData, locus, version);

		} catch (Exception ex) { System.out.println("DownloadRawData: Downloading locus data failed: " + ex); }
	}

	protected void parseIncomingData(InputStream incomingData, String locus, String version) {
		try{
			incomingJsonData.parseNeo4jResponse(locus, version, incomingData);
		} catch (Exception ex) { System.out.println("DownloadRawHlaData: Parsing incoming " + locus + " data failed: " + ex); }
	}
}