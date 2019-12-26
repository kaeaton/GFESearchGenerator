package org.chori.gsg.view.gfeSearchPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
// import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
// import javax.swing.border.EmptyBorder;
// import javax.swing.BorderFactory;
// import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
// import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * Assembles the search boxes (JTextFields) and checkboxes for the HLA GFE search page
 * 
 * @author Katrina Eaton
 * 
 */
public class HlaSearchPanelAssembler {

	// Exons per locus
	private HashMap<String, Integer> hlaExonTotal = new HashMap();

	// component sources
	// private SelectAllCheckbox selectAllCheckBox = new SelectAllCheckbox();
	// private LocusLabelPanel locusLabelPanel = new LocusLabelPanel();
	// private IndividualFeatureSearchPanel individualFeatureSearchPanel = new IndividualFeatureSearchPanel();
	private FeatureAssembler featureAssembler = new FeatureAssembler();

	// component arraylists
	public static ArrayList<JCheckBox> allCheckboxes;
	public static ArrayList<JTextField> allTextboxes;
	private static ArrayList<JPanel> allFeaturePanels = new ArrayList();

	public HlaSearchPanelAssembler() {
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
	public JPanel getHlaPanel(String locus) {
		// reset the arrayLists, populated via IndividualFeatureSearchPanel
		allCheckboxes = new ArrayList();
		allTextboxes = new ArrayList();
		if(allFeaturePanels.size() != 0) {
			allFeaturePanels.clear();
		}

		allFeaturePanels = featureAssembler.getAllFeaturePanels(locus, hlaExonTotal.get(locus));
		System.out.println("allFeaturePanels size: " + allFeaturePanels.size());

		JPanel completedPanel = assembleHlaPanel(allFeaturePanels);
		return completedPanel;
	}

	private JPanel assembleHlaPanel(ArrayList<JPanel> allFeaturePanels){

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
		System.out.println("allFeaturePanels size: " + allFeaturePanels.size());
		gfeSearchPanel.add(allFeaturePanels.get(0), c);
		
		allFeaturePanels.remove(0);
		// c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;

		int i = 1;
		for(JPanel featurePanel:allFeaturePanels) {
			c.gridx = i;
			gfeSearchPanel.add(featurePanel, c);
			i++;
		}

		return gfeSearchPanel;
	}
}

