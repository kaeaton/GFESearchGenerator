package org.chori.bsg.view;

import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class HlaSearchBoxGenerator {



	// Exons per locus
	HashMap<String, Integer> hlaExonTotal = new HashMap();

	// Searchbox source
	SearchBox searchBox = new SearchBox();

	public HlaSearchBoxGenerator() {
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

	public JPanel generateHlaPanel(String locus){

		// layout

		// parent panel
		JPanel gfeSearchPanel = new JPanel();

		// locus label
		JLabel locusLabel = new JLabel(locus);

		// label, and workshop status & 5'UTR bundles
		gfeSearchPanel.add(locusLabel);
		gfeSearchPanel.add(generateWBox());
		gfeSearchPanel.add(generate5PrimeUtr());

		// location counter for names to sort boxes
		int locationCounter = 2;

		// add the exon/intron pairs starting at 1 going up to exon total - 1
		for (int i = 1; i < hlaExonTotal.get(locus); i++) {
			
			// Add exon bundle
			JPanel exonBox = searchBox.assemble(("Exon " + i), 
					String.valueOf(locationCounter));
			locationCounter++;
			gfeSearchPanel.add(exonBox);

			// add intron bundle
			JPanel intronBox = searchBox.assemble(("Intron " + i), 
					String.valueOf(locationCounter));
			locationCounter++;
			gfeSearchPanel.add(intronBox);

		}

		// add final exon box. Use hash table to get its ID number
		JPanel exonBox = searchBox.assemble(("Exon " + hlaExonTotal.get(locus)), 
				String.valueOf(locationCounter));
		locationCounter++;
		gfeSearchPanel.add(exonBox);

		// 3' UTR bundle
		gfeSearchPanel.add(generate3PrimeUtr(locationCounter));


		return gfeSearchPanel;
	}

	private JPanel generateWBox() {
		JPanel wBox = searchBox.assemble("Workshop Status", "00");

		return wBox;
	}

	private JPanel generate5PrimeUtr() {
		JPanel fivePrimeUtr = searchBox.assemble("5' UTR", "01");

		return fivePrimeUtr;
	}

	private JPanel generate3PrimeUtr(int locationCounter) {
		JPanel threePrimeUtr = searchBox.assemble("3' UTR", String.valueOf(locationCounter));

		return threePrimeUtr;
	}

}

