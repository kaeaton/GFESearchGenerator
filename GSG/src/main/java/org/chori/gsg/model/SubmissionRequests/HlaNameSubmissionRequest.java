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

public class HlaNameSubmissionRequest extends SubmissionRequest implements HlaDataSources {

	// // class instantiations
	// private BuildRegex buildRegex = new BuildRegex();
	// private WhereTheDataLives whereTheDataLives = new WhereTheDataLives();
	// private BuildHeaderSearchString buildHeaderSearchString = new BuildHeaderSearchString();
	
	// // private String dataSource = "http://neo4j.b12x.org";
	// private String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
	// private String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString();

	// private String jsonRegexRequest = "";
	// private String humanReadableSearchString = "";

	public HlaNameSubmissionRequest() { }

	public String getDataSource() {
		return dataSource;
	}
}