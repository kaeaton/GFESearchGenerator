package org.chori.gsg.model.submissionRequests;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.submissionRequestFactory.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.*;

public class KirFeatureSubmissionRequest extends SubmissionRequest implements LocusInterface, TabInterface {

	// // class instantiations
	// private BuildRegex buildRegex = new BuildRegex();
	// private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	// private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	private String headerDataSource = HeaderDataSource.KIR.getHeaderDataSource();
	private JTextArea textAreaToPrintTo = WhereToPrint.FEATURE.getWhereToPrint(); 
	// private String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
	// private String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString();

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public KirFeatureSubmissionRequest() { }

	
	
}