package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

/**
 *
 * @author kaeaton
 */
public class Neo4jVersionRequest 
{
	
	private String request;
	private GenerateJson generateJson = new GenerateJson();
	
	public Neo4jVersionRequest() { }
	
	public String formNeo4jVersionRequest(String versionType) throws IOException 
	{

			request = "MATCH (n:IMGT_" + versionType + ")-[e:HAS_FEATURE]-(feat:FEATURE) " 
					+ "RETURN DISTINCT e.imgt_release AS " + versionType + "_DB ORDER BY "
					+ "e.imgt_release DESC";
			// request string: MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY e.imgt_release DESC
			// request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC
			
		try{

			// generate json request
			return generateJson.neo4jJsonGenerator(request);

		} catch (Exception ex) { System.out.println("Neo4j version request: " + ex); }
		
		return null;
	}
}
