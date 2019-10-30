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
            // connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Stream", "true");
            // connection.setRequestProperty("cache-control", "no-cache");
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

// package org.chori.gsg.model;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.InputStream;
// import java.io.IOException;
// import java.net.URI;
// import java.util.prefs.Preferences;

// import org.chori.gsg.view.B12xGui;
// // import org.apache.http.client.methods.HttpGet;
// // import org.apache.http.client.methods.CloseableHttpResponse;
// // import org.apache.http.client.utils.URIBuilder;
// // import org.apache.http.HttpEntity;
// // import org.apache.http.impl.client.CloseableHttpClient;
// // import org.apache.http.impl.client.HttpClients;

// public class Neo4jHttpCall {

// 	private WhereTheDataLives wtdl = new WhereTheDataLives();
// 	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
// 	private String rawDataPath = prefs.get("BSG_RAW_DATA", wtdl.getRawDataPath());
// 	// private final String dataHome = WhereTheDataLives.defaultRawDataPath;
// 	// http://neo4j.b12x.org/db/data/transaction/commit

// 	public Neo4jHttpCall() { }

// 	public void makeCall() throws IOException {
// 		// CloseableHttpClient httpclient = HttpClients.createDefault();

// 		// try {
			
// 		// 	URI uri = new URIBuilder()
// 		// 		.setScheme("http")
// 		// 		.setHost("neo4j.b12x.org")
// 		// 		.setPath("/db/data/transaction/commit")
// 		// 		.setParameter("tool", "PHYCuS")
// 		// 		.setParameter("email", "phycusproject@gmail.com")
// 		// 		.setParameter("ids", "23193287")
// 		// 		.setParameter("versions", "no")
// 		// 		.setParameter("format", "csv")
// 		// 		.build();
// 		// 	HttpGet httpget = new HttpGet(uri);
			
// 		// 	CloseableHttpResponse response = httpclient.execute(httpget);
// 		// 	System.out.println(response);
// 		// 	HttpEntity entity = response.getEntity();
// 		// 	if (entity != null) {
//   //               long len = entity.getContentLength();
//   //               InputStream is = entity.getContent();
//   //               String filePath = userDocumentsPath + "doi.csv";
// 		// 		FileOutputStream fos = new FileOutputStream(new File(filePath));
// 		// 		int inByte;
// 		// 		while((inByte = is.read()) != -1)
// 		// 			 fos.write(inByte);
// 		// 		is.close();
// 		// 		fos.close();
//   //           }
// 	}



// }