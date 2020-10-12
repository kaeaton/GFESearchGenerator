package org.chori.gsg.gui.nameTab;

import java.awt.Component;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
// import javax.swing.UIManager;

import org.chori.gsg.gui.*;
import org.chori.gsg.gui.buttons.*;
import org.chori.gsg.gui.dropdownMenus.*;
import org.chori.gsg.gui.dropdownMenus.whichLociType.*;
import org.chori.gsg.gui.dropdownMenus.whatLocus.*;
import org.chori.gsg.gui.dropdownMenus.whatVersion.*;
import org.chori.gsg.gui.gfeTab.gfeSearchPanels.*;

public class NameTab extends JPanel {

    private Preferences prefs = Preferences.userNodeForPackage(GSG.class);

    // default locus settings
    private String gfeSelectedLoci = prefs.get("GSG_GFE_LOCI_STRING", "HLA");
    private String gfeSelectedLocus = prefs.get("GSG_HLA_LOCUS_STRING", "HLA-A");

    // component generators
    private static WhatLocusFactory whatLocusFactory = WhatLocusFactory.getWhatLocusFactoryInstance();
    private static WhatVersionFactory whatVersionFactory = WhatVersionFactory.getWhatVersionFactoryInstance();
    private static WhichLociFactory whichLociFactory = WhichLociFactory.getWhichLociFactoryInstance();
    private static ResetButton resetButtonGenerator = new ResetButton();
    private static FileFormatPanel fileFormatPanelGenerator = new FileFormatPanel();
    private static ExitButton exitButtonGenerator = new ExitButton();
    private static SubmitButton submitButtonGenerator = new SubmitButton();
    // private static ResetPrefsButton resetPrefsButtonGenerator = new ResetPrefsButton();

    public static JTextArea resultsTextAreaName = new JTextArea();
    public static JPanel fileFormatName = fileFormatPanelGenerator.getFileFormatPanel("NAME");
    public static JPanel namePanel = new JPanel();

    // combo boxes for locus and version selection
    public static JComboBox whatVersionName = new JComboBox();
    public static JComboBox whatLocusName = new JComboBox();
    public static JComboBox whichLociName = new JComboBox();

    // search box for name search
    public static JTextField nameSearchBox = new JTextField("", 20);
    
    private static final NameTab INSTANCE = new NameTab();

    private NameTab() { 
        // the JPanel to return
        // JPanel gfeTab = new JPanel();

        // the holder panel - embedded in the layout, with contents to be changed
        // JPanel gfePanel = new JPanel();
        // JPanel currentGfePanel = gfePanelGenerator.makeGfePanel("HLA-A");

        whichLociName = whichLociFactory.createWhichLoci("NAME").createWhichLociComboBox();
        whatVersionName = whatVersionFactory.createWhatVersion("NAME").createWhatVersionComboBox();
        whatLocusName = whatLocusFactory.createWhatLocus("NAME").createWhatLocusComboBox(whatVersionName.getSelectedItem().toString(), prefs.get("GSG_NAME_LOCI_STRING", "HLA"));
    }

    public static NameTab getNameTabInstance() {
        return INSTANCE;
    }

    public JPanel assembleGfeTab() {

        // currentGfePanel.setName("NAME");
        namePanel.add(nameSearchBox);
        nameSearchBox.addActionListener(submitButtonGenerator.nameListener);
        
        // results textarea
        JScrollPane resultsScrollPaneName = new JScrollPane(resultsTextAreaName);
        resultsTextAreaName.setFont(new Font("Courier New", 0, 13));
        resultsScrollPaneName.setPreferredSize(new Dimension(950, 400));

        // buttons
        JButton submitButtonName = submitButtonGenerator.createSubmitButton("NAME");
        JButton exitButtonName = exitButtonGenerator.createExitButton();

        // submit/cancel buttons panel
        JPanel bottomButtonsName = new JPanel();
        bottomButtonsName.add(submitButtonName);
        bottomButtonsName.add(exitButtonName);

        // version/loci dropdowns
        JPanel versionLociPanelName = new JPanel();
        versionLociPanelName.add(whatVersionName);
        versionLociPanelName.add(whichLociName);

        // layout / add them to the this
        this.setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();
        e.anchor = GridBagConstraints.NORTHWEST;
        e.insets = new Insets(0,0,10,0);
        e.weightx = 0.5;
        
        // line 0
        e.gridx = 0;
        e.gridy = 0;
        this.add(whatLocusName, e);
        
        // e.gridx = 1;
        // this.add(usageInstructions, e);

        // line 1
        // e.insets = new Insets(0,0,0,0);
        // e.gridx = 0;
        // e.gridy = 1;
        // this.add(selectAllLabel, e);

        // line 2
        e.gridy = 2;
        e.gridwidth = 4;
        this.add(namePanel, e);

        // line 3
        e.anchor = GridBagConstraints.WEST;
        // e.gridwidth = 1;
        e.gridy = 3;
        // this.add(resetButtonName, e);

        // e.gridx = 1;
        this.add(versionLociPanelName, e);

        // line 4
        e.anchor = GridBagConstraints.CENTER;
        e.gridx = 0;
        e.gridy = 4;
        this.add(fileFormatName, e);

        // line 5
        e.anchor = GridBagConstraints.NORTH;
        e.weightx = 1;
        e.weighty = 1;
        e.gridy = 5;
        this.add(resultsScrollPaneName, e);

        // line 6
        e.weightx = 0;
        e.weighty = 0;
        e.gridy = 6;
        this.add(bottomButtonsName, e);

        return this;
    }

    private String whatLocusShouldInitiallyLoad() {
        String whichLoci = prefs.get("GSG_GFE_LOCI_STRING", "HLA");
        String whatLocus = "HLA-A";

        if(whichLoci == "HLA") {
            whatLocus = prefs.get("GSG_GFE_HLA_LOCUS_STRING", "HLA-A");
        } else if (whichLoci == "KIR") {
            whatLocus = prefs.get("GSG_GFE_KIR_LOCUS_STRING", "KIR2DL4");
        }

        return whatLocus;
    }

}

