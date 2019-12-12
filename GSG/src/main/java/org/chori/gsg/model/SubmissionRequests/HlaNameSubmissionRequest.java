package org.chori.gsg.model.SubmissionRequests;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.SubmissionRequestFactory.*;
import org.chori.gsg.view.*;

public class HlaNameSubmissionRequest extends SubmissionRequest{

	// // class instantiations
	// private BuildRegex buildRegex = new BuildRegex();
	// private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	// private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	public String dataSource = DataSource.HLA.getDataSource();
	// private String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
	// private String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString();

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public HlaNameSubmissionRequest() { }

	
}