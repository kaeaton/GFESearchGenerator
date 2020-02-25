package org.chori.gsg.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

import org.chori.gsg.model.*;
import org.chori.gsg.model.utilities.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.view.*;

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
	private DataAvailableOnline dataAvailableOnline = new DataAvailableOnline();

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
			lociType = GSG.whichLociBulk.getSelectedItem().toString();
			dataAvailableOnline.getCurrentVersionsByLoci(lociType);

			String version = GSG.whatVersionBulk.getSelectedItem().toString();

			downloadData(lociType, version);
		}
	};

	private void downloadData(String lociType, String version) {
		switch(lociType) {
			case "HLA":
				getHlaData(version);
				break;

			case "KIR":
				getKirData(version);
				break;

			default:
				System.out.println("BulkDownloadListener: Can't download this datatype: " + lociType);
		}
	}

	private void getHlaData(String version) {
		try {
			for (String locus:hlaLoci) {
				dataAvailableOnline.getRawLocusData("HLA", locus, version);
			}
		} catch (Exception ex) { System.out.println("Bulk downloading failed: " + ex); }
	}

	private void getKirData(String version) {
		try {
			dataAvailableOnline.getRawLocusData("KIR", "KIR", version);
		} catch (Exception ex) { System.out.println("Bulk downloading failed: " + ex); }
	}
}