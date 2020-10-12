package org.chori.gsg.gui.dropdownMenus.whatVersion.versionModel;

// import java.util.Arrays;
// import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;

import org.chori.gsg.model.*;
import org.chori.gsg.utilities.*;
import org.chori.gsg.gui.*;


public class VersionModelBulk extends VersionModel{

	public VersionModelBulk() { }

	public DefaultComboBoxModel assembleVersionModel(String lociType) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		String onlineVersions = prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", "");
		String[] parsedOnlineVersions;

		// if you have internet download the current versions
		if(onlineVersions.equals("") && internet.tester()) {
			versionsAvailableOnline.getCurrentVersionsByLoci(lociType);
			onlineVersions = prefs.get("GSG_" + lociType + "_ONLINE_VERSIONS", null);
		}

		parsedOnlineVersions = parseArrayFromString(onlineVersions);
		
		model = new DefaultComboBoxModel(parsedOnlineVersions);
		return model;
	}
}