package tablemodels;

import java.util.ArrayList;

import entities.RefKonto;
import superclasses.Entity;
import superclasses.TableModel;

public class RefKontoTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Login", "Role"};
    
    public RefKontoTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefKonto konto = (RefKonto) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return konto.getId();
            case 1:
                return konto.getLogin();
            default:
            	return konto.getRole();
        }
    }

}