package org.chori.gsg.view.gfeSearchPanels;

import java.util.ArrayList;
import javax.swing.JPanel;

public class FeatureAssembler {

	private LocusLabelPanel locusLabelPanel = new LocusLabelPanel();
	private IndividualFeatureSearchPanel individualFeatureSearchPanel = new IndividualFeatureSearchPanel();

	private ArrayList<JPanel> allFeaturePanels = new ArrayList();

	public FeatureAssembler() { }

	public ArrayList<JPanel> getAllFeaturePanels(String locus, int totalExonCount) {
		// reset the arrayList
		ArrayList<JPanel> allFeaturePanels = new ArrayList();
		System.out.println("allFeaturePanels size: " + allFeaturePanels.size());

		allFeaturePanels = gatherAllFeaturePanels(locus, totalExonCount);
		System.out.println("allFeaturePanels size(FeatureAssembler): " + allFeaturePanels.size());

		return allFeaturePanels;
	}

	private ArrayList<JPanel> gatherAllFeaturePanels(String locus, int totalExonCount) {
		allFeaturePanels.add(locusLabelPanel.getLocusLabelPanel(locus));
		allFeaturePanels.add(assemble5PrimeUtr());

		int nameCounter = 1;

		if(locus.equals("KIR2DL4") || locus.equals("KIR2DL5A") || locus.equals("KIR2DL5B")) {
			nameCounter = generateExonAndIntronPanelSkipExon4(totalExonCount);
		} else if(locus.equals("KIR3DL3")) {
			nameCounter = generateExonAndIntronPanelSkipExon6(totalExonCount);
		} else {
			nameCounter = generateExonAndIntronPanel(totalExonCount);
		}

		allFeaturePanels.add(assemble3PrimeUtr(nameCounter));

		return allFeaturePanels;
	}

	private int generateExonAndIntronPanel(int totalExonCount) {
		int positionCounter = 1;
		int nameCounter = 1;

		for(positionCounter = 1; positionCounter < totalExonCount; positionCounter++) {
			allFeaturePanels.add(assembleExon(positionCounter, nameCounter));
			nameCounter++;
			allFeaturePanels.add(assembleIntron(positionCounter, nameCounter));
			nameCounter++;
		}
		allFeaturePanels.add(assembleExon(totalExonCount, nameCounter));
		nameCounter++;

		return nameCounter;
	}

	private int generateExonAndIntronPanelSkipExon4(int totalExonCount) {
		int positionCounter = 1;
		int nameCounter = 1;

		for(positionCounter = 1; positionCounter < totalExonCount; positionCounter++) {
			if(positionCounter == 4) {
				positionCounter ++;
			}
			allFeaturePanels.add(assembleExon(positionCounter, nameCounter));
			nameCounter++;
			allFeaturePanels.add(assembleIntron(positionCounter, nameCounter));
			nameCounter++;
		}
		allFeaturePanels.add(assembleExon(totalExonCount, nameCounter));
		nameCounter++;

		return nameCounter;
	}

	private int generateExonAndIntronPanelSkipExon6(int totalExonCount) {
		int positionCounter = 1;
		int nameCounter = 1;

		for(positionCounter = 1; positionCounter < totalExonCount; positionCounter++) {
			if(positionCounter == 6) {
				positionCounter ++;
			}
			allFeaturePanels.add(assembleExon(positionCounter, nameCounter));
			nameCounter++;
			allFeaturePanels.add(assembleIntron(positionCounter, nameCounter));
			nameCounter++;
		}
		allFeaturePanels.add(assembleExon(totalExonCount, nameCounter));
		nameCounter++;

		return nameCounter;
	}

	private JPanel assemble5PrimeUtr() {
		JPanel fivePrimeUtr = individualFeatureSearchPanel.assembleFeatureSearchPanel("5' UTR", "00");

		return fivePrimeUtr;
	}

	private JPanel assembleExon(int locationCounter, int nameCounter) {
		JPanel exonFeature = individualFeatureSearchPanel.assembleFeatureSearchPanel(("Exon " 
			+ locationCounter), String.valueOf(nameCounter));

		return exonFeature;
	}

	private JPanel assembleIntron(int locationCounter, int nameCounter) {
		JPanel intronFeature = individualFeatureSearchPanel.assembleFeatureSearchPanel(("Intron " 
			+ locationCounter), String.valueOf(nameCounter));

		return intronFeature;
	}

	private JPanel assemble3PrimeUtr(int nameCounter) {
		JPanel threePrimeUtr = individualFeatureSearchPanel.assembleFeatureSearchPanel("3' UTR", 
			String.valueOf(nameCounter));

		return threePrimeUtr;
	}

}