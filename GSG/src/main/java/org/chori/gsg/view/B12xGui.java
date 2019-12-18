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
import javax.swing.UIManager;

import org.chori.gsg.exceptions.*;
import org.chori.gsg.model.*;
import org.chori.gsg.view.buttons.*;
import org.chori.gsg.view.dropdowns.*;
import org.chori.gsg.view.prefslisteners.*;
import org.chori.gsg.view.searchboxes.*;

public class B12xGui extends JFrame {

	private Preferences prefs = Preferences.userNodeForPackage(this.getClass());

	// default locus settings
	private String gfeSelectedLocus = prefs.get("GSG_HLA_LOCUS_STRING", "HLA-A");
	private String nameSelectedLocus = prefs.get("GSG_NAME_VERSION_1", "HLA-A");
	private String kirSelectedLocus = prefs.get("GSG_KIR_LOCUS_STRING", "KIR2DL4");

	// the panel generators
	private HlaSearchBoxAssembler hlaPanelGenerator = new HlaSearchBoxAssembler();
	
	// component generators
	private static WhatLocus whatLocusGenerator = new WhatLocus();
	private static WhatVersion whatVersionGenerator = new WhatVersion();
	private static WhichLoci whichLociGenerator = new WhichLoci();
	private static ResetButton resetButtonGenerator = new ResetButton();
	private static FileFormatPanel fileFormatPanelGenerator = new FileFormatPanel();
	private static CancelButton cancelButtonGenerator = new CancelButton();
	private static SubmitButton submitButtonGenerator = new SubmitButton();
	private static ResetPrefsButton resetPrefsButtonGenerator = new ResetPrefsButton();
	private static BulkDownloadButton bulkDownloadButtonGenerator = new BulkDownloadButton();

	// need this to add at initialization
	public static JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

	// the tabs, added initially so I can make them 
	// public, static and update them
	public static JPanel gfeTab = new JPanel();
	public static JPanel nameTab = new JPanel();
	public static JPanel featureTab = new JPanel();
	public static JPanel optionsTab = new JPanel();

	// the holder panels
	// they're embedded in the layout, with contents to be changed
	public static JPanel gfePanel = new JPanel();
	public static JPanel namePanel = new JPanel();

	// results text areas
	public static JTextArea resultsTextAreaGfe = new JTextArea();
	public static JTextArea resultsTextAreaName = new JTextArea();
	public static JTextArea resultsTextAreaFeature = new JTextArea();
	
	// combo boxes for locus and version selection
	public static JComboBox whatVersionGfe = new JComboBox();
	public static JComboBox whatLocusGfe = new JComboBox();
	public static JComboBox whichLociGfe = new JComboBox();
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
	public static JPanel fileFormatGfe = fileFormatPanelGenerator.createFileFormatPanel("GFE");
	public static JPanel fileFormatName = fileFormatPanelGenerator.createFileFormatPanel("NAME");
	public static JPanel fileFormatFeature = fileFormatPanelGenerator.createFileFormatPanel("FEATURE");

	// search box for name search
	public static JTextField nameSearchBox = new JTextField("", 20);
	
