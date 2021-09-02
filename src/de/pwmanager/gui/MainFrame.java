package de.pwmanager.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import de.pwmanager.util.Util;

public class MainFrame extends JFrame {

	private JLabel lblHeader;
	private JLabel lblFooter;
	private JPanel pnlLeft;
	private JTable tbldata;
	private JScrollPane spdata;
	private DefaultTableModel tblmodel;

	private final Object[] COLUMNS = { "Anbieter", "Nutzername / E-Mail", "Passwort", "Geändert am" };

	private JTextField fldProvider;
	private JTextField fldNameEmail;
	private JTextField fldPassword;
	private JTextField fldLastChange;

	private JButton btnAddRow;
	private JButton btnDeleteRow;

	private String fldProviderText = "Anbieter";
	private String fldNameEmailText = "Name / Email";
	private String fldPasswordText = "Passwort";
	private String fldLastChangeText = "letzte Änderung";

////////////////////////////////////////////////////////////////////////////////////////////

	public MainFrame() {

		setTitle(Util.PROGNAME);

		createWidgets();
		setupInteractions();
		addWidgets();

		pack();
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

////////////////////////////////////////////////////////////////////////////////////////////

	private void addWidgets() {

		getContentPane().setLayout(new BorderLayout(2, 2));
		getContentPane().add(BorderLayout.CENTER, spdata);
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.WEST, pnlLeft);
		getContentPane().add(BorderLayout.PAGE_END, lblFooter);

		pnlLeft.add(fldProvider);
		pnlLeft.add(fldNameEmail);
		pnlLeft.add(fldPassword);
		pnlLeft.add(fldLastChange);
		pnlLeft.add(btnAddRow);
		pnlLeft.add(Box.createHorizontalGlue());
		pnlLeft.add(btnDeleteRow);
		pnlLeft.add(Box.createVerticalGlue());

	}

	private void createWidgets() {

		lblHeader = new JLabel("PW-Manager");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		lblFooter = new JLabel("Placeholder");
		lblFooter.setHorizontalAlignment(SwingConstants.CENTER);

		// panel left to add / Delete rows to table
		pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));

		fldProvider = new JTextField(fldProviderText);
		fldProvider.setMaximumSize(new Dimension(Integer.MAX_VALUE, fldProvider.getMinimumSize().height));
		fldNameEmail = new JTextField(fldNameEmailText);
		fldNameEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, fldNameEmail.getMinimumSize().height));
		fldPassword = new JTextField(fldPasswordText);
		fldPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, fldPassword.getMinimumSize().height));
		fldLastChange = new JTextField(fldLastChangeText);
		fldLastChange.setMaximumSize(new Dimension(Integer.MAX_VALUE, fldLastChange.getMinimumSize().height));

		btnAddRow = new JButton("Hinzufügen");
		btnDeleteRow = new JButton("Entfernen");

		// JTable
		tbldata = new JTable();

		tblmodel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int columns) {
				return false;
			}
		};

		// TODO Columns fett + andere farbe
		tblmodel.setColumnIdentifiers(COLUMNS);
		tbldata.setModel(tblmodel);

		spdata = new JScrollPane();
		spdata.setViewportView(tbldata);

	}

	private void setupInteractions() {

		btnAddRow.addActionListener(new AddRowListener());
		btnDeleteRow.addActionListener(new DeleteRowListener());

	}

	private class AddRowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

//			private JTextField fldProvider;
//			private JTextField fldNameEmail;
//			private JTextField fldPassword;
//			private JTextField fldLastChange;

			if (!fldProvider.getText().isEmpty() && !fldNameEmail.getText().isEmpty()
					&& !fldPassword.getText().isEmpty()) {
				Object[] row = new Object[4];
				row[0] = fldProvider.getText();
				row[1] = fldNameEmail.getText();
				row[2] = fldPassword.getText();

				if (fldLastChange.getText().equalsIgnoreCase("letzte Änderung")) {
					// TODO Datum automatisch eingeben
					row[3] = "Datum";
				} else {
					// TODO eingegebenes Datum wandeln in Datum Format
					row[3] = fldLastChange.getText();
				}

				addRow(row);
				resetFields();
			}

			System.out.println("Felder nicht korrekt ausgefüllt!");

		}

	}

	private class DeleteRowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Btn del row");

		}

	}

	// reset field text to default
	private void resetFields() {
		fldProvider.setText(fldProviderText);
		fldNameEmail.setText(fldNameEmailText);
		fldPassword.setText(fldPasswordText);
		fldLastChange.setText(fldLastChangeText);
	}

////////////////////////////////////////////////////////////////////////////////////////////

	// setter for add a new line in table
	private void addRow(Object[] row) {

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
			System.out.println("[Error] The size of array is incorrect!!");

			// TODO Nachricht als Gui order in Footer
			return;
		}

	}

}
