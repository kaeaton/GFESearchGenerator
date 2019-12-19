package org.chori.gsg.model.SubmissionRequestFactory;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.chori.gsg.model.SubmissionRequests.*;
import org.chori.gsg.view.B12xGui;

public interface TabInterface {

	public enum WhereToPrint {
		GFE(B12xGui.resultsTextAreaGfe),
		NAME(B12xGui.resultsTextAreaName),
 		FEATURE(B12xGui.resultsTextAreaFeature);

		private final JTextArea whereToPrint;

		private WhereToPrint(JTextArea whereToPrint) {
			this.whereToPrint = whereToPrint;
		}
    
		public JTextArea getWhereToPrint() {
			return this.whereToPrint;
		}
	}

	public enum WhatToPrint {
		GFE(B12xGui.fileFormatGfe),
		NAME(B12xGui.fileFormatName),
 		FEATURE(B12xGui.fileFormatFeature);

		private final JPanel whatToPrint;

		private WhatToPrint(JPanel whatToPrint) {
			this.whatToPrint = whatToPrint;
		}
    
		public JPanel getWhatToPrint() {
			return this.whatToPrint;
		}
	}
}