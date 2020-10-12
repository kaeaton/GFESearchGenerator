package org.chori.gsg.gui.dropdownMenus.whatVersion;

/**
 * The whatVersion dropdown factory.
 * 
 * @author Katrina Eaton
 * 
 */
public class WhatVersionFactory { 

	private static final WhatVersionFactory instance = new WhatVersionFactory();

	private WhatVersionFactory() { }

	public static WhatVersionFactory getWhatVersionFactoryInstance() {
		return instance;
	}

	public WhatVersion createWhatVersion(String tab) {
		WhatVersion whatVersion = null;
	
		switch (tab) {
			case "GFE":
				whatVersion = new WhatVersionGfe();
				break;
			case "NAME":
				whatVersion = new WhatVersionName();
				break;
			case "BULK":
				whatVersion = new WhatVersionBulk();
				break;
			default:
				System.out.println("WhatVersionFactory: haven't added that tab (" + tab + ") yet");
		}

		return whatVersion;
	}
}