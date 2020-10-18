package org.chori.gsg.data.processJson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.StringWriter;

public class GenerateJson {
	
	private JsonFactoryInstance getFactory = JsonFactoryInstance.getInstance();
	private JsonFactory factory = getFactory.factory;

	public GenerateJson() { }
	
	public String neo4jJsonGenerator(String request) {
		StringWriter writer = new StringWriter();
		
		try {
			JsonGenerator generator = factory.createGenerator(writer);

			generator.writeStartObject(); 			// {
			generator.writeFieldName("statements");
			generator.writeStartArray(); 			// [
			generator.writeStartObject(); 			// {
			generator.writeStringField("statement", request);
			generator.writeEndObject(); 			// }
			generator.writeEndArray(); 				// ]
			generator.writeEndObject(); 			// }
			generator.close();

			System.out.println(writer.toString());

		} catch (Exception ex) { System.out.println("Error assembling JSON for Neo4j: " + ex); }

		return writer.toString();
	}
}