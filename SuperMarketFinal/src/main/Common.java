package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import entities.RefKonto;
import entities.RefUser;

public class Common {
	private static RefKonto registeredKonto;
	private static RefUser registeredUser;
	
	public static RefKonto getRegisteredKonto() {
		return registeredKonto;
	}
	
	public static void setRegisteredKonto(RefKonto konto) {
		registeredKonto = konto;
	}
	
	public static RefUser getRegisteredUser() {
		return registeredUser;
	}

	public static void setRegisteredUser(RefUser registeredUser) {
		Common.registeredUser = registeredUser;
	}
	
	/**
	 * Show frame on the center of screen 
	 */
	public static void showFrame(Window frame) {
		int screenWidth = 0, screenHeight = 0;
		
		GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice graphicsDevice : screenDevices) {
            screenWidth = graphicsDevice.getDefaultConfiguration().getBounds().width;
            screenHeight = graphicsDevice.getDefaultConfiguration().getBounds().height;
        }
		
        Rectangle r = frame.getBounds();
		
		int frameWidth = r.width, frameHeight = r.height;
		int posX = (screenWidth - frameWidth) / 2;
		int posY = (screenHeight - frameHeight) / 2 - 40;
		
		frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.setBounds(posX, posY, r.width, r.height);
		
		frame.setVisible(true);
	}
	
	public static void makeFrame(JPanel panel, String title) {
		Rectangle r = panel.getBounds();
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(r.x, r.y, r.width, r.height);
		frame.add(panel);
		
		showFrame(frame);
	}
	
	public static int parseInt(String s) {
		int n = 0;
		try {
			n = Integer.parseInt(s);
		} catch (Exception e) {}
		return n;
	}
	
	public static float parseFloat(String s) {
		float f = 0;
		try {
			f = Float.parseFloat(s);
		} catch (Exception e) {}
		return f;
	}
	
	public static void showErrorMessage(Component cmp, String message) {
		JOptionPane.showMessageDialog(cmp, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static int showConfirmDialog(Component cmp, String message, String title) {
		Object[] options = { "Yes", "No" };
        return JOptionPane.showOptionDialog(
        		cmp, message, title,
        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        		null, options, options[1]
        );
	}
	
	public static float Round(float value, int digits) {
		return new BigDecimal(value).setScale(digits, RoundingMode.HALF_UP).floatValue();
	}
	
	// for text fields with numeric values
	private static DocumentFilter getTextFilter(String filterType) {
		String bannedSymbols = filterType.toUpperCase().equals("FLOAT") ? "[^0123456789.]" : "[^0123456789]";
		
		return new DocumentFilter() {
		    @Override
		    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		        string = string.replaceAll(bannedSymbols, "");
		        super.insertString(fb, offset, string, attr);
		    }

		    @Override
		    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		        text = text.replaceAll(bannedSymbols, "");
		        super.replace(fb, offset, length, text, attrs);
		    }
		};
		
	}
	
	protected static void setRestrictions(JTextField field, String filterType) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(getTextFilter(filterType));
	}

}
