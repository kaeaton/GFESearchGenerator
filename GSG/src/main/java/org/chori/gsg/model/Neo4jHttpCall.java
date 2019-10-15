package org.chori.gsg.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URI;
import java.util.prefs.Preferences;

import org.chori.gsg.view.B12xGui;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.utils.URIBuilder;
// import org.apache.http.HttpEntity;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;

public class Neo4jHttpCall {

	private WhereTheDataLives wtdl = new WhereTheDataLives();
	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private String rawDataPath = prefs.get("BSG_RAW_DATA", wtdl.getRawDataPath());
	// private final String dataHome = WhereTheDataLives.defaultRawDataPath;
	// http://neo4j.b12x.org/db/data/transaction/commit

	public Neo4jHttpCall() { }

	public void makeCall() throws IOException {
		// CloseableHttpClient httpclient = HttpClients.createDefault();

		// try {
			
		// 	URI uri = new URIBuilder()
		// 		.setScheme("http")
		// 		.setHost("neo4j.b12x.org")
		// 		.setPath("/db/data/transaction/commit")
		// 		.setParameter("tool", "PHYCuS")
		// 		.setParameter("email", "phycusproject@gmail.com")
		// 		.setParameter("ids", "23193287")
		// 		.setParameter("versions", "no")
		// 		.setParameter("format", "csv")
		// 		.build();
		// 	HttpGet httpget = new HttpGet(uri);
			
		// 	CloseableHttpResponse response = httpclient.execute(httpget);
		// 	System.out.println(response);
		// 	HttpEntity entity = response.getEntity();
		// 	if (entity != null) {
  //               long len = entity.getContentLength();
  //               InputStream is = entity.getContent();
  //               String filePath = userDocumentsPath + "doi.csv";
		// 		FileOutputStream fos = new FileOutputStream(new File(filePath));
		// 		int inByte;
		// 		while((inByte = is.read()) != -1)
		// 			 fos.write(inByte);
		// 		is.close();
		// 		fos.close();
  //           }
	}



}