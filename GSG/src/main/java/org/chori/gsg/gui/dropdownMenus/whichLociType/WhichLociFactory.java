package org.chori.gsg.gui.dropdownMenus.whichLociType;

/**
 * The whichLoci dropdown factory.
 * 
 * @author Katrina Eaton
 * 
 */

public class WhichLociFactory {

	private static final WhichLociFactory instance = new WhichLociFactory();
	
	private WhichLociFactory() { }

	public static WhichLociFactory getWhichLociFactoryInstance() {
		return instance;
	}

	public static WhichLoci createWhichLoci(String tab) {
		WhichLoci whichLoci = null;

		switch (tab) {
			case "GFE":
				whichLoci = new WhichLociGfe();
				break;
			case "NAME":
				whichLoci = new WhichLociName();
				break;
			case "BULK":
				whichLoci = new WhichLociBulk();
				break;
			default:
				System.out.println("WhichLociFactory: haven't added that tab (" + tab + ") yet");
		}

		return whichLoci;
	}
}