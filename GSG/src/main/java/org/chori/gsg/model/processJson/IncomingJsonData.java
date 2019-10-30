
package org.chori.gsg.model.processJson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
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
		// putting in try/catch cuts off the beginning and end
		// of the InputStream, no idea why
		// try 
		// {
			WhereTheDataLives wtdl = new WhereTheDataLives();
			String dataFilesPath = wtdl.getRawDataPath();
			File gfeRaw = new File(dataFilesPath
					+ version + System.getProperty("file.separator") 
					+ "neo4j_" + locus + "_" + version 
					+ "_Download.csv");

			if (!gfeRaw.exists()) {
				System.out.println("The file does not exist.");
                gfeRaw.getParentFile().mkdirs();
                gfeRaw.createNewFile();
            }

			BufferedWriter writer = new BufferedWriter(new FileWriter(gfeRaw));
			
			LocalDate date = LocalDate.now();
			writer.write(date.toString() + System.lineSeparator());
			writer.write(version + System.lineSeparator());

			HashMap<String, String> neo4jPairs = new HashMap<String, String>();
			
			JsonParser parser = factory.createParser(httpResult);
			
			// continue parsing the token until the end of input is reached
			while (!parser.isClosed()) {
				// get the token
				JsonToken token = parser.nextToken();

				// if its the last token then we are done
				if (token == null) break;
  
				// we want to look for a field that says data
				if (JsonToken.FIELD_NAME.equals(token) && "data".equals(parser.getCurrentName())) 
				{
					// we are entering the datasets now. The first token should be
					// start of array
					token = parser.nextToken();

					// we are now looking for a field that says "row". We
					// continue looking till we find all such fields. This is
					// probably not a best way to parse this json, but this will
					// suffice for this example.
					while (true) {
						token = parser.nextToken();
						if (token == null) break;
						
						if (JsonToken.FIELD_NAME.equals(token) && "row".equals(parser.getCurrentName())) {
							token = parser.nextToken();
							token = parser.nextToken();
							String value = parser.getText();
							token = parser.nextToken();
							String key = parser.getText();
							
							// test for duplicate key (GFE)
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
			
			// // sort the Hashmap
			System.out.println("Calling the sort");
			SortData sortData = new SortData();
			LinkedHashMap<String, String> sortedNeo4jHlaPairs = sortData.sortTheData(neo4jPairs);
			
			// // write sorted hashmap to file
			for(Map.Entry m:sortedNeo4jHlaPairs.entrySet()) {  
				writer.write(m.getKey() + "," + m.getValue()
						+ System.lineSeparator());  
			}  

		/* Testing code start */
				// InputStreamReader isReader = new InputStreamReader(httpResult);
				// BufferedReader reader = new BufferedReader(isReader);
				// BufferedReader reader = new BufferedReader(key);

				// StringBuffer sb = new StringBuffer();
				// String str;
			 //    while((str = reader.readLine())!= null){
				//     sb.append(str);
			 //    }
				// writer.write(sb.toString()); 
				// writer.close();

			// }
		// }
		/* Testing code end */

			writer.close();

		// } catch (Exception ex) { System.out.println(ex); }
	}
	
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
	
	// function to sort hashmap by values
// 	private HashMap<String, String> sortMapByValue (HashMap<String, String> incomingData)
// 	{
		
// 		// create Linked Hashmap and feed the name as the key 
// 		// and the unaltered pair as the value
// 		HashMap<String, Map.Entry<String, String>> dataToSort 
// 				= new LinkedHashMap<String, Map.Entry<String, String>>();
// 		for(Map.Entry m:incomingData.entrySet()) {
// 			dataToSort.put(m.getValue().toString(), m);
// 		}
// 		System.out.println(dataToSort.keySet());
		
// 		// pattern to match
// 		// The locus group is not "remembered" and does not count as a capture group.
// 		// The first recorded capture group is at the serology level and is mandatory.
// 		// All colons and all other capture groups are optional.
// 		Pattern p = Pattern.compile("^([A-Z0-9-]+)\\*([0-9]+)\\:?([0-9]+)?\\:?([0-9]+)?\\:?([0-9]+)?([A-Z])?$");
// 		Matcher m;
// 		String editedGroup;
// 		String finalName = "";
// 		HashMap<String, String> processedNames = new LinkedHashMap<String, String>();
		
// 		for(Map.Entry name:dataToSort.entrySet()){
// 			// if match is found
// 			m = p.matcher(name.getKey().toString());
			
// 			if (m.find()) {
// 				finalName = (m.group(1) + "*");
// //				System.out.println("finalName before while loop: " + finalName);

// 				int i = 2;
// 				while(m.group(i) != null){
// 					// pull the capture group, convert to int, 
// 					// pad left with 0's, return to string
// 					editedGroup = String.format("%04d", Integer.parseInt(m.group(i))).toString();
// 					finalName = finalName + editedGroup + ":";
// 					i++;
// 				}
// 				System.out.println("finalName: " + finalName);
// 				System.out.println("Corresponding key: " + name.getKey().toString());
// 			}
// 			System.out.println("Adding finalName to processedNames");
// 			processedNames.put(finalName, name.getKey().toString());
// 		}
// 		for(Map.Entry<String, String> processedName:processedNames.entrySet()){
// 			String[] data = processedName.getKey().toString().split("=");
// 			System.out.println("Processed names: " + data[0]);
// 		}

// //		return parsedAttribute;
// 		// Create a list from elements of HashMap 
//         List<Map.Entry<String, String> > list = 
//                new LinkedList<Map.Entry<String, String> >(processedNames.entrySet());
		
// 		// Sort the list 
//         Collections.sort(list, new Comparator<Map.Entry<String, String> >() { 
//             public int compare(Map.Entry<String, String> o1,  
//                                Map.Entry<String, String> o2) 
//             { 
//                 return (o1.getValue()).compareTo(o2.getValue()); 
//             } 
//         }); 
		
// 		HashMap<String, String> sortedNames = new LinkedHashMap<String, String>(); 
//         for (Map.Entry<String, String> aa : list) { 
//             sortedNames.put(aa.getKey(), aa.getValue()); 
//         }
		
// 		String[] currentData;
// 		HashMap<String, String> sortedData = new LinkedHashMap<String, String>(); 
//         for (Map.Entry<String, String> data : sortedNames.entrySet()) { 
//             currentData = data.getValue().toString().split("=");
// 			System.out.println(currentData);
//             sortedData.put(currentData[0], currentData[1]); 
//         }
		
// 		// put data from sorted list to hashmap 
// //		String[] currentData;
// //        HashMap<String, String> sortedData = new LinkedHashMap<String, String>(); 
// //        for (Map.Entry data:dataToSort.entrySet()) { 
// //			currentData = data.getValue().toString().split("=");
// //			System.out.println(currentData);
// //            sortedData.put(currentData[0], currentData[1]); 
// //        }
		
// 		return sortedData;
// 	}
	
	public ArrayList<String> parseVersion(InputStream httpResult, 
			String versionType) throws IOException 
	{
		ArrayList<String> versions = new ArrayList<>();
		System.out.println(versions.toString());

		try 
		{
			// reading raw data and extracting the version string
			// open the json parser
// 			JsonParser parser = factory.createParser(httpResult);
// 			System.out.println("Taken the input string and opened the Version parser");
			
// 			// continue parsing the token till the end of input is reached
// 			while (!parser.isClosed()) 
// 			{
// 				// get the token
// 				JsonToken token = parser.nextToken();

// 				while (true) 
// 				{
// 					token = parser.nextToken();
// 					if (token == null) break;
					
// 					// we want to look for a key field that says row
// 					if (JsonToken.FIELD_NAME.equals(token) 
// 							&& "row".equals(parser.getCurrentName())) 
// 					{
// //                    if (JsonToken.VALUE_STRING.equals(token) 
// //                            && "row".equals(parser.getText())) {
// 						token = parser.nextToken();
// 						token = parser.nextToken();
// 						versions.add(parser.getText());
// 						System.out.println(versions.toString());

// 					}
// 				}
// 			}
			
// 			// close the json parser
// 			parser.close();
			
			/* Testing code start */
				WhereTheDataLives wtdl = new WhereTheDataLives();
				String dataFilesPath = wtdl.getRawDataPath();
				File gfeRaw = new File(dataFilesPath
						// + version + System.getProperty("file.separator") 
						+ "neo4j_version_data" 
						+ "_Download.csv");

				if (!gfeRaw.exists()) {
					System.out.println("The file does not exist.");
	                gfeRaw.getParentFile().mkdirs();
	                gfeRaw.createNewFile();
	            }

				BufferedWriter writer = new BufferedWriter(new FileWriter(gfeRaw));
				
				LocalDate date = LocalDate.now();
				writer.write(date.toString() + System.lineSeparator());
				// writer.write(version + System.lineSeparator());
				InputStreamReader isReader = new InputStreamReader(httpResult);
				BufferedReader reader = new BufferedReader(isReader);
				StringBuffer sb = new StringBuffer();
				String str;
			    while((str = reader.readLine())!= null){
				    sb.append(str);
			    }
				writer.write(sb.toString()); 
			// }
	
		/* Testing code end */
			
	//         writer.close();

		} catch (Exception ex) 
		{
			System.out.println(ex);
		}
		return versions;
	}
}