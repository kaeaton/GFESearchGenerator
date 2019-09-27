package org.chori.gsg.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CancelButton { 

	public CancelButton() {

	}

	public static JButton createCancelButton() {
		JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
				System.exit(0);
            }
        });

        return cancelButton;
	}
}