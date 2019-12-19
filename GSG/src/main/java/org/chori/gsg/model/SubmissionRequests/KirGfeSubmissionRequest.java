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

public class KirGfeSubmissionRequest extends SubmissionRequest implements LocusInterface, TabInterface {

	// class instantiations
	private BuildRegex buildRegex = new BuildRegex();
	private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	// interface data
	private String headerDataSource = HeaderDataSource.KIR.getHeaderDataSource();
	private String httpCallDataSource = HttpCallDataSource.KIR.getHttpCallDataSource();
	private JTextArea textAreaToPrintTo = WhereToPrint.GFE.getWhereToPrint(); 
	private JPanel fileFormatPanel = WhatToPrint.GFE.getWhatToPrint(); 
	
	// data retrieved from GUI
	private String whatLocus = B12xGui.whatLocusGfe.getSelectedItem().toString();
	private String whatVersion = B12xGui.whatVersionGfe.getSelectedItem().toString();
	private String resultsFormat = super.dataFormatFinder(fileFormatPanel);
	private Boolean printToFile = super.printToFileFinder(fileFormatPanel);

	// private ArrayList<JTextField> allTextFields = HlaSearchBoxAssembler.allTextboxes;
	// private ArrayList<JCheckBox> allCheckBoxes = HlaSearchBoxAssembler.allCheckboxes;

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public KirGfeSubmissionRequest() { }

	

}