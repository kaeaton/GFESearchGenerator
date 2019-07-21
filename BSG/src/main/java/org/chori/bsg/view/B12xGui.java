package org.chori.bsg.view;

// import org.chori.bsg.view.searchbox.*;

// import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

public class B12xGui extends JFrame {


	Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	String hlaSelectedLocus = "HLA-A";
	String kirSelectedLocus = "KIR2DL4";
	
	// the panel generators
	HlaSearchBoxGenerator hlaPanelGenerator = new HlaSearchBoxGenerator();
	
	// component generators
	WhatLocus whatLocusGenerator = new WhatLocus();

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

    /**
     * Creates new form B12xGUI
     */ 
    public B12xGui() {
		

		// jFrame settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1060,640));
		this.add(parentTabbedPane);
		this.pack();
		this.setLocationRelativeTo(null);


        initComponents();
    }

    private void initComponents() {

    /* tabbed pane */
    	parentTabbedPane.setPreferredSize(new Dimension(1000, 600));

    /* HLA GFE tab */

    	// set layout
		GroupLayout layout = new GroupLayout(hlaGfeTab);
		hlaGfeTab.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

    	// add panel to tab pane
    	parentTabbedPane.addTab("HLA GFE Search", null, hlaGfeTab, "HLA GFE Search tool");
    	
    	// generate the HLA GFE panel
    	JPanel currentHlaPanel = hlaPanelGenerator.generateHlaPanel(hlaSelectedLocus);
    	currentHlaPanel.setName("HLA-GFE");
    	hlaPanel.add(currentHlaPanel);

    	// combo box for locus selection
    	JComboBox whatLocus = whatLocusGenerator.createWhatLocusComboBox("HLA");

		// layout horizontal: all in one group so they stack
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				// .getContainerGap(whatLocus, SwingConstants.WEST, hlaGfeTab)
				.addComponent(whatLocus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

				// .getContainerGap(currentHlaPanel, SwingConstants.WEST, hlaGfeTab)
				.addComponent(hlaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		// layout vertical: separate groups so they stack
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				// .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				// .getContainerGap(whatLocus, SwingConstants.NORTH, hlaGfeTab)
				.addComponent(whatLocus))
				// .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				// .addComponent(selectAllCheckBoxes))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				// .getContainerGap(currentHlaPanel, SwingConstants.NORTH, hlaGfeTab)
				.addComponent(hlaPanel)));


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
