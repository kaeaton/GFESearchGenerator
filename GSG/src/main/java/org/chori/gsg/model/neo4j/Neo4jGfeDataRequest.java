package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

/**
 * Generates the Json formatted requests for data from the assorted neo4j Databases
 * 
 * @author Katrina Eaton
 * 
 */

public class Neo4jGfeDataRequest {

	private char quote = '"';
	private GenerateJson generateJson = new GenerateJson();

	public Neo4jGfeDataRequest() { }

	public String createNeo4jGfeDataRequest(String lociType, String locus, String version)
	{
		String neo4jDataRequest;
		String jsonNeo4jDataRequest;

		neo4jDataRequest = whichLociDataRequest(lociType, locus, version);

		jsonNeo4jDataRequest = convertNeo4jRequestToJson(neo4jDataRequest);

		return jsonNeo4jDataRequest;
	}

	private String whichLociDataRequest(String lociType, String locus, String version){
		switch(lociType) {
			case "HLA":
				return hlaRequest(locus, version);

			case "KIR":
				return kirRequest(version);

			default:
				System.out.println("Neo4jGfeDataRequest: Have not set up request strings for those loci yet: " + lociType);
		}

		return "";
	}

	private String hlaRequest(String locus, String version) {
		// request string: MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = "locus" AND r.imgt_release = "version" RETURN n.name, g.name

		String hlaRequest = ("MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) " +
							"WHERE n.locus = " + quote + locus + quote + " " +
							"AND r.imgt_release = " + quote + version + quote +
							" RETURN n.name, g.name");
		return hlaRequest;
	}

	private String kirRequest(String version) {
		// request string MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = "2.7.0" RETURN n.name, g.name
				 
		String kirRequest = ("MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) " +
							"WHERE r.imgt_release = " + quote + version + quote +
							" RETURN n.name, g.name");
		return kirRequest;
	}

	private String convertNeo4jRequestToJson(String neo4jDataRequest) {
		try {
			
			return generateJson.neo4jJsonGenerator(neo4jDataRequest);
		   
		} catch (Exception ex) { System.out.println("Neo4jRequest: createNeo4jJsonRequest: " + ex); }
		
		return "";
	}
}