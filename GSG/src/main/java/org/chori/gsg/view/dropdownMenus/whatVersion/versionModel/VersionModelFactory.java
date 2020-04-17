package org.chori.gsg.view.dropdownMenus.whatVersion.versionModel;

/**
 * The Version Model factory. A model is the object used
 * to populate a dropdown menu. This factory is only used by the
 * WhatVersion Dropdowns. Broken down by tab.
 * 
 * @author Katrina Eaton
 * 
 */
public class VersionModelFactory { 

	private static final VersionModelFactory instance = new VersionModelFactory();

	private VersionModelFactory() { }

	public static VersionModelFactory getVersionModelFactoryInstance() {
		return instance;
	}

	public VersionModel createVersionModel(String tab) {
		VersionModel versionModel = null;
	
		switch (tab) {
			case "GFE":
				versionModel = new VersionModelGfe();
				break;
			case "NAME":
				versionModel = new VersionModelName();
				break;
			case "BULK":
				versionModel = new VersionModelBulk();
				break;
			default:
				System.out.println("versionModelFactory: haven't added that tab (" + tab + ") yet");
		}

		return versionModel;
	}
}