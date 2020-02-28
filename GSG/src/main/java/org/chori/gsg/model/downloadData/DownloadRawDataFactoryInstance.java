package org.chori.gsg.model.downloadData;

import java.io.InputStream;

import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;

public class DownloadRawDataFactoryInstance {


	// make it a Singleton by instantiating the single instance
	private static DownloadRawDataFactoryInstance instance = new DownloadRawDataFactoryInstance();

	public static DownloadRawDataFactory factory = new DownloadRawDataFactory();

	// private prevents the class from being instantiated
	private DownloadRawDataFactoryInstance() { }
	
	public static DownloadRawDataFactoryInstance getInstance(){
		return instance;
	}
}