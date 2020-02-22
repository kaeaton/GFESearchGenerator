package org.chori.gsg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.*;
import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;


/**
 * This class compares the data to the appropriate regex string and prints
 * the matches to the appropriate textArea.
 * 
 * @author Katrina Eaton
 * 
 */
public class SearchData {

	private static HashMap<String, JTextArea> whichTextArea = new HashMap<>();
	private FileUtilities fileUtilities = new FileUtilities();
	private SortData sortData = new SortData();

	public SearchData() {
		whichTextArea.put("GFE", B12xGui.resultsTextAreaGfe);
		whichTextArea.put("NAME", B12xGui.resultsTextAreaName);
	}

	public void searchThroughData(File file, String regex, String dataFormat, String whichTab) {
		HashMap<String, String> unsortedData = new HashMap();
		LinkedHashMap<String, String> sortedDataMatches = new LinkedHashMap();

		JTextArea printToMe = whichTextArea.get(whichTab);
		
		System.out.println("Made it to SearchData: " + regex);
			
		try {    
			// Read the File
			BufferedReader br = new BufferedReader(new FileReader(file));
			String fileDate = br.readLine();
			String version = br.readLine();
			String line = br.readLine();

			int gfe = fileUtilities.whichSideIsTheGfe(line);

			switch(whichTab) {
				case "GFE":
					unsortedData = sortGfeData(br, regex, line, gfe);
					break;
				case "NAME":
					unsortedData = sortNameData(br, regex, line, gfe);
					break;
				default:
					System.out.println("SearchData doesn't know how to process the data");
			}

			// Close the buffer
			br.close();

		} catch (Exception ex) { System.out.println("SearchData: searchThroughData: " + ex); }

		// sort the data: passed on with GFE as key
		sortedDataMatches = sortData.sortTheData(unsortedData);
		
		// print the sorted data to the appropriate screen 
		int totalMatches = printData(sortedDataMatches, dataFormat, printToMe);
		printFooter(totalMatches, printToMe);
	}

	private HashMap<String, String> sortGfeData(BufferedReader br, String regex, String line, int gfe) {
		HashMap<String, String> unsortedData = new HashMap();
		String[] gfeAlleles = new String[2];

		try {
			do {
				// use comma as separator
				gfeAlleles = line.split(",");
				if (gfeAlleles[gfe].matches(regex)){
					
					unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
					// totalMatches++;
				}
			} while ((line = br.readLine()) != null);
		} catch(Exception ex) { System.out.println("SearchData: sortNameData: " + ex); }

		return unsortedData;
	}

	private HashMap<String, String> sortNameData(BufferedReader br, String regex, String line, int gfe) {
		HashMap<String, String> unsortedData = new HashMap();
		String[] gfeAlleles = new String[2];

		// Create a pattern from regex 
		Pattern pattern = Pattern.compile(regex);

		try {
			do {
				// use comma as separator
				gfeAlleles = line.split(",");

				// Create a matcher for the input String 
				Matcher matcher = pattern.matcher(gfeAlleles[1 - gfe]);

				if (matcher.lookingAt()){
					
					unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
				}
			} while ((line = br.readLine()) != null);
		} catch(Exception ex) { System.out.println("SearchData: sortNameData: " + ex); }

		return unsortedData;
	}

	private int printData(LinkedHashMap<String, String> sortedDataMatches, String dataFormat, JTextArea printToMe) {

		int totalMatches = 0;

		// print the sorted data to the appropriate screen 
		for (Map.Entry me:sortedDataMatches.entrySet()) {

			switch (dataFormat){
				case "CSV":
					printToMe.append(me.getValue() + "," + me.getKey());
					printToMe.append(System.lineSeparator());
					totalMatches++;

					break;
				case "TSV":
					printToMe.append((String)me.getValue()+ "\t" + (String)me.getKey());
					printToMe.append(System.lineSeparator());
					totalMatches++;

					break;
				default:
					System.out.println("SearchData is looking for a format that isn't listed.");
			}
		}

		return totalMatches;
	}

	private void printFooter(int totalMatches, JTextArea printToMe) {
		// Footer
		if (totalMatches == 0){
			printToMe.append("No results found");
		} else {
			printToMe.append("Total Results: " + totalMatches);
		}
	}
}