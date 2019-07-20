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

	private static ArrayList<JCheckBox> selectAllCheckboxes(JPanel panelToSearch) {

		ArrayList<JCheckBox> checkBoxes = new ArrayList();
		// Component currentPanel = panelToSearch;

		// Find the right GFE entry panel
        // for (Component component : panelToSearch.getComponents()) {
        //     if (component.getName().equals(panelName)){
        //         currentPanel = component;
        //         System.out.println("panel: " + currentGfePanel);
        //     } 
        // }
		System.out.println("HLA find all the checkboxes function triggered");
        
        // Find the check boxes and add to arrayList
        for (Component component : ((JPanel)panelToSearch).getComponents()) {
            if (component instanceof JCheckBox){
                System.out.println("Component: " + component);
                checkBoxes.add((JCheckBox)component);
            }
        }

        return checkBoxes;
	}

	public static void selectAllCB(JPanel panelToSearch) {
		ArrayList<JCheckBox> checkBoxes = selectAllCheckboxes(panelToSearch);
		System.out.println("HLA select all checkbox event triggered");
	}
}