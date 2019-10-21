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
import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.*;


public class SubmitButton { 

	// // class instantiations
	private BuildRegex buildRegex = new BuildRegex();
	private BuildHeaderSearchString buildHSS = new BuildHeaderSearchString();
	private Headers header = new Headers();
	private WhereTheDataLives rawData = new WhereTheDataLives();

    private HashMap<String, String> dataSources = new HashMap<>();


	// the button
	// public JButton submitButton = new JButton("Submit");

	public SubmitButton() {
        dataSources.put("neo4j", "http://neo4j.b12x.org");	
    }

	public JButton createSubmitButton(String whichTab) {
		System.out.println("Generating the submit button");
        JButton submitButton = new JButton("Submit");

		// who is this reset button for?
		switch(whichTab) {
			case "HLA":
				hlaListener(submitButton);
				break;
            case "NAME":
                nameListener(submitButton);
                break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return submitButton;
	}

	private void hlaListener(JButton subButton) {
		subButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
                Runnable submit = new Runnable() {
                    public void run() {

                    	// the lists of hla components
                    	ArrayList<JTextField> allTextFields = HlaSearchBoxGenerator.allTextboxes;
                    	ArrayList<JCheckBox> allCheckBoxes = HlaSearchBoxGenerator.allCheckboxes;

                    	// what locus, version, and format?
                    	String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
                    	String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString(); //"3.34.0"; // 
                    	String dataFormat = dataFormatFinder(B12xGui.fileFormatHla);
                    	Boolean printToFile = printToFileFinder(B12xGui.fileFormatHla);
                    	System.out.println(whatLocus + ", " + whatVersion + ", " + dataFormat + ", " + printToFile);

                    	// where's the data file?                 
                    	File data = rawData.getRawData(whatLocus, whatVersion);

                    	// build me some Regex
                    	String regex = buildRegex.assembleRegex("HLA", whatLocus, 
                    											allCheckBoxes, allTextFields);
                    	String headerSS = buildHSS.assembleHeaderSearchString("HLA", whatLocus, 
                    											allCheckBoxes, allTextFields);

                        // clear results screen
                        B12xGui.resultsTextAreaHla.setText("");

                    	// print headers
                    	header.printHeaders("HLA", headerSS, whatVersion, whatLocus, dataSources.get("neo4j"));
                    	
                    	// search the data & print to screen
                    	if (!dataFormat.equals("Pretty")) {
                            SearchData searchData = new SearchData();
        	            	searchData.searchThroughData(data, regex, dataFormat, "HLA");
                    	} else {
                    		PrettyData prettyData = new PrettyData();
                    		prettyData.searchThroughData(data, regex, "HLA");
                    	}
                    }
                };
                new Thread(submit).start();

            }
        });
	}

    private void nameListener(JButton subButton) {
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Runnable submit = new Runnable() {
                    public void run() {




                    }
                };
                new Thread(submit).start();
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