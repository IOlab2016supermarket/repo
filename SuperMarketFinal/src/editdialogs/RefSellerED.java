package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefSeller;
import sprzedaz.Sprzedawca;

public class RefSellerED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtImie, txtNazwisko;
	
	public RefSellerED(Entity ent) {
		super();
		
		setTitle("Add / edit seller");
		setBounds(100, 100, 325, 165);
		
		JLabel lblImie = new JLabel("Imie:");
		lblImie.setBounds(12, 35, 30, 25);
		panelFields.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(12, 65, 60, 25);
		panelFields.add(lblNazwisko);
		
		txtImie = new JTextField();
		txtImie.setBounds(80, 35, 230, 25);
		txtImie.setColumns(10);
		panelFields.add(txtImie);
		
		txtNazwisko = new JTextField();
		txtNazwisko.setBounds(80, 65, 230, 25);
		txtNazwisko.setColumns(10);
		panelFields.add(txtNazwisko);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefSeller seller = (RefSeller) ent;
		
        if (seller != null) {
            id = seller.getId();
            Sprzedawca sprzedawca = seller.getSprzedawca();
            
            super.txtId.setText("" + id);
            txtImie.setText(sprzedawca.getImie());
            txtNazwisko.setText(sprzedawca.getNazwisko());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefSeller(
				id, 
				txtImie.getText().trim(), 
				txtNazwisko.getText().trim() 
			);
	}

}