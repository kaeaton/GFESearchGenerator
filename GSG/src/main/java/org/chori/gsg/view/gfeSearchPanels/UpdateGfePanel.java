package org.chori.gsg.view.gfeSearchPanels;

import java.awt.Component;
import javax.swing.JPanel;

import org.chori.gsg.view.*;


/**
 * Updates the search GFE search panel to the correct locus on the GFE tab.
 * 
 * @author Katrina Eaton
 * 
 */
public class UpdateGfePanel {

	private GfeSearchPanelAssembler gfeSearchPanelAssembler = new GfeSearchPanelAssembler();

	public UpdateGfePanel() { }

	public static void updateTheGfePanel(String whatLocus) {
		System.out.println("Triggered setNewGfePanel");
		JPanel newPanel = createNewGfePanel(whatLocus);
		JPanel oldPanel = findPanel(GSG.gfePanel, "GFE");

		GSG.gfePanel.remove(oldPanel);
		GSG.gfePanel.add(newPanel).revalidate();
		GSG.gfePanel.repaint();
	}

	private static JPanel createNewGfePanel(String whatLocus) {
		JPanel newPanel = new JPanel();

		// newPanel = gfeSearchPanelAssembler.getGfePanel(whatLocus);
		newPanel.setName("GFE");

		return newPanel;
	}

	/**
	 * A helper method that allows the program to find the currently displayed search panel
	 * 
	 * @param whichTab which tab is should look for the panel in
	 * @param whichPanel the current panel's name the locus it's for
	 * @return the currently displayed JPanel
	 */
	private static JPanel findPanel(JPanel whichTabPanel, String whichPanel) {
		Component selectedPanel = GSG.gfePanel;
		for (Component component : whichTabPanel.getComponents()) {
			if (component.getName().equals("GFE")){
				selectedPanel = component;
				System.out.println("panel: " + selectedPanel);
			} 
		}
		return (JPanel)selectedPanel;
	}

}