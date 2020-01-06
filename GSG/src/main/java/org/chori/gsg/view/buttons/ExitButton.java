package org.chori.gsg.view.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import org.chori.gsg.view.*;

public class ExitButton { 

	public ExitButton() { }

	public static JButton createExitButton() {
		JButton exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(90, 30));

		exitButton.addActionListener(exitListener);

		return exitButton;
	}

	public static ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.exit(0);
		}
	};
}