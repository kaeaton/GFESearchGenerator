package org.chori.gsg.model.neo4j;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;

/**
 * Opens the connection for the Neo4j Database
 * 
 * @author Katrina Eaton
 * 
 */
public class Neo4jHttpCall {
	
	private InputStream incomingData;
	private InternetAccess internetAccess = new InternetAccess();

	final String neo4jHlaURL = "http://neo4j.b12x.org/db/data/transaction/commit";
	final String neo4jKirURL = "http://neo4j-kir.b12x.org/db/data/transaction/commit";

	public Neo4jHttpCall() { }
	
	/**
	 * Opens the call to the Neo4j database housing the GFEs
	 * 
	 * @param lociType tells the program which url to use to connect to the appropriate database
	 * @param request The json string submitted to the database to request data
	 * @throws IOException allows it to report exceptions in the call
	 * @return a data InputStream containing the response from the Neo4j database
	 */
	public InputStream makeCall(String lociType, String request) throws IOException {

		if(internetAccess.tester()) {

			URL neo4jURL = determineWhichURL(lociType);
				
			// Open connection
			HttpURLConnection connection = (HttpURLConnection) neo4jURL.openConnection();
			connection = addConnectionHeaders(connection);

			sendRequest(connection, request);
			incomingData = getResponse(connection);
		}

		return incomingData;
	}

	private URL determineWhichURL(String lociType) throws MalformedURLException{

		switch(lociType) {
			case "HLA":
				URL url = new URL(neo4jHlaURL);
				return url;

			case "KIR":
				url = new URL(neo4jKirURL);
				return url;

			default:
				System.out.println("Neo4jHttpCall: determineURL: Haven't set up that loci's URL yet");
		}
		
		// if all else fails
		URL url = new URL("");
		return url;
	}

	private HttpURLConnection addConnectionHeaders (HttpURLConnection connection) throws ProtocolException {
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("X-Stream", "true");
		connection.setRequestProperty("Authorization",***REMOVED***);

		return connection;
	}

	private void sendRequest(HttpURLConnection connection, String request) {
		try {

			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(request);
			wr.flush();

		} catch (Exception ex) { System.out.println("Neo4jHttpCall: sendRequest: " + ex); }
	}

	private InputStream getResponse(HttpURLConnection connection) {
		try {

			int httpResult = connection.getResponseCode();
			System.out.println("http response code: " + httpResult);

			if (httpResult == HttpURLConnection.HTTP_OK) {
				incomingData = connection.getInputStream();
			}
			
		} catch (Exception ex) { System.out.println("Neo4jHttpCall: getResponse: " + ex); }

		return incomingData;
	}
}