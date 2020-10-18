package org.chori.gsg.data.submissionRequestFactory;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.chori.gsg.data.submissionRequests.*;
import org.chori.gsg.gui.GSG;
import org.chori.gsg.gui.gfeTab.*;
import org.chori.gsg.gui.nameTab.*;

public interface TabInterface {

	public enum WhereToPrint {
		GFE(GfeTab.resultsTextAreaGfe),
		NAME(NameTab.resultsTextAreaName),
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
		// GFE(GfeTab.getFileFormatGfe()),
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