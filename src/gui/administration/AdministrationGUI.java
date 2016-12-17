package gui.administration;

import java.util.ArrayList;

import javax.swing.JDialog;

import administracja.Administracja;
import administracja.Pracownik;
import gui.Common;
import gui.superclasses.ListPanel;
import gui.superclasses.TableModel;

public class AdministrationGUI extends ListPanel {

  private static final long serialVersionUID = 1L;
  
  private Administracja administration;
  private Pracownik pracownik;

  public AdministrationGUI( String title ) {
    super( title );
    setSize(500, 500);
    setVisible( true );
  }

  @Override
  protected void addObject( ) {
    Common.showFrame(new AdminstrationEditPanel( pracownik ));
  }

  @Override
  protected void editObject( ) {
    administration.edytujPracownika( pracownik );
  }

  @Override
  protected void deleteObject( ) {
    administration.usunPracownika( pracownik );
  }

  @Override
  protected JDialog getEditDialog( ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected TableModel getTableModel( ArrayList<Object> objects ) {
    new AdministrationTableModel( objects );
    return null;
  }

}
