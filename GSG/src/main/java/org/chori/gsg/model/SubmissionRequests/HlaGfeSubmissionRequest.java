package org.chori.gsg.model.submissionRequests;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.submissionRequestFactory.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.gfeSearchPanels.*;

public class HlaGfeSubmissionRequest extends SubmissionRequest implements LocusInterface, TabInterface {

	// class instantiations
	private Headers header = new Headers();
	private BuildRegex buildRegex = new BuildRegex();
	private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	// interface data
	private String headerDataSource = HeaderDataSource.HLA.getHeaderDataSource();
	private String httpCallDataSource = HttpCallDataSource.HLA.getHttpCallDataSource();
	private JTextArea textAreaToPrintTo = WhereToPrint.GFE.getWhereToPrint(); 
	private JPanel fileFormatPanel = WhatToPrint.GFE.getWhatToPrint(); 

	private String whatLocus;
	private String whatVersion;
	private String resultsFormat;
	private Boolean printToFile;
	
	private ArrayList<JTextField> allTextFields;
	private ArrayList<JCheckBox> allCheckBoxes;

	private String searchRegex;
	private String headerSearchString;
	private File rawData;

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public HlaGfeSubmissionRequest() {
		// data retrieved from GUI
		this.whatLocus = B12xGui.whatLocusGfe.getSelectedItem().toString();
		this.whatVersion = B12xGui.whatVersionGfe.getSelectedItem().toString();
		this.resultsFormat = super.dataFormatFinder(fileFormatPanel);
		this.printToFile = super.printToFileFinder(fileFormatPanel);
		this.rawData = whereTheDataLives.getRawHlaData(whatLocus, whatVersion);
		
		this.allTextFields = HlaSearchPanelAssembler.allTextboxes;
		this.allCheckBoxes = HlaSearchPanelAssembler.allCheckboxes;

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
		this.searchRegex = buildRegex.assembleGfeRegex("HLA", whatLocus, allCheckBoxes, allTextFields);
		this.headerSearchString = buildHeaderSearchString.assembleGfeHeaderSearchString("HLA", whatLocus, 
																			allCheckBoxes, allTextFields);
	}

	private void printTheHeaders() {
		textAreaToPrintTo.setText("");

		header.printHeaders("GFE", headerSearchString, whatVersion, whatLocus, headerDataSource);
	}

	private void searchTheData() {
		if (resultsFormat.equals("Pretty")) {
			PrettyData prettyData = new PrettyData();
			prettyData.searchThroughData(rawData, searchRegex, "GFE");
		} else {
			SearchData searchData = new SearchData();
			searchData.searchThroughData(rawData, searchRegex, resultsFormat, "GFE");
		}
	}
	
	private void saveResultsToFile() {
		WriteToFile writeToFile = new WriteToFile();
		writeToFile.writeFile(whatLocus, whatVersion, "GFE", resultsFormat);
	}
}