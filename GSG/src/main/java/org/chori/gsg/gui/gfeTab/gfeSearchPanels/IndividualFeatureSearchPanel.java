package org.chori.gsg.gui.gfeTab.gfeSearchPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.chori.gsg.gui.buttons.SubmitButton;

public class IndividualFeatureSearchPanel extends JPanel{ 

	private TextIcon t1;
	private RotatedIcon r1;
	private static SubmitButton submitButtonGenerator = new SubmitButton();
	
	public IndividualFeatureSearchPanel() { }

	public JPanel createIndividualFeaturePanel(String label, String nameNumber) {

		// gather components
		JCheckBox noZeroCheckBox = createNoZeroCheckBox(nameNumber);
		JTextField featureTextField = createFeatureTextField(nameNumber);
		JLabel featureLabel = createFeatureLabel(label);

		return assembleFeatureSearchPanel(noZeroCheckBox, featureTextField, featureLabel);
	}

	private JCheckBox createNoZeroCheckBox(String nameNumber) {
		JCheckBox noZeroCheckBox = new JCheckBox();
		noZeroCheckBox.setName(nameComponent(nameNumber));

		GfeSearchPanelAssembler.allCheckboxes.add(noZeroCheckBox);

		return noZeroCheckBox;
	}

	private JTextField createFeatureTextField(String nameNumber) {
		JTextField featureTextField = new JTextField();
		featureTextField.setColumns(3);
		featureTextField.setName(nameComponent(nameNumber));
		featureTextField.setHorizontalAlignment(JTextField.CENTER);
		featureTextField.addActionListener(submitButtonGenerator.gfeListener);

		GfeSearchPanelAssembler.allTextboxes.add(featureTextField);

		return featureTextField;
	}

	private JLabel createFeatureLabel(String label) {
		JLabel jLabel = new JLabel();

		// turn the label into an icon, then rotate it
		t1 = new TextIcon(jLabel, (" " + label), TextIcon.Layout.HORIZONTAL);
		r1 = new RotatedIcon(t1, RotatedIcon.Rotate.DOWN);
		jLabel.setIcon( r1 );

		return jLabel;
	}

	private String nameComponent(String nameNumber) {
		// set number for names:
		// set name numbers so anything under 10 starts with a 0
		// this is to make sorting by name work
		if (nameNumber.length() == 1) {
			nameNumber = "0" + nameNumber;
		}

		return nameNumber;
	}

	private JPanel assembleFeatureSearchPanel(JCheckBox noZeroCheckBox, 
											  JTextField featureTextField, JLabel featureLabel){
		
		JPanel individualFeaturePanel = new JPanel();

		individualFeaturePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		individualFeaturePanel.add(noZeroCheckBox, c);

		c.gridy = 1;
		individualFeaturePanel.add(featureTextField, c);

		c.gridy = 2;
		individualFeaturePanel.add(featureLabel, c);

		return individualFeaturePanel;
	}
}