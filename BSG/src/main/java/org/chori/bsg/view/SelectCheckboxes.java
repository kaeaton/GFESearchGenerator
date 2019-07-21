package org.chori.bsg.view;

// import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class SelectCheckboxes { 

	public SelectCheckboxes() {

	}

	public static void selectAllCB(Component panelToSearch) {
		System.out.println("HLA select all checkbox event triggered");
		for (JCheckBox checkbox:HlaSearchBoxGenerator.allCheckboxes){
			checkbox.setSelected(HlaSearchBoxGenerator.selectAllCheckBox.isSelected());
		}
	}
}