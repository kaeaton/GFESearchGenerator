package org.chori.gsg.model.downloadData;

public class DownloadRawDataFactory {

	private static final DownloadRawDataFactory instance = new DownloadRawDataFactory();
	
	private DownloadRawDataFactory() { }

	public static DownloadRawDataFactory getInstance() {
		return instance;
	}

	public static DownloadRawData createDownloadRawDataByLoci(String lociType) {
		DownloadRawData downloadRawData = null;

		switch (lociType) {
		case "HLA":
			downloadRawData = new DownloadRawHlaData();
			break;
		case "KIR":
			downloadRawData = new DownloadRawKirData();
			break;
		default:
			System.out.println("DownloadRawDataSwitch, DownloadRawData Factory: haven't added those genes yet");
			break;
		}

		return downloadRawData;
	}
}