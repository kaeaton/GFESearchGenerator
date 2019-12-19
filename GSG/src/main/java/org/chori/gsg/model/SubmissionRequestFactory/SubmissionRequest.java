package org.chori.gsg.model.submissionRequestFactory;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.submissionRequests.*;
import org.chori.gsg.view.*;

public abstract class SubmissionRequest {

	// public String whatTabItsFor;
	public String whatLocus;
	public String whatVersion;
	public String resultsFormat;
	public Boolean printToFile;
	public JTextArea textAreaToPrintTo;
	public JPanel fileFormatPanel;
	private String headerDataSource;

	public SubmissionRequest() { }

	private void assembleData() {
		// this.resultsFormat 
		// 	= dataFormatFinder(whichTabDataFormatPanel.get(whatTab));
		// this.printToFile
		// 	= printToFileFinder(whichTabDataFormatPanel.get(whatTab));
	}

	public String dataFormatFinder(JPanel fileFormatPanel){
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

	public Boolean printToFileFinder(JPanel fileFormatPanel){
		for (Component component : ((JPanel)fileFormatPanel).getComponents()) {
			if (component instanceof JCheckBox){
				return ((JCheckBox)component).isSelected();
			}
		}
		return false;
	}
}