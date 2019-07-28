package org.chori.bsg.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// import org.chori.bsg.model;
import org.chori.bsg.view.*;

public class BuildRegex {
	public BuildRegex() {

	}

	public String assembleRegex(String whichTab, String whichLocus,
								ArrayList<JCheckBox> checkBoxes, 
								ArrayList<JTextField> textFields) {
		String regex = new String("^");

		// quick diagnostic test: are the lists the same size?
		if(checkBoxes.size() != textFields.size()) {
			B12xGui.resultsTextAreaHla.append("There's a problem with the Arraylists in BuildRegex.");
			B12xGui.resultsTextAreaHla.append(" (They're not the same length.)");
			B12xGui.resultsTextAreaHla.append(System.lineSeparator());
			System.out.println("There's a problem with the Arraylists in BuildRegex.");
		}

		// add the name
		regex = regex.concat(whichLocus);

		// add the workshop status. Right now it has to be "w".
        if (textFields.get(0).getText().isEmpty()){
            regex = regex.concat("w");
            // searchString = searchString.concat("w");}
        } else {
            regex = regex.concat(textFields.get(0).getText());
        }

		// For each textfield add regex or specified term to currentRegex
        for (int i = 1; i < textFields.size(); i++){

            if ((textFields.get(i).getText().isEmpty()) && !(checkBoxes.get(i).isSelected())) {
                regex = regex.concat("(\\d+)-");
                // searchString = searchString.concat( "*-");
            } else if ((textFields.get(i).getText().isEmpty()) && (checkBoxes.get(i).isSelected())) {
				regex = regex.concat("([1-9]{1}|\\d{2,6})-");
            } else {
                regex = regex.concat(textFields.get(i).getText() + "-");
                // searchString = searchString.concat(typeFields.get(i).getText() + "-");
            }
        }

        // Check for extraneous dash at the end and close regex
        if (regex.matches("^.+-$")){
            regex = regex.substring(0, (regex.length() - 1)).concat("$");
        } else {
            regex = regex.concat("$");
        }

		System.out.println(whichTab + " regex: " + regex);
		return regex;
	}
}










