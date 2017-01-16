package main;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FloatTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	
	public FloatTextField() {
		super();
		setHorizontalAlignment(SwingConstants.RIGHT);
		setColumns(10);
		Common.setRestrictions(this, "float");
		this.setText("0.0");
	}
	
	public float getFloat() {
		return Common.parseFloat(this.getText().trim());
	}
	
}