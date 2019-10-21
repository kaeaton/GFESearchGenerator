package org.chori.gsg.controller;

import java.time.LocalDateTime;
import javax.swing.JTextArea;

import org.chori.gsg.view.*;

public class Headers {

	private JTextArea writeToMe = new JTextArea();
	private String database = "";
	private String placeholder = "placeholder";

	public Headers() {

	}

	public void printHeaders(String whichTab, String headerSearchString,
							 String version, String locus, String dataSource) {

		// where are we printing the headers?
		switch(whichTab) {
			case "HLA":
				writeToMe = B12xGui.resultsTextAreaHla;
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

}