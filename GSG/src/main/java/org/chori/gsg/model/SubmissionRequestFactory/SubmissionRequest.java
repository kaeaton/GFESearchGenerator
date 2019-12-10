package org.chori.gsg.model.SubmissionRequestFactory;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.SubmissionRequests.*;
import org.chori.gsg.view.*;

public abstract class SubmissionRequest {

	// public String whatTabItsFor;
	// public String whatLocus;
	// public String whatVersion;
	public String resultsFormat;
	public Boolean printToFile;
	public JTextArea textAreaToPrintTo;
	// public String dataSource;

	// private	static HashMap<String, JTextArea> whichTextArea = new HashMap();
	// static {
	// 	whichTextArea.put("HLA", B12xGui.resultsTextAreaHla);
	// 	whichTextArea.put("NAME", B12xGui.resultsTextAreaName);
	// 	whichTextArea.put("FEATURE", B12xGui.resultsTextAreaFeature);
	// }

	// private static HashMap<String, JPanel> whichTabDataFormatPanel = new Hashmap();
	// static {
	// 	whichTabDataFormatPanel.put("HLA", B12xGui.fileFormatHla);
	// 	whichTabDataFormatPanel.put("NAME", B12xGui.fileFormatName);
	// 	whichTabDataFormatPanel.put("FEATURE", B12xGui.fileFormatFeature);
	// }

	// public String getDataSource() {
	// 	return dataSource;
	// }

	public SubmissionRequest() {
		// this.whatTabItsFor = whatTab;
		// this.textAreaToPrintTo = whichTextArea.get(whatTab);

		// assembleData();
	}

	private void assembleData() {
		// this.resultsFormat 
		// 	= dataFormatFinder(whichTabDataFormatPanel.get(whatTab));
		// this.printToFile
		// 	= printToFileFinder(whichTabDataFormatPanel.get(whatTab));
	}

	private String dataFormatFinder(JPanel fileFormatPanel){
		// for (Component component : ((JPanel)fileFormatPanel).getComponents()) {
		// 	if (component instanceof JRadioButton){
		// 		if (((JRadioButton)component).isSelected()) {
		// 			return ((JRadioButton)component).getText();
		// 		}
		// 	}
		// }
		System.out.println("Didn't find a Data format");
		return null;
	}

	private Boolean printToFileFinder(JPanel fileFormatPanel){
		// for (Component component : ((JPanel)fileFormatPanel).getComponents()) {
		// 	if (component instanceof JCheckBox){
		// 		return ((JCheckBox)component).isSelected();
		// 	}
		// }
		return false;
	}
}