package org.chori.gsg.controller;

import java.time.LocalDateTime;
import javax.swing.JTextArea;

import org.chori.gsg.view.*;


/**
 * Prints the headers for the search results
 * 
 * @author Katrina Eaton
 * 
 */

public class Headers {

	private JTextArea writeToMe = new JTextArea();
	private String database = "";
	private String placeholder = "placeholder";

	public Headers() {

	}

	/**
	 * Prints the headers for single version/single locus processes.
	 * 
	 * @param whichTab which tab of the program should they be printed to
	 * @param headerSearchString human-readable version of the search string
	 * @param version what version of the database
	 * @param locus what locus looked at
	 * @param dataSource what database the data is coming from
	 */
	public void printHeaders(String whichTab, String headerSearchString,
							 String version, String locus, String dataSource) {

		// where are we printing the headers?
		switch(whichTab) {
			case "HLA":
				writeToMe = B12xGui.resultsTextAreaHla;
				database = "IMGT/HLA Database ";
				break;
			case "NAME":
				writeToMe = B12xGui.resultsTextAreaName;
				database = "IMGT/HLA Database ";
				break;
			default:
				System.out.println("Haven't set up that tab yet");
		}

		String timeStamp = LocalDateTime.now().toString();

		writeToMe.append("File generated at: " + timeStamp);
		writeToMe.append(System.lineSeparator());
		writeToMe.append("Data source: " + dataSource);
		writeToMe.append(" - " + locus);
		writeToMe.append(System.lineSeparator());
		writeToMe.append("Search parameters: " + headerSearchString);
		writeToMe.append(System.lineSeparator());
		writeToMe.append(database + "Version " + version);
		writeToMe.append(System.lineSeparator());

	}

	/**
	 * Prints the headers for multiple locus search processes.
	 * 
	 * @param whichTab which tab of the program should they be printed to
	 * @param headerSearchString human-readable version of the search string
	 * @param version what version of the database
	 * @param locus1 locus 1 for comparison
	 * @param locus2 locus 2 for comparison
	 * @param dataSource what database the data is coming from
	 */
	public void printComparisonHeaders(String whichTab, String headerSearchString,
							 String version, String locus1, String locus2, String dataSource) {

		// where are we printing the headers?
		switch(whichTab) {
			case "NAME2":
				writeToMe = B12xGui.resultsTextAreaName;
				database = "IMGT/HLA Database ";
				break;
			case "FEATURE":
				writeToMe = B12xGui.resultsTextAreaFeature;
				database = "IMGT/HLA Database ";
				break;
			default:
				System.out.println("Haven't set up that tab yet");
		}

		String timeStamp = LocalDateTime.now().toString();

		writeToMe.append("File generated at: " + timeStamp);
		writeToMe.append(System.lineSeparator());
		writeToMe.append("Data source: " + dataSource);
		writeToMe.append(" - " + locus1 + " & " + locus2);
		writeToMe.append(System.lineSeparator());
		writeToMe.append("Search parameters: " + headerSearchString);
		writeToMe.append(System.lineSeparator());
		writeToMe.append(database + "Version " + version);
		writeToMe.append(System.lineSeparator());

	}

}