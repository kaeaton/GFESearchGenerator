package org.chori.gsg.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.prefs.Preferences;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.model.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.view.buttons.*;
import org.chori.gsg.view.dropdownMenus.*;
import org.chori.gsg.view.dropdownMenus.whichLociType.*;
import org.chori.gsg.view.dropdownMenus.whatLocus.*;
import org.chori.gsg.view.dropdownMenus.whatVersion.*;
import org.chori.gsg.view.gfeSearchPanels.*;
import org.chori.gsg.view.tabs.*;

public class GSG extends JFrame {

	private Preferences prefs = Preferences.userNodeForPackage(this.getClass());

	// default locus settings
	// private String gfeSelectedLoci = prefs.get("GSG_GFE_LOCI_STRING", "HLA");
	// private String gfeSelectedLocus = prefs.get("GSG_HLA_LOCUS_STRING", "HLA-A");
	private String nameSelectedLoci = prefs.get("GSG_NAME_LOCI_STRING", "HLA");
	private String nameSelectedLocus = prefs.get("GSG_NAME_VERSION", "HLA-A");
	// private String featureSelectedLoci = prefs.get("GSG_FEATURE_LOCI_STRING", "HLA");
	// private String featureSelectedLocus = prefs.get("GSG_KIR_LOCUS_STRING", "KIR2DL4");
	private String bulkSelectedLoci = prefs.get("GSG_BULK_LOCI_STRING", "HLA");

	// the GFE panel generator
	// private GfeSearchPanelAssembler gfePanelGenerator = new GfeSearchPanelAssembler();
	
	// component generators
	private static WhatLocusFactory whatLocusFactory = WhatLocusFactory.getWhatLocusFactoryInstance();
	private static WhatVersionFactory whatVersionFactory = WhatVersionFactory.getWhatVersionFactoryInstance();
	private static WhichLociFactory whichLociFactory = WhichLociFactory.getWhichLociFactoryInstance();
	private static ResetButton resetButtonGenerator = new ResetButton();
	private static FileFormatPanel fileFormatPanelGenerator = new FileFormatPanel();
	private static ExitButton exitButtonGenerator = new ExitButton();
	private static SubmitButton submitButtonGenerator = new SubmitButton();
	private static ResetPrefsButton resetPrefsButtonGenerator = new ResetPrefsButton();
	private static BulkDownloadButton bulkDownloadButtonGenerator = new BulkDownloadButton();

	private static GfeTab assembleGfeTab = GfeTab.getGfeTabInstance();

	// need this to add at initialization
	public static JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

	// the tabs, added initially so I can make them 
	// public, static and update them
	public static JPanel gfeTab = new JPanel();
	public static JPanel nameTab = new JPanel();
	public static JPanel featureTab = new JPanel();
	public static JPanel optionsTab = new JPanel();

	// the holder panel
	// they're embedded in the layout, with contents to be changed
	// public static JPanel gfePanel = new JPanel();
	public static JPanel namePanel = new JPanel();

	// results text areas
	// public static JTextArea resultsTextAreaGfe = new JTextArea();
	public static JTextArea resultsTextAreaName = new JTextArea();
	public static JTextArea resultsTextAreaFeature = new JTextArea();
	
	// combo boxes for locus and version selection
	// public static JComboBox whatVersionGfe = new JComboBox();
	// public static JComboBox whatLocusGfe = new JComboBox();
	// public static JComboBox whichLociGfe = new JComboBox();
	public static JComboBox whatVersionName = new JComboBox();
	public static JComboBox whatLocusName = new JComboBox();
	public static JComboBox whichLociName = new JComboBox();
	public static JComboBox whatVersion1Feature = new JComboBox();
	public static JComboBox whatVersion2Feature = new JComboBox();
	public static JComboBox whatLocus1Feature = new JComboBox();
	public static JComboBox whatLocus2Feature = new JComboBox();
	public static JComboBox whichLociFeature = new JComboBox();
	public static JComboBox whatVersionBulk = new JComboBox();
	public static JComboBox whichLociBulk = new JComboBox();

	// file format panels
	// public static JPanel fileFormatGfe = fileFormatPanelGenerator.getFileFormatPanel("GFE");
	public static JPanel fileFormatName = fileFormatPanelGenerator.getFileFormatPanel("NAME");
	public static JPanel fileFormatFeature = fileFormatPanelGenerator.getFileFormatPanel("FEATURE");

	// search box for name search
	public static JTextField nameSearchBox = new JTextField("", 20);
	
