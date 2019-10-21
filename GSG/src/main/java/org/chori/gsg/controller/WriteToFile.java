package org.chori.gsg.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import javax.swing.JTextArea;

import org.chori.gsg.model.*;
import org.chori.gsg.view.*;

public class WriteToFile {

	private HashMap<String, String> fileSuffix = new HashMap<>();
	private HashMap <String, JTextArea> whichTextArea = new HashMap<>();
	private WhereTheDataLives wtdl = new WhereTheDataLives();
	
	public WriteToFile () {
        whichTextArea.put("HLA", B12xGui.resultsTextAreaHla);
		// whichTextArea.put("KIR", B12xGui.kirNeo4jResults);
		whichTextArea.put("NAME", B12xGui.resultsTextAreaName);

		fileSuffix.put("CSV", ".csv");
		fileSuffix.put("TSV", ".tsv");
		fileSuffix.put("Pretty", ".txt");
    }
    
    private String fileName(String locus, String version, String fileType) 
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss");

        LocalDate dateStamp = LocalDate.now();
        String timeStamp = LocalTime.now().format(dtf).toString();
        String fileName = wtdl.getResultsDataPath()
                + System.getProperty("file.separator") + locus
                + "_" + version
                + "_" + dateStamp + "_" + timeStamp
                + fileSuffix.get(fileType);
        
        System.out.println("Write to file's file path/name: " + fileName);
        
        return fileName;
    }
    
    public void writeFile(String locus, String version, String whichTab, String fileType)
    {
    	System.out.println("Made it to the HLA write to file");
        String text = (whichTextArea.get(whichTab)).getText();
        
        try {
            File destinationFile = new File(fileName(locus, version, fileType));
			System.out.println("Created new file name");
			
            // if file doesnt exists, then create it
            if (!destinationFile.exists()) {
                destinationFile.createNewFile();
            }
			System.out.println("created file");			

            FileWriter fw = new FileWriter(destinationFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(text);
            bw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}