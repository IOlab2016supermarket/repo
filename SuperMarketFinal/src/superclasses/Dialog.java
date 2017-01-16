package superclasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public abstract class Dialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	protected JPanel panelFields;
	protected JButton btnOK, btnCancel; 
	
	public Dialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		
		contentPane = (JPanel) getContentPane(); 
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelFields = new JPanel();
		panelFields.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelFields.setLayout(null);
		contentPane.add(panelFields, BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		btnOK = new JButton("OK");
		panelButtons.add(btnOK);
		btnOK.addActionListener(this);
		
		getRootPane().setDefaultButton(btnOK);
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {}
	
}