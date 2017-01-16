package main;

import javax.swing.JOptionPane;

public interface Constants {
	final int YES = JOptionPane.YES_OPTION;
	
	final String[] ROLES = {"Admin", "Sale", "Stock", "Manager", "Delivery"};
	final String ROLEFRAMES_PACKAGE = "roleframes.";
	
	final String[] UNITS = {"pc", "kg", "ton", "m", "l"};
	
	final String DATE_PATTERN = "dd.MM.yyyy";
	//final String DATE_PATTERN = "yyyy-MM-dd";
}
