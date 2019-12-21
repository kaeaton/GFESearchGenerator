package org.chori.gsg.view.searchboxes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class KirSearchBoxAssembler {

	// Exons per locus
	private HashMap<String, Integer> kirExonTotal = new HashMap();

	// Searchbox source
	private SearchBox searchBox = new SearchBox();

	// select all checkbox
	public static JCheckBox selectAllCheckBox;

	// component arraylists
	public static ArrayList<JCheckBox> allCheckboxes;
	public static ArrayList<JTextField> allTextboxes;

	public KirSearchBoxAssembler() {
		kirExonTotal.put("KIR2DL4", 9);   // missing Exon 4
		kirExonTotal.put("KIR2DL5A", 9);  // missing Exon 4
		kirExonTotal.put("KIR2DL5B", 9);  // missing Exon 4
		kirExonTotal.put("KIR2DP1", 9);
		kirExonTotal.put("KIR2DS1", 9);
		kirExonTotal.put("KIR2DS2", 9);
		kirExonTotal.put("KIR2DS3", 9);
		kirExonTotal.put("KIR2DS4", 9);
		kirExonTotal.put("KIR2DS5", 9);
		kirExonTotal.put("KIR3DL1", 9);
		kirExonTotal.put("KIR3DL2", 9);
		kirExonTotal.put("KIR3DL3", 9);  // missing Exon 6
		kirExonTotal.put("KIR3DP1", 5);  // Exon 2 = 0 bp?
		kirExonTotal.put("KIR3DS1", 9);
	}
}