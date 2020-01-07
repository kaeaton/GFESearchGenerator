package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

public class Neo4jDataRequest {

	private char quote = '"';
	private GenerateJson generateJson = new GenerateJson();

	public Neo4jDataRequest() { }

	public String formNeo4jRequest(String locus, String version) throws IOException 
	{
		String neo4jDataRequest;

		if(locus.equals("KIR")) {
			neo4jDataRequest = kirRequest(version);
		} else {
			neo4jDataRequest = hlaRequest(locus, version);
		}

		return createNeo4jJsonRequest(neo4jDataRequest);
	}

	private String kirRequest(String version) {
		// request string MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = "2.7.0" RETURN n.name, g.name
				 
		String kirRequest = ("MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) " +
							"WHERE r.imgt_release = " + quote + version + quote +
							" RETURN n.name, g.name");
		return kirRequest;
	}

	private String hlaRequest(String locus, String version) {
		// request string: MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = "locus" AND r.imgt_release = "version" RETURN n.name, g.name

		String hlaRequest = ("MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) " +
							"WHERE n.locus = " + quote + locus + quote + " " +
							"AND r.imgt_release = " + quote + version + quote +
							" RETURN n.name, g.name");
		return hlaRequest;
	}

	private String createNeo4jJsonRequest(String neo4jDataRequest) {
		try {
			
			return generateJson.neo4jJsonGenerator(neo4jDataRequest);
		   
		} catch (Exception ex) { System.out.println("Neo4jRequest: createNeo4jJsonRequest: " + ex); }
		
		return null;
	}
}