package org.chori.gsg.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.prefs.Preferences;

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

import org.chori.gsg.view.searchboxes.*;

public class B12xGui extends JFrame {


	private Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	
	// default locus settings
	String hlaSelectedLocus = "HLA-A";
	String kirSelectedLocus = "KIR2DL4";
	
	// the panel generators
	private HlaSearchBoxGenerator hlaPanelGenerator = new HlaSearchBoxGenerator();
	
	// component generators
	private static WhatLocus whatLocusGenerator = new WhatLocus();
	private static WhatVersion whatVersionGenerator = new WhatVersion();
	private static ResetButton resetButtonGenerator = new ResetButton();
	private static FileFormatPanel fileFormatPanelGenerator = new FileFormatPanel();
	private static CancelButton cancelButtonGenerator = new CancelButton();
	private static SubmitButton submitButtonGenerator = new SubmitButton();

	// need this to add at initialization
	public JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

	// the tabs, added initially so I can make them 
	// public, static and update them
    public static JPanel hlaGfeTab = new JPanel();
	public static JPanel kirGfeTab = new JPanel();
	public static JPanel nameGfeTab = new JPanel();
	public static JPanel optionsGfeTab = new JPanel();

	// the holder panels
	// they're embedded in the layout, with contents to be changed
	public static JPanel hlaPanel = new JPanel();
	public static JPanel namePanel = new JPanel();

	// combo boxes for locus and version selection
	public static JComboBox whatLocusHla = whatLocusGenerator.createWhatLocusComboBox("HLA");
	public static JComboBox whatVersionHla = whatVersionGenerator.createWhatVersionComboBox("HLA");
	public static JComboBox whatLocusName = whatLocusGenerator.createWhatLocusComboBox("NAME");
	public static JComboBox whatVersionName = whatVersionGenerator.createWhatVersionComboBox("NAME");
	
	// file format panels
	public static JPanel fileFormatHla = fileFormatPanelGenerator.createFileFormatPanel("HLA");
	public static JPanel fileFormatName = fileFormatPanelGenerator.createFileFormatPanel("NAME");

	// results text areas
	public static JTextArea resultsTextAreaHla = new JTextArea();
	public static JTextArea resultsTextAreaName = new JTextArea();

