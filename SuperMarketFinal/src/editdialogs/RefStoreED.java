package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefStore;
import magazyn.Magazynier;

public class RefStoreED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtImie, txtNazwisko;
	
	public RefStoreED(Entity ent) {
		super();
		
		setTitle("Add / edit store");
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
		RefStore store = (RefStore) ent;
		
        if (store != null) {
            id = store.getId();
            Magazynier magazynier = store.getMagazynier();
            
            super.txtId.setText("" + id);
            txtImie.setText(magazynier.pobierzImie());
            txtNazwisko.setText(magazynier.pobierzNazwisko());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefStore(
				id, 
				txtImie.getText().trim(), 
				txtNazwisko.getText().trim() 
			);
	}

}