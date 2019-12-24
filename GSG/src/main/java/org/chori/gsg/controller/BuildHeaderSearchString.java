package org.chori.gsg.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.view.*;

public class BuildHeaderSearchString {

	// this creates a text version of the search string to be included in the
	// results so users can see what they were looking for/at.
	// * is a wildcard, x is a wildcard without 0.
	public BuildHeaderSearchString() { }

	public String assembleGfeHeaderSearchString(String whichTab, String whichLocus,
								ArrayList<JCheckBox> checkBoxes, 
								ArrayList<JTextField> textFields) {
		String searchString = new String("");

		// add the name
		searchString = searchString.concat(whichLocus);

		// add the workshop status. Right now it has to be "w".
        searchString = searchString.concat("w");

		// For each textfield add searchString or specified term to currentsearchString
        for (int i = 0; i < textFields.size(); i++){

            if ((textFields.get(i).getText().isEmpty()) && !(checkBoxes.get(i).isSelected())) {
                searchString = searchString.concat("*-");
            } else if ((textFields.get(i).getText().isEmpty()) && (checkBoxes.get(i).isSelected())) {
				searchString = searchString.concat("x-");
            } else {
                searchString = searchString.concat(textFields.get(i).getText() + "-");
            }
        }

        // Check for extraneous dash at the end and close regex
        if (searchString.matches("^.+-$")){
            searchString = searchString.substring(0, (searchString.length() - 1));
        }

		System.out.println(whichTab + " searchString: " + searchString);
		return searchString;
	}
}