    /**
     * Creates new form B12xGUI
     */ 
    public B12xGui() {

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
    	parentTabbedPane.addTab("HLA GFE Search", null, hlaGfeTab, "HLA GFE Search tool");
    	
    	// generate the HLA GFE panel
    	JPanel currentHlaPanel = hlaPanelGenerator.generateHlaPanel(hlaSelectedLocus);
    	currentHlaPanel.setName("HLA-GFE");
    	hlaPanel.add(currentHlaPanel);
    	
    	// results textarea
    	JScrollPane resultsScrollPaneHla = new JScrollPane(resultsTextAreaHla);
    	resultsTextAreaHla.setFont(new Font("Courier New", 0, 13));
    	resultsScrollPaneHla.setPreferredSize(new Dimension(950, 300));
    	// resultsTextAreaHla.setEditable(false);

		// labels
    	JLabel selectAllLabelHla = new JLabel("Check all");
    	JTextArea usageInstructionsHla = new JTextArea("Enter in the terms you are looking for. (Zero represents unsequenced data, and is a valid term.) Empty boxes function as wildcards."
    												+ "\nChecking a box will prevent any results containing the number zero (an unsequenced feature) in that feature.");
    	usageInstructionsHla.setBackground(hlaPanel.getBackground());
    	usageInstructionsHla.setEditable(false);
    	usageInstructionsHla.setFocusable(false);

    	// buttons
    	JButton resetButtonHla = resetButtonGenerator.createResetButton("HLA");
    	JButton submitButtonHla = submitButtonGenerator.createSubmitButton("HLA");
    	JButton cancelButtonHla = cancelButtonGenerator.createCancelButton();

    	// submit/cancel buttons panel
    	JPanel bottomButtonsHla = new JPanel();
    	bottomButtonsHla.add(submitButtonHla);
    	bottomButtonsHla.add(cancelButtonHla);

    	// layout / add them to the hlaGfeTab
    	hlaGfeTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(0,0,10,0);
		c.weightx = 0.5;
		
		// line 0
		c.gridx = 0;
		c.gridy = 0;
		hlaGfeTab.add(whatLocusHla, c);
		
		c.gridx = 1;
		hlaGfeTab.add(usageInstructionsHla, c);

		// line 1
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		hlaGfeTab.add(selectAllLabelHla, c);

		// line 2
		c.gridy = 2;
		c.gridwidth = 4;
		hlaGfeTab.add(hlaPanel, c);

		// line 3
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.gridy = 3;
		hlaGfeTab.add(resetButtonHla, c);

		c.gridx = 1;
		hlaGfeTab.add(whatVersionHla, c);

		// line 4
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 4;
		hlaGfeTab.add(fileFormatHla, c);

		// line 5
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 5;
		hlaGfeTab.add(resultsScrollPaneHla, c);

		// line 6
		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 6;
		hlaGfeTab.add(bottomButtonsHla, c);

    /* KIR GFE tab */
    	// parentTabbedPane.addTab("KIR GFE Search", null, kirGfeTab, "KIR GFE Search tool");

    /* Name Search tab */
    	parentTabbedPane.addTab("Name Search", null, nameGfeTab, "HLA Name Search tool");
    	
    	// generate the HLA GFE panel
    	JTextField nameSearchBox = new JTextField("", 20);
    	// currentHlaPanel.setName("NAME");
    	namePanel.add(nameSearchBox);
    	
    	// results textarea
    	JScrollPane resultsScrollPaneName = new JScrollPane(resultsTextAreaName);
    	resultsTextAreaName.setFont(new Font("Courier New", 0, 13));
    	resultsScrollPaneName.setPreferredSize(new Dimension(950, 300));
    	// resultsTextAreaHla.setEditable(false);

    	// buttons
    	JButton resetButtonName = resetButtonGenerator.createResetButton("NAME");
    	JButton submitButtonName = submitButtonGenerator.createSubmitButton("NAME");
    	JButton cancelButtonName = cancelButtonGenerator.createCancelButton();

    	// submit/cancel buttons panel
    	JPanel bottomButtonsName = new JPanel();
    	bottomButtonsName.add(submitButtonName);
    	bottomButtonsName.add(cancelButtonName);

    	// layout / add them to the hlaGfeTab
    	nameGfeTab.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.anchor = GridBagConstraints.NORTHWEST;
		e.insets = new Insets(0,0,10,0);
		e.weightx = 0.5;
		
		// line 0
		e.gridx = 0;
		e.gridy = 0;
		nameGfeTab.add(whatLocusName, e);
		
		// e.gridx = 1;
		// nameGfeTab.add(usageInstructions, e);

		// line 1
		// e.insets = new Insets(0,0,0,0);
		// e.gridx = 0;
		// e.gridy = 1;
		// nameGfeTab.add(selectAllLabel, e);

		// line 2
		e.gridy = 2;
		e.gridwidth = 4;
		nameGfeTab.add(namePanel, e);

		// line 3
		e.anchor = GridBagConstraints.WEST;
		e.gridwidth = 1;
		e.gridy = 3;
		nameGfeTab.add(resetButtonName, e);

		e.gridx = 1;
		nameGfeTab.add(whatVersionName, e);

		// line 4
		e.anchor = GridBagConstraints.CENTER;
		e.gridwidth = 4;
		e.gridx = 0;
		e.gridy = 4;
		nameGfeTab.add(fileFormatName, e);

		// line 5
		e.anchor = GridBagConstraints.NORTH;
		e.weightx = 1;
		e.weighty = 1;
		e.gridy = 5;
		nameGfeTab.add(resultsScrollPaneName, e);

		// line 6
		e.weightx = 0;
		e.weighty = 0;
		e.gridy = 6;
		nameGfeTab.add(bottomButtonsName, e);

    /* Options tab */
    	// parentTabbedPane.addTab("Options", null, optionsGfeTab, "Options");

	}

    public static void main(String args[]) {
  //   	try {
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
            }
        });
    }
}
