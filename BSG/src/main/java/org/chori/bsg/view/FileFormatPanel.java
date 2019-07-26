package org.chori.bsg.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class FileFormatPanel { 

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	public JRadioButton csvButton = new JRadioButton("CSV");
	public JRadioButton tsvButton = new JRadioButton("TSV");
	public JRadioButton prettyButton = new JRadioButton("Pretty");
    public JCheckBox saveToFileCheckBox = new JCheckBox("Save to file");

	public FileFormatPanel() {

	}

	public JPanel createFileFormatPanel(String whichTab) {
		System.out.println("Generating the file format panel");
		
		// panel
		JPanel fileFormatPanel = new JPanel();
		fileFormatPanel.setPreferredSize(new Dimension(950, 20));
		JPanel filler = new JPanel();

		// radio button group
		ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(csvButton);
        bGroup.add(tsvButton);
        bGroup.add(prettyButton);

        // save file checkbox

        // avengers assemble!
        fileFormatPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		fileFormatPanel.add(csvButton, c);
		
		c.gridx = 1;
		fileFormatPanel.add(tsvButton, c);
		
		c.gridx = 2;
		fileFormatPanel.add(prettyButton, c);

		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 3;
		fileFormatPanel.add(filler, c);

		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 4;
		fileFormatPanel.add(saveToFileCheckBox, c);

		// who is this reset button for?
		switch(whichTab) {
			case "HLA":
        		csvButton.setSelected(prefs.getBoolean("HLA_CSV_SELECTED", true));
        		tsvButton.setSelected(prefs.getBoolean("HLA_TSV_SELECTED", false));
        		prettyButton.setSelected(prefs.getBoolean("HLA_PRETTY_SELECTED", false));
        		saveToFileCheckBox.setSelected(prefs.getBoolean("BSG_HLA_SAVE_FILE", false));
				hlaCsvListener();
				hlaTsvListener();
				hlaPrettyListener();
				saveToFileCheckBoxListener();
				break;
			default:
				System.out.println("Haven't set up that combobox model yet");
		}

		return fileFormatPanel;
	}

	// separate functions because each tab needs it's own set of prefs
	private void hlaPrefsFileFormat() {
		if (csvButton.isSelected()) prefs.putBoolean("HLA_CSV_SELECTED", true);
		else prefs.putBoolean("HLA_CSV_SELECTED", false);
		System.out.println("HLA_CSV is selected: " + prefs.getBoolean("HLA_CSV_SELECTED", true));
	   
		if (tsvButton.isSelected()) prefs.putBoolean("HLA_TSV_SELECTED", true);
		else prefs.putBoolean("HLA_TSV_SELECTED", false);
		System.out.println("HLA_TSV is selected: " + prefs.getBoolean("HLA_TSV_SELECTED", false));

		if (prettyButton.isSelected()) prefs.putBoolean("HLA_PRETTY_SELECTED", true);
		else prefs.putBoolean("HLA_PRETTY_SELECTED", false);
		System.out.println("HLA_PRETTY is selected: " + prefs.getBoolean("HLA_PRETTY_SELECTED", false));
	}

	// separate listeners because Java button groups don't have listeners
	/* HLA */
	private void hlaCsvListener() {
		csvButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	hlaPrefsFileFormat();
            }
        });
	}

	private void hlaTsvListener() {
		tsvButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	hlaPrefsFileFormat();
            }
        });
	}

	private void hlaPrettyListener() {
		prettyButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	hlaPrefsFileFormat();
            }
        });
	}

	private void saveToFileCheckBoxListener() {
		saveToFileCheckBox.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent evt) {
            	prefs.putBoolean("BSG_HLA_SAVE_FILE", saveToFileCheckBox.isSelected());
            }
        });
	}
}