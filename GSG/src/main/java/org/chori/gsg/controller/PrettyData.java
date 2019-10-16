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
import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

public class PrettyData {

	// Exons per locus
	private static HashMap<String, Integer> hlaExonTotal = new HashMap();
	private	static HashMap<String, JTextArea> whichTextArea = new HashMap();
	private HashMap<String, String> dataMatches = new HashMap();
	private LinkedHashMap<String, String> sortedDataMatches = new LinkedHashMap();

	public PrettyData() {
		whichTextArea.put("HLA", B12xGui.resultsTextAreaHla);
		whichTextArea.put("NAME", B12xGui.resultsTextAreaName);

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
		try {
			
			String line;
			int[] spacingList = new int[18];
			String[] hlaIdentifier = new String[2];
			JTextArea printToMe = whichTextArea.get(whichTab);

			System.out.println("Made it to SearchData: " + regex);
			
			// Read the File
			BufferedReader br = new BufferedReader(new FileReader(file));
			String fileDate = br.readLine();
			String version = br.readLine();
			String firstDataLine = br.readLine();

			// use comma as separator
			String[] gfeAlleles = firstDataLine.split(",");

			// which side is the GFE? (Old files use names as key, new ones gfe)
			// we want the side that doesn't contain the asterisk.
			int gfe = 1;
			if(gfeAlleles[1].contains("*")) { gfe = 0; }
			System.out.println(gfeAlleles[gfe]);

			// flag to know if the first line was a match
			boolean flag = false;

			// Run the GFE portion past the regex
			if (gfeAlleles[gfe].matches(regex)){

				// put gfe and name in holding hashmap for later printing
				dataMatches.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);

				flag = true;
			}
				
			// split the gfe along the dashes
			String[] protoSplitGfe = gfeAlleles[gfe].split("-");
			spacingList = new int[protoSplitGfe.length - 1];

			// split the first entry of the split GFE to break apart w and 3' UTR
			hlaIdentifier = protoSplitGfe[1].split("w");

			// for spacing purposes, split off 'HLA-_w', return 3' UTR to array
			protoSplitGfe[0] = hlaIdentifier[1];
			// System.out.println(splitGfe[0]);
			// System.out.println(hlaIdentifier[1]);
			// System.out.println(splitGfe[1]);

			// copy to new arrayList to remove protoSplitGfe[1]
			LinkedList<String> splitGfe = new LinkedList<>(Arrays.asList(protoSplitGfe));
			splitGfe.remove(1);

			System.out.println(splitGfe.get(1));


			int j = 0;
			// cycle through the separated GFE. If the original GFE was a match 
			// use value.length in spacingList, otherwise use 3 to populate it.
			for(String splitUpGfe:splitGfe) {

				spacingList[j] = 3;

				if (flag && splitUpGfe.length() >= 3) 
					spacingList[j] = (splitUpGfe.length() + 1);
				
				j++;
			}

			
			// parse the gfe spacing for each matching line
			while ((line = br.readLine()) != null) {

				// use comma as separator
				gfeAlleles = line.split(",");

				// Run the GFE portion through the parser
				if (gfeAlleles[gfe].matches(regex)){
					
					// put gfe and name in holding hashmap for later printing
					dataMatches.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);

					// split the gfe along the dashes
					protoSplitGfe = gfeAlleles[gfe].split("-");
					// copy to new arrayList to remove protoSplitGfe[1]
					splitGfe = new LinkedList<>(Arrays.asList(protoSplitGfe));
					splitGfe.remove(1);

					int k = 0;

					// if a feature String is longer than the length listed 
					// in the spacing array, replace that length with the new one
					for(String splitUpGfe:splitGfe) {
						if (splitUpGfe.length() > spacingList[k]) {
							spacingList[k] = splitUpGfe.length();
						}

						k++;
					}
				}
			}

			// sort the dataMatches hashmap before printing
			SortData sorting = new SortData();
			sortedDataMatches = sorting.sortTheData(dataMatches);

			// Pretty data header
			printHeader(hlaIdentifier[0], spacingList, printToMe);

	// need to figure out spacing for header and assorted breakdown.
	// maybe put into linked array list and insert as needed?


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
				// splitGfe.remove(2);

				// printToMe.append(String.format("%-" + spacingList[n] + "s", hlaIdentifier[1]));
				// n++;

				for (String gfeFeature:splitGfe) {
					
					printToMe.append(String.format("%-" + spacingList[n] + "s", gfeFeature) + " ");
					// if(n < (spacingList.length - 1)) 
					// 	{ printToMe.append(" "); }
					n++;
				}
		        printToMe.append(System.lineSeparator());

		        m++;
			}		

			// Footer
			if (m == 0){
				B12xGui.resultsTextAreaHla.append("No results found");
			} else {
				B12xGui.resultsTextAreaHla.append("Total Results: " + m);
			}

			// Close the buffer
			br.close();

			// Write contents of textbox to file
			// if(hlaWriteToFileChecked){
			//     WriteFile fileWriter = new WriteFile();
			//     fileWriter.writeToFile(locus, version, "HLA", dataType);
				
			// }
		} catch (Exception ex) {
			System.out.println(ex); 
			ex.printStackTrace();
		}
	}

	private void printHeader(String locus, int[] spacingList, JTextArea printToMe) {
		int j = 1;

		printToMe.append(String.format("%-25s", "Allele Name"));
		printToMe.append(String.format("%6s", "Locus "));
		printToMe.append(String.format(("%-" + spacingList[j] + "s"), "5'") + " ");
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