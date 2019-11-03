package org.chori.gsg.model.neo4j;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

public class Neo4jHttpCall {
    
    private InputStream incomingData;

    public Neo4jHttpCall() { }
       
    public InputStream makeCall(String versionType, String params) throws IOException {
        final URL neo4jHlaURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");
		// final URL neo4jKirURL = new URL("http://neo4j-kir.b12x.org/db/data/transaction/commit");
			  
		// which URL do we use?
		// URL neo4jURL = versionType.equals("KIR") ? neo4jKirURL : neo4jHlaURL; 
		
		try {
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
            wr.write(params);
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
            B12xGui.resultsTextAreaHla.append(ex.toString());
            System.out.println(ex);
        }
        return incomingData;
    }
}