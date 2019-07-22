package org.chori.bsg.view;

// import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

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