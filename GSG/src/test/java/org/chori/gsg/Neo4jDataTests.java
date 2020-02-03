import static org.junit.Assert.*;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import org.chori.gsg.model.neo4j.*;

public class Neo4jDataTests {

	private Neo4jGfeDataRequest neo4jGfeDataRequest = new Neo4jGfeDataRequest();

	@Test
	public void testNeo4jGfeDataRequest() throws Exception{
		String expectedHlaJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = \\\"HLA-DRB1\\\" AND r.imgt_release = \\\"3.39.0\\\" RETURN n.name, g.name\"}]}";
		String expectedKirJsonString = "{\"statements\":[{\"statement\":\"MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = \\\"2.7.0\\\" RETURN n.name, g.name\"}]}";
		
		String generatedHlaJsonString = neo4jGfeDataRequest.createNeo4jGfeDataRequest("HLA", "HLA-DRB1", "3.39.0");
		String generatedKirJsonString = neo4jGfeDataRequest.createNeo4jGfeDataRequest("KIR", "KIR", "2.7.0");

		JSONAssert.assertEquals(expectedHlaJsonString, generatedHlaJsonString, false);
		JSONAssert.assertEquals(expectedKirJsonString, generatedKirJsonString, false);
		assertSame("The neo4jGfeDataRequest for ABO does not match", null,
						neo4jGfeDataRequest.createNeo4jGfeDataRequest("ABO", "KIR", "2.7.0"));
	}
}

