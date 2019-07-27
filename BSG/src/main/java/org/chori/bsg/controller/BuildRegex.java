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

	public String assembleRegex(String whichTab, 
								ArrayList<JCheckBox> checkBoxes, 
								ArrayList<JTextField> textFields) {
		String regex = new String("^");
		// ArrayList<JCheckBox> checkBoxes = checkboxes;
		// ArrayList<JTextField> textFields = textfields;
		if(checkBoxes.size() == textFields.size()) {
			B12xGui.resultsTextAreaHla.append("There's a problem with the Arraylists in BuildRegex.");
			System.out.println("There's a problem with the Arraylists in BuildRegex.");
		}





		System.out.println(whichTab + " regex: " + regex);
		return regex;
	}
}










