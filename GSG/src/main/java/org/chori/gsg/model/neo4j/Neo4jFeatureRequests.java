package org.chori.gsg.model.neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import org.chori.gsg.model.processJson.*;
import org.chori.gsg.model.*;

/**
 *
 * @author kaeaton
 */
public class Neo4jFeatureRequests 
{
	
	private String request;
	
	public Neo4jFeatureRequests() { }
	
	public String formNeo4jDuplicateFeatureRequest(String locus1, String locus2, String versionType1, String versionType2) throws IOException 
	{
		try 
		{
			char quote = '"';

			// hla
			if(versionType.equals("HLA")) {
				request = "MATCH (fa:FEATURE)-[hfa:HAS_FEATURE]-(a:IMGT_HLA), " 
						+ "(fb:FEATURE)-[hfb:HAS_FEATURE]-(b:IMGT_HLA) "
						+ "where fa.sequence = fb.sequence "
						+ "and a.locus = " + quote + locus1 + quote + " " 
						+ "and b.locus =" + quote + locus2 + quote + " "
						+ "and hfa.imgt_release = " + quote + version1 + quote + " "
						+ "and hfb.imgt_release = " + quote + version2 + quote + " "
						+ "RETURN DISTINCT fa.sequenceId ORDER BY fa.sequenceId";
			// request string: MATCH (fa:FEATURE)-[hfa:HAS_FEATURE]-(a:IMGT_HLA), (fb:FEATURE)-[hfb:HAS_FEATURE]-(b:IMGT_HLA) where fa.sequence = fb.sequence and a.locus = "HLA-C" and b.locus = "HLA-B" and hfa.imgt_release = "3.37.0" and hfb.imgt_release = "3.37.0" RETURN DISTINCT fa.sequenceId ORDER BY fa.sequenceId

			
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
