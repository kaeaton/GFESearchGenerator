package org.chori.gsg.utilities;

import java.util.prefs.Preferences;

// import org.chori.gsg.view.*;


public class Prefs {

    private static final Preferences preferences = Preferences.userNodeForPackage(Prefs.class);

    private Prefs() { }

    /* for which tab is open */

    /**
     * Gets the id of what tab was last open. 
     * Used in Gui to determine which tab should be open on start.
     */
    public static int getCurrentTab() {
        System.out.println("Get current tab: which tab are we storing? " 
                            + preferences.getInt("SELECTED_TAB", 0));
        return preferences.getInt("SELECTED_TAB", 0);
        
    }

    /**
     * Sets the preference for what tab is currently selected. Used in Gui's ChangeListener.
     *
     * @param currentTab The integer associated with the currently open tab.
     */
    public static void setCurrentTab(int currentTab) {
        preferences.putInt("SELECTED_TAB", currentTab);
        System.out.println("Set current tab: which tab is selected? " 
                            + preferences.getInt("SELECTED_TAB", 0));
    }

    /* GFE Tab */

    /**
     * Gets the index of which loci was last selected on the GFE tab. 
     * Used by the GFE Loci dropdown
     */
    public static int getCurrentGfeLoci() {
        return preferences.getInt("GFE_LOCI", 0);
    }

    /**
     * Sets the index of which loci was last selected on the GFE tab.
     *
     * @param selectedLoci The integer associated with the GFE loci dropdown.
     */
    // public static void setCurrentGfeLoci(int selectedLoci) {
    //     preferences.putInt("GFE_LOCI", selectedLoci);
    // }
    
    /**
     * Gets the index of what locus was last selected on the GFE tab. 
     * Used by the GFE Locus dropdown
     */
    // public static int getCurrentGfeLoci() {
    //     return preferences.getInt("GFE_LOCUS", 0);
    // }

    /**
     * Sets the index of which loci was last selected on the GFE tab.
     *
     * @param selectedLoci The integer associated with the GFE loci dropdown.
     */
    // public static void setCurrentGfeLoci(int selectedLoci) {
    //     preferences.putInt("GFE_LOCI", selectedLoci);
    // }

    /* Name Tab */
    // public static String get 











}