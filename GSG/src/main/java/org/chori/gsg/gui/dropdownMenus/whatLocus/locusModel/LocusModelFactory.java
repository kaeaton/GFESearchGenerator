package org.chori.gsg.gui.dropdownMenus.whatLocus.locusModel;

/**
 * The Locus Model factory. A model is the object used
 * to populate a dropdown menu. This factory is only used by the
 * WhatLocus Dropdowns. Broken down by loci.
 * 
 * @author Katrina Eaton
 * 
 */
public class LocusModelFactory { 

	private static final LocusModelFactory instance = new LocusModelFactory();

	private LocusModelFactory() { }

	public static LocusModelFactory getLocusModelFactoryInstance() {
		return instance;
	}

	public LocusModel createLocusModel(String whichLoci) {
		LocusModel locusModel = null;
	
		switch (whichLoci) {
			case "HLA":
				locusModel = new LocusModelHla();
				break;
			case "KIR":
				locusModel = new LocusModelKir();
				break;
			default:
				System.out.println("LocusModelFactory: haven't added (" + whichLoci + ") yet");
		}

		return locusModel;
	}
}