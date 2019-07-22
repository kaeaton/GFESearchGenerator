package org.chori.bsg.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ResetButton { 

	public ResetButton() {

	}

	public JButton createResetButton(String whichTab) {
		System.out.println("Generating the reset button");
		
		// instantiate combobox and its model
		JButton resetButton = new JButton("Reset search terms");

		// who is this combobox for?
		switch(whichTab) {
			case "HLA":
				// comboBoxModel = new DefaultComboBoxModel(hlaLoci);
				// whatLocus.setName("HLA-dropdown");
				// hlaListener(whatLocus);
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

    	// whatLocus.setModel(comboBoxModel);

		return resetButton;
	}

	// private void hlaListener(JComboBox hlaWhatLocus) {
	// 	hlaWhatLocus.addActionListener(new ActionListener() {
 //    		@Override
 //            public void actionPerformed(ActionEvent evt) {
	// 			JPanel newGfePanel = hlaSBG.generateHlaPanel(hlaWhatLocus.getSelectedItem().toString());
 //            	newGfePanel.setName("HLA-GFE");
 //            	JPanel oldPanel = findPanel(B12xGui.hlaPanel, "HLA-GFE");
 //            	B12xGui.hlaPanel.remove(oldPanel);
 //            	B12xGui.hlaPanel.add(newGfePanel).revalidate();
 //            	B12xGui.hlaPanel.repaint();
 //                System.out.println("Which Loci listener triggered");
 //            }
 //        });
	// }

	// private JPanel findPanel(JPanel whichTab, String whichPanel) {
	// 	Component selectedPanel = B12xGui.hlaPanel;
	// 	for (Component component : whichTab.getComponents()) {
 //            if (component.getName().equals(whichPanel)){
 //                selectedPanel = component;
 //                System.out.println("panel: " + selectedPanel);
 //            } 
 //        }
 //        return (JPanel)selectedPanel;
	// }
}