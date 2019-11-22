package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

/**
 *
 * @author kaeaton
 */
public class Neo4jFeatureRequest 
{
	
	private String request;
	
	public Neo4jFeatureRequest() { }
	
	public String formNeo4jFeatureRequest(String versionType) throws IOException 
	{
		try 
		{
			// Is it an HLA or KIR request?
			// hla
			if(versionType.equals("HLA")) {
				request = "MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) " 
						+ "RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY "
						+ "e.imgt_release DESC";
			// request string: MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY r.imgt_release DESC
			} 
			
			// kir
			else if(versionType.equals("KIR")) {
				request = "MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) " 
						+ "RETURN DISTINCT e.imgt_release AS KIR_DB " 
						+ "ORDER BY e.imgt_release DESC";
			// request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC
			} else {
				// oops
				System.out.println("versionType neither HLA nor KIR");
			}
			
			// generate json request
			GenerateJson generateJson = new GenerateJson();
			return generateJson.neo4jJsonGenerator(request);

		} catch (Exception ex) 
		{
			System.out.println(ex);
		}
		return null;
	}
}
