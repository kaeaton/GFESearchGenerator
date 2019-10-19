package org.chori.gsg.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.chori.gsg.view.searchboxes.*;
import org.chori.gsg.view.*;

public class ResetButton { 

	public JButton resetButton = new JButton("Reset search terms");

	public ResetButton() {

	}

	public JButton createResetButton(String whichTab) {
		System.out.println("Generating the reset button");
		
		// who is this reset button for?
		switch(whichTab) {
			case "HLA":
				hlaListener();
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return resetButton;
	}

	private void hlaListener() {
		resetButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	
            	// the list of hla textfields
            	ArrayList<JTextField> allTextFields = HlaSearchBoxGenerator.allTextboxes;
            	
            	// set the textfields back to empty
            	for (JTextField textField:allTextFields){
					
					textField.setText("");

					// special rule for workshop status
					if(textField.getName().equals("00")) textField.setText("w");
				}
            }
        });
	}
}