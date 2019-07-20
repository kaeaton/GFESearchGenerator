package org.chori.bsg.view;

// import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;



public class HlaSearchBoxGenerator {



	// Exons per locus
	HashMap<String, Integer> hlaExonTotal = new HashMap();

	// Searchbox source
	SearchBox searchBox = new SearchBox();

	// checkboxes
	public static JCheckBox selectAllCheckBox;
	public static ArrayList<JCheckBox> allCheckboxes = new ArrayList();

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
		// GroupLayout layout = new GroupLayout(gfeSearchPanel);
		// gfeSearchPanel.setLayout(layout);
		// layout.setAutoCreateGaps(true);
		// layout.setAutoCreateContainerGaps(true);

		// label, and workshop status & 5'UTR bundles
		gfeSearchPanel.add(labelPanel(locus));
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

		// gather up the JPanels comprising the gfe array
		ArrayList<JPanel> subPanels = new ArrayList();

		// Find the text fields and add to array
        for (Component component : ((JPanel)gfeSearchPanel).getComponents()) {
            if (component instanceof JPanel){
                System.out.println(component);
                subPanels.add((JPanel)component);
            }
        }

        System.out.println(allCheckboxes);
		return gfeSearchPanel;
	}

	private JPanel labelPanel(String locus) {
		// check all checkbox
		selectAllCheckBox = new JCheckBox();

		// locus label
		JLabel locusLabel = new JLabel(locus);

		// sub panel and layout
		JPanel labelPanel = new JPanel();
		GroupLayout labelLayout = new GroupLayout(labelPanel);
		labelPanel.setLayout(labelLayout);
		labelLayout.setAutoCreateGaps(true);
		labelLayout.setAutoCreateContainerGaps(true);

		// add checkbox and label
		labelPanel.add(selectAllCheckBox);
		labelPanel.add(locusLabel);

		// checkbox listner
		System.out.println("created check all checkbox");

    	selectAllCheckBox.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
                SelectCheckboxes.selectAllCB(labelPanel.getParent());
                System.out.println("Check All listener triggered");
            }
        });

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

