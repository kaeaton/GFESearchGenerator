package org.chori.bsg.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.chori.bsg.view.searchboxes.*;


public class SubmitButton { 

	public JButton submitButton = new JButton("Submit");

	public SubmitButton() {

	}

	public JButton createSubmitButton(String whichTab) {
		System.out.println("Generating the submit button");

		// who is this reset button for?
		switch(whichTab) {
			case "HLA":
				hlaListener();
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return submitButton;
	}

	private void hlaListener() {
		submitButton.addActionListener(new ActionListener() {
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