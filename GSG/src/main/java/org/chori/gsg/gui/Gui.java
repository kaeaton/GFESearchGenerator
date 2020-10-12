package org.chori.gsg.gui;

import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.chori.gsg.gui.gfeTab.GfeTab;
import org.chori.gsg.gui.nameTab.NameTab;
import org.chori.gsg.gui.optionsTab.OptionsTab;
import org.chori.gsg.utilities.Prefs;

public class Gui extends JFrame {
    public static JTabbedPane parentTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    private GfeTab gfeTab = GfeTab.getGfeTabInstance();
    private NameTab nameTab = NameTab.getNameTabInstance();
    private OptionsTab optionsTab = OptionsTab.getOptionsTabInstance();

    public Gui() {
        // jFrame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1060,740));
        this.add(parentTabbedPane);
        
        // gfe search tab
        parentTabbedPane.addTab("GFE Search", null, gfeTab.assembleGfeTab(), "Search via GFE, or parts thereof");
        
        // name search tab
        parentTabbedPane.addTab("Name Search", null, nameTab, "Search via name to get GFE");
        
        // options tab
        parentTabbedPane.addTab("Options", null, optionsTab, "Program options");
        
        // help tab
        // parentTabbedPane.addTab("Help", null, helpTab, "Help");

        this.pack();
        this.setLocationRelativeTo(null);

        /* Get and set open tab */
        try {
            parentTabbedPane.setSelectedIndex(Prefs.getCurrentTab());
        } catch (Exception ex) { 
            System.out.println("GUI set selected Index error: " + ex); 
            // PrefProbException ppex = new PrefProbException();
        }

        parentTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Prefs.setCurrentTab(parentTabbedPane.getSelectedIndex());
            }
        });
    }
}