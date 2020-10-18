package org.chori.gsg.gui.dropdownMenus.whatLocus.locusModel;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.chori.gsg.data.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;

	

public abstract class LocusModel {

	// class instantiations
	// private WhereTheDataLives wtdl = new WhereTheDataLives();
	// private final String[] fullHlaLoci = {"HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1", "HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"};
	// private final String[] fullKirLoci = {"KIR2DL4", "KIR2DL5A", "KIR2DL5B", "KIR2DP1", "KIR2DS1", "KIR2DS2", "KIR2DS3", "KIR2DS4", "KIR2DS5", "KIR3DL1", "KIR3DL2", "KIR3DL3", "KIR3DP1", "KIR3DS1"};

	protected Preferences prefs = Preferences.userNodeForPackage(GSG.class);
	protected InternetAccess internet = new InternetAccess();
	protected VersionsAvailableLocally versionsAvailableLocally = new VersionsAvailableLocally();

	public LocusModel() { }

	public abstract DefaultComboBoxModel assembleLocusModel(String version);
	protected abstract Boolean onlineVersion(String version);
}