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

/**
 * Assembles the search boxes (JTextFields) and checkboxes for the HLA GFE search page
 * 
 * @author Katrina Eaton
 * 
 */
public class HlaSearchBoxAssembler {

	// Exons per locus
	private HashMap<String, Integer> hlaExonTotal = new HashMap();

	// Searchbox source
	private SearchBox searchBox = new SearchBox();

	// select all checkbox
	public static JCheckBox selectAllCheckBox;

	// component arraylists
	public static ArrayList<JCheckBox> allCheckboxes;
	public static ArrayList<JTextField> allTextboxes;

	public HlaSearchBoxAssembler() {
		hlaExonTotal.put("HLA-A", 8);
		hlaExonTotal.put("HLA-B", 7);
		hlaExonTotal.put("HLA-C", 8);
		hlaExonTotal.put("HLA-DPA1", 4);
		hlaExonTotal.put("HLA-DPB1", 5);
		hlaExonTotal.put("HLA-DQA1", 4);
		hlaExonTotal.put("HLA-DQB1", 6);
		hlaExonTotal.put("HLA-DRB1", 6);
		hlaExonTotal.put("HLA-DRB3", 6);
		hlaExonTotal.put("HLA-DRB4", 6);
		hlaExonTotal.put("HLA-DRB5", 6);
	}

	/**
	 * Assembles the JPanel housing all the JTextFields, JCheckBoxes and 
	 * JLabels associated with the HLA GFE search panel. 
	 * Also populates the allCheckboxes and allTextFields ArrayLists
	 * 
	 * @param locus what locus panel is being built
	 * @return the populated JPanel
	 */
	public JPanel assembleHlaPanel(String locus){

		// reinitiate the array lists so they only have 
		// the current components in them
		allCheckboxes = new ArrayList();
		allTextboxes = new ArrayList();

		// parent panel
		JPanel gfeSearchPanel = new JPanel();
		gfeSearchPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// label, and workshop status & 5'UTR bundles
		c.weightx = 0;
		// c.insets = new Insets(0,0,10,0);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		gfeSearchPanel.add(labelPanel(locus), c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		gfeSearchPanel.add(assembleWBox(), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		gfeSearchPanel.add(assemble5PrimeUtr(), c);

		// location counter for names to sort boxes
		int locationCounter = 2;
		int gridXCounter = 3;

		// add the exon/intron pairs starting at 1 going up to exon total - 1
		for (int i = 1; i < hlaExonTotal.get(locus); i++) {
			
			// Add exon bundle
			JPanel exonBox = searchBox.assemble(("Exon " + i), 
					String.valueOf(locationCounter));
			c.gridx = gridXCounter;
			gfeSearchPanel.add(exonBox, c);
			locationCounter++;
			gridXCounter++;

			// add intron bundle
			JPanel intronBox = searchBox.assemble(("Intron " + i), 
					String.valueOf(locationCounter));
			c.gridx = gridXCounter;
			gfeSearchPanel.add(intronBox, c);
			locationCounter++;
			gridXCounter++;

		}

		// add final exon box. Use hash table to get its ID number
		JPanel exonBox = searchBox.assemble(("Exon " + hlaExonTotal.get(locus)), 
				String.valueOf(locationCounter));
		c.gridx = gridXCounter;
		gfeSearchPanel.add(exonBox, c);
		locationCounter++;
		gridXCounter++;

		// 3' UTR bundle
		c.gridx = gridXCounter;
		gfeSearchPanel.add(assemble3PrimeUtr(locationCounter), c);

        System.out.println("Total checkboxes = " + allCheckboxes.size());
        System.out.println("Total textboxes = " + allTextboxes.size());
		return gfeSearchPanel;
	}

	private JPanel labelPanel(String locus) {
		// check all checkbox
		selectAllCheckBox = new JCheckBox();
		selectAllCheckBox.setBorder(new EmptyBorder(3, 10, 8, 0));

		// locus label
		JLabel locusLabel = new JLabel(locus + "  ");

		// sub panel and layout
		JPanel labelPanel = new JPanel();
		GroupLayout labelLayout = new GroupLayout(labelPanel);
		labelPanel.setLayout(labelLayout);

		// add checkbox and label
		labelPanel.add(selectAllCheckBox);
		labelPanel.add(locusLabel);

		// checkbox listner
        SelectCheckboxes.selectAllCB(selectAllCheckBox, allCheckboxes);

		/* assembly */

		// layout horizontal: all in one group so they stack
		labelLayout.setHorizontalGroup(
			labelLayout.createSequentialGroup()
				.addGroup(labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(selectAllCheckBox)
				.addComponent(locusLabel)));
				// .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		// layout vertical: separate groups so they stack
		labelLayout.setVerticalGroup(
			labelLayout.createSequentialGroup()
				.addGroup(labelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(selectAllCheckBox))
				.addGroup(labelLayout.createParallelGroup() // GroupLayout.Alignment.BASELINE
				.addComponent(locusLabel))
				.addGroup(labelLayout.createParallelGroup())); // GroupLayout.Alignment.BASELINE
				// .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		return labelPanel;
	}


	private JPanel assembleWBox() {
		JPanel wBox = searchBox.assemble("Workshop Status    ", "00");

		return wBox;
	}

	private JPanel assemble5PrimeUtr() {
		JPanel fivePrimeUtr = searchBox.assemble("5' UTR", "01");

		return fivePrimeUtr;
	}

	private JPanel assemble3PrimeUtr(int locationCounter) {
		JPanel threePrimeUtr = searchBox.assemble("3' UTR", String.valueOf(locationCounter));

		return threePrimeUtr;
	}
}

