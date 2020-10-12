package org.chori.gsg.gui.optionsTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

import org.chori.gsg.model.*;
import org.chori.gsg.model.downloadData.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.dropdownMenus.*;
import org.chori.gsg.gui.*;

/**
 * Creates a button for an easy way to bulk download all data for a given loci type.
 * The button is found on the Options tab of the GUI.
 * 
 * @author Katrina Eaton
 * 
 */

public class BulkDownloadButton {
	
	private String lociType = "HLA";
	private final List<String> hlaLoci = Arrays.asList("HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5");

	private InternetAccess internetAccess = new InternetAccess();
	private VersionsAvailableOnline versionsAvailableOnline = new VersionsAvailableOnline();
	private DownloadRawDataFactory downloadRawDataFactory = DownloadRawDataFactory.getDownloadRawDataFactoryInstance();

	public BulkDownloadButton() { }

	public JButton createBulkDownloadButton() {
		JButton bulkDownloadButton = new JButton("Bulk Download");
		bulkDownloadButton.addActionListener(downloadListener);

		return bulkDownloadButton;
	}

	public ActionListener downloadListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (internetAccess.tester()) 
				new Thread(download).start();
		}
	};

	private Runnable download = new Runnable() {
		public void run() {
			lociType = OptionsTab.whichLociBulk.getSelectedItem().toString();
			versionsAvailableOnline.getCurrentVersionsByLoci(lociType);

			String version = OptionsTab.whatVersionBulk.getSelectedItem().toString();

			// downloadData(lociType, version);
			DownloadRawData downloadRawData = downloadRawDataFactory.createDownloadRawDataByLoci(lociType);
			downloadData(downloadRawData, lociType, version);		
		}
	};

	private void downloadData(DownloadRawData downloadRawData, String lociType, String version) {
		switch(lociType) {
			case "HLA":
				getHlaData(downloadRawData, version);
				break;

			case "KIR":
				getKirData(downloadRawData, version);
				break;

			default:
				System.out.println("BulkDownloadListener: Can't download this datatype: " + lociType);
		}
	}

	private void getHlaData(DownloadRawData downloadRawData, String version) {
		try {
			for (String locus:hlaLoci) {
				downloadRawData.getRawLocusData(locus, version);
			}
		} catch (Exception ex) { System.out.println("Bulk downloading failed: " + ex); }
	}

	private void getKirData(DownloadRawData downloadRawData, String version) {
		try {
			downloadRawData.getRawLocusData("KIR", version);
		} catch (Exception ex) { System.out.println("Bulk downloading failed: " + ex); }
	}
}