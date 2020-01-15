package org.chori.gsg.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;


import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.view.*;

public class BulkDownloadButton {
	
	private String type = "GFE";
	private final List<String> hlaLoci = Arrays.asList("HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5");
	private final List<String> kirLoci = Arrays.asList("KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1");

	public BulkDownloadButton() { }

	private InternetAccess internetAccess = new InternetAccess();

	public JButton createBulkDownloadButton() {
		JButton bulkDownloadButton = new JButton("Bulk Download");
		bulkDownloadButton.addActionListener(downloadListener);

		return bulkDownloadButton;
	}

	public ActionListener downloadListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			Runnable download = new Runnable() {
				public void run() {
					CurrentReleaseData crd = new CurrentReleaseData();
					crd.getCurrentVersions(type);
					String version = B12xGui.whatVersionBulk.getSelectedItem().toString();

					try {
						for (String locus:hlaLoci) {
							crd.getRawLocusData(type, locus, version);
						}
					} catch (Exception ex) { System.out.println("Bulk downloading failed: " + ex); }

				}
			};

			if (internetAccess.tester()) 
				new Thread(download).start();
			
		}
	};
}