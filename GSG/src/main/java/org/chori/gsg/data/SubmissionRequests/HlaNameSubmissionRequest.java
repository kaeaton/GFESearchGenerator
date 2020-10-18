package org.chori.gsg.data.submissionRequests;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.data.*;
import org.chori.gsg.data.submissionRequestFactory.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;

public class HlaNameSubmissionRequest extends SubmissionRequest implements LocusInterface, TabInterface {

	// class instantiations
	private Headers header = new Headers();
	private BuildRegex buildRegex = new BuildRegex();
	private FileUtilities fileUtilities = new FileUtilities();
	// private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	// interface data
	private String headerDataSource = HeaderDataSource.HLA.getHeaderDataSource();
	private String httpCallDataSource = HttpCallDataSource.HLA.getHttpCallDataSource();
	private JTextArea textAreaToPrintTo = WhereToPrint.NAME.getWhereToPrint(); 
	private JPanel fileFormatPanel = WhatToPrint.NAME.getWhatToPrint(); 

	private String whatLocus;
	private String whatVersion;
	private String resultsFormat;
	private Boolean printToFile;
	
	private String searchRegex;
	private String headerSearchString;
	private File rawData;

	public HlaNameSubmissionRequest() {
		
		// data retrieved from GUI
		this.whatLocus = GSG.whatLocusName.getSelectedItem().toString();
		this.whatVersion = GSG.whatVersionName.getSelectedItem().toString();
		this.resultsFormat = super.dataFormatFinder(fileFormatPanel);
		this.printToFile = super.printToFileFinder(fileFormatPanel);
		this.rawData = fileUtilities.getTheRawDataFile(whatLocus, whatVersion, "HLA");
		
		submitRequest();
	}

	private void submitRequest() {
		Runnable submit = new Runnable() {
			public void run() {
				createRegexStrings();
				printTheHeaders();
				searchTheData();

				if(printToFile)
					saveResultsToFile();
			}
		};

		new Thread(submit).start();
	}

	private void createRegexStrings() {
		this.headerSearchString = GSG.nameSearchBox.getText();
		this.searchRegex = buildRegex.assembleNameRegex(headerSearchString);
	}

	private void printTheHeaders() {
		// clear the prior results
		textAreaToPrintTo.setText("");

		header.printHeaders("NAME", headerSearchString, whatVersion, whatLocus, headerDataSource);
	}

	private void searchTheData() {
		if (resultsFormat.equals("Pretty")) {
			PrettyData prettyData = new PrettyData();
			prettyData.searchThroughData(rawData, searchRegex, "NAME");
		} else {
			SearchData searchData = new SearchData();
			searchData.searchThroughData(rawData, searchRegex, resultsFormat, "NAME");
		}
	}
	
	private void saveResultsToFile() {
		WriteToFile writeToFile = new WriteToFile();
		writeToFile.writeFile(whatLocus, whatVersion, "NAME", resultsFormat);
	}
}