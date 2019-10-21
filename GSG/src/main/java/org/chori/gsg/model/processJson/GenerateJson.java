package org.chori.gsg.model.processJson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.StringWriter;

public class GenerateJson {
    
    private JsonFactoryInstance getFactory = JsonFactoryInstance.getInstance();
    private JsonFactory factory = getFactory.factory;
    // private JsonFactory factory = factorySingleton.getInstance();

    public GenerateJson() 
    {
        
    }
    
    public String jsonGenerator(String request)
    {
        StringWriter writer = new StringWriter();
        
        try
        {
            JsonGenerator generator = factory.createGenerator(writer);

            // start writing with {
            generator.writeStartObject();
            generator.writeFieldName("statements");
            generator.writeStartArray();
            generator.writeStartObject();
            generator.writeStringField("statement", request);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.close();

            System.out.println(writer.toString());            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return writer.toString();
    }
}