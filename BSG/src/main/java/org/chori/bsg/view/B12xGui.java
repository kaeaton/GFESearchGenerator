package org.chori.bsg.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Console;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.prefs.Preferences;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import org.chori.bsg.view.searchboxes.*;

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
	JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

	// the tabs, added initially so I can make them 
	// public, static and update them
    public static JPanel hlaGfeTab = new JPanel();
	public static JPanel kirGfeTab = new JPanel();
	public static JPanel nameGfeTab = new JPanel();
	public static JPanel optionsGfeTab = new JPanel();

	// the holder panels
	// they're embedded in the layout, with contents to be changed
	public static JPanel hlaPanel = new JPanel();

	// combo boxes for locus and version selection
	public static JComboBox whatLocusHla = whatLocusGenerator.createWhatLocusComboBox("HLA");
	public static JComboBox whatVersionHla = whatVersionGenerator.createWhatVersionComboBox("HLA");

	// file format panels
	public static JPanel fileFormatHla = fileFormatPanelGenerator.createFileFormatPanel("HLA");

	// results text areas
	public static JTextArea resultsTextAreaHla = new JTextArea();

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

    /* reusable */

    	// labels HLA and KIR
    	JLabel selectAllLabel = new JLabel("Check all");
    	JTextArea usageInstructions = new JTextArea("Enter in the terms you are looking for. (Zero represents unsequenced data, and is a valid term.) Empty boxes function as wildcards."
    												+ "\nChecking a box will prevent any results containing the number zero (an unsequenced feature) in that feature.");
    	usageInstructions.setBackground(hlaPanel.getBackground());
    	usageInstructions.setEditable(false);
    	usageInstructions.setFocusable(false);

    	// buttons
    	JButton cancelButton = cancelButtonGenerator.createCancelButton();

    /* HLA GFE tab */

    	// add panel to tab pane
    	parentTabbedPane.addTab("HLA GFE Search", null, hlaGfeTab, "HLA GFE Search tool");
    	
    	// generate the HLA GFE panel
    	JPanel currentHlaPanel = hlaPanelGenerator.generateHlaPanel(hlaSelectedLocus);
    	currentHlaPanel.setName("HLA-GFE");
    	hlaPanel.add(currentHlaPanel);

    	
    	// results textarea
    	JScrollPane resultsScrollPaneHla = new JScrollPane(resultsTextAreaHla);
    	resultsTextAreaHla.setPreferredSize(new Dimension(950, 300));
    	resultsTextAreaHla.setFont(new Font("Courier New", 0, 13));

    	// buttons
    	JButton resetButtonHla = resetButtonGenerator.createResetButton("HLA");
    	JButton submitButtonHla = submitButtonGenerator.createSubmitButton("HLA");

    	// submit/cancel buttons panel
    	JPanel bottomButtons = new JPanel();
    	bottomButtons.add(submitButtonHla);
    	bottomButtons.add(cancelButton);

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
		hlaGfeTab.add(usageInstructions, c);

		// line 1
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		hlaGfeTab.add(selectAllLabel, c);

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
		hlaGfeTab.add(bottomButtons, c);

    /* KIR GFE tab */
    	parentTabbedPane.addTab("KIR GFE Search", null, kirGfeTab, "KIR GFE Search tool");

    /* Name Search tab */
    	parentTabbedPane.addTab("Name Search", null, nameGfeTab, "HLA Name Search tool");

    /* Options tab */
    	parentTabbedPane.addTab("Options", null, optionsGfeTab, "Options");

	}

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Console c = System.console();
            	System.out.println("Hello World");
                new B12xGui().setVisible(true);
            }
        });
    }
}
