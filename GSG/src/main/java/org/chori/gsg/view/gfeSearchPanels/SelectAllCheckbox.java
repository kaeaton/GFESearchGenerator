package org.chori.gsg.view.gfeSearchPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

public class SelectCheckbox { 

	public SelectAllCheckbox() { }

	public JCheckBox getSelectAllCheckBox(ArrayList<JCheckBox> allFeatureCheckBoxes) {
		JCheckBox selectAllCheckBox = new JCheckBox();
		selectAllCheckBox.setBorder(new EmptyBorder(3, 10, 8, 0));

		// add listener to the selectAllCheckBox
		SelectAllCheckboxListener(selectAllCheckBox, allFeatureCheckBoxes);

		return selectAllCheckBox;
	}

	public void SelectAllCheckboxListener(JCheckBox selectAllCheckBox, ArrayList<JCheckBox> allCheckboxes) {
		selectAllCheckBox.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
                for (JCheckBox checkbox:allCheckboxes){
					checkbox.setSelected(selectAllCheckBox.isSelected());
				}
            }
        });
	}
}