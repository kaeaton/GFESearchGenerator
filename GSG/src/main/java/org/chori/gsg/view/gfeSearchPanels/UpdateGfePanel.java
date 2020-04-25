package org.chori.gsg.view.gfeSearchPanels;

import java.awt.Component;
import javax.swing.JPanel;

import org.chori.gsg.view.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.tabs.GfeTab;


/**
 * Updates the search GFE search panel to the correct locus on the GFE tab.
 * 
 * @author Katrina Eaton
 * 
 */
public class UpdateGfePanel {

	private GfeSearchPanelAssembler gfeSearchPanelAssembler = new GfeSearchPanelAssembler();

	public UpdateGfePanel() { }

	// public void updateTheGfePanel(String whatLocus) {
	// 	System.out.println("Triggered setNewGfePanel");
	// 	JPanel currentGfePanel = createNewGfePanel(whatLocus);
	// 	// JPanel newPanel = createNewGfePanel(whatLocus);
	// 	JPanel oldPanel = GSG.currentGfePanel;
	// 	// JPanel oldPanel = findPanel(GSG.gfePanel, "GFE");

	// 	GSG.gfePanel.remove(oldPanel);
	// 	GSG.gfePanel.add(currentGfePanel).revalidate();
	// 	GSG.gfePanel.repaint();
	// }

	// private JPanel createNewGfePanel(String whatLocus) {
	// 	JPanel newPanel = new JPanel();

	// 	newPanel = gfeSearchPanelAssembler.getGfePanel(whatLocus);
	// 	newPanel.setName("GFE");

	// 	return newPanel;
	// }

	// private JPanel findPanel(JPanel whichTabPanel, String whichPanel) {
	// 	Component selectedPanel = GSG.gfePanel;
	// 	for (Component component : whichTabPanel.getComponents()) {
	// 		if (component.getName().equals("GFE")){
	// 			selectedPanel = component;
	// 			System.out.println("panel: " + selectedPanel);
	// 		} 
	// 	}
	// 	return (JPanel)selectedPanel;
	// }

}