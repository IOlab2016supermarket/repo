package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefClient;
import main.IntTextField;
import sprzedaz.Klient;

public class RefClientED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtImie, txtNazwisko;
	private IntTextField txtIloscPunktow, txtKodPocztowy;
	
	public RefClientED(Entity ent) {
		super();
		
		setTitle("Add / edit client");
		setBounds(100, 100, 325, 225);
		
		JLabel lblImie = new JLabel("Imie:");
		lblImie.setBounds(12, 35, 30, 25);
		panelFields.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(12, 65, 60, 25);
		panelFields.add(lblNazwisko);
		
		JLabel lblIloscPunktow = new JLabel("Ilosc punktow:");
		lblIloscPunktow.setBounds(12, 95, 100, 25);
		panelFields.add(lblIloscPunktow);
		
		JLabel lblKodPocztowy = new JLabel("Kod pocztowy:");
		lblKodPocztowy.setBounds(12, 125, 100, 25);
		panelFields.add(lblKodPocztowy);
		
		super.txtId.setBounds(110, 5, 50, 25);
		
		txtImie = new JTextField();
		txtImie.setBounds(110, 35, 200, 25);
		txtImie.setColumns(10);
		panelFields.add(txtImie);
		
		txtNazwisko = new JTextField();
		txtNazwisko.setBounds(110, 65, 200, 25);
		txtNazwisko.setColumns(10);
		panelFields.add(txtNazwisko);
		
		txtIloscPunktow = new IntTextField();
		txtIloscPunktow.setBounds(110, 95, 80, 25);
		panelFields.add(txtIloscPunktow);
		
		txtKodPocztowy = new IntTextField();
		txtKodPocztowy.setBounds(110, 125, 80, 25);
		panelFields.add(txtKodPocztowy);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefClient client = (RefClient) ent;
		
        if (client != null) {
            id = client.getId();
            Klient klient = client.getKlient();
            
            super.txtId.setText("" + id);
            txtImie.setText(klient.getImie());
            txtNazwisko.setText(klient.getNazwisko());
            txtIloscPunktow.setText("" + klient.getIloscPunktow());
            txtKodPocztowy.setText(klient.getKodPocztowy());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefClient(
				id, 
				txtImie.getText().trim(), 
				txtNazwisko.getText().trim(), 
				txtIloscPunktow.getInt(), 
				txtKodPocztowy.getText().trim() 
			);
	}

}