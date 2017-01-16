package superclasses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import main.DatePicker;
import main.IntTextField;
import superclasses.Entity;
import superclasses.ListPanel;

public abstract class EditDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	private static final String SAVE = "SAVE";
	
	protected abstract Entity entityFromFields();
	protected IntTextField txtId;
	protected boolean readOnly = false;
	protected EditDialogDoc parentDialog = null;
	
	public EditDialog() {
		super();
		super.btnOK.setText("Save");
		super.btnOK.setActionCommand(SAVE);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 5, 50, 25);
		panelFields.add(lblId);
		
		txtId = new IntTextField();
		txtId.setBounds(80, 5, 50, 25);
		txtId.setEditable(false);
		panelFields.add(txtId);
	}
	
	public EditDialog(EditDialogDoc parentDialog) {
		this();
		this.parentDialog = parentDialog;
	}
	
	void setReadOnly(boolean readOnly) {
		boolean isEnabled = !readOnly;
		this.readOnly = readOnly;
		
		Component[] components = panelFields.getComponents();
		
		for (Component component : components) {
			if (component instanceof JButton)
				((JButton) component).setEnabled(isEnabled);
			else if (component instanceof JTextField) {
				JTextField text = (JTextField) component;
				if (text.isEditable())
					text.setEditable(isEnabled);
			}
			else if (component instanceof DatePicker)
				((DatePicker) component).setEditable(isEnabled);
			else if (component instanceof JComboBox)
				((JComboBox<?>) component).setEnabled(isEnabled);
		}
		
		btnOK.setEnabled(isEnabled);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		ListPanel.entityFromDialog = null;
		
		if (ae.getActionCommand() == SAVE)
			ListPanel.entityFromDialog = entityFromFields();
			
		dispose();
	}
	
	public int getId() {
		return txtId.getInt();
	}	
	
	// for combo boxes
	protected void setComboItems(JComboBox<Entity> combo, DataManager dataManager, int parentId) {
		List<Entity> entities = dataManager.getEntityList();
		
		int index = 0;
		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			if (ent.getId() == parentId)
				index = i;
			combo.addItem(ent);	
		}
		
		combo.setSelectedIndex(index);
	}

}