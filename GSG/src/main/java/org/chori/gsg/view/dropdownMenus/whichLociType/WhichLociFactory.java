package org.chori.gsg.view.dropdownMenus.whichLociType;

public class WhichLociFactory {

	private static final WhichLociFactory instance = new WhichLociFactory();
	
	private WhichLociFactory() { }

    public static WhichLociFactory getWhichLociFactoryInstance() {
        return instance;
    }

	public static WhichLoci createWhichLociDropdown(String tab) {
		WhichLoci whichLociDropdown = null;

		switch (tab) {
		case "GFE":
			// downloadRawData = new DownloadRawHlaData();
			break;
		case "NAME":
			// downloadRawData = new DownloadRawKirData();
			break;
		case "BULK":
			// downloadRawData = new DownloadRawKirData();
			break;
		default:
			System.out.println("DownloadRawDataSwitch, DownloadRawData Factory: haven't added those genes yet");
			break;
		}

		return whichLociDropdown;
	}
}