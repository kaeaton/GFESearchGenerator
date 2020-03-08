package org.chori.gsg.model.downloadData;

import java.io.InputStream;

public class DownloadRawKirData extends DownloadRawData {
	public DownloadRawKirData() { }

	public void getRawLocusData(String locus, String version) {
		try{
			// create the request and send it
			InputStream incomingData = neo4jHttpCall.makeCall("KIR", 
				neo4jGfeDataRequest.createNeo4jGfeDataRequest("KIR", "KIR", version));
			System.out.println("DownloadRawData: received Input Stream");
			
			parseIncomingData(incomingData, "KIR", version);

		} catch (Exception ex) { System.out.println("DownloadRawData: Downloading locus data failed: " + ex); }
	}

	protected void parseIncomingData(InputStream incomingData, String locus, String version) {
		try {
			incomingJsonData.parseNeo4jResponse("KIR", version, incomingData);
		} catch (Exception ex) { System.out.println("DownloadRawKirData: Parsing incoming kir data failed: " + ex); }
	}
}