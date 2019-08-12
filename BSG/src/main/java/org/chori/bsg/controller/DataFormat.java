package org.chori.bsg.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JTextArea;

import org.chori.bsg.view.B12xGui;

public class DataFormat 
{
	static HashMap<String, JTextArea> whichTextArea = new HashMap();
    private int gfe = 1;

    public DataFormat()
	{
        this.whichTextArea.put("HLA", B12xGui.resultsTextAreaHla);
		// this.whichTextArea.put("KIR", B12xGui.kirNeo4jResults);
    }
    
    public void csvFormat(String line, String type)
	{
        String[] gfeAllele = line.split(",");

        // which side is the GFE? (Old files use names as key, new ones gfe)
        // we want the side that doesn't contain the asterisk.
        if(gfeAllele[1].contains("*")) { gfe = 0; }

        // print to appropriate screen
        whichTextArea.get(type).append(gfeAllele[1 - gfe]+ "," + gfeAllele[gfe]);
        whichTextArea.get(type).append(System.lineSeparator());
    }
    
    public void tsvFormat(String line, String type)
	{
        String[] gfeAllele = line.split(",");

        // which side is the GFE? (Old files use names as key, new ones gfe)
        // we want the side that doesn't contain the asterisk.
        if(gfeAllele[1].contains("*")) { gfe = 0; }

        // print to appropriate screen
        whichTextArea.get(type).append(gfeAllele[1 - gfe]+ "\t" + gfeAllele[gfe]);
        whichTextArea.get(type).append(System.lineSeparator());
    }
}