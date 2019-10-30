package org.chori.gsg.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.InputStream;
// import java.util.ArrayList;
import javax.swing.JButton;


import org.chori.gsg.controller.*;
import org.chori.gsg.model.*;
import org.chori.gsg.model.neo4j.*;
import org.chori.gsg.model.processJson.*;
import org.chori.gsg.view.dropdowns.*;
import org.chori.gsg.view.*;


public class BulkDownloadButton {
	
	private String versionType = "HLA";

	public BulkDownloadButton() { }

	private InternetAccess internet = new InternetAccess();

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
					crd.getCurrentVersions(versionType);
					crd.getRawLocusData();
					
					try {
						
			        } catch (Exception ex) { System.out.println("Downloading versions failed: " + ex); }

				}
			};

			if (internet.tester()) 
				new Thread(download).start();
			
		}
	};
}