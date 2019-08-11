package org.chori.bsg.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.chori.bsg.controller.*;
import org.chori.bsg.model.*;
import org.chori.bsg.view.searchboxes.*;


public class SubmitButton { 

	// class instantiations
	private BuildRegex buildRegex = new BuildRegex();
	private BuildReportingSearchString buildRSS = new BuildReportingSearchString();
	private SearchData searchData = new SearchData();
	private Headers header = new Headers();
	private WhereTheDataLives rawData = new WhereTheDataLives();


	// the button
	public JButton submitButton = new JButton("Submit");

	public SubmitButton() {

	}

	public JButton createSubmitButton(String whichTab) {
		System.out.println("Generating the submit button");

		// who is this reset button for?
		switch(whichTab) {
			case "HLA":
				hlaListener();
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return submitButton;
	}

	private void hlaListener() {
		submitButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {

            	// the lists of hla components
            	ArrayList<JTextField> allTextFields = HlaSearchBoxGenerator.allTextboxes;
            	ArrayList<JCheckBox> allCheckBoxes = HlaSearchBoxGenerator.allCheckboxes;

            	// what locus, version, and format?
            	String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
            	String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString();
            	String dataFormat = dataFormatFinder(B12xGui.fileFormatHla);
            	Boolean printToFile = printToFileFinder(B12xGui.fileFormatHla);
            	System.out.println(whatLocus + ", " + whatVersion + ", " + dataFormat + ", " + printToFile);

            	// where's the data file?
            	File data = rawData.getRawData(whatLocus, "3.34.0");
            	// build me some Regex
            	String regex = buildRegex.assembleRegex("HLA", whatLocus, 
            											allCheckBoxes, allTextFields);
            	String reportingSS = buildRSS.assembleReportingSearchString("HLA", whatLocus, 
            											allCheckBoxes, allTextFields);
            	
            	// clear the results window
            	B12xGui.resultsTextAreaHla.setText("");

            	// print headers                        whatVersion
            	header.printHeaders("HLA", reportingSS, "3.34.0", whatLocus);
            	
            	// search the data
            	if (!dataFormat.equals("Pretty")) {
	            	searchData.searchThroughData(data, regex, dataFormat, "HLA");
            	} else {
            		PrettyData prettyData = new PrettyData();
            		prettyData.searchThroughData(data, regex, "HLA");
            	}



            	// dataSearch
            }
        });
	}

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