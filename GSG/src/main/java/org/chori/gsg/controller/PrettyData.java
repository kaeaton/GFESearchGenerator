package org.chori.gsg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.tabs.*;

public class PrettyData {

	// Exons per locus
	private static HashMap<String, Integer> hlaExonTotal = new HashMap();
	private	static HashMap<String, JTextArea> whichTextArea = new HashMap();
	private HashMap<String, String> dataMatches = new HashMap();
	private LinkedHashMap<String, String> sortedDataMatches = new LinkedHashMap();
	private FileUtilities fileUtilities = new FileUtilities();

	public PrettyData() {
		whichTextArea.put("GFE", GfeTab.resultsTextAreaGfe);
		// whichTextArea.put("GFE", GSG.resultsTextAreaGfe);
		whichTextArea.put("NAME", GSG.resultsTextAreaName);

		hlaExonTotal.put("A", 8);
		hlaExonTotal.put("B", 7);
		hlaExonTotal.put("C", 8);
		hlaExonTotal.put("DPA1", 4);
		hlaExonTotal.put("DPB1", 5);
		hlaExonTotal.put("DQA1", 4);
		hlaExonTotal.put("DQB1", 6);
		hlaExonTotal.put("DRB1", 6);
		hlaExonTotal.put("DRB3", 6);
		hlaExonTotal.put("DRB4", 6);
		hlaExonTotal.put("DRB5", 6);
	}

