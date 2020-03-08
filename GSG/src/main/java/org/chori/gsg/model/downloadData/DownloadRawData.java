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

	protected abstract void parseIncomingData(InputStream incomingData, String locus, String version);
}