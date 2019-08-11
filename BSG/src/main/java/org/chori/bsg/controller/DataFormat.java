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
    
    public DataFormat()
	{
        this.whichTextArea.put("HLA", B12xGui.resultsTextAreaHla);
		// this.whichTextArea.put("KIR", B12xGui.kirNeo4jResults);
    }
    
    public void csvFormat(String line, String type)
	{
        String[] gfeAllele = line.split(",");
        whichTextArea.get(type).append(gfeAllele[1]+ "," + gfeAllele[0]);
        whichTextArea.get(type).append(System.lineSeparator());
    }
    
    public void tsvFormat(String line, String type)
	{
        String[] gfeAllele = line.split(",");
        whichTextArea.get(type).append(gfeAllele[1]+ "\t" + gfeAllele[0]);
        whichTextArea.get(type).append(System.lineSeparator());
    }
    
 //    public void prettyFormat(String line, String type)
	// {
 //        String[] gfeAllele = line.split(",");
 //        whichTextArea.get(type).append(String.format("%-30s", gfeAllele[0]));
 //        whichTextArea.get(type).append(gfeAllele[1]);
 //        whichTextArea.get(type).append(System.lineSeparator());
 //    }
}