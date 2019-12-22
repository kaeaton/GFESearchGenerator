package org.chori.gsg.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.prefs.Preferences;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

// import package org.chori.gsg.view.B12xGui;

/**
 * The class for the panel for selecting what format 
 * the data appears in and whether to write it to file
 * 
 * @author Katrina Eaton
 * 
 */
public class FileFormatPanel { 

	private Preferences prefs = Preferences.userNodeForPackage(B12xGui.class);
	private String loci;

	

	public FileFormatPanel() { }

	/**
	 * Assembles the JPanel housing all the JRadioButtons and the JCheckBox for selecting data format 
	 * and if a file is saved. All features have an associated listener.
	 * 
	 * @param tab tells which set of listeners should be attached to the panel features
	 * @return the populated JPanel
	 */
	public JPanel getFileFormatPanel(String tab) {
		loci = prefs.get("GSG_" + tab + "_LOCI_STRING", "HLA");

		JRadioButton csvButton = new JRadioButton("CSV");
		JRadioButton tsvButton = new JRadioButton("TSV");
		JRadioButton prettyButton = new JRadioButton("Pretty");
		JCheckBox saveToFileCheckBox = new JCheckBox("Save to file");
		
		createButtonGroup(csvButton, tsvButton, prettyButton);
		setSelected(csvButton, tsvButton, prettyButton, saveToFileCheckBox, tab, loci);
		addListeners(csvButton, tsvButton, prettyButton, saveToFileCheckBox, tab, loci);

		JPanel fileFormatPanel = createFileFormatPanel(csvButton, tsvButton, prettyButton, saveToFileCheckBox);

		return fileFormatPanel;
	}

	private ButtonGroup createButtonGroup(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton) {
		ButtonGroup formatButtonGroup = new ButtonGroup();
		formatButtonGroup.add(csvButton);
		formatButtonGroup.add(tsvButton);
		formatButtonGroup.add(prettyButton);

		return formatButtonGroup;
	}

	private void setSelected(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
							JCheckBox saveToFileCheckBox, String tab, String loci) {
		csvButton.setSelected(prefs.getBoolean("GSG_" + tab + "_" + loci + "_CSV_SELECTED", true));
		tsvButton.setSelected(prefs.getBoolean("GSG_" + tab + "_" + loci + "_TSV_SELECTED", false));
		prettyButton.setSelected(prefs.getBoolean("GSG_" + tab + "_" + loci + "_PRETTY_SELECTED", false));
		saveToFileCheckBox.setSelected(prefs.getBoolean("GSG_" + tab + "_" + loci + "_SAVE_FILE", false));
	}

	private void addListeners(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
							  JCheckBox saveToFileCheckBox, String tab, String loci) {
		csvListener(csvButton, tsvButton, prettyButton, tab, loci);
		tsvListener(csvButton, tsvButton, prettyButton, tab, loci);
		prettyListener(csvButton, tsvButton, prettyButton, tab, loci);
		saveToFileCheckBoxListener(saveToFileCheckBox, tab, loci);
	}

	private void csvListener(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
							 String tab, String loci) {
		csvButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				prefsFileFormat(csvButton, tsvButton, prettyButton, tab, loci);
			}
		});
	}

	private void tsvListener(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
							 String tab, String loci) {
		tsvButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				prefsFileFormat(csvButton, tsvButton, prettyButton, tab, loci);
			}
		});
	}

	private void prettyListener(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
								String tab, String loci) {
		prettyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				prefsFileFormat(csvButton, tsvButton, prettyButton, tab, loci);
			}
		});
	}

	private void saveToFileCheckBoxListener(JCheckBox saveToFileCheckBox, String tab, String loci) {
		saveToFileCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				prefs.putBoolean("GSG_" + tab + "_" + loci + "_SAVE_FILE", saveToFileCheckBox.isSelected());
			}
		});
	}

	private void prefsFileFormat(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton,
								 String tab, String loci) {
		if (csvButton.isSelected()) prefs.putBoolean("GSG_" + tab + "_" + loci + "_CSV_SELECTED", true);
		else prefs.putBoolean("GSG_" + tab + "_" + loci + "_CSV_SELECTED", false);
	   
		if (tsvButton.isSelected()) prefs.putBoolean("GSG_" + tab + "_" + loci + "_TSV_SELECTED", true);
		else prefs.putBoolean("GSG_" + tab + "_" + loci + "_TSV_SELECTED", false);

		if (prettyButton.isSelected()) prefs.putBoolean("GSG_" + tab + "_" + loci + "_PRETTY_SELECTED", true);
		else prefs.putBoolean("GSG_" + tab + "_" + loci + "_PRETTY_SELECTED", false);
	}

	private JPanel createFileFormatPanel(JRadioButton csvButton, JRadioButton tsvButton, JRadioButton prettyButton, 
										 JCheckBox saveToFileCheckBox) {
		// panels
		JPanel formatPanel = new JPanel();
		formatPanel.setPreferredSize(new Dimension(950, 50));
		formatPanel.setBorder(new EmptyBorder(20, 0, 10, 0));
		JPanel filler = new JPanel();

		// avengers assemble!
		formatPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		formatPanel.add(csvButton, c);
		
		c.gridx = 1;
		formatPanel.add(tsvButton, c);
		
		c.gridx = 2;
		formatPanel.add(prettyButton, c);

		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 3;
		formatPanel.add(filler, c);

		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 4;
		formatPanel.add(saveToFileCheckBox, c);

		return formatPanel;
	}
}