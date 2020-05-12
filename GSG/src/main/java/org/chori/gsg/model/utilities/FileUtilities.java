package org.chori.gsg.model.utilities;

import java.io.File;
import java.nio.file.*;

import org.chori.gsg.model.downloadData.*;
import org.chori.gsg.model.utilities.WhereTheDataLives;


/**
 * Utilities for processing files
 * 
 * @author Katrina Eaton
 * 
 */

public class FileUtilities {

	private DownloadRawDataFactory downloadRawDataFactory = DownloadRawDataFactory.getDownloadRawDataFactoryInstance();

	private String specificFile = "";

	public FileUtilities() { }

	/**
	 * Returns the csv file of raw data.
	 * First it checks to see if the data has already been downloaded 
	 * (scans for file length to make sure it's not just a header)
	 * If the file does not exist or contains no data, this downloads it.
	 * 
	 * @param locus a string telling which locus for HLA, or just "KIR" for KIR data.
	 * @param version a string listing the version.
	 * @param lociType a string identifying which loci we're looking at.
	 * @return a csv file of raw data.
	 */
	public File getTheRawDataFile(String locus, String version, String lociType) {

		// DownloadRawData downloadRawData = new DownloadRawData();
		WhereTheDataLives whereTheDataLives = new WhereTheDataLives();

		if(lociType.equals("HLA")) {
			specificFile = whereTheDataLives.getRawDataPath() + lociType 
							+ System.getProperty("file.separator")
							+ version + System.getProperty("file.separator") 
							+ "neo4j_" + locus + "_" + version + "_download.csv";
		} else if(lociType.equals("KIR")) {
			specificFile = whereTheDataLives.getRawDataPath() + lociType 
							+ System.getProperty("file.separator")
							+ version + System.getProperty("file.separator") 
							+ "neo4j_" + lociType + "_" + version + "_download.csv";
		}

		File theFile = new File(specificFile);
		
		if(theFile.exists() && isTheFileLongEnough(theFile)) {
			System.out.println("Found the raw data file");
			return theFile;
		} else {
			DownloadRawData downloadRawData = downloadRawDataFactory.createDownloadRawDataByLoci(lociType);
			downloadRawData.getRawLocusData(locus, version);
			return theFile;
		}
	}

	/**
	 * Sometimes data files end up with only a header. It's typically 18 bytes long.
	 * Just in case there's any variation, the file length cutoff here is 100 bytes
	 * (The shortest datafile is still over 1 kb)
	 * 
	 * @param file a csv file. (Not just the path to the file. The actual file is passed in.)
	 * @return a boolean, true if the file is greater than 100 bytes long, false if not.
	 */
	public boolean isTheFileLongEnough(File file) {
		if (file.exists()) {
			System.out.println("File Utilities: isTheFileLongEnough(): The file exists, we're going to check the length: " 
								+ file.toString());

			if(file.length() > 100)
				return true;
		}

		return false;
	}

	/**
	 * Which side is the GFE? (Old files use names as key, new ones gfe.)
	 * We want the side that doesn't contain the asterisk.
	 * 
	 * @param lineOfData a non-header String (line) from the data file, usually from a buffered reader.
	 * @return an int identifying which piece of data is the GFE.
	 */
	public int whichSideIsTheGfe(String lineOfData) {
		String[] gfeAlleles = lineOfData.split(",");

		int gfe = 1;

		if(gfeAlleles[1].contains("*"))
			gfe = 0;

		return gfe;
	}
}