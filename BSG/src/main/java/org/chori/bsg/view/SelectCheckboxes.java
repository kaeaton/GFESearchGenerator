package org.chori.bsg.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;

public class SelectCheckboxes { 

	public SelectCheckboxes() {

	}

	public static void selectAllCB(JCheckBox selectAllCB, ArrayList<JCheckBox> allCheckboxes) {
		selectAllCB.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
                for (JCheckBox checkbox:allCheckboxes){
					checkbox.setSelected(selectAllCB.isSelected());
				}
            }
        });
	}
}