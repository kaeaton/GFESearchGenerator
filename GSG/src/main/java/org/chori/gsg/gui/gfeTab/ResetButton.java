package org.chori.gsg.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.chori.gsg.gui.gfeTab.gfeSearchPanels.*;
import org.chori.gsg.gui.*;

public class ResetButton { 

	public JButton resetButton = new JButton("Reset text fields");

	public ResetButton() {

	}

	public JButton createResetButton(String whichTab) {
		System.out.println("Generating the reset button");
		
		// who is this reset button for?
		switch(whichTab) {
			case "GFE":
				gfeListener();
				break;
			default:
				System.out.println("Haven't set up that reset combobox model yet");
		}

		return resetButton;
	}

	private void gfeListener() {
		resetButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	
            	// the list of gfe textfields
            	ArrayList<JTextField> allTextFields = GfeSearchPanelAssembler.allTextboxes;
            	
            	// set the textfields back to empty
            	for (JTextField textField:allTextFields){
					textField.setText("");
				}
            }
        });
	}
}