package superclasses;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.util.ArrayList;

import main.Common;
import main.Constants;

public abstract class ListPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
	
	private int selectedRow = 0;
	private boolean readOnly = false;
	private ArrayList<Entity> entities;
    private JButton btnAdd, btnEdit, btnDelete;
    
	public static Entity entityFromDialog;		// for recieve entity from edit dialog
	
	protected final JTable entityTable = new JTable();
	protected DataManager entityDataManager;
	protected EditDialogDoc parentDialog = null;

	protected abstract TableModel getTableModel(ArrayList<Entity> entities);
    protected abstract EditDialog getEditDialog(Entity entity);
    
    public ListPanel(String title, int width) {
    	this(title);
    	setBounds(100, 100, width, 400);
    }
	
	public ListPanel(String title) {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), " " + title + ": ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelButtons, BorderLayout.NORTH);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[] {80, 80, 80, 0};
		gbl_panelButtons.rowWeights = new double[] {0.0};
		gbl_panelButtons.columnWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand(ADD);
		btnAdd.addActionListener(this);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(6, 6, 6, 0);
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnAdd, gbc_btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand(EDIT);
		btnEdit.addActionListener(this);
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(6, 6, 6, 0);
		gbc_btnEdit.anchor = GridBagConstraints.NORTH;
		gbc_btnEdit.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnEdit, gbc_btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand(DELETE);
		btnDelete.addActionListener(this);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(6, 6, 6, 0);
		gbc_btnDelete.anchor = GridBagConstraints.NORTH;
		gbc_btnDelete.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnDelete, gbc_btnDelete);
		
		entityTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					editEntity();
			}
		});
		
		entityTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
		            editEntity();
			}
		});
		
		JScrollPane panelTable = new JScrollPane(entityTable);
		add(panelTable, BorderLayout.CENTER);
	}
	
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		
		btnAdd.setVisible(!readOnly);
		btnDelete.setVisible(!readOnly);
		btnEdit.setText(readOnly ? "View" : "Edit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch (action) {
        case ADD:
            addEntity();
            break;
        case EDIT:
            editEntity();
            break;
        case DELETE:
            deleteEntity();
            break;
		}
	}
	
	private Entity getEntity(int id) {
		Entity res = null;
    	for (Entity ent : entities)
			if (ent.getId() == id) {
				res = ent;
				break;
			}
    	return res;
	}
	
    protected void decorateTableColumn(JTable table, int colummnIndex, int width, int alignment) {
		TableColumn column = table.getColumnModel().getColumn(colummnIndex);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(alignment);
		column.setCellRenderer(renderer);
		if (width != 0)
			column.setMaxWidth(width);
	}
    
    protected void decorateTable() {
    	decorateTableColumn(entityTable, 0, 30, SwingConstants.CENTER);
    }
	
	protected void loadEntities() {
		entities = entityDataManager.getEntityList();
		TableModel tm = getTableModel(entities);
		
        entityTable.setModel(tm);
        decorateTable();
        
        if (entities.size() != 0) {
        	entityTable.setRowSelectionInterval(selectedRow, selectedRow);
        	
        	if (parentDialog != null)
        		parentDialog.txtSumma.setText("" + calcSumma());
        }
        	
	}
	
	// Can override in subclasses
	protected boolean extraSaveCheck(Entity ent) {
    	return true;
    }
	
    private void saveEntity() {
    	if (entityFromDialog == null) return;
    	if ( !extraSaveCheck(entityFromDialog) ) return;
    		
        if (entityFromDialog.getId() != 0) {
            entityDataManager.updateEntity(entityFromDialog);
        } else {
            entityDataManager.addEntity(entityFromDialog);
        }
        loadEntities();
        entityFromDialog = null;
    }
	
	private void addEntity() {
		EditDialog dialog = getEditDialog(null);
		dialog.setReadOnly(readOnly);
		Common.showFrame(dialog);
		saveEntity();
	}
	
	private void editEntity() {
		if (!btnEdit.isVisible()) return;
		
		selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
        	int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
        	
        	Entity selectedEntity = getEntity(id);
        	
        	if (selectedEntity != null) {
        		EditDialog dialog = getEditDialog(selectedEntity);
        		dialog.setReadOnly(readOnly);
        		Common.showFrame(dialog);
        		saveEntity();	
        	}
            
        } else {
        	Common.showErrorMessage(this, "No row is selected!");
        }
	}
	
	// Can override in subclasses
	protected boolean extraDeleteCheck(Entity ent) {
    	return true;
    }
	
	private void deleteEntity() {
        selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
            
            Entity selectedEntity = getEntity(id);
            if ( !extraDeleteCheck(selectedEntity) ) return;
            
    		if (Common.showConfirmDialog(this, "Delete record: \n" + selectedEntity + "?", "Delete record") != Constants.YES) 
    			return;
            
            entityDataManager.deleteEntity(id);
            selectedRow = 0;
            loadEntities();
        } else {
        	Common.showErrorMessage(this, "No row is selected!");
        }
	}
	
	public Entity getSelectedEntity() {
        selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
            return getEntity(id);
        } else {
        	return null;
        }
	}
	
	public ArrayList<Entity> getList() {
        return entities;
	}
	
	private float calcSumma() {
		if (entities.size() == 0) return 0;
		
		float sum = 0;

		for (Entity entity : entities) {
			EntityItem item = (EntityItem) entity;
			sum += item.getSumma();
		}
		
		return Common.Round(sum, 2);
	}

}