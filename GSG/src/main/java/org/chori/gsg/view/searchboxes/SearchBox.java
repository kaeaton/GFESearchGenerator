package org.chori.gsg.view.searchboxes;

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

public class SearchBox extends JPanel{ 

	private TextIcon t1;
	private RotatedIcon r1;
	
	public SearchBox(){
		System.out.println("Assembling a regular searchbox");

	}

	public JPanel assemble(String label, String nameNumber){

		/* setting up variables */
		JPanel featureGroup = new JPanel();
		featureGroup.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// set number for names:
		// set name numbers so anything under 10 starts with a 0
		// this is to make sorting by name work
		if (nameNumber.length() == 1) {
			nameNumber = "0" + nameNumber;
		}

		/* checkbox */
		JCheckBox noZero = new JCheckBox();
		HlaSearchBoxGenerator.allCheckboxes.add(noZero);

		/* textbox */
		JTextField featureNumber = new JTextField();
		featureNumber.setColumns(3);
		featureNumber.setHorizontalAlignment(JTextField.CENTER);
		featureNumber.setName(nameNumber);

		// special rules for workshop status
		if (nameNumber.equals("00")) {
			featureNumber.setColumns(2);
			featureNumber.setText("w");
		}

		HlaSearchBoxGenerator.allTextboxes.add(featureNumber);

		/* label */
		JLabel jLabel = new JLabel();

		// turn the label into an icon, then rotate it
		t1 = new TextIcon(jLabel, (" " + label), TextIcon.Layout.HORIZONTAL);
		r1 = new RotatedIcon(t1, RotatedIcon.Rotate.DOWN);
		jLabel.setIcon( r1 );

		/* assembly */

		c.weightx = 0;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		featureGroup.add(noZero, c);

		c.gridy = 1;
		featureGroup.add(featureNumber, c);

		c.gridy = 2;
		featureGroup.add(jLabel, c);

		return featureGroup;
	}
}