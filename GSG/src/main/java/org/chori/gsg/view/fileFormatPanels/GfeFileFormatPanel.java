package org.chori.gsg.view.fileFormatPanel;

import javax.swing.JPanel;

/**
 * Assembles the GFE file format panel for selecting what format the data appears in and whether to write it to file
 * 
 * @author Katrina Eaton
 * 
 */
public class GfeFileFormatPanel extends FileFormatPanel {

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private String loci;

	public JPanel fileFormatPanel = super.createFileFormatPanel();

	public GfeFileFormatPanel() {

	}



}