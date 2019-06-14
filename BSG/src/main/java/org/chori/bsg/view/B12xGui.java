package org.chori.bsg.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
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
import javax.swing.JTextField;

public class B12xGui extends JFrame {


	Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	// TextIcon t1;
 //    RotatedIcon r1;
	String hlaSelectedLocus = "HLA-A";
	String kirSelectedLocus = "KIR2DL4";
	
	// Exons per locus
	HashMap<String, String> hlaExonTotal = new HashMap();
		
	
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
        initComponents();
    }

    private void initComponents() {
    	
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
