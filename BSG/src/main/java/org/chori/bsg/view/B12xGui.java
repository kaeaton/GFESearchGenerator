package org.chori.bsg.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
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
	// TextIcon t1;
 	// RotatedIcon r1;
	String hlaSelectedLocus = "HLA-A";
	String kirSelectedLocus = "KIR2DL4";
	
	// Exons per locus
	HashMap<String, String> hlaExonTotal = new HashMap();
		
	JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    /**
     * Creates new form B12xGUI
     */ 
    public B12xGui() {
		hlaExonTotal.put("HLA-A", "8");
		hlaExonTotal.put("HLA-B", "7");
		hlaExonTotal.put("HLA-C", "8");
		hlaExonTotal.put("HLA-DPA1", "4");
		hlaExonTotal.put("HLA-DPB1", "5");
		hlaExonTotal.put("HLA-DQA1", "4");
		hlaExonTotal.put("HLA-DQB1", "6");
		hlaExonTotal.put("HLA-DRB1", "6");
		hlaExonTotal.put("HLA-DRB3", "6");
		hlaExonTotal.put("HLA-DRB4", "6");
		hlaExonTotal.put("HLA-DRB5", "6");
		hlaExonTotal.put("TEST", "test");

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
    	// JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    	parentTabbedPane.setPreferredSize(new Dimension(1000, 600));

    	// HLA GFE tab
    	JPanel hlaGfeTab = new JPanel();
    	parentTabbedPane.addTab("HLA GFE Search", null, hlaGfeTab, "HLA GFE Search tool");

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
