package editdialogs;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import superclasses.Entity;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;

public class _TempForm extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtId, txtImie, txtNazwisko, txtPesel, txtStanowisko, txtPremia, txtData_zatrudnienia, txtData_zwolnienia;
	private JComboBox<String> comboAccount;

	public _TempForm() {
		
		JPanel panelFields = new JPanel();
		panelFields.setAutoscrolls(true);
		getContentPane().add(panelFields, BorderLayout.CENTER);
		panelFields.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 5, 30, 25);
		panelFields.add(lblId);
		
		JLabel lblImie = new JLabel("Imie:");
		lblImie.setBounds(12, 35, 30, 25);
		panelFields.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(12, 65, 60, 25);
		panelFields.add(lblNazwisko);
		
		JLabel lblPesel = new JLabel("Pesel:");
		lblPesel.setBounds(12, 95, 60, 25);
		panelFields.add(lblPesel);
		
		JLabel lblStanowisko = new JLabel("Stanowisko:");
		lblStanowisko.setBounds(12, 125, 80, 25);
		panelFields.add(lblStanowisko);
		
		JLabel lblPremia = new JLabel("Premia:");
		lblPremia.setBounds(12, 155, 80, 25);
		panelFields.add(lblPremia);
		
		JLabel lblData_zatrudnienia = new JLabel("Zatrudnienie:");
		lblData_zatrudnienia.setBounds(12, 185, 80, 25);
		panelFields.add(lblData_zatrudnienia);
		
		JLabel lblData_zwolnienia = new JLabel("Zwolnienie:");
		lblData_zwolnienia.setBounds(12, 215, 80, 25);
		panelFields.add(lblData_zwolnienia);
		
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setBounds(12, 245, 80, 25);
		panelFields.add(lblAdres);
		
		txtId = new JTextField();
		txtId.setBounds(110, 5, 50, 25);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setEditable(false);
		txtId.setColumns(10);
		panelFields.add(txtId);
		
		txtImie = new JTextField();
		txtImie.setBounds(110, 35, 200, 25);
		txtImie.setColumns(10);
		panelFields.add(txtImie);
		
		txtNazwisko = new JTextField();
		txtNazwisko.setBounds(110, 65, 200, 25);
		txtNazwisko.setColumns(10);
		panelFields.add(txtNazwisko);
		
		txtPesel = new JTextField();
		txtPesel.setBounds(110, 95, 100, 25);
		txtPesel.setColumns(10);
		panelFields.add(txtPesel);
		
		txtStanowisko = new JTextField();
		txtStanowisko.setBounds(110, 125, 200, 25);
		txtStanowisko.setColumns(10);
		panelFields.add(txtStanowisko);
		
		txtPremia = new JTextField();
		txtPremia.setBounds(110, 155, 80, 25);
		txtPremia.setColumns(10);
		panelFields.add(txtPremia);
		
		txtData_zatrudnienia = new JTextField();
		txtData_zatrudnienia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		txtData_zatrudnienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		txtData_zatrudnienia.setBounds(110, 185, 80, 25);
		txtData_zatrudnienia.setColumns(10);
		panelFields.add(txtData_zatrudnienia);
		
		txtData_zwolnienia = new JTextField();
		txtData_zwolnienia.setBounds(110, 215, 80, 25);
		txtData_zwolnienia.setColumns(10);
		panelFields.add(txtData_zwolnienia);
		
		comboAccount = new JComboBox<String>();
		comboAccount.setBounds(139, 393, 150, 25);
		panelFields.add(comboAccount);
		
		JLabel labelKonto = new JLabel("Konto:");
		labelKonto.setBounds(12, 393, 80, 25);
		panelFields.add(labelKonto);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 274, 298, 107);
		panelFields.add(textArea);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnX.setBounds(318, 124, 42, 26);
		panelFields.add(btnX);
		
		JComboBox<Entity> comboBox = new JComboBox<Entity>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(212, 215, 98, 25);
		panelFields.add(comboBox);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setSelected(true);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		checkBox.setBounds(110, 393, 21, 25);
		panelFields.add(checkBox);
		
				
	}
}
