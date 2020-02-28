package org.chori.gsg.model.downloadData;

import java.io.InputStream;

import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;

public class DownloadRawDataFactory {

	public DownloadRawDataFactory() { }

	public static DownloadRawData createDownloadRawDataByLoci(String lociType) {
		DownloadRawData downloadRawData = null;

		switch (lociType) {
		case "HLA":
			downloadRawData = new DownloadRawHlaData();
			break;
		case "KIR":
			downloadRawData = new DownloadRawHlaData();
			break;
		default:
			System.out.println("DownloadRawDataSwitch, DownloadRawData Factory: haven't added those genes yet");
			break;
		}

		return downloadRawData;
	}
}