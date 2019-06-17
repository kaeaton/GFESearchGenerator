package org.chori.bsg.view;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBox extends JPanel{ 
	
	public SearchBox(){
		System.out.println("Assembling a regular searchbox");

	}

	public JPanel assemble(String label, int idNumber){

		JPanel featureGroup = new JPanel();

		GroupLayout layout = new GroupLayout(featureGroup);
		
		// parent group panel
		featureGroup.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// checkbox
		JCheckBox noZero = new JCheckBox();

		// textbox
		JTextField featureNumber = new JTextField();
		featureNumber.setSize(45, 20);

		// label

		// assembly

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(noZero)
				.addComponent(featureNumber)));
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(noZero))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(featureNumber)));
				// .addComponent(c3))
// );
		// compsToExperiment.add(noZero);
		// featureGroup.add(noZero, BorderLayout.NORTH);
		// compsToExperiment.add(featureNumber);
		// featureGroup.add(featureNumber, BorderLayout.WEST);


		return featureGroup;
	}
}