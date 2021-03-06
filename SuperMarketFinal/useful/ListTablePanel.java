﻿package superclasses;

import java.awt.Color;

public abstract class ListTablePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
	
	private int selectedRow = 0;
	private ArrayList<Entity> entities;
    private JButton btnAdd, btnEdit, btnDelete; 
    
	public static Entity entityFromDialog;		// for recieve entity from edit dialog
	
	protected String listTitle;
	protected final JTable entityTable = new JTable();
	protected DataManager entityDataManager;

	protected abstract TableModel getTableModel(ArrayList<Entity> entities);
    protected abstract EditDialog getEditDialog(Entity entity);
    
    public ListTablePanel(String title, int width) {
    	this(title);
    	setBounds(100, 100, width, 400);
    }
	
	public ListTablePanel(String title) {
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
	
	public void setButtonsVisiblity(boolean btnAddVisible, boolean btnEditVisible, boolean btnDeleteVisible) {
		btnAdd.setVisible(btnAddVisible);
		btnEdit.setVisible(btnEditVisible);
		btnDelete.setVisible(btnDeleteVisible);
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
	
	private boolean firstRun = true;
    
    protected void loadEntities() {
		if (firstRun) {
			entities = entityDataManager.getEntityList();
			firstRun = false;
		}
    	
		TableModel tm = getTableModel(entities);
		
        entityTable.setModel(tm);
        decorateTable();
        
        if (entities.size() != 0)
        	entityTable.setRowSelectionInterval(selectedRow, selectedRow);
	}
	
	// Can override in subclasses
	protected boolean extraSaveCheck(Entity ent) {
    	return true;
    }
	
    @SuppressWarnings("unused")
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
    
    private void saveEntity(int id) {
    	if (entityFromDialog == null) return;
    	if ( !extraSaveCheck(entityFromDialog) ) return;
    		
        if (entityFromDialog.getId() != 0) {
        	entities.set(id, entityFromDialog);
        } else {
        	entities.add(entityFromDialog);
        }
        loadEntities();
        entityFromDialog = null;
    }
	
	private void addEntity() {
		EditDialog dialog = getEditDialog(null);
		if (dialog != null) {
			Common.showFrame(dialog);
			saveEntity(0);
		}
	}
	
	private void editEntity() {
		if (!btnEdit.isVisible()) return;
		
		selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
        	int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
        	
        	Entity selectedEntity = getEntity(id);
        	
        	if (selectedEntity != null) {
        		EditDialog dialog = getEditDialog(selectedEntity);
        		Common.showFrame(dialog);
        		//saveEntity();	
        		saveEntity(id);
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
            
    		if (Common.showConfirmDialog(this, "You really want to delete record: \n" + selectedEntity + "?", "Delete record") != Constants.YES) 
    			return;
            
            //entityDataManager.deleteEntity(id);
    		entities.remove(id);
    		
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

}
