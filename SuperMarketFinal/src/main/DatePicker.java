package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePicker extends JDatePickerImpl {
	private static final long serialVersionUID = 1L;
	
	private static final Properties p = new Properties();
	
	public DatePicker() {
		super(getDatePanel(), getDateLabelFormatter());
	}
	
	private static JDatePanelImpl getDatePanel() {
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		return datePanel;
	}

	private static AbstractFormatter getDateLabelFormatter() {
		return new AbstractFormatter() {
			private static final long serialVersionUID = 1L;
			private SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);

			@Override
			public Object stringToValue(String text) throws ParseException {
				return dateFormatter.parseObject(text);
			}

			@Override
			 public String valueToString(Object value) throws ParseException {
			     if (value != null) {
			         Calendar cal = (Calendar) value;
			         return dateFormatter.format(cal.getTime());
			     }
			     return "";
			}
		};
	}
	
	public void setDate(Date dt) {
		if (dt == null) return;
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        
        this.getModel().setSelected(true);
        this.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public Date getDate() {
		UtilDateModel model = (UtilDateModel) this.getModel();
		if (!model.isSelected()) return null;

		GregorianCalendar calendar = new GregorianCalendar(model.getYear(), model.getMonth(), model.getDay());
		return calendar.getTime();
	}
	
	public void setEditable(boolean enabled) {
		this.getComponent(1).setEnabled(enabled);
	}

}