	public B12xGui() {
		try {

			// error handling: if you have no data and no internet
			// the program doesn't work
			InternetAccess internet = new InternetAccess();
			LocalData localData = new LocalData();

			if(!internet.tester() && localData.localVersionData() == null) {
				throw new NoInternetOrDataException();
			}

			whatVersionGfe = whatVersionGenerator.createWhatVersionComboBox("GFE");
			whatLocusGfe = whatLocusGenerator.createWhatLocusComboBox("GFE", whatVersionGfe.getSelectedItem().toString());
			whichLociGfe = whichLociGenerator.createWhichLociComboBox("GFE");
			whatVersionName = whatVersionGenerator.createWhatVersionComboBox("NAME");
			whatLocusName = whatLocusGenerator.createWhatLocusComboBox("NAME", whatVersionName.getSelectedItem().toString());
			whichLociName = whichLociGenerator.createWhichLociComboBox("NAME");
			whichLociFeature = whichLociGenerator.createWhichLociComboBox("FEATURE");
			whatVersionBulk = whatVersionGenerator.createWhatVersionComboBox("BULK");
			whichLociBulk = whichLociGenerator.createWhichLociComboBox("BULK");


		} catch (NoInternetOrDataException ex) { 
			System.exit(0);
		} catch (Exception ex) {
			System.out.println("B12xGui initialization: " + ex);
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

	/* HLA GFE tab */

		// add panel to tab pane
		parentTabbedPane.addTab("GFE Search", null, gfeTab, "GFE Search tool");
		
		// generate the HLA GFE panel
		try {
			System.out.println("Generating the initial gfePanel using whatLocusGfe: " + whatLocusGfe.getSelectedItem().toString());
			JPanel currentHlaPanel = hlaPanelGenerator.assembleHlaPanel(whatLocusGfe.getSelectedItem().toString());
			currentHlaPanel.setName("GFE");
			gfePanel.add(currentHlaPanel);
		} catch (IllegalArgumentException iex) {
			PrefProbException ppex = new PrefProbException();
		}

		// results textarea
		JScrollPane resultsScrollPaneGfe = new JScrollPane(resultsTextAreaGfe);
		resultsTextAreaGfe.setFont(new Font("Courier New", 0, 13));
		resultsScrollPaneGfe.setPreferredSize(new Dimension(950, 300));

		// labels
		JLabel selectAllLabelGfe = new JLabel("Check all");
		JTextArea usageInstructionsGfe = new JTextArea("Enter in the terms you are looking for. (Zero represents unsequenced data, and is a valid term.) Empty boxes function as wildcards."
													+ "\nChecking a box will prevent any results containing the number zero (an unsequenced feature) in that feature.");
		usageInstructionsGfe.setBackground(gfePanel.getBackground());
		usageInstructionsGfe.setEditable(false);
		usageInstructionsGfe.setFocusable(false);

		// buttons
		JButton resetButtonGfe = resetButtonGenerator.createResetButton("GFE");
		JButton submitButtonGfe = submitButtonGenerator.createSubmitButton("GFE");
		JButton cancelButtonGfe = cancelButtonGenerator.createCancelButton();

		// submit/cancel buttons panel
		JPanel bottomButtonsGfe = new JPanel();
		bottomButtonsGfe.add(submitButtonGfe);
		bottomButtonsGfe.add(cancelButtonGfe);

		// version/loci dropdowns
		JPanel versionLociPanelGfe = new JPanel();
		versionLociPanelGfe.add(resetButtonGfe);
		versionLociPanelGfe.add(whatVersionGfe);
		versionLociPanelGfe.add(whichLociGfe);

		// layout / add them to the gfeTab
		gfeTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(0,0,10,0);
		c.weightx = 0.5;
		
		// line 0
		c.gridx = 0;
		c.gridy = 0;
		gfeTab.add(whatLocusGfe, c);
		
		c.gridx = 1;
		gfeTab.add(usageInstructionsGfe, c);

		// line 1
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		gfeTab.add(selectAllLabelGfe, c);

		// line 2
		c.gridy = 2;
		c.gridwidth = 4;
		gfeTab.add(gfePanel, c);

		// line 3
		c.anchor = GridBagConstraints.WEST;
		// c.gridwidth = 1;
		c.gridy = 3;
		gfeTab.add(versionLociPanelGfe, c);
		// gfeTab.add(resetButtonGfe, c);

		// c.gridx = 1;
		// gfeTab.add(whatVersionGfe, c);

		// c.anchor = GridBagConstraints.CENTER;
		// c.gridx = 2;
		// gfeTab.add(whichLociGfe, c);

		// line 4
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 4;
		gfeTab.add(fileFormatGfe, c);

		// line 5
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 5;
		gfeTab.add(resultsScrollPaneGfe, c);

		// line 6
		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 6;
		gfeTab.add(bottomButtonsGfe, c);

	/* Name Search tab */
		parentTabbedPane.addTab("Name Search", null, nameTab, "Name Search tool");
		
		// currentHlaPanel.setName("NAME");
		namePanel.add(nameSearchBox);
		nameSearchBox.addActionListener(submitButtonGenerator.nameListener);
		
		// results textarea
		JScrollPane resultsScrollPaneName = new JScrollPane(resultsTextAreaName);
		resultsTextAreaName.setFont(new Font("Courier New", 0, 13));
		resultsScrollPaneName.setPreferredSize(new Dimension(950, 300));
		// resultsTextAreaGfe.setEditable(false);

		// buttons
		JButton resetButtonName = resetButtonGenerator.createResetButton("NAME");
		JButton submitButtonName = submitButtonGenerator.createSubmitButton("NAME");
		JButton cancelButtonName = cancelButtonGenerator.createCancelButton();

		// submit/cancel buttons panel
		JPanel bottomButtonsName = new JPanel();
		bottomButtonsName.add(submitButtonName);
		bottomButtonsName.add(cancelButtonName);

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
		JButton cancelButtonFeature = cancelButtonGenerator.createCancelButton();

		// submit/cancel buttons panel
		JPanel bottomButtonsFeature = new JPanel();
		bottomButtonsFeature.add(submitButtonFeature);
		bottomButtonsFeature.add(cancelButtonFeature);

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
		JButton cancelButtonOptions = cancelButtonGenerator.createCancelButton();

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
		optionsTab.add(cancelButtonOptions, f);


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
		// catch(Exception ex) {System.out.println("B12xGui: look and feel class name: " + ex); }

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
				new B12xGui().setVisible(true);
				
				// assign preference listeners for loci changes
				LociPrefsListeners prefsListener = new LociPrefsListeners();
				BulkLociPreferenceListener bulkLociPreferenceListener = new BulkLociPreferenceListener();
				GfeLociPreferenceListener gfeLociPreferenceListener = new GfeLociPreferenceListener();

				InternetAccess internet = new InternetAccess();

				if(!internet.tester()) {
					resultsTextAreaGfe.append("No internet access available, local data only");
					resultsTextAreaName.append("No internet access available, local data only");
					resultsTextAreaFeature.append("No internet access available, local data only");
				}
			}
		});
	}
}
