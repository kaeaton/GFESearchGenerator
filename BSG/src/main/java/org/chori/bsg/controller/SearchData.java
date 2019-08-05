package org.chori.bsg.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// import org.chori.bsg.model;
import org.chori.bsg.view.*;

public class SearchData {
	public SearchData() {

	}

	public void searchThroughData(File file, String regex, String dataType) throws IOException {
        try {
            
    //         String line,
    //                csvSplitBy = ",",
    //                dataType = B12xGUI.hlaButtonGroupNeo4jOutput
    //                                  .getSelection().getActionCommand(),
    //                timeStamp = LocalDateTime.now().toString();
    //         boolean hlaWriteToFileChecked = prefs.getBoolean("BSG_HLA_SAVE_FILE", false);        
            
    //         System.out.println(locus);
    //         System.out.println("Made it to SearchData: " + regex);
            
    //         // Read the File
    //         BufferedReader br = new BufferedReader(new FileReader(file));
    //         String fileDate = br.readLine();
    //         String version = br.readLine();
            
    //         // Write the data
    //         int i = 0;
    //         while ((line = br.readLine()) != null) {
                
    //             // use comma as separator
    //             String[] gfeAlleles = line.split(csvSplitBy);
                
    //             // Run the GFE portion through the parser
				// Neo4jDataFormat dataFormat = new Neo4jDataFormat();
    //             if (gfeAlleles[0].matches(regex)){
    //                 switch (dataType){
    //                     case "CSV":
    //                         dataFormat.csvFormat(line, "HLA");
    //                         break;
    //                     case "TSV":
    //                         dataFormat.tsvFormat(line, "HLA");
    //                         break;
    //                     case "txt":
    //                         dataFormat.prettyFormat(line, "HLA");
    //                         break;
    //                 }
                    
    //                 i++;
    //             }
    //         }
            
    //         // Footer
    //         if (i == 0){
    //             B12xGUI.neo4jResults.append("No results found");
    //         } else {
    //             B12xGUI.neo4jResults.append("Total Results: " + i);
    //         }

    //         // Close the buffer
    //         br.close();

    //         // Write contents of textbox to file
    //         if(hlaWriteToFileChecked){
    //             WriteFile fileWriter = new WriteFile();
    //             fileWriter.writeToFile(locus, version, "HLA", dataType);
				
            // }
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
}