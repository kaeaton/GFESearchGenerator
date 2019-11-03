
package org.chori.gsg.model.processJson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.view.*;

public class IncomingJsonData {
	
	private JsonFactoryInstance getFactory = JsonFactoryInstance.getInstance();
	private JsonFactory factory = getFactory.factory;

	public IncomingJsonData() {

	}
	
	public void parseNeo4jResponse(String locus, String version, 
			InputStream httpResult) throws IOException {

		try 
		{
			WhereTheDataLives wtdl = new WhereTheDataLives();
			String gfeRawPath = wtdl.storeRawData("HLA", locus, version);

			File gfeRaw = new File(gfeRawPath);

			BufferedWriter writer = new BufferedWriter(new FileWriter(gfeRaw));
			
			// write a date stamp and version number at the top of the file
			LocalDate date = LocalDate.now();
			writer.write(date.toString() + System.lineSeparator());
			writer.write(version + System.lineSeparator());

			// hashmap to hold downloaded pairs before sorting
			HashMap<String, String> neo4jPairs = new HashMap<String, String>();
			
			JsonParser parser = factory.createParser(httpResult);
			
			// continue parsing the token until the end of input is reached
			while (!parser.isClosed()) {

				// get the token
				JsonToken token = parser.nextToken();

				// if its the last token then we are done
				if (token == null) break;
  
				// we want to look for a field that says data
				if (JsonToken.FIELD_NAME.equals(token) && "data".equals(parser.getCurrentName())) {

					// we are entering the datasets now. The first token should be
					// start of array
					token = parser.nextToken();

					// we are now looking for a field that says "row". We
					// continue looking till we find all such fields.
					while (true) {
						token = parser.nextToken();
						if (token == null) break;
						
						if (JsonToken.FIELD_NAME.equals(token) && "row".equals(parser.getCurrentName())) {
							token = parser.nextToken();
							token = parser.nextToken();
							String value = parser.getText();
							token = parser.nextToken();
							String key = parser.getText();
							
							// test for duplicate key (GFE) (Unlikely, but just in case)
							if(neo4jPairs.containsKey(key)) {
								System.out.println("Duplicate gfe key found: " + key);
							
								javax.swing.JOptionPane.showMessageDialog(B12xGui.parentTabbedPane,
									("GFE " + key + " exists in the database at least twice. It is associated with " + value),
									"This GFE has been downloaded before",
									javax.swing.JOptionPane.ERROR_MESSAGE);
							}
							System.out.println(key + " " + value);
							neo4jPairs.put(key, value);
						}
					}
				}
			}
			parser.close();
			
			// sort the Hashmap
			System.out.println("Calling the sort");
			SortData sortData = new SortData();
			LinkedHashMap<String, String> sortedNeo4jHlaPairs = sortData.sortTheData(neo4jPairs);
			
			// write sorted hashmap to file
			for(Map.Entry m:sortedNeo4jHlaPairs.entrySet()) {  
				writer.write(m.getKey() + "," + m.getValue()
						+ System.lineSeparator());  
			}  

			writer.close();

		} catch (Exception ex) { System.out.println(ex); }
	}

		/* Testing code start */
		// writes raw data to file (it will be labeled csv,
		// but it's actually straight text)
		// to use comment out everything after BufferedWriter (line 51, not inclusive)
		// through to this (line 123, inclusive)

		// 		InputStreamReader isReader = new InputStreamReader(httpResult);
		// 		BufferedReader reader = new BufferedReader(isReader);
		// 		BufferedReader reader = new BufferedReader(key);

		// 		StringBuffer sb = new StringBuffer();
		// 		String str;
		// 		while((str = reader.readLine())!= null){
		// 			sb.append(str);
		// 		}
		// 		writer.write(sb.toString()); 
		// 		writer.close();

		// 	} catch (Exception ex) { System.out.println(ex); }
		// }
		/* Testing code end */

	
   //  public void parseKirResponse(String locus, String version, 
   //          InputStream httpResult, JsonFactory factory) throws IOException 
   //  {
   //      try 
   //      {
   //          File neo4jRaw = new File(Neo4j.dataFilesPath 
   //                  + version + System.getProperty("file.separator") 
   //                  + "neo4j_KIR_" + version 
   //                  + "_Download.csv");
   //          neo4jRaw.createNewFile();

   //          BufferedWriter writer = new BufferedWriter(new FileWriter(neo4jRaw));
			
   //          LocalDate date = LocalDate.now();
   //          writer.write(date.toString() + System.lineSeparator());
   //          writer.write(version + System.lineSeparator());

   //          HashMap<String, String> neo4jKirPairs = new HashMap();
			
   //          JsonParser parser = factory.createParser(httpResult);
			
   //          // continue parsing the token until the end of input is reached
   //          while (!parser.isClosed()) 
   //          {
   //              // get the token
   //              JsonToken token = parser.nextToken();

   //              // if its the last token then we are done
   //              if (token == null)
   //                  break;
				
