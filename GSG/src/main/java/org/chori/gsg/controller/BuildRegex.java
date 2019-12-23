package org.chori.gsg.controller;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// import org.chori.bsg.model;
import org.chori.gsg.view.*;

public class BuildRegex {
	public BuildRegex() {

	}

	public String assembleGfeRegex(String whichTab, String whichLocus,
								ArrayList<JCheckBox> checkBoxes, 
								ArrayList<JTextField> textFields) {
		// start the regex
		String regex = new String("^");

		// quick diagnostic test: 
		// are the checkbox and textfield lists the same size?
		if(checkBoxes.size() != textFields.size()) {
			B12xGui.resultsTextAreaGfe.append("There's a problem with the Arraylists in BuildRegex.");
			B12xGui.resultsTextAreaGfe.append(" (They're not the same length.)");
			B12xGui.resultsTextAreaGfe.append(System.lineSeparator());
			System.out.println("There's a problem with the Arraylists in BuildRegex.");
		}

		// add the name
		regex = regex.concat(whichLocus);

		// add the workshop status. Right now it has to be "w".
		regex = regex.concat("w");

		// For each textfield add regex or specified term to currentRegex
		for (int i = 0; i < textFields.size(); i++){

			// textfield empty, no checkbox
			if ((textFields.get(i).getText().isEmpty()) && !(checkBoxes.get(i).isSelected())) {

				// accepts any integer
				regex = regex.concat("(\\d+)-");

			// textfield empty, but checkbox selected
			} else if ((textFields.get(i).getText().isEmpty()) && (checkBoxes.get(i).isSelected())) {

				// either a single digit from 1 - 9 or 2 - 6 total digits that can contain 0
				regex = regex.concat("([1-9]{1}|\\d{2,6})-");

			// whatever is entered in the textfield
			} else {
				regex = regex.concat(textFields.get(i).getText() + "-");
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

	public String assembleNameRegex(String searchString) {
		
		System.out.println("Made it to assembleNameRegex");

		StringBuilder regex = new StringBuilder("^.+");
		String asterisk = "(\\*)";

		// Create a pattern from regex 
		Pattern pattern = Pattern.compile(asterisk);

		// Create a matcher for the input String 
		Matcher matcher = pattern.matcher(searchString);

		if (matcher.lookingAt()) {
			System.out.println("BuildRegex: assembleNameRegex: asterisk found");
			regex.append("\\");
			regex = regex.append(matcher.replaceAll("\\*"));
			System.out.println("Asterisk replacement: " + regex);
		} else {
			regex = regex.append(searchString);
		}

		return regex.toString();
	}
}
