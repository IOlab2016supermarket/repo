package gui.administration;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import administracja.Administracja;
import administracja.Pracownik;
import baza.BazaDanych;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class AdminstrationEditPanel extends JDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  private int id = 0;
  
  private JTextField txtId;
  private JCheckBox checkAdmin;
  private JTextField txtImie;
  private JTextField txtNazwisko;
  private JTextField txtPESEL;
  private JTextField txtStanowisko;
  private JTextField txtPremia;
  private JTextField txtDataZatrudnienia;
  private JTextField txtAdres;
  private JTextField txtDataZwolnienia;
  private JTextField txtIdKonta;
  
  private Administracja adm;
  
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  
  
  public AdminstrationEditPanel(Pracownik user) {
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    getContentPane().setSize(713, 20);
    setTitle("Add / edit user");
    setResizable(false);
    setModal(true);
    setBounds(100, 100, 355, 419);
    getContentPane().setLayout(null);
    
    JPanel panelFields = new JPanel();
    panelFields.setBounds(10, 11, 329, 335);
    getContentPane().add(panelFields);
    panelFields.setLayout(null);
    
    JLabel lblId = new JLabel("ID:");
    lblId.setBounds(12, 12, 14, 16);
    panelFields.add(lblId);
    
    JLabel lblFirstName = new JLabel("Imi\u0119:");
    lblFirstName.setBounds(12, 40, 72, 25);
    panelFields.add(lblFirstName);
    
    JLabel lblLastName = new JLabel("Nazwisko:");
    lblLastName.setBounds(12, 70, 55, 25);
    panelFields.add(lblLastName);
    
    JLabel lblLogin = new JLabel("PESEL:");
    lblLogin.setBounds(12, 100, 34, 25);
    panelFields.add(lblLogin);
    
    JLabel lblPassword = new JLabel("Stanowisko:");
    lblPassword.setBounds(12, 130, 61, 25);
    panelFields.add(lblPassword);
    
    JLabel lblPhone = new JLabel("Premia:");
    lblPhone.setBounds(12, 160, 39, 25);
    panelFields.add(lblPhone);
    
    JLabel lblEmail = new JLabel("Data zatrudnienia:");
    lblEmail.setBounds(12, 190, 110, 25);
    panelFields.add(lblEmail);
    
    txtId = new JTextField();
    txtId.setBounds(85, 12, 55, 20);
    txtId.setHorizontalAlignment(SwingConstants.CENTER);
    txtId.setEditable(false);
    txtId.setColumns(10);
    panelFields.add(txtId);
    
    checkAdmin = new JCheckBox("admin");
    checkAdmin.setBounds(211, 8, 61, 24);
    panelFields.add(checkAdmin);
    
    txtImie = new JTextField();
    txtImie.setBounds(108, 40, 187, 25);
    txtImie.setColumns(10);
    panelFields.add(txtImie);
    
    txtNazwisko = new JTextField();
    txtNazwisko.setBounds(108, 70, 187, 25);
    txtNazwisko.setColumns(10);
    panelFields.add(txtNazwisko);
    
    txtPESEL = new JTextField();
    txtPESEL.setBounds(108, 100, 87, 25);
    txtPESEL.setColumns(10);
    panelFields.add(txtPESEL);
    
    txtStanowisko = new JTextField();
    txtStanowisko.setBounds(108, 130, 87, 25);
    txtStanowisko.setColumns(10);
    panelFields.add(txtStanowisko);
    
    txtPremia = new JTextField();
    txtPremia.setBounds(108, 160, 187, 25);
    txtPremia.setColumns(10);
    panelFields.add(txtPremia);
    
    txtDataZatrudnienia = new JTextField();
    txtDataZatrudnienia.setBounds(108, 190, 187, 25);
    txtDataZatrudnienia.setColumns(10);
    panelFields.add(txtDataZatrudnienia);
    
    JLabel lblIdKonta = new JLabel("Adres:");
    lblIdKonta.setBounds(12, 250, 110, 25);
    panelFields.add(lblIdKonta);
    
    txtAdres = new JTextField();
    txtAdres.setColumns(10);
    txtAdres.setBounds(108, 250, 187, 25);
    panelFields.add(txtAdres);
    
    JLabel lblDataZwolnienia = new JLabel("Data zwolnienia:");
    lblDataZwolnienia.setBounds(12, 220, 110, 25);
    panelFields.add(lblDataZwolnienia);
    
    txtDataZwolnienia = new JTextField();
    txtDataZwolnienia.setColumns(10);
    txtDataZwolnienia.setBounds(108, 220, 187, 25);
    panelFields.add(txtDataZwolnienia);
    
    JLabel lblIdKonta_1 = new JLabel("Id konta:");
    lblIdKonta_1.setBounds(12, 280, 110, 25);
    panelFields.add(lblIdKonta_1);
    
    txtIdKonta = new JTextField();
    txtIdKonta.setColumns(10);
    txtIdKonta.setBounds(108, 280, 187, 25);
    panelFields.add(txtIdKonta);
    
    JButton btnSave = new JButton("Save");
    btnSave.setActionCommand("SAVE");
    btnSave.setBounds(29, 357, 86, 23);
    btnSave.addActionListener(this);
    getContentPane().add(btnSave);
    
    JButton btnCancel = new JButton("Cancel");
    btnCancel.setBounds(144, 357, 86, 23);
    btnCancel.addActionListener(this);
    getContentPane().add(btnCancel);
    
    getRootPane().setDefaultButton(btnSave);
    
    initFields(user);
    
  }
  
  private void initFields(Pracownik user) {
        if (user != null) {
            id = user.getId_pracownika( );
            
            txtId.setText("" + id);
            
            txtImie.setText(user.getImie( ));
            txtNazwisko.setText(user.getNazwisko( ));
            txtPESEL.setText(user.getPESEL( ));
            txtStanowisko.setText(user.getStanowisko( ));
            txtPremia.setText(String.valueOf( user.getPremia( ) ));
            txtDataZatrudnienia.setText(dateFormat.format(user.getData_zatrudnienia( )));
            txtDataZwolnienia.setText(dateFormat.format(user.getData_zwolnienia( )));
            txtAdres.setText( user.getAdres( ) );
            txtIdKonta.setText( user.getId_konta( ) );
        }
    }
  
  @Override
  public void actionPerformed(ActionEvent ae) {
   Pracownik pracownik = null;
    if (ae.getActionCommand() == "SAVE") {
      try {
        pracownik = new Pracownik (
          id,
          txtIdKonta.getText( ).trim( ),
          txtImie.getText().trim(), 
          txtNazwisko.getText().trim(), 
          txtPESEL.getText().trim(), 
          txtStanowisko.getText().trim(), 
          Float.parseFloat(txtPremia.getText().trim()), 
          dateFormat.parse((txtDataZatrudnienia.getText().trim())),
          dateFormat.parse(txtDataZwolnienia.getText( ).trim( )),
          txtAdres.getText( ).trim( )
        );
      } catch ( NumberFormatException e ) {
        e.printStackTrace();
      } catch ( ParseException e ) {
        e.printStackTrace();
      }
    
    BazaDanych.polacz( );
    adm = new Administracja( BazaDanych.getPolaczenie( ) );  
    adm.dodajPracownika( pracownik );
    }
    dispose();
  }
}
