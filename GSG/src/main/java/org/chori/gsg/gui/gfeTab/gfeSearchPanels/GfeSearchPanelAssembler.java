package org.chori.gsg.gui.gfeTab.gfeSearchPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Assembles the subpanels for the GFE search panel
 * 
 * @author Katrina Eaton
 * 
 */
public class GfeSearchPanelAssembler {

	// component sources
	private ExonCount exonCount = new ExonCount();
	private GatherIndividualFeaturePanels gatherIndividualFeaturePanels = new GatherIndividualFeaturePanels();

	// exon hashmap
	private final HashMap<String, Integer> allExonCounts = exonCount.getExonCount();

	// component arraylists
	public static ArrayList<JCheckBox> allCheckboxes;
	public static ArrayList<JTextField> allTextboxes;
	private static ArrayList<JPanel> allFeaturePanels = new ArrayList();

	public GfeSearchPanelAssembler() { }

	/**
	 * Assembles the JPanel housing all the JTextFields, JCheckBoxes and 
	 * JLabels associated with the HLA GFE search panel. 
	 * Also populates the allCheckboxes and allTextFields ArrayLists
	 * 
	 * @param locus what locus panel is being built
	 * @return the populated JPanel
	 */
	public JPanel makeGfePanel(String locus) {
		// reset the arrayLists, populated via IndividualFeatureSearchPanel
		allCheckboxes = new ArrayList();
		allTextboxes = new ArrayList();
		if(allFeaturePanels.size() != 0) {
			allFeaturePanels.clear();
		}

		allFeaturePanels = gatherIndividualFeaturePanels.getAllFeaturePanels(locus, allExonCounts.get(locus));

		JPanel completedPanel = assembleGfePanel(allFeaturePanels);
		return completedPanel;
	}

	private JPanel assembleGfePanel(ArrayList<JPanel> allFeaturePanels){

		// parent panel
		JPanel gfeSearchPanel = new JPanel();
		gfeSearchPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// label, and workshop status & 5'UTR bundles
		c.weightx = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
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

