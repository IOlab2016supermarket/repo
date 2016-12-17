package gui.superclasses;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class ListPanel extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  private static final String ADD = "ADD";
  private static final String EDIT = "EDIT";
  private static final String DELETE = "DELETE";
  private static final String EXPORT = "EXPORT";
  
  private JButton btnAdd, btnEdit, btnDelete, btnExport;
  
  private int selectedRow = 0;
  
  protected String listTitle;
  protected final JTable objTable = new JTable();  
  
  protected Object ob;
  
  protected abstract TableModel getTableModel(ArrayList<Object> objects);

  protected abstract JDialog getEditDialog();
  protected abstract void addObject();
  protected abstract void editObject();
  protected abstract void deleteObject();
    
  public ListPanel(String title, int width) {
    this(title);
    setBounds(100, 100, width, 400);
  }
  
  /**
   * @wbp.parser.constructor
   */
  public ListPanel(String title) {
    setSize( 500, 500 );
    setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), " " + title + ": ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    setLayout(new BorderLayout(0, 0));
    
    JPanel panelButtons = new JPanel();
    panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    add(panelButtons, BorderLayout.NORTH);
    GridBagLayout gbl_panelButtons = new GridBagLayout();
    gbl_panelButtons.columnWidths = new int[] {80, 80, 80, 0, 0};
    gbl_panelButtons.rowWeights = new double[] {0.0};
    gbl_panelButtons.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    panelButtons.setLayout(gbl_panelButtons);
    
    btnAdd = new JButton("Add");
    btnAdd.setActionCommand(ADD);
    btnAdd.addActionListener(this);
    GridBagConstraints gbc_btnAdd = new GridBagConstraints();
    gbc_btnAdd.gridx = 0;
    gbc_btnAdd.gridy = 0;
    gbc_btnAdd.insets = new Insets(6, 6, 6, 5);
    gbc_btnAdd.anchor = GridBagConstraints.WEST;
    gbc_btnAdd.gridheight = GridBagConstraints.REMAINDER;
    gbc_btnAdd.fill = GridBagConstraints.BOTH;
    panelButtons.add(btnAdd, gbc_btnAdd);
    
    btnEdit = new JButton("Edit");
    btnEdit.setActionCommand(EDIT);
    btnEdit.addActionListener(this);
    GridBagConstraints gbc_btnEdit = new GridBagConstraints();
    gbc_btnEdit.gridx = 1;
    gbc_btnEdit.gridy = 0;
    gbc_btnEdit.insets = new Insets(6, 6, 6, 5);
    gbc_btnEdit.anchor = GridBagConstraints.NORTH;
    gbc_btnEdit.gridheight = GridBagConstraints.REMAINDER;
    gbc_btnEdit.fill = GridBagConstraints.BOTH;
    panelButtons.add(btnEdit, gbc_btnEdit);
    
    btnDelete = new JButton("Delete");
    btnDelete.setActionCommand(DELETE);
    btnDelete.addActionListener(this);
    GridBagConstraints gbc_btnDelete = new GridBagConstraints();
    gbc_btnDelete.gridx = 2;
    gbc_btnDelete.gridy = 0;
    gbc_btnDelete.insets = new Insets(6, 6, 6, 5);
    gbc_btnDelete.anchor = GridBagConstraints.NORTH;
    gbc_btnDelete.gridheight = GridBagConstraints.REMAINDER;
    gbc_btnDelete.fill = GridBagConstraints.BOTH;
    panelButtons.add(btnDelete, gbc_btnDelete);
    
    btnExport = new JButton("Export");
    btnExport.setActionCommand(EXPORT);
    btnExport.addActionListener(this);
    GridBagConstraints gbc_btnExport = new GridBagConstraints();
    gbc_btnExport.gridx = 3;
    gbc_btnExport.gridy = 0;
    panelButtons.add(btnExport, gbc_btnExport);
    
    objTable.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
          editObject();
      }
    });
    
    objTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2)
                editObject();
      }
    });
    
    JScrollPane panelTable = new JScrollPane(objTable); 
    add(panelTable, BorderLayout.CENTER);
    
    setVisible( true );
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    
    switch (action) {
        case ADD:
            addObject();
            break;
        case EDIT:
            editObject();
            break;
        case DELETE:
            deleteObject();
            break;
        /*case EXPORT:
          exportToXML();
      break;*/
    }
    
  }
   
  public void setButtonsVisiblity(boolean btnAddVisible, boolean btnEditVisible, boolean btnDeleteVisible, boolean btnExportVisible) {
    btnAdd.setVisible(btnAddVisible);
    btnEdit.setVisible(btnEditVisible);
    btnDelete.setVisible(btnDeleteVisible);
    btnExport.setVisible(btnExportVisible);
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
   decorateTableColumn(objTable, 0, 30, SwingConstants.CENTER);
 }
  
  protected void loadObjects(ArrayList<Object> entities) {
    TableModel ltm = getTableModel(entities);
    
        objTable.setModel(ltm);
        decorateTable();
        
        if (entities.size() != 0)
          objTable.setRowSelectionInterval(selectedRow, selectedRow);
  }
  
  
}
