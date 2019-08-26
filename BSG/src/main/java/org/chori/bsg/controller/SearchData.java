package org.chori.bsg.controller;

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
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.bsg.model.*;
import org.chori.bsg.view.*;

public class SearchData {

	public SearchData() {

	}

	public void searchThroughData(File file, String regex, String dataType, String whichTab) {

        try {
            String line,
                   // dataType = B12xGUI.hlaButtonGroupNeo4jOutput
                                     // .getSelection().getActionCommand(),
                   timeStamp = LocalDateTime.now().toString();
            HashMap<String, String> unsortedData = new HashMap();
            LinkedHashMap<String, String> sortedDataMatches = new LinkedHashMap();
            // boolean hlaWriteToFileChecked = prefs.getBoolean("BSG_HLA_SAVE_FILE", false);        
            
            System.out.println("Made it to SearchData: " + regex);
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileDate = br.readLine();
            String version = br.readLine();

            // the first dataline to check which side is GFE
            String[] gfeAlleles = br.readLine().split(",");

            // which side is the GFE? (Old files use names as key, new ones gfe)
            // we want the side that doesn't contain the asterisk.
            int gfe = 1;
            if(gfeAlleles[1].contains("*")) { gfe = 0; }

            int i = 0;
            if (gfeAlleles[gfe].matches(regex)){
                    
                // if the first data line matches the regex, add to hashmap
                unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
                i++;
            }

            
            // screen the rest of the data
            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                gfeAlleles = line.split(",");
                if (gfeAlleles[gfe].matches(regex)){
                    
                    unsortedData.put(gfeAlleles[gfe], gfeAlleles[1 - gfe]);
                    i++;
                }
            }

            // sort the data: passed on with GFE as key
            SortData sorting = new SortData();
            sortedDataMatches = sorting.sortTheData(unsortedData);
                
    //         for (Map.Entry me:sortedDataMatches.entrySet()) {
    // // need to rethink this logic:
    // // originally passing a csv line to dataformat
    // // now have a hashmap. How do I pass the data?
    //             // Run the GFE portion through the parser
    //             DataFormat dataFormat = new DataFormat();
    //             switch (dataType){
    //                 case "CSV":
    //                     dataFormat.csvFormat(line, whichTab);
    //                     break;
    //                 case "TSV":
    //                     dataFormat.tsvFormat(line, whichTab);
    //                     break;
    //                 default:
    //                     System.out.println("SearchData is looking for a format that isn't listed.");
    //             }
    //         }

            // Footer
            if (i == 0){
                B12xGui.resultsTextAreaHla.append("No results found");
            } else {
                B12xGui.resultsTextAreaHla.append("Total Results: " + i);
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
        }
    }
}