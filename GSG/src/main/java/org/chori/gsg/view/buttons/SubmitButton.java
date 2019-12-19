package org.chori.gsg.view.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.submissionRequestFactory.*;
import org.chori.gsg.view.*;
import org.chori.gsg.view.searchboxes.*;

/**
 * Generates the assorted search buttons and associated listeners
 * 
 * @author Katrina Eaton
 * 
 */

public class SubmitButton { 

	// class instantiations
	private BuildRegex buildRegex = new BuildRegex();
	private BuildHeaderSearchString buildHSS = new BuildHeaderSearchString();
	// private GfeSubmitListener gfeSubmitListener = new GfeSubmitListener();
	private Headers header = new Headers();
	private WhereTheDataLives wtdl = new WhereTheDataLives();

	private HashMap<String, String> dataSources = new HashMap<>();

	private SubmissionRequestFactoryInstance submissionRequestFactoryInstance = SubmissionRequestFactoryInstance.getInstance();
	private SubmissionRequestFactory submissionRequestFactory = SubmissionRequestFactoryInstance.factory;

	// the button
	// public JButton submitButton = new JButton("Submit");

	public SubmitButton() {
		dataSources.put("neo4j", "http://neo4j.b12x.org");	
	}


	/**
	 * creates a submit button
	 * 
	 * @param whichTab which listener is associated with the submit button
	 * @return a JButton with an associated listener
	 */
	public JButton createSubmitButton(String whichTab) {
		System.out.println("Generating the submit button");
		JButton submitButton = new JButton("Submit");

		// who is this reset button for?
		switch(whichTab) {
			case "GFE":
				submitButton.addActionListener(gfeListener);
				break;
			case "NAME":
				submitButton.addActionListener(nameListener);
				break;
			case "FEATURE":
				submitButton.addActionListener(featureListener);
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return submitButton;
	}

	/**
	 * Runs in a separate thread. It gathers information from assorted points in the GUI and passes it to controller methods.
	 */
	public ActionListener gfeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			submissionRequestFactory.assembleSubmissionRequest("HLA", "GFE");
		}
	};

	/**
	 * Runs in a separate thread. It gathers information from assorted points in the GUI and passes it to controller methods.
	 */
	public ActionListener nameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			Runnable submit = new Runnable() {
				public void run() {

					// what locus, version, and format?
					String whatLocus = B12xGui.whatLocusName.getSelectedItem().toString();
					String whatVersion = B12xGui.whatVersionName.getSelectedItem().toString(); //"3.34.0"; // 
					String dataFormat = dataFormatFinder(B12xGui.fileFormatName);
					Boolean printToFile = printToFileFinder(B12xGui.fileFormatName);
					System.out.println(whatLocus + ", " + whatVersion + ", " + dataFormat + ", " + printToFile);

					// where's the data file?                 
					File data = wtdl.getRawHlaData(whatLocus, whatVersion);

					// build me some Regex
					String searchTerm = B12xGui.nameSearchBox.getText();
					
					System.out.println("Name Search Term: " + searchTerm);
					String regex = buildRegex.assembleNameRegex(searchTerm);
					
					// clear results screen
					B12xGui.resultsTextAreaName.setText("");

					// print headers
					header.printHeaders("NAME", searchTerm, whatVersion, whatLocus, dataSources.get("neo4j"));
					
					// search the data & print to screen
					if (dataFormat.equals("Pretty")) {
						PrettyData prettyData = new PrettyData();
						prettyData.searchThroughData(data, regex, "NAME");
					} else {
						SearchData searchData = new SearchData();
						searchData.searchThroughData(data, regex, dataFormat, "NAME");
					}

					if (printToFile) {
						WriteToFile writeToFile = new WriteToFile();
						writeToFile.writeFile(whatLocus, whatVersion, "NAME", dataFormat);
					}

				}
			};
			new Thread(submit).start();
		}
	};

	/**
	 * Runs in a separate thread. It gathers information from assorted points in the GUI and passes it to controller methods.
	 */
	public ActionListener featureListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			Runnable submit = new Runnable() {
				public void run() {

					// what locus, version, and format?
					String whatLocus1 = B12xGui.whatLocus1Feature.getSelectedItem().toString();
					String whatLocus2 = B12xGui.whatLocus2Feature.getSelectedItem().toString();
					String whatVersion1 = B12xGui.whatVersion1Feature.getSelectedItem().toString(); //"3.34.0"; // 
					String whatVersion2 = B12xGui.whatVersion2Feature.getSelectedItem().toString(); //"3.34.0"; // 
					String dataFormat = dataFormatFinder(B12xGui.fileFormatFeature);
					Boolean printToFile = printToFileFinder(B12xGui.fileFormatFeature);
					System.out.println(whatLocus1 + ", " + whatVersion1 + ", " 
										+ whatLocus2 + ", " + whatVersion2 + ", "
										+ dataFormat + ", " + printToFile);

					// where's the data file?                 
					// File data = wtdl.getRawData(whatLocus, whatVersion);

					// build me some Regex
					// String regex = buildRegex.assembleHlaGfeRegex("HLA", whatLocus, 
					// 										allCheckBoxes, allTextFields);
					// String headerSS = buildHSS.assembleHlaHeaderSearchString("HLA", whatLocus, 
					// 										allCheckBoxes, allTextFields);

					// clear results screen
					B12xGui.resultsTextAreaFeature.setText("");

					// print headers
					header.printComparisonHeaders("FEATURE", " ", whatVersion1, whatVersion2, 
									whatLocus1, whatLocus2, dataSources.get("neo4j"));
					
					// search the data & print to screen
					// if (dataFormat.equals("Pretty")) {
					// 	PrettyData prettyData = new PrettyData();
					// 	prettyData.searchThroughData(data, regex, "HLA");
					// } else {
					// 	SearchData searchData = new SearchData();
					// 	searchData.searchThroughData(data, regex, dataFormat, "HLA");
					// }

					if (printToFile) {
						// WriteToFile writeToFile = new WriteToFile();
						// writeToFile.writeFile(whatLocus, whatVersion, "HLA", dataFormat);
					}
				}
			};
			new Thread(submit).start();

		}
	};

	private String dataFormatFinder(JPanel fileFormatPanel){
		for (Component component : ((JPanel)fileFormatPanel).getComponents()) {
			if (component instanceof JRadioButton){
				if (((JRadioButton)component).isSelected()) {
					return ((JRadioButton)component).getText();
				}
			}
		}
		System.out.println("Didn't find a Data format");
		return null;
	}

	private Boolean printToFileFinder(JPanel fileFormatPanel){
		for (Component component : ((JPanel)fileFormatPanel).getComponents()) {
			if (component instanceof JCheckBox){
				return ((JCheckBox)component).isSelected();
			}
		}
		return false;
	}
}