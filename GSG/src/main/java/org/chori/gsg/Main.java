package org.chori.gsg;

import javax.swing.UIManager;

import org.chori.gsg.gui.Gui;


public class Main {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch(Exception ex) { System.out.println("Problem setting look and feel: " + ex); }

                new Gui().setVisible(true);
            }
        });
    }
}