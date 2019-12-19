package org.chori.gsg.model.submissionRequests;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.submissionRequestFactory.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.searchboxes.*;

public class HlaGfeSubmissionRequest extends SubmissionRequest implements LocusInterface, TabInterface {

	// class instantiations
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

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public HlaGfeSubmissionRequest() {
		// data retrieved from GUI
		this.whatLocus = B12xGui.whatLocusGfe.getSelectedItem().toString();
		this.whatVersion = B12xGui.whatVersionGfe.getSelectedItem().toString();
		this.resultsFormat = super.dataFormatFinder(fileFormatPanel);
		this.printToFile = super.printToFileFinder(fileFormatPanel);
		
		this.allTextFields = HlaSearchBoxAssembler.allTextboxes;
		this.allCheckBoxes = HlaSearchBoxAssembler.allCheckboxes;

		submitData();
	}

	private void submitData() {
		// new Thread(submit).start();

	}
	

}