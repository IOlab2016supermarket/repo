package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class Logowanie extends JDialog implements ActionListener {
  
  private static final long serialVersionUID = 1L;
  private JTextField textFieldLogin;
  private JPasswordField passwordField;
  private JButton btnOk;
  private JButton btnCancel;
  private JTextField textOutput;
  private String login = "mark1984";
  private String password = "1234";
  
  private KeyListener exitOnEsc() {
    return new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
              System.exit(0);
      }
    };
  }
  
  public Logowanie() {
    setSize(350, 350);
    setResizable( false );
    
    setTitle("Logowanie");
    getContentPane().setLayout(null);
    
    JLabel lblLogowanieDoSystemu = new JLabel("Logowanie do systemu");
    lblLogowanieDoSystemu.setFont(new Font("Calisto MT", Font.BOLD, 15));
    lblLogowanieDoSystemu.setBounds(102, 11, 158, 35);
    getContentPane().add(lblLogowanieDoSystemu);
    
    JLabel lblLogin = new JLabel("Login:");
    lblLogin.setBounds(10, 66, 46, 14);
    getContentPane().add(lblLogin);
    
    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setBounds(10, 121, 61, 14);
    getContentPane().add(lblPassword);
    
    textFieldLogin = new JTextField();
    textFieldLogin.setBounds(83, 63, 86, 20);
    getContentPane().add(textFieldLogin);
    textFieldLogin.setColumns(10);
    
    passwordField = new JPasswordField();
    passwordField.setBounds(83, 118, 86, 20);
    getContentPane().add(passwordField);
    
    btnOk = new JButton("OK");
    btnOk.setBounds(36, 170, 89, 35);
    btnOk.addActionListener( this );
    btnOk.addKeyListener( exitOnEsc( ) );
    getContentPane().add(btnOk);
    
    btnCancel = new JButton("Cancel");
    btnCancel.setBounds(191, 170, 89, 35);
    btnCancel.addActionListener( this );
    getContentPane().add(btnCancel);
    
    textOutput = new JTextField();
    textOutput.setEditable(false);
    textOutput.setBounds(36, 233, 244, 47);
    getContentPane().add(textOutput);
    textOutput.setColumns(10);
    
    getRootPane().setDefaultButton(btnOk);
    
    setVisible(true);
  }

  @Override
  public void actionPerformed( ActionEvent evt ) {
    String passwordF = new String (passwordField.getPassword( ));
    if( evt.getSource( ) == btnOk ) {
      if( ( login.equals( textFieldLogin.getText( ) ) ) && ( password.equals ( passwordF) ) ) {
        textOutput.setText( "Uzytkownik " + login + " jest zalogowany" ); 
      }
      else {
          JOptionPane.showMessageDialog( null,"Log in error", "Error", JOptionPane.ERROR_MESSAGE );
      }
    }
    
    if( evt.getSource( ) == btnCancel) {
        System.exit( 0 );
    }
  }
}
