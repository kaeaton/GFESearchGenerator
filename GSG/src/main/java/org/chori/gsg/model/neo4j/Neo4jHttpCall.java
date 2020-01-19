package org.chori.gsg.model.neo4j;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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


    public Neo4jHttpCall() { }
    
    /**
     * Opens the call to the Neo4j database housing the GFEs
     * 
     * @param versionType tells the program which url to use to connect to the appropriate database
     * @param request The json string submitted to the database to request data
     * @throws IOException allows it to report exceptions in the call
     * @return a data InputStream containing the response from the Neo4j database
     */
    public InputStream makeCall(String versionType, String request) throws IOException {
        // final URL neo4jHlaURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");
		// final URL neo4jKirURL = new URL("http://neo4j-kir.b12x.org/db/data/transaction/commit");
			  
		// which URL do we use?
		// URL neo4jURL = versionType.equals("KIR") ? neo4jKirURL : neo4jHlaURL; 
		if(internetAccess.tester()) {
    		try {

                final URL neo4jHlaURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");

                // Open connection
    			HttpURLConnection connection = (HttpURLConnection) neo4jHlaURL.openConnection();
              
                // Setup the connection
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("X-Stream", "true");
                connection.setRequestProperty("Authorization",***REMOVED***);
                
                // Send our request
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(request);
                wr.flush();
                
                // Did we get a response?
    			System.out.println("Connection: " + connection);
                int httpResult = connection.getResponseCode();
                System.out.println("http response code: " + httpResult);
                if (httpResult == HttpURLConnection.HTTP_OK) {
                    incomingData = connection.getInputStream();
                    System.out.println("got input string");
                }
            } catch (Exception ex) {
                B12xGui.resultsTextAreaGfe.append(ex.toString());
                System.out.println(ex);
            }
        } else {
            
        }
        return incomingData;
    }

    private HttpURLConnection createUrlAndOpenConnection() {
        try {

            URL url = new URL(neo4jHlaURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection = addConnectionHeaders(connection);

            return connection;
        } catch (Exception ex) {
            System.out.println("createUrlAndOpenConnection error: " + ex);
        }
        
        return null;
    }

    private HttpURLConnection addConnectionHeaders (HttpURLConnection connection) throws ProtocolException {
        try {
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Stream", "true");
            connection.setRequestProperty("Authorization",***REMOVED***);
        } catch (Exception ex) { System.out.println("Neo4jHttpCall: " + ex); }

        return connection;
    }
}