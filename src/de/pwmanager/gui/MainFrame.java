package de.pwmanager.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.sun.media.sound.ModelAbstractChannelMixer;

import de.pwmanager.util.Util;

public class MainFrame extends JFrame {

	private JLabel lblHeader;
	private JLabel lblFooter;
	private JPanel pnlLeft;
	private JTable tbldata;
	private JScrollPane spdata;
	private DefaultTableModel tblmodel;

	private final Object[] COLUMNS = { "Anbieter", "Nutzername / E-Mail", "Passwort", "Geändert am" };

////////////////////////////////////////////////////////////////////////////////////////////

	public MainFrame() {

		setTitle(Util.PROGNAME);

		createWidgets();
		setupInteractions();
		addWidgets();

		pack();
		setLocationRelativeTo(null);
	}

////////////////////////////////////////////////////////////////////////////////////////////

	private void addWidgets() {

		getContentPane().setLayout(new BorderLayout(2, 2));
		getContentPane().add(BorderLayout.CENTER, spdata);
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.WEST, pnlLeft);
		getContentPane().add(BorderLayout.PAGE_END, lblFooter);

	}

	private void setupInteractions() {

	}

	private void createWidgets() {

		lblHeader = new JLabel("PW-Manager");
		lblFooter = new JLabel("Placeholder");
		pnlLeft = new JPanel();

		// JTable
		tbldata = new JTable();
		tblmodel = new DefaultTableModel();

		// TODO Columns fett + andere farbe
		tblmodel.setColumnIdentifiers(COLUMNS);
		tbldata.setModel(tblmodel);

		Object[] row = new Object[4];
		row[0] = "Test 1";
		row[1] = "Test 2";
		row[2] = "Test 3";
		row[3] = "Test 4";

		tblmodel.addRow(row);

		spdata = new JScrollPane();
		spdata.setViewportView(tbldata);

	}

////////////////////////////////////////////////////////////////////////////////////////////

	// setter for add a new line in table
	public void addRow(Object[] row) {

		if (row.length == 4) {
			if (row[0].toString().isEmpty()) {
				System.out.println("[Error] " + COLUMNS[0] + " darf nicht leer sein!");
				return;
			}
			if (row[1].toString().isEmpty()) {
				System.out.println("[Error] " + COLUMNS[1] + " darf nicht leer sein!");
				return;
			}
			if (row[2].toString().isEmpty()) {
				System.out.println("[Error] " + COLUMNS[2] + " darf nicht leer sein!");
				return;
			}
			if (row[3].toString().isEmpty()) {
				System.out.println("[Error] " + COLUMNS[3] + " darf nicht leer sein!");
				return;
			}

			tblmodel.addRow(row);
		} else {
			System.out.println("[Error] The size of the array is incorrect!!");

			// TODO Nachricht als Gui order in Footer
			return;
		}

	}

}
