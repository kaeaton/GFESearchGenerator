package org.chori.gsg.gui.optionsTab;

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
import org.chori.gsg.gui.gfeTab.GfeTab;

public class OptionsTab extends JPanel {

    private Preferences prefs = Preferences.userNodeForPackage(GSG.class);

    // default locus settings
    private String gfeSelectedLoci = prefs.get("GSG_GFE_LOCI_STRING", "HLA");
    private String gfeSelectedLocus = prefs.get("GSG_HLA_LOCUS_STRING", "HLA-A");

    // component generators
    private GfeSearchPanelAssembler gfePanelGenerator = new GfeSearchPanelAssembler();
    private static WhatLocusFactory whatLocusFactory = WhatLocusFactory.getWhatLocusFactoryInstance();
    private static WhatVersionFactory whatVersionFactory = WhatVersionFactory.getWhatVersionFactoryInstance();
    private static WhichLociFactory whichLociFactory = WhichLociFactory.getWhichLociFactoryInstance();
    private static ResetButton resetButtonGenerator = new ResetButton();
    private static FileFormatPanel fileFormatPanelGenerator = new FileFormatPanel();
    private static ExitButton exitButtonGenerator = new ExitButton();
    private static SubmitButton submitButtonGenerator = new SubmitButton();
    private static ResetPrefsButton resetPrefsButtonGenerator = new ResetPrefsButton();
    private static BulkDownloadButton bulkDownloadButtonGenerator = new BulkDownloadButton();

    // the JPanel to return
    private JPanel gfeTab = new JPanel();

    // the holder panel - embedded in the layout, with contents to be changed
    private JPanel gfePanel = new JPanel();
    private JPanel currentGfePanel = gfePanelGenerator.makeGfePanel("HLA-A");


    // combo boxes for locus and version selection
    public static JComboBox whatVersionBulk = new JComboBox();
    public static JComboBox whichLociBulk = new JComboBox();

    private static final OptionsTab INSTANCE = new OptionsTab();

    private OptionsTab() { 
        // the JPanel to return
        // JPanel gfeTab = new JPanel();

        // the holder panel - embedded in the layout, with contents to be changed
        // JPanel gfePanel = new JPanel();
        // JPanel currentGfePanel = gfePanelGenerator.makeGfePanel("HLA-A");

        whichLociBulk = whichLociFactory.createWhichLoci("BULK").createWhichLociComboBox();
        whatVersionBulk = whatVersionFactory.createWhatVersion("BULK").createWhatVersionComboBox();
    }

    public static OptionsTab getOptionsTabInstance() {
        return INSTANCE;
    }

    public JPanel assembleOptionsTab() {

        // local data only option/can java ping for internet access?

        // buttons
        JButton bulkDownloadButton = bulkDownloadButtonGenerator.createBulkDownloadButton();
        JButton resetPrefsButton = resetPrefsButtonGenerator.createResetPrefsButton();
        JButton exitButtonOptions = exitButtonGenerator.createExitButton();

        // layout / add them to the this
        this.setLayout(new GridBagLayout());
        GridBagConstraints f = new GridBagConstraints();
        f.anchor = GridBagConstraints.NORTHWEST;
        f.insets = new Insets(0,0,10,0);
        f.weightx = 0.5;
        
        // line 0
        f.gridx = 0;
        f.gridy = 0;
        this.add(bulkDownloadButton, f);

        f.gridx = 1;
        this.add(whichLociBulk, f);

        f.gridx = 2;
        this.add(whatVersionBulk, f);
        // line 1
        f.gridx = 0;
        f.gridy = 1;
        this.add(resetPrefsButton, f);


        // line 6
        f.weightx = 0;
        f.weighty = 0;
        f.gridy = 6;
        this.add(exitButtonOptions, f);

        return this;

    }

    public void writeToGfeTab(String text) {
        GfeTab.resultsTextAreaGfe.append(text); 
    }

    public static JPanel getFileFormatGfe() {
        return GfeTab.fileFormatGfe;
    }

    public void updateTheGfePanel(String whatLocus) {
        System.out.println("Triggered setNewGfePanel");

        Component[] components = gfePanel.getComponents();
        gfePanel.remove(components[0]);

        currentGfePanel = gfePanelGenerator.makeGfePanel(whatLocus);
        gfePanel.add(currentGfePanel).revalidate();
        gfePanel.repaint();
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

