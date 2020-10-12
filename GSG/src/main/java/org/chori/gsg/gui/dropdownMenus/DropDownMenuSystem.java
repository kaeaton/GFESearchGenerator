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

    /**
     * Returns the whatVersion JComboBox with Listener.
     */
    public abstract JComboBox getWhichLociComboBox();

    /**
     * Returns the whatVersion JComboBox with Listener.
     */
    public abstract JComboBox getWhatLocusComboBox();

    /**
     * Returns the whatVersion JComboBox with Listener.
     */
    public abstract JComboBox getWhatVersionComboBox();

    // protected abstract void addWhichLociListener(JComboBox whichLociDropDown);
    // protected abstract void updateLocusAndVersionDropdowns(String lociType);
}