package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

public class Neo4jRequests {

	private String request;

	public Neo4jRequests() { }

	public String formNeo4jRequest(String type, String locus, String version) throws IOException 
	{
		try 
		{
			char quote = '"';
			
			// hla or kir?
			// String requestType = LocusNameParser.hlaOrKir(locus);
			
			// hla
			if(type.equals("HLA")) {
			// request string: MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = "locus" AND r.imgt_release = "version" RETURN n.name, g.name

				request = ("MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) " +
							"WHERE n.locus = " + quote + locus + quote + " " +
							"AND r.imgt_release = " + quote + version + quote +
							" RETURN n.name, g.name");
			}
			
			// kir
			else if(locus.equals("KIR")) {
			// request string MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = "2.7.0" RETURN n.name, g.name
				 
			request = ("MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) " +
						"WHERE r.imgt_release = " + quote + version + quote +
						" RETURN n.name, g.name");
			}
			
			// oops
			else
			{
				System.out.println("Problem creating the neo4j data request.");
			}
			
			// generate the json request
			GenerateJson generateJson = new GenerateJson();
			return generateJson.neo4jJsonGenerator(request);
		   
		} catch (Exception ex) 
		{
			System.out.println(ex);
		}
		
		return null;
	}
}