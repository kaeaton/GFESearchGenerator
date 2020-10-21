package org.chori.gsg.gui.dropdownMenus;

import javax.swing.JComboBox;

/**
 * Creates the dropdown menu system to select the set of genes being used, and listeners for the dropdowns.
 * 
 * @author Katrina Eaton
 * 
 */

public abstract class DropDownMenuSystem {

    protected static final String[] LOCI = {"HLA", "KIR"};
    protected static final String[] FULL_HLA_LOCI = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
    protected static final String[] FULL_KIR_LOCI = {"KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1"};

    public DropDownMenuSystem() { }

    enum DropdownMenu {
        WHICH_LOCI("loci") {
            public JComboBox getComboBox (Tab tab) { return whichLociFactory.createWhichLoci(tab);}},
        WHAT_LOCUS("locus") {
            public JComboBox getComboBox (Tab tab) { return whatLocusFactory.createWhatLocus(tab);}},
        WHAT_VERSION("version") {
            public JComboBox getComboBox (Tab tab) { return whatVersionFactory.createWhatVersion(tab);}};

        private static WhatLocusFactory whatLocusFactory = WhatLocusFactory.getWhatLocusFactoryInstance();
        private static WhatVersionFactory whatVersionFactory = WhatVersionFactory.getWhatVersionFactoryInstance();
        private static WhichLociFactory whichLociFactory = WhichLociFactory.getWhichLociFactoryInstance();

        // combo boxes for locus and version selection
        // private JComboBox whichLociGfe = whichLociFactory.createWhichLoci("GFE"); //.createWhichLociComboBox();
        // private JComboBox whatVersionGfe = whatVersionFactory.createWhatVersion("GFE").createWhatVersionComboBox();
        // private JComboBox whatLocusGfe = whatLocusFactory.createWhatLocus("GFE").createWhatLocusComboBox(whatVersionGfe.getSelectedItem().toString(), prefs.get("GSG_GFE_LOCI_STRING", "HLA"));


        private JComboBox comboBox;

        private DropdownMenu(JComboBox jComboBox) {
            this.comboBox = jComboBox;
        }

        public JComboBox getComboBox() {
            return comboBox;
        }
    }

    enum Tab {
        GFE(GfeMenuSystem.getInstance()),
        NAME(GfeMenuSystem.getInstance());
        // IDENTITY(GfeMenuSystem.class),
        // BULK(GfeMenuSystem.class);

        private final DropDownMenuSystem dropdownMenuSystem;

        private Tab(DropDownMenuSystem ddms) {
            this.dropdownMenuSystem = ddms;
        }
    }
}