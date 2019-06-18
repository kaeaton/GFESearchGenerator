package org.chori.bsg.view;

// import org.chori.bsg.view.searchbox.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.prefs.Preferences;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class B12xGui extends JFrame {


	Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	String hlaSelectedLocus = "HLA-A";
	String kirSelectedLocus = "KIR2DL4";
	
	HlaSearchBoxGenerator hlaPanelGenerator = new HlaSearchBoxGenerator();
		
	JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

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

    	// tabbed pane
    	parentTabbedPane.setPreferredSize(new Dimension(1000, 600));

    	// HLA GFE tab. layout? BorderLayout.WEST
    	JPanel hlaGfeTab = new JPanel();
    	parentTabbedPane.addTab("HLA GFE Search", null, hlaGfeTab, "HLA GFE Search tool");

    	hlaGfeTab.add(hlaPanelGenerator.generateHlaPanel("HLA-DRB1"), BorderLayout.WEST);

    	// KIR GFE tab
    	JPanel kirGfeTab = new JPanel();
    	parentTabbedPane.addTab("KIR GFE Search", null, kirGfeTab, "KIR GFE Search tool");

    	// Name Search tab
    	JPanel nameGfeTab = new JPanel();
    	parentTabbedPane.addTab("Name Search", null, nameGfeTab, "HLA Name Search tool");

    	// Options tab
    	JPanel optionsGfeTab = new JPanel();
    	parentTabbedPane.addTab("Options", null, optionsGfeTab, "Options");

	}

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B12xGui().setVisible(true);
            }
        });
    }
}
