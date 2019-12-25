// package org.chori.gsg.view.gfeSearchPanels;

// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
// import java.awt.Insets;
// import java.util.ArrayList;
// import java.util.HashMap;
// import javax.swing.border.EmptyBorder;
// import javax.swing.BorderFactory;
// import javax.swing.GroupLayout;
// import javax.swing.JCheckBox;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JTextField;
// import javax.swing.LayoutStyle;

// /**
//  * Assembles the search boxes (JTextFields) and checkboxes for the KIR GFE search page
//  * 
//  * @author Katrina Eaton
//  * 
//  */
// public class KirSearchPanelAssembler {

// 	// Exons per locus
// 	private HashMap<String, Integer> kirExonTotal = new HashMap();

// 	// Searchbox source
// 	private SearchBox searchBox = new SearchBox();

// 	// select all checkbox
// 	public static JCheckBox selectAllCheckBox;

// 	// component arraylists
// 	public static ArrayList<JCheckBox> allCheckboxes;
// 	public static ArrayList<JTextField> allTextboxes;

// 	public KirSearchPanelAssembler() {
// 		kirExonTotal.put("KIR2DL4", 9);   // missing Exon 4
// 		kirExonTotal.put("KIR2DL5A", 9);  // missing Exon 4
// 		kirExonTotal.put("KIR2DL5B", 9);  // missing Exon 4
// 		kirExonTotal.put("KIR2DP1", 9);
// 		kirExonTotal.put("KIR2DS1", 9);
// 		kirExonTotal.put("KIR2DS2", 9);
// 		kirExonTotal.put("KIR2DS3", 9);
// 		kirExonTotal.put("KIR2DS4", 9);
// 		kirExonTotal.put("KIR2DS5", 9);
// 		kirExonTotal.put("KIR3DL1", 9);
// 		kirExonTotal.put("KIR3DL2", 9);
// 		kirExonTotal.put("KIR3DL3", 9);  // missing Exon 6
// 		kirExonTotal.put("KIR3DP1", 5);  // Exon 2 = 0 bp?
// 		kirExonTotal.put("KIR3DS1", 9);
// 	}

// 	/**
// 	 * Assembles the JPanel housing all the JTextFields, JCheckBoxes and 
// 	 * JLabels associated with the KIR GFE search panel. 
// 	 * Also populates the allCheckboxes and allTextFields ArrayLists
// 	 * 
// 	 * @param locus what locus panel is being built
// 	 * @return the populated JPanel
// 	 */
// 	public JPanel assembleKirPanel(String locus){

// 		// reinitiate the array lists so they only have 
// 		// the current components in them
// 		allCheckboxes = new ArrayList();
// 		allTextboxes = new ArrayList();

// 		// parent panel
// 		JPanel gfeSearchPanel = new JPanel();
// 		gfeSearchPanel.setLayout(new GridBagLayout());
// 		GridBagConstraints c = new GridBagConstraints();

// 		// label, and workshop status & 5'UTR bundles
// 		c.weightx = 0;
// 		// c.insets = new Insets(0,0,10,0);
// 		c.anchor = GridBagConstraints.NORTHWEST;
// 		c.gridx = 0;
// 		c.gridy = 0;
// 		gfeSearchPanel.add(labelPanel(locus), c);
		
// 		c.insets = new Insets(0,0,0,0);
// 		c.gridx = 1;
// 		gfeSearchPanel.add(assembleWBox(), c);
		
// 		c.fill = GridBagConstraints.HORIZONTAL;
// 		c.gridx = 2;
// 		gfeSearchPanel.add(assemble5PrimeUtr(), c);

// 		// location counter for names to sort boxes
// 		int locationCounter = 2;
// 		int gridXCounter = 3;

// 		// add the exon/intron pairs starting at 1 going up to exon total - 1
// 		for (int i = 1; i < hlaExonTotal.get(locus); i++) {
			
// 			// Add exon bundle
// 			JPanel exonBox = searchBox.assemble(("Exon " + i), 
// 					String.valueOf(locationCounter));
// 			c.gridx = gridXCounter;
// 			gfeSearchPanel.add(exonBox, c);
// 			locationCounter++;
// 			gridXCounter++;

// 			// add intron bundle
// 			JPanel intronBox = searchBox.assemble(("Intron " + i), 
// 					String.valueOf(locationCounter));
// 			c.gridx = gridXCounter;
// 			gfeSearchPanel.add(intronBox, c);
// 			locationCounter++;
// 			gridXCounter++;

// 		}

// 		// add final exon box. Use hash table to get its ID number
// 		JPanel exonBox = searchBox.assemble(("Exon " + hlaExonTotal.get(locus)), 
// 				String.valueOf(locationCounter));
// 		c.gridx = gridXCounter;
// 		gfeSearchPanel.add(exonBox, c);
// 		locationCounter++;
// 		gridXCounter++;

// 		// 3' UTR bundle
// 		c.gridx = gridXCounter;
// 		gfeSearchPanel.add(assemble3PrimeUtr(locationCounter), c);

//         System.out.println("Total checkboxes = " + allCheckboxes.size());
//         System.out.println("Total textboxes = " + allTextboxes.size());
// 		return gfeSearchPanel;
// 	}



// }