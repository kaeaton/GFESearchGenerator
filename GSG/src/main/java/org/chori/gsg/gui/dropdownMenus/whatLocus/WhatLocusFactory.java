package org.chori.gsg.gui.dropdownMenus.whatLocus;

// import java.awt.Component;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.prefs.Preferences;
// import javax.swing.DefaultComboBoxModel;
// import javax.swing.JComboBox;
// import javax.swing.JPanel;

// import org.chori.gsg.exceptions.*;
// import org.chori.gsg.gui.gfeTab.gfeSearchPanels.*;
// import org.chori.gsg.gui.*;


/**
 * The whatLocus dropdown factory.
 * 
 * @author Katrina Eaton
 * 
 */
public class WhatLocusFactory { 

	private static final WhatLocusFactory instance = new WhatLocusFactory();

	private WhatLocusFactory() { }

	public static WhatLocusFactory getWhatLocusFactoryInstance() {
		return instance;
	}

	public WhatLocus createWhatLocus(String tab) {
		WhatLocus whatLocus = null;
	
		switch (tab) {
		case "GFE":
			whatLocus = new WhatLocusGfe();
			break;
		case "NAME":
			whatLocus = new WhatLocusName();
			break;
		default:
			System.out.println("WhatLocusFactory: haven't added that tab (" + tab + ") yet");
		}

		return whatLocus;
	}
}