	public GSG() {
		try {

			// error handling: if you have no data and no internet
			// the program doesn't work
			InternetAccess internet = new InternetAccess();

			VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();
			boolean localData = versionsAvailableLocally.isThereAnyLocalData();

			if(internet.tester()) {

				// if you have internet access, download the current versions in the background
				VersionsAvailableOnline versionsAvailableOnline = new VersionsAvailableOnline();
				versionsAvailableOnline.downloadCurrentVersionsInTheBackground();

			} else if(!localData) {

				// if you DON'T have internet access OR local data, throw an error
				throw new NoInternetOrDataException();
			}

			// whichLociGfe = whichLociFactory.createWhichLoci("GFE").createWhichLociComboBox();
			// whatVersionGfe = whatVersionFactory.createWhatVersion("GFE").createWhatVersionComboBox();
			// whatLocusGfe = whatLocusFactory.createWhatLocus("GFE").createWhatLocusComboBox(whatVersionGfe.getSelectedItem().toString(), prefs.get("GSG_GFE_LOCI_STRING", "HLA"));
			
			whichLociName = whichLociFactory.createWhichLoci("NAME").createWhichLociComboBox();
			whatVersionName = whatVersionFactory.createWhatVersion("NAME").createWhatVersionComboBox();
			whatLocusName = whatLocusFactory.createWhatLocus("NAME").createWhatLocusComboBox(whatVersionName.getSelectedItem().toString(), prefs.get("GSG_NAME_LOCI_STRING", "HLA"));
			
			// whichLociFeature = whichLociFactory.createWhichLoci("FEATURE").createWhichLociComboBox();
			
			whichLociBulk = whichLociFactory.createWhichLoci("BULK").createWhichLociComboBox();
			whatVersionBulk = whatVersionFactory.createWhatVersion("BULK").createWhatVersionComboBox();


		} catch (NoInternetOrDataException ex) { 
			System.exit(0);
		} catch (Exception ex) {
			System.out.println("GSG initialization: " + ex);
		}


		// jFrame settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1060,740));
		this.add(parentTabbedPane);
		this.pack();
		this.setLocationRelativeTo(null);

		initComponents();
	}

	private void initComponents() {

	/* tabbed pane */
		parentTabbedPane.setPreferredSize(new Dimension(1000, 700));

	/* GFE search tab */

		// add panel to tab pane
		parentTabbedPane.addTab("GFE Search", null, gfeTab, "GFE Search tool");
		JPanel gfeAssembledTab = assembleGfeTab.assembleGfeTab();
		gfeTab.add(gfeAssembledTab);

	/* Name Search tab */
		parentTabbedPane.addTab("Name Search", null, nameTab, "Name Search tool");
		
		// currentGfePanel.setName("NAME");
		namePanel.add(nameSearchBox);
		nameSearchBox.addActionListener(submitButtonGenerator.nameListener);
		
		// results textarea
		JScrollPane resultsScrollPaneName = new JScrollPane(resultsTextAreaName);
		resultsTextAreaName.setFont(new Font("Courier New", 0, 13));
		resultsScrollPaneName.setPreferredSize(new Dimension(950, 400));

		// buttons
		JButton submitButtonName = submitButtonGenerator.createSubmitButton("NAME");
		JButton exitButtonName = exitButtonGenerator.createExitButton();

		// submit/cancel buttons panel
		JPanel bottomButtonsName = new JPanel();
		bottomButtonsName.add(submitButtonName);
		bottomButtonsName.add(exitButtonName);

		// version/loci dropdowns
		JPanel versionLociPanelName = new JPanel();
		versionLociPanelName.add(whatVersionName);
		versionLociPanelName.add(whichLociName);

		// layout / add them to the nameTab
		nameTab.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.anchor = GridBagConstraints.NORTHWEST;
		e.insets = new Insets(0,0,10,0);
		e.weightx = 0.5;
		
		// line 0
		e.gridx = 0;
		e.gridy = 0;
		nameTab.add(whatLocusName, e);
		
		// e.gridx = 1;
		// nameTab.add(usageInstructions, e);

		// line 1
		// e.insets = new Insets(0,0,0,0);
		// e.gridx = 0;
		// e.gridy = 1;
		// nameTab.add(selectAllLabel, e);

		// line 2
		e.gridy = 2;
		e.gridwidth = 4;
		nameTab.add(namePanel, e);

		// line 3
		e.anchor = GridBagConstraints.WEST;
		// e.gridwidth = 1;
		e.gridy = 3;
		// nameTab.add(resetButtonName, e);

		// e.gridx = 1;
		nameTab.add(versionLociPanelName, e);

		// line 4
		e.anchor = GridBagConstraints.CENTER;
		e.gridx = 0;
		e.gridy = 4;
		nameTab.add(fileFormatName, e);

		// line 5
		e.anchor = GridBagConstraints.NORTH;
		e.weightx = 1;
		e.weighty = 1;
		e.gridy = 5;
		nameTab.add(resultsScrollPaneName, e);

		// line 6
		e.weightx = 0;
		e.weighty = 0;
		e.gridy = 6;
		nameTab.add(bottomButtonsName, e);

	/* Features tab */
		parentTabbedPane.addTab("Feature Comparison", null, featureTab, "Feature Comparison tool");

		// results textarea
		JScrollPane resultsScrollPaneFeature = new JScrollPane(resultsTextAreaFeature);
		resultsTextAreaFeature.setFont(new Font("Courier New", 0, 13));
		resultsScrollPaneFeature.setPreferredSize(new Dimension(950, 300));

		// buttons
		JButton submitButtonFeature = submitButtonGenerator.createSubmitButton("FEATURE");
		JButton exitButtonFeature = exitButtonGenerator.createExitButton();

		// submit/cancel buttons panel
		JPanel bottomButtonsFeature = new JPanel();
		bottomButtonsFeature.add(submitButtonFeature);
		bottomButtonsFeature.add(exitButtonFeature);

		// layout / add them to the nameTab
		featureTab.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0,0,10,0);
		g.weightx = 0.5;
		