	public void searchThroughData(File file, String regex, String whichTab) {
		String line;
		
		int gfe = 1;

		int[] spacingList = new int[18];
		
		String[] hlaIdentifier = new String[2];
		String[] protoSplitGfe = new String[2];
		String[] gfeAlleles = new String[2];

		LinkedList<String> splitGfe = new LinkedList<>();

		JTextArea printToMe = whichTextArea.get(whichTab);

		boolean idGfeFlag = true;
		boolean listFlag = true;
		boolean matchFlag = false;

		System.out.println("Made it to SearchData: " + regex);


		try {
			System.out.println("PrettyData: checking that the file passed to searchThroughData actually exists: "
								+ file.toString());
			// Read the File
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine(); // 1st line: filedate
			br.readLine(); // 2nd line: version

			// parse the gfe spacing for each matching line
			while ((line = br.readLine()) != null) {

				// reset the match flag
				matchFlag = false;

				// which side is the GFE?
				// flag so we only run it once
				if (idGfeFlag) {
					
					gfe = fileUtilities.whichSideIsTheGfe(line);

					idGfeFlag = false;
				}

				// use comma as separator
				gfeAlleles = line.split(",");

				switch(whichTab) {
					case "GFE":
						if (gfeAlleles[gfe].matches(regex)){
								
							// put gfe and name in holding hashmap for later printing
							dataMatches.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
							matchFlag = true;
						}
						break;

					case "NAME":
						// Create a pattern from regex 
						Pattern pattern = Pattern.compile(regex);

						// Create a matcher for the input String 
						Matcher matcher = pattern.matcher(gfeAlleles[1 - gfe]);

						if (matcher.lookingAt()){

							// put gfe and name in holding hashmap for later printing
							dataMatches.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
							matchFlag = true;
						}
						break;

					default:
						System.out.println("Pretty data doesn't know how to process this data");
				}

				if (matchFlag){

					// split the gfe along the dashes
					protoSplitGfe = gfeAlleles[gfe].split("-");

					// replace "HLA" with the actual locus name
					hlaIdentifier = protoSplitGfe[1].split("w");
					protoSplitGfe[0] = hlaIdentifier[0];
				

					// reset the list to the appropriate size for the allele
					// using a flag so it runs only once
					// has to be within the while loop because I need the 
					// length from protoSplitGfe
					if (listFlag) {
						spacingList = new int[protoSplitGfe.length];
						Arrays.fill(spacingList, 3);
						listFlag = false;
					}

					// copy to new arrayList to remove protoSplitGfe[1]
					splitGfe = new LinkedList<>(Arrays.asList(protoSplitGfe));
					splitGfe.remove(1);

					int k = 0;

					// if a feature String is longer than the length listed 
					// in the spacing array, replace that length with the new one
					for(String splitUpGfe:splitGfe) {

						// if (k > 0) {
							if (spacingList[k] < splitUpGfe.length()) // + 1)) 
								spacingList[k] = splitUpGfe.length(); //+ 1);
						// }
						k++;
					}
				}
			}

			// Close the buffer
			br.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// sort the dataMatches hashmap before printing
		SortData sorting = new SortData();
		sortedDataMatches = sorting.sortTheData(dataMatches);

		// Pretty data header
		printHeader(splitGfe.get(0), spacingList, printToMe);

		// Write the data to the appropriate text area
		int m = 0;
		int n = 0;
		// int o = 0;
		for (Map.Entry me:sortedDataMatches.entrySet()) {

			// print allele name
			printToMe.append(String.format("%-25s", me.getValue()));

			// cycle through gfe's individual features, 
			// print with appropriate lengths
			n = 0;
			protoSplitGfe = ((String)me.getKey()).split("-");
			splitGfe = new LinkedList<>(Arrays.asList(protoSplitGfe));
			// splitGfe.remove(1);
			// split the first entry of the split GFE to break apart w and 3' UTR
			hlaIdentifier = splitGfe.get(1).split("w");

			// add the w back in (removed when split)
			printToMe.append(String.format("%6s", hlaIdentifier[0] + "w "));

			// for spacing purposes, split off 'HLA-_w', return 3' UTR to array
			splitGfe.set(0, hlaIdentifier[1]);
			splitGfe.remove(1);
			// System.out.println("splitGfe.get(0): " + splitGfe.get(0));
			// System.out.println("splitGfe.get(1): " + splitGfe.get(1));
			// printToMe.append(String.format("%-" + spacingList[n] + "s", hlaIdentifier[1]));
			// n++;

			for (String gfeFeature:splitGfe) {
				
				if((hlaIdentifier[0].compareTo("A") == 0 && n == 0) || (hlaIdentifier[0].compareTo("B") == 0 && n == 0) || (hlaIdentifier[0].compareTo("C") == 0 && n == 0)) {
					printToMe.append(String.format("%-5s", gfeFeature) + " ");
				} else {
					printToMe.append(String.format("%-" + spacingList[n] + "s", gfeFeature) + " ");
				}
				// if(n < (spacingList.length - 1)) 
				// 	{ printToMe.append(" "); }
				n++;
			}
	        printToMe.append(System.lineSeparator());

	        m++;
		}		

		// Footer
		if (m == 0){
			printToMe.append("No results found");
		} else {
			printToMe.append("Total Results: " + m);
		}
	}

	private void printHeader(String locus, int[] spacingList, JTextArea printToMe) {
		int j = 0;

		printToMe.append(String.format("%-25s", "Allele Name"));
		printToMe.append(String.format("%6s", "Locus "));
		if(locus.compareTo("A") == 0 || locus.compareTo("B") == 0 || locus.compareTo("C") == 0)
			printToMe.append(String.format(("%-6s"), "5'"));
		else if (spacingList[j] >= 3)
			printToMe.append(String.format(("%-" + spacingList[j] + "s"), "5'") + " ");
		else
			printToMe.append(String.format(("%-" + spacingList[j] + "s"), "5'"));

		j++;

		for(int i = 1; i < hlaExonTotal.get(locus); i++) {
			printToMe.append(String.format("%-" + spacingList[j] + "s", ("E" + i)) + " ");
			j++;
			printToMe.append(String.format("%-" + spacingList[j] + "s", ("I" + i)) + " ");
			j++;
		}
		
		printToMe.append(String.format("%-" + spacingList[j] + "s", ("E" + hlaExonTotal.get(locus))) + " ");
		printToMe.append(String.format("3'"));
		printToMe.append(System.lineSeparator());
	}
}



