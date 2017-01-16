package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.DocSalaryItemDM;
import editdialogs.DocSalaryItemED;
import entities.DocSalaryItem;
import main.Common;
import tablemodels.DocSalaryItemTM;

public class DocSalaryItemLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Salary data";
	
	public DocSalaryItemLP(int docId, EditDialogDoc parentDialog) {
		super(TITLE);
		super.parentDialog = parentDialog;
		init(docId);
	}
	
	private void init(int docId) {
		super.entityDataManager = new DocSalaryItemDM(docId);
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new DocSalaryItemTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		EditDialogDoc parentDialog = super.parentDialog;
		return new DocSalaryItemED(ent, parentDialog);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 2, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 3, 90, SwingConstants.RIGHT);
		super.decorateTableColumn(entityTable, 4, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 5, 90, SwingConstants.RIGHT);
		super.decorateTableColumn(entityTable, 6, 90, SwingConstants.RIGHT);
    }
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		DocSalaryItem salaryItem = (DocSalaryItem) ent;
		
		int salaryId = super.parentDialog.getId();
		int id = salaryItem.getId();
		int userId = salaryItem.getUserId();
		String userName = salaryItem.getUserName();
		
		if (new DocSalaryItemDM(salaryId).isUserSalaryExists(id, userId)) {
	    	Common.showErrorMessage(this, "Salary for '" + userName + "' already calculated!");
	    	return false;
	    }

		return true;
	}

}