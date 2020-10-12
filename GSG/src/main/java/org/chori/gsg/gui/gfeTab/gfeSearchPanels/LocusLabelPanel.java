package org.chori.gsg.gui.gfeTab.gfeSearchPanels;

import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LocusLabelPanel {

	SelectAllCheckbox selectAllCheckboxGenerator = new SelectAllCheckbox();

	public LocusLabelPanel() { }

	public JPanel getLocusLabelPanel(String locus) {
		JLabel locusLabel = new JLabel(locus + " w");
		ArrayList<JCheckBox> allTheCheckBoxes = GfeSearchPanelAssembler.allCheckboxes;
		JCheckBox selectAllCheckBox = selectAllCheckboxGenerator.getSelectAllCheckBox(allTheCheckBoxes);

		return assembleLocusLabelPanel(selectAllCheckBox, locusLabel);
	}

	private JPanel assembleLocusLabelPanel(JCheckBox selectAllCheckBox, JLabel locusLabel) {
		JPanel labelPanel = new JPanel();
		GroupLayout labelLayout = new GroupLayout(labelPanel);
		labelPanel.setLayout(labelLayout);

		// add checkbox and label
		labelPanel.add(selectAllCheckBox);
		labelPanel.add(locusLabel);

		// layout horizontal: all in one group so they stack
		labelLayout.setHorizontalGroup(
			labelLayout.createSequentialGroup()
				.addGroup(labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(selectAllCheckBox)
				.addComponent(locusLabel)));

		// layout vertical: separate groups so they stack
		labelLayout.setVerticalGroup(
			labelLayout.createSequentialGroup()
				.addGroup(labelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(selectAllCheckBox))
				.addGroup(labelLayout.createParallelGroup() // GroupLayout.Alignment.BASELINE
				.addComponent(locusLabel))
				.addGroup(labelLayout.createParallelGroup())); // GroupLayout.Alignment.BASELINE

		return labelPanel;
	}
}