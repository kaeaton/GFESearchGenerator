package org.chori.gsg.gui.gfeTab;

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

public class GfeTab extends JPanel {

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
    // private static ResetPrefsButton resetPrefsButtonGenerator = new ResetPrefsButton();

    // the JPanel to return
    private JPanel gfeTab = new JPanel();

    // the holder panel - embedded in the layout, with contents to be changed
    private JPanel gfePanel = new JPanel();
    private JPanel currentGfePanel = gfePanelGenerator.makeGfePanel("HLA-A");

    public static JTextArea resultsTextAreaGfe = new JTextArea();
    public static JPanel fileFormatGfe = fileFormatPanelGenerator.getFileFormatPanel("GFE");

    // combo boxes for locus and version selection
    public static JComboBox whatVersionGfe = new JComboBox();
    public static JComboBox whatLocusGfe = new JComboBox();
    public static JComboBox whichLociGfe = new JComboBox();

    private static final GfeTab instance = new GfeTab();

    private GfeTab() { 
        // the JPanel to return
        // JPanel gfeTab = new JPanel();

        // the holder panel - embedded in the layout, with contents to be changed
        // JPanel gfePanel = new JPanel();
        // JPanel currentGfePanel = gfePanelGenerator.makeGfePanel("HLA-A");

        whichLociGfe = whichLociFactory.createWhichLoci("GFE").createWhichLociComboBox();
        whatVersionGfe = whatVersionFactory.createWhatVersion("GFE").createWhatVersionComboBox();
        whatLocusGfe = whatLocusFactory.createWhatLocus("GFE").createWhatLocusComboBox(whatVersionGfe.getSelectedItem().toString(), prefs.get("GSG_GFE_LOCI_STRING", "HLA"));
    }

    public static GfeTab getGfeTabInstance() {
        return instance;
    }

    public JPanel assembleGfeTab() {

        // generate the currentGfePanel
        String currentLocus = whatLocusShouldInitiallyLoad();
        currentGfePanel = gfePanelGenerator.makeGfePanel(currentLocus);
        gfePanel.add(currentGfePanel);

        // results textarea
        JScrollPane resultsScrollPaneGfe = new JScrollPane(resultsTextAreaGfe);
        resultsTextAreaGfe.setFont(new Font("Courier New", 0, 13));
        resultsScrollPaneGfe.setPreferredSize(new Dimension(950, 350));

        // labels
        JLabel selectAllLabelGfe = new JLabel("Check all");
        JTextArea usageInstructionsGfe = new JTextArea("Enter in the terms you are looking for. (Zero represents unsequenced data, and is a valid term.) Empty boxes function as wildcards."
                                                    + "\nChecking a box will prevent any results containing the number zero (an unsequenced feature) in that feature.");
        usageInstructionsGfe.setBackground(gfePanel.getBackground());
        usageInstructionsGfe.setEditable(false);
        usageInstructionsGfe.setFocusable(false);

        // buttons
        JButton resetButtonGfe = resetButtonGenerator.createResetButton("GFE");
        JButton submitButtonGfe = submitButtonGenerator.createSubmitButton("GFE");
        JButton exitButtonGfe = exitButtonGenerator.createExitButton();

        // submit/cancel buttons panel
        JPanel bottomButtonsGfe = new JPanel();
        bottomButtonsGfe.add(submitButtonGfe);
        bottomButtonsGfe.add(exitButtonGfe);

        // version/loci dropdowns
        JPanel versionLociPanelGfe = new JPanel();
        versionLociPanelGfe.add(resetButtonGfe);
        versionLociPanelGfe.add(whatVersionGfe);
        versionLociPanelGfe.add(whichLociGfe);

        // layout / add them to the gfePanel
        gfeTab.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0,0,10,0);
        c.weightx = 0.5;
        
        // line 0
        c.gridx = 0;
        c.gridy = 0;
        gfeTab.add(whatLocusGfe, c);
        
        c.gridx = 1;
        gfeTab.add(usageInstructionsGfe, c);

        // line 1
        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        gfeTab.add(selectAllLabelGfe, c);

        // line 2
        c.gridy = 2;
        c.gridwidth = 4;
        gfeTab.add(gfePanel, c);

        // line 3
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 3;
        gfeTab.add(versionLociPanelGfe, c);

        // line 4
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 4;
        gfeTab.add(fileFormatGfe, c);

        // line 5
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridy = 5;
        gfeTab.add(resultsScrollPaneGfe, c);

        // line 6
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0;
        c.weighty = 0;
        c.gridy = 6;
        gfeTab.add(bottomButtonsGfe, c);

        return gfeTab;

    }

    public void writeToGfeTab(String text) {
        resultsTextAreaGfe.append(text); 
    }

    public static JPanel getFileFormatGfe() {
        return fileFormatGfe;
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

