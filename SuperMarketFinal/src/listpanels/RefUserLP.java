package listpanels;

import java.util.ArrayList;
import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefUserDM;
import editdialogs.RefUserED;
import entities.RefUser;
import main.Common;
import tablemodels.RefUserTM;

public class RefUserLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Users list";
	
	public RefUserLP() {
		super(TITLE);
		init();
	}
	
	public RefUserLP(int width) {
		super(TITLE, width);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefUserDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefUserTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefUserED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 3, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 5, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 6, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 7, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 8, 90, SwingConstants.CENTER);
    }
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		RefUser user = (RefUser) ent;
		int kontoId = user.getKontoId();
		
		if (kontoId == 0) return true;
		
		int id = user.getId();
		String login = user.getLogin();
		
		if (new RefUserDM().isKontoInUse(id, kontoId)) {
	    	Common.showErrorMessage(this, "Account '" + login + "' is already in use!");
	    	return false;
	    }

		return true;
	}
}
