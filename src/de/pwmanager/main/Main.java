package de.pwmanager.main;

import de.pwmanager.gui.MainFrame;

public class Main {

	public static void main(String[] args) {

		System.out.println("[PW-Manager] wurde gestartet!");

		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);

		Object[] test = new Object[4];

		test[0] = "1";
		test[1] = "2";
		test[2] = "3";
		test[3] = "4";

		mainFrame.addRow(test);
	}

}
