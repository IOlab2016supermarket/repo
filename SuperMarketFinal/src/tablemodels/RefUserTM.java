package tablemodels;

import java.util.ArrayList;
import entities.RefUser;
import superclasses.Entity;
import superclasses.TableModel;

public class RefUserTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "First name", "Last name", "Login", "Stanowisko", "Salary", "Bonus (%)", "Date1", "Date2"};
    
    public RefUserTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefUser user = (RefUser) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return user.getId();
            case 1:
                return user.getWorker().getImie();
            case 2:
            	return user.getWorker().getNazwisko();
            case 3:
            	return user.getLogin();
            case 4:
            	return user.getWorker().getStanowisko();
            case 5:
            	return user.getSalary();
            case 6:
            	return user.getWorker().getPremia();
            case 7:
            	return user.getWorker().getData_zatrudnienia();
            default: 
            	return user.getWorker().getData_zwolnienia();
        }
    }

}