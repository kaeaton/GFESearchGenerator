package org.chori.gsg.data.downloadData;

public class DownloadRawDataFactory {

	private static final DownloadRawDataFactory instance = new DownloadRawDataFactory();
	
	private DownloadRawDataFactory() { }

	public static DownloadRawDataFactory getDownloadRawDataFactoryInstance() {
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