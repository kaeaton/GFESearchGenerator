package org.chori.gsg.view.dropdownMenus.whichLociType;

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
			// downloadRawData = new DownloadRawKirData();
			break;
		case "BULK":
			// downloadRawData = new DownloadRawKirData();
			break;
		default:
			System.out.println("WhichLociFactory: haven't added that tab yet");
			break;
		}

		return whichLoci;
	}
}