		// line 0
		g.gridx = 0;
		g.gridy = 0;
		featureTab.add(whatLocus1Feature, g);
		
		g.gridx = 1;
		featureTab.add(whatVersion1Feature, g);

		// line 1
		g.gridx = 0;
		g.gridy = 1;
		featureTab.add(whatLocus2Feature, g);

		g.gridx = 1;
		featureTab.add(whatVersion2Feature, g);

		g.gridx = 2;
		featureTab.add(whichLociFeature, g);

		// line 2
		// g.gridy = 2;
		// g.gridwidth = 4;
		// nameTab.add(namePanel, g);

		// line 3
		// g.anchor = GridBagConstraints.WEST;
		// g.gridwidth = 1;
		// g.gridy = 3;
		// // nameTab.add(resetButtonName, g);

		// // g.gridx = 1;
		// featureTab.add(whatVersion1Feature, g);

		// line 4
		g.anchor = GridBagConstraints.CENTER;
		g.gridwidth = 4;
		g.gridx = 0;
		g.gridy = 4;
		featureTab.add(fileFormatFeature, g);

		// line 5
		g.anchor = GridBagConstraints.NORTH;
		g.weightx = 1;
		g.weighty = 1;
		g.gridy = 5;
		featureTab.add(resultsScrollPaneFeature, g);

		// line 6
		g.weightx = 0;
		g.weighty = 0;
		g.gridy = 6;
		featureTab.add(bottomButtonsFeature, g);

	/* Options tab */
		parentTabbedPane.addTab("Options", null, optionsTab, "Options");

		// local data only option/can java ping for internet access?

		// buttons
		JButton bulkDownloadButton = bulkDownloadButtonGenerator.createBulkDownloadButton();
		JButton resetPrefsButton = resetPrefsButtonGenerator.createResetPrefsButton();
		JButton exitButtonOptions = exitButtonGenerator.createExitButton();

		// layout / add them to the optionsTab
		optionsTab.setLayout(new GridBagLayout());
		GridBagConstraints f = new GridBagConstraints();
		f.anchor = GridBagConstraints.NORTHWEST;
		f.insets = new Insets(0,0,10,0);
		f.weightx = 0.5;
		
		// line 0
		f.gridx = 0;
		f.gridy = 0;
		optionsTab.add(bulkDownloadButton, f);

		f.gridx = 1;
		optionsTab.add(whichLociBulk, f);

		f.gridx = 2;
		optionsTab.add(whatVersionBulk, f);
		// line 1
		f.gridx = 0;
		f.gridy = 1;
		optionsTab.add(resetPrefsButton, f);


		// line 6
		f.weightx = 0;
		f.weighty = 0;
		f.gridy = 6;
		optionsTab.add(exitButtonOptions, f);


	/* Get and set open tab */
		try {
			parentTabbedPane.setSelectedIndex(prefs.getInt("GSG_OPEN_TAB", 0));
		} catch (IllegalArgumentException iex) { 
			System.out.println("GUI set selected Index error: " + iex); 
			PrefProbException ppex = new PrefProbException();
		}

		parentTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				prefs.putInt("GSG_OPEN_TAB", parentTabbedPane.getSelectedIndex());
			}
		});

	}

	public static void main(String args[]) {

		// System.setProperty("apple.laf.useScreenMenuBar","true");
		// System.setProperty("com.apple.mrj.application.apple.menu.about.name", "GFE Search Generator");
		// try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		// catch(Exception ex) {System.out.println("GSG: look and feel class name: " + ex); }

		// look and feel breaks the background on the labels and spacing on locus label
 		// try {
		// 	for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		// 		if ("Nimbus".equals(info.getName())) {
		// 			javax.swing.UIManager.setLookAndFeel(info.getClassName());
		// 			break;
		// 		}
		// 	}
		// } catch (Exception ex) { System.out.println(ex); }
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GSG().setVisible(true);
				
				// assign preference listeners for loci changes
				// LociPrefsListeners prefsListener = new LociPrefsListeners();
				// BulkLociPreferenceListener bulkLociPreferenceListener = new BulkLociPreferenceListener();
				// GfeLociPreferenceListener gfeLociPreferenceListener = new GfeLociPreferenceListener();

				// InternetAccess internet = new InternetAccess();

				// if(!internet.tester()) {
				// 	resultsTextAreaGfe.append("No internet access available, local data only");
				// 	resultsTextAreaName.append("No internet access available, local data only");
				// 	resultsTextAreaFeature.append("No internet access available, local data only");
				// }
			}
		});
	}
}
