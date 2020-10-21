package org.chori.gsg.data.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.data.processJson.*;
import org.chori.gsg.data.*;

/**
 * Generates the Json request for the releases available in the databases
 * 
 * @author Katrina Eaton
 * 
 */
public class Neo4jVersionRequest 
{
	
	private String request;
	private GenerateJson generateJson = new GenerateJson();
	
	public Neo4jVersionRequest() { }

	/**
	 * Creates the JSON request for the GFE data in the neo4j database.
	 * 
	 * @param lociType The loci whose versions should be requested
	 * @return The compiled JSON version request string
	 */
	public String createNeo4jVersionRequest(String lociType) {

		String neo4jVersionRequest;

		request = "MATCH (n:IMGT_" + lociType + ")-[e:HAS_FEATURE]-(feat:FEATURE) " 
				+ "RETURN DISTINCT e.imgt_release AS " + lociType + "_DB ORDER BY "
				+ "e.imgt_release DESC";
		// request string: MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY e.imgt_release DESC
		// request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC
		
		neo4jVersionRequest = convertNeo4jRequestToJson(request);
		
		return neo4jVersionRequest;
	}

	private String convertNeo4jRequestToJson(String neo4jVersionRequest) {
		try {
			
			return generateJson.neo4jJsonGenerator(neo4jVersionRequest);
		   
		} catch (Exception ex) { System.out.println("Neo4jRequest: createNeo4jJsonRequest: " + ex); }
		
		return null;
	}
}
