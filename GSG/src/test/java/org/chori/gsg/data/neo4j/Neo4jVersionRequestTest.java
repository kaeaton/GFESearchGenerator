package org.chori.gsg.data.neo4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONException;

import org.chori.gsg.data.neo4j.Neo4jVersionRequest;

public class Neo4jVersionRequestTest {

	private Neo4jVersionRequest neo4jVersionRequest = new Neo4jVersionRequest();

	private String expectedHlaJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY e.imgt_release DESC\"}]}";
	private String expectedKirJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC\"}]}";
	
	@Test
	public void createNeo4jVersionRequest_CorrectValue_ValidLociInput() throws JSONException {
		
		String generatedHlaJsonString = neo4jVersionRequest.createNeo4jVersionRequest("HLA");
		String generatedKirJsonString = neo4jVersionRequest.createNeo4jVersionRequest("KIR");

		JSONAssert.assertEquals(expectedHlaJsonString, generatedHlaJsonString, false);
		JSONAssert.assertEquals(expectedKirJsonString, generatedKirJsonString, false);
	}

	@Test
	public void createNeo4jVersionRequest_Null_InvalidLociInput() {
		assertNull(neo4jVersionRequest.createNeo4jVersionRequest("ABO"));
	}
}

