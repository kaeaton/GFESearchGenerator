package org.chori.gsg.model.submissionRequestFactory;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.chori.gsg.model.submissionRequests.*;
import org.chori.gsg.view.GSG;
import org.chori.gsg.view.tabs.*;

public interface TabInterface {

	public enum WhereToPrint {
		GFE(GfeTab.resultsTextAreaGfe),
		// GFE(GSG.resultsTextAreaGfe),
		NAME(GSG.resultsTextAreaName),
 		FEATURE(GSG.resultsTextAreaFeature);

		private final JTextArea whereToPrint;

		private WhereToPrint(JTextArea whereToPrint) {
			this.whereToPrint = whereToPrint;
		}
    
		public JTextArea getWhereToPrint() {
			return this.whereToPrint;
		}
	}

	public enum WhatToPrint {
		GFE(GfeTab.fileFormatGfe),
		// GFE(GSG.fileFormatGfe),
		NAME(GSG.fileFormatName),
 		FEATURE(GSG.fileFormatFeature);

		private final JPanel whatToPrint;

		private WhatToPrint(JPanel whatToPrint) {
			this.whatToPrint = whatToPrint;
		}
    
		public JPanel getWhatToPrint() {
			return this.whatToPrint;
		}
	}
}