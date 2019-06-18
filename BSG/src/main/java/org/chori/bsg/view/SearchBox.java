package org.chori.bsg.view;

import java.awt.BorderLayout;
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

		JPanel featureGroup = new JPanel();

		GroupLayout layout = new GroupLayout(featureGroup);
		
		/* parent group panel */
		featureGroup.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		/* checkbox */
		JCheckBox noZero = new JCheckBox();

		/* textbox */
		JTextField featureNumber = new JTextField();
		featureNumber.setSize(55, 20);

		/* label */
		JLabel jLabel = new JLabel();

		// turn the label into an icon, then rotate it
		t1 = new TextIcon(jLabel, label, TextIcon.Layout.HORIZONTAL);
		r1 = new RotatedIcon(t1, RotatedIcon.Rotate.DOWN);
		jLabel.setIcon( r1 );

		/* assembly */

		// layout horizontal: all in one group so they stack
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(noZero)
				.addComponent(featureNumber)
				.addComponent(jLabel)));

		// layout vertical: separate groups so they stack
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(noZero))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(featureNumber))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(jLabel)));

		return featureGroup;
	}
}