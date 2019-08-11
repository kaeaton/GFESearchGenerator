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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                   csvSplitBy = ",",
                   // dataType = B12xGUI.hlaButtonGroupNeo4jOutput
                                     // .getSelection().getActionCommand(),
                   timeStamp = LocalDateTime.now().toString();
            // boolean hlaWriteToFileChecked = prefs.getBoolean("BSG_HLA_SAVE_FILE", false);        
            
            System.out.println("Made it to SearchData: " + regex);
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileDate = br.readLine();
            String version = br.readLine();
            
            // Write the data
            int i = 0;
            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                
                // Run the GFE portion through the parser
				DataFormat dataFormat = new DataFormat();
                if (gfeAlleles[1].matches(regex)){
                    switch (dataType){
                        case "CSV":
                            dataFormat.csvFormat(line, whichTab);
                            break;
                        case "TSV":
                            dataFormat.tsvFormat(line, whichTab);
                            break;
                        default:
                        	System.out.println("SearchData is looking for a format that isn't listed.");
                    }
                    
                    i++;
                }
            }
            
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