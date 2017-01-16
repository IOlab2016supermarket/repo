package listpanels;

import java.util.ArrayList;

import main.Common;
import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefKontoDM;
import datamanagers.RefUserDM;
import editdialogs.RefKontoED;
import entities.RefKonto;
import tablemodels.RefKontoTM;

public class RefKontoLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Accounts list";
	
	public RefKontoLP() {
		super(TITLE);
		init();
	}
	
	public RefKontoLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefKontoDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefKontoTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefKontoED(ent);
	}
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		int id = ent.getId();
		String login = ((RefKonto) ent).getLogin();
		
		if (new RefKontoDM().isKontoExists(id, login)) {
	    	Common.showErrorMessage(this, "Account '" + login + "' already exists!");
	    	return false;
	    }

		return true;
	}
	
	@Override
	protected boolean extraDeleteCheck(Entity ent) {
		int id = ent.getId();
		
		if (id == 1) {
	    	Common.showErrorMessage(this, "You can't delete default account!");
	    	return false;
	    }
		
		if (Common.getRegisteredKonto().getId() == id) {
	    	Common.showErrorMessage(this, "You can't delete yours own account!");
	    	return false;
	    }
		
		if (new RefUserDM().isKontoInUse(id)) {
			String login = ((RefKonto) ent).getLogin();
	    	Common.showErrorMessage(this, "Account '" + login + "' is already in use!");
	    	return false;
	    }
		
		return true;
	}

}
