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
	private Headers header = new Headers();
	private WhereTheDataLives wtdl = new WhereTheDataLives();

	private HashMap<String, String> dataSources = new HashMap<>();

	private SubmissionRequestFactoryInstance submissionRequestFactoryInstance = SubmissionRequestFactoryInstance.getInstance();
	private SubmissionRequestFactory submissionRequestFactory = SubmissionRequestFactoryInstance.factory;

	// the button
	// public JButton submitButton = new JButton("Submit");

	public SubmitButton() { }

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

	public ActionListener gfeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String loci = getWhichLoci("GFE");
			submissionRequestFactory.assembleSubmissionRequest(loci, "GFE");
		}
	};

	public ActionListener nameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String loci = getWhichLoci("NAME");
			submissionRequestFactory.assembleSubmissionRequest(loci, "NAME");
		}
	};

	public ActionListener featureListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String loci = getWhichLoci("FEATURE");
			submissionRequestFactory.assembleSubmissionRequest(loci, "FEATURE");
		}
	};

	private String getWhichLoci(String whichTab) {
		switch(whichTab) {
			case "GFE":
				return B12xGui.whichLociGfe.getSelectedItem().toString();
				// break;
			case "NAME":
				return B12xGui.whichLociName.getSelectedItem().toString();
				// break;
			case "FEATURE":
				return B12xGui.whichLociFeature.getSelectedItem().toString();
				// break;
			default:
				System.out.println("getWhichLoci in Submit button: Haven't set up that tab yet");
		}
		return null;
	}
}