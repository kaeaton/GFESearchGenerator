package org.chori.gsg.model;

import java.io.File;
import java.nio.file.*;

/**
 * Utilities for processing files
 * 
 * @author Katrina Eaton
 * 
 */

public class FileUtilities {
	public FileUtilities() { }



	// sometimes files have nothing but a header in them
	// makes sure the file is big enough to actually contain data
	public boolean isTheFileLongEnough(File file) {
		// File data = getRawData(whatLocus, whatVersion);
		if (file.exists()) {
			System.out.println("File Utilities: isTheFileLongEnough(): The file exists, we're going to check the length: " 
								+ file.toString());

			long isTheFileLongEnough = file.length();

			// (a header is about 18 bytes. This is giving a bit of a cushion)
			if(file.length() > 100)
				return true;
		}

		return false;
	}
}