   //              // we want to look for a field that says data
   //              if (JsonToken.FIELD_NAME.equals(token) && "data".equals(parser.getCurrentName())) 
   //              {
   //                  // we are entering the datasets now. The first token should be
   //                  // start of array
   //                  token = parser.nextToken();

   //                  // we are now looking for a field that says "row". We
   //                  // continue looking till we find all such fields. This is
   //                  // probably not a best way to parse this json, but this will
   //                  // suffice for this example.
   //                  while (true) 
   //                  {
   //                      token = parser.nextToken();
   //                      if (token == null) break;
   //                      if (JsonToken.FIELD_NAME.equals(token) && "row".equals(parser.getCurrentName())) 
   //                      {
   //                          token = parser.nextToken();
   //                          token = parser.nextToken();
   //                          String value = parser.getText();
   //                          System.out.println(parser.getText());
   //                          token = parser.nextToken();
   //                          String key = parser.getText();
   //                          System.out.println(parser.getText());
							
			// 				// test for duplicate key (GFE)
			// 				if(neo4jKirPairs.containsKey(key)) {
			// 					System.out.println("Duplicate gfe key found: " + key);
							
			// 					javax.swing.JOptionPane.showMessageDialog(B12xGUI.jTabbedPane,
			// 						("GFE " + key + " exists in the database at least twice. It is associated with " + value),
			// 						"This GFE has been downloaded before",
			// 						javax.swing.JOptionPane.ERROR_MESSAGE);
			// 				}
							
   //                          neo4jKirPairs.put(key, value);
   //                      }
   //                  }
   //              }
   //          }
   //          parser.close();
			
			// // sort the Hashmap
			// HashMap<String, String> sortedNeo4jKirPairs = sortMapByValue(neo4jKirPairs);

			// // write sorted hashmap to file
   //          for(Map.Entry m:sortedNeo4jKirPairs.entrySet())
   //          {  
   //              writer.write(m.getKey() + "," + m.getValue()
   //                      + System.lineSeparator());  
   //          }  
   //          writer.close();
			
			// Debugging tools
			// Write raw data to file to see structure
//            ObjectMapper mapper = new ObjectMapper();
//            Object json = mapper.readValue(httpResult, Object.class);
//            File neo4jRaw = new File(Neo4j.dataFilesPath 
//                    + version + System.getProperty("file.separator")
//                    + "neo4jRaw" + locus + "Data.json");
//            mapper.writerWithDefaultPrettyPrinter().writeValue(neo4jRaw, json);  
			
	//     } catch (Exception ex) 
	//     {
	//         System.out.println(ex);
	//     }
	// }
	

	
	public ArrayList<String> parseVersion(InputStream httpResult, 
			String versionType) throws IOException 
	{
		ArrayList<String> versions = new ArrayList<>();
		System.out.println(versions.toString());

		try 
		{
			// reading raw data and extracting the version string
			// open the json parser
			JsonParser parser = factory.createParser(httpResult);
			System.out.println("Taken the input string and opened the Version parser");
			
			// continue parsing the token till the end of input is reached
			while (!parser.isClosed()) {
				// get the token
				JsonToken token = parser.nextToken();

				while (true) {
					token = parser.nextToken();
					if (token == null) break;
					
					// we want to look for a key field that says row
					if (JsonToken.FIELD_NAME.equals(token) 
							&& "row".equals(parser.getCurrentName())) {
//                    if (JsonToken.VALUE_STRING.equals(token) 
//                            && "row".equals(parser.getText())) {
						token = parser.nextToken();
						token = parser.nextToken();
						versions.add(parser.getText());
						System.out.println(versions.toString());

					}
				}
			}
			
			/* Testing code start */
			// writes raw data to a text file labeled csv, but is 
			// really straight text.
			// To use comment out everything from the JSON parser (inclusive)
			// to the bracket directly above (inclusive)

			// 	WhereTheDataLives wtdl = new WhereTheDataLives();
			// 	String dataFilesPath = wtdl.getRawDataPath();
			// 	File gfeRaw = new File(dataFilesPath
			// 			// + version + System.getProperty("file.separator") 
			// 			+ "neo4j_version_data" 
			// 			+ "_Download.csv");

			// 	if (!gfeRaw.exists()) {
			// 		System.out.println("The file does not exist.");
				// 	gfeRaw.getParentFile().mkdirs();
				// 	gfeRaw.createNewFile();
				// }

			// 	BufferedWriter writer = new BufferedWriter(new FileWriter(gfeRaw));
				
			// 	LocalDate date = LocalDate.now();
			// 	writer.write(date.toString() + System.lineSeparator());
			// 	InputStreamReader isReader = new InputStreamReader(httpResult);
			// 	BufferedReader reader = new BufferedReader(isReader);
			// 	StringBuffer sb = new StringBuffer();
			// 	String str;
			//     while((str = reader.readLine())!= null){
			// 	    sb.append(str);
			//     }
			// 	writer.write(sb.toString()); 
			// }
	
			/* Testing code end */

		} catch (Exception ex) { System.out.println(ex); }

		return versions;
	}
}