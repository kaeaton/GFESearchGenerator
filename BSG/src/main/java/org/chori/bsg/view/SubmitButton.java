package org.chori.bsg.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.chori.bsg.controller.*;
import org.chori.bsg.view.*;
import org.chori.bsg.view.searchboxes.*;


public class SubmitButton { 

	// class instantiations
	public BuildRegex buildRegex = new BuildRegex();
	public BuildReportingSearchString buildRSS = new BuildReportingSearchString();
	public SearchData searchData = new SearchData();
	public Headers header = new Headers();

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

            	// build me some Regex
            	String regex = buildRegex.assembleRegex("HLA", whatLocus, 
            											allCheckBoxes, allTextFields);
            	String reportingSS = buildRSS.assembleReportingSearchString("HLA", whatLocus, 
            											allCheckBoxes, allTextFields);
            	
            	// clear the results window
            	B12xGui.resultsTextAreaHla.setText("");
            	
            	// print headers
            	header.printHeaders("HLA", reportingSS, whatVersion, whatLocus);
            	// search the data
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