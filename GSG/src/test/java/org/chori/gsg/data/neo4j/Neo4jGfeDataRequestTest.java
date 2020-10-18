package org.chori.gsg.data.neo4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONException;

import org.chori.gsg.data.neo4j.Neo4jGfeDataRequest;

public class Neo4jGfeDataRequestTest {

	private static final String HLA_VERSION = "3.39.0"; 
	private static final String HLA_LOCUS = "HLA-DRB1"; 
	private static final String KIR_VERSION = "2.7.0"; 
	private static final String KIR_LOCUS = "KIR"; 
	private static final String ABO_VERSION = "Unknown"; 
	private static final String ABO_LOCUS = "ABO"; 

	private Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();
	private String expectedHlaJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = \\\"" + HLA_LOCUS + "\\\" AND r.imgt_release = \\\"" + HLA_VERSION + "\\\" RETURN n.name, g.name\"}]}";
	private String expectedKirJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = \\\"" + KIR_VERSION + "\\\" RETURN n.name, g.name\"}]}";
	
	@Test
	public void createNeo4jGfeDataRequest_CorrectValue_ValidLociAndVersionInput() throws JSONException {
		
		String generatedHlaJsonString = neo4jGfeDataRequest.createNeo4jGfeDataRequest("HLA", HLA_LOCUS, HLA_VERSION);
		String generatedKirJsonString = neo4jGfeDataRequest.createNeo4jGfeDataRequest("KIR", KIR_LOCUS, KIR_VERSION);

		JSONAssert.assertEquals(expectedHlaJsonString, generatedHlaJsonString, false);
		JSONAssert.assertEquals(expectedKirJsonString, generatedKirJsonString, false);
	}

	@Test
	public void createNeo4jGfeDataRequest_Null_InvalidLociAndVersionInput() {
		assertNull(neo4jGfeDataRequest.createNeo4jGfeDataRequest("ABO", ABO_LOCUS, ABO_VERSION));
	}
}

