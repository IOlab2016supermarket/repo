package editdialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import administracja.Pracownik;
import datamanagers.RefKontoDM;
import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefKonto;
import entities.RefUser;
import main.DatePicker;
import main.FloatTextField;

public class RefUserED extends EditDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private RefUser user = null;
	private JTextField txtImie, txtNazwisko, txtPesel, txtStanowisko, txtAdres;
	private FloatTextField txtSalary, txtPremia;
	private JComboBox<Entity> comboKonto;
	private DatePicker dpDateIn, dpDateOut;
	private JCheckBox checkKonto;
	
	public RefUserED(Entity ent) {
		super();
		
		setTitle("Add / edit user");
		setBounds(100, 100, 370, 430);
		
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
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(12, 155, 80, 25);
		panelFields.add(lblSalary);
		
		JLabel lblPremia = new JLabel("Premia:");
		lblPremia.setBounds(12, 185, 80, 25);
		panelFields.add(lblPremia);
		
		JLabel lblData_zatrudnienia = new JLabel("Zatrudnienie:");
		lblData_zatrudnienia.setBounds(12, 215, 80, 25);
		panelFields.add(lblData_zatrudnienia);
		
		JLabel lblData_zwolnienia = new JLabel("Zwolnienie:");
		lblData_zwolnienia.setBounds(12, 245, 80, 25);
		panelFields.add(lblData_zwolnienia);
		
		JLabel lblKonto = new JLabel("Konto:");
		lblKonto.setBounds(12, 275, 80, 25);
		panelFields.add(lblKonto);
		
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setBounds(12, 305, 80, 25);
		panelFields.add(lblAdres);
		
		super.txtId.setBounds(110, 5, 50, 25);
		
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
		
		txtSalary = new FloatTextField();
		txtSalary.setBounds(110, 155, 80, 25);
		panelFields.add(txtSalary);
		
		txtPremia = new FloatTextField();
		txtPremia.setBounds(110, 185, 80, 25);
		panelFields.add(txtPremia);
		
		dpDateIn = new DatePicker();
		dpDateIn.setBounds(110, 214, 200, 27);
		panelFields.add(dpDateIn);
		
		JButton btnX1 = new JButton("X");
		btnX1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dpDateIn.getModel().setSelected(false);
			}
		});
		btnX1.setBounds(315, 214, 42, 26);
		panelFields.add(btnX1);
		
		dpDateOut = new DatePicker();
		dpDateOut.setBounds(110, 244, 200, 27);
		panelFields.add(dpDateOut);
		
		JButton btnX2 = new JButton("X");
		btnX2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dpDateOut.getModel().setSelected(false);
			}
		});
		btnX2.setBounds(315, 244, 42, 26);
		panelFields.add(btnX2);
		
		checkKonto = new JCheckBox("");
		checkKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				useKonto();
			}
		});
		checkKonto.setBounds(107, 275, 21, 25);
		panelFields.add(checkKonto);
		
		comboKonto = new JComboBox<Entity>();
		comboKonto.setBounds(130, 275, 180, 25);
		panelFields.add(comboKonto);
		
		txtAdres = new JTextField();
		txtAdres.setBounds(12, 330, 345, 25);
		txtAdres.setColumns(10);
		panelFields.add(txtAdres);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		user = (RefUser) ent;
		
        if (user != null) {
            id = user.getId();
            Pracownik worker = user.getWorker();
            
            super.txtId.setText("" + id);
            txtImie.setText(worker.getImie());
            txtNazwisko.setText(worker.getNazwisko());
            txtPesel.setText(worker.getPesel());
            txtStanowisko.setText(worker.getStanowisko());
            txtSalary.setText("" + user.getSalary());
            txtPremia.setText("" + worker.getPremia());
            dpDateIn.setDate(worker.getData_zatrudnienia());
            dpDateOut.setDate(worker.getData_zwolnienia());
            
            checkKonto.setSelected(user.getKontoId() != 0);
        }
        
        useKonto();
    }
	
	@Override
	protected Entity entityFromFields() {
		RefKonto konto = (RefKonto) comboKonto.getSelectedItem();
		
		int kontoId = 0;
		String kontoLogin = "";
		
		if (checkKonto.isSelected()) {
			kontoId = konto.getId();
			kontoLogin = konto.getLogin();
		}
		
		return 
			new RefUser(
				id, 
				kontoId, 
				kontoLogin, 
				txtImie.getText().trim(), 
				txtNazwisko.getText().trim(), 
				txtPesel.getText().trim(), 
				txtStanowisko.getText().trim(),
				txtSalary.getFloat(),
				txtPremia.getFloat(),
				dpDateIn.getDate(), 
				dpDateOut.getDate(), 
				txtAdres.getText().trim()
			);
	}
	
	private void useKonto() {
		if (checkKonto.isSelected()) {
			int kontoId = (user != null) ? user.getKontoId() : 0; 
			super.setComboItems(comboKonto, new RefKontoDM(), kontoId);
	        comboKonto.setEnabled(true);
		} else {
			comboKonto.removeAllItems();
			comboKonto.setEnabled(false);
		}
	}
	
}