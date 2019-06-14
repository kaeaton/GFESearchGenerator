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

public class B12xGUI extends javax.swing.JFrame {


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
    public B12xGUI() {
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /* 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(B12xGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(B12xGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(B12xGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(B12xGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B12xGUI().setVisible(true);
            }
        });
    }
}