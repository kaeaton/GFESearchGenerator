package org.chori.gsg.gui.dropdownMenus;

import javax.swing.JComboBox;


import org.chori.gsg.utilities.Prefs;

/**
 * An implementation of the dropdown menu system for the GFE tab.
 * 
 * @author Katrina Eaton
 * 
 */

public class GfeMenuSystem extends DropDownMenuSystem { 

	private static final GfeMenuSystem INSTANCE = new GfeMenuSystem();

	private GfeMenuSystem() { }

	public static GfeMenuSystem getInstance() {
		return INSTANCE;
	}


	

}