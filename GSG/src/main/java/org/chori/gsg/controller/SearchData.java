package org.chori.gsg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.*;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;

public class SearchData {

	private static HashMap<String, JTextArea> whichTextArea = new HashMap<>();
	private FileUtilities fileUtilities = new FileUtilities();

	public SearchData() {
		whichTextArea.put("GFE", B12xGui.resultsTextAreaGfe);
		whichTextArea.put("NAME", B12xGui.resultsTextAreaName);
	}

	public void searchThroughData(File file, String regex, String dataFormat, String whichTab) {

		int i = 0;
		String line,
			   timeStamp = LocalDateTime.now().toString();
		String[] gfeAlleles = new String[2]; 
			   
		HashMap<String, String> unsortedData = new HashMap();
		LinkedHashMap<String, String> sortedDataMatches = new LinkedHashMap();

		JTextArea printToMe = whichTextArea.get(whichTab);
		
		System.out.println("Made it to SearchData: " + regex);
			
		try {    
			// Read the File
			BufferedReader br = new BufferedReader(new FileReader(file));
			String fileDate = br.readLine();
			String version = br.readLine();
			line = br.readLine();

			int gfe = fileUtilities.whichSideIsTheGfe(line);

			switch(whichTab) {
				case "GFE":
					do {
						// use comma as separator
						gfeAlleles = line.split(",");
						if (gfeAlleles[gfe].matches(regex)){
							
							unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
							i++;
						}
					} while ((line = br.readLine()) != null);

					break;
				case "NAME":
					// Create a pattern from regex 
					Pattern pattern = Pattern.compile(regex);

					do {
						// use comma as separator
						gfeAlleles = line.split(",");

						// Create a matcher for the input String 
						Matcher matcher = pattern.matcher(gfeAlleles[1 - gfe]);

						if (matcher.lookingAt()){
							
							unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
							i++;
						}
					} while ((line = br.readLine()) != null);
					
					break;
				default:
					System.out.println("SearchData doesn't know how to process the data");
			}

			// Close the buffer
			br.close();

		} catch (Exception ex) { System.out.println("SearchData: searchThroughData: " + ex); }

		// sort the data: passed on with GFE as key
		SortData sorting = new SortData();
		sortedDataMatches = sorting.sortTheData(unsortedData);
		
		// print the sorted data to the appropriate screen 
		for (Map.Entry me:sortedDataMatches.entrySet()) {

			switch (dataFormat){
				case "CSV":
					// System.out.println("Reached CSV in switch in SearchData");
					// whichTextArea.get(whichTab).append("test");
					// B12xGui.resultsTextAreaGfe.append("Test");
					printToMe.append(me.getValue() + "," + me.getKey());
					printToMe.append(System.lineSeparator());
					break;
				case "TSV":
					// System.out.println("Reached TSV in switch in SearchData");
					printToMe.append((String)me.getValue()+ "\t" + (String)me.getKey());
					// printToMe.append("Test");
					printToMe.append(System.lineSeparator());
					break;
				default:
					System.out.println("SearchData is looking for a format that isn't listed.");
			}
		}

		// Footer
		if (i == 0){
			printToMe.append("No results found");
		} else {
			printToMe.append("Total Results: " + i);
		}



		// Write contents of textbox to file
		// if(hlaWriteToFileChecked){
		//     WriteFile fileWriter = new WriteFile();
		//     fileWriter.writeToFile(locus, version, "HLA", dataType);
			
		// }
		
	}
}