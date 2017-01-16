package entities;

import java.util.Date;

import superclasses.EntityDoc;

public class DocSalary extends EntityDoc {
	private String period;
	private int daysAll;
	private int daysWork;
	
	public DocSalary(int id, Date date, String number, String period, int daysAll, int daysWork, float summa) {
		super(id, date, number, summa);
		
		this.period = period;
		this.daysAll = daysAll;
		this.daysWork = daysWork;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getDaysAll() {
		return daysAll;
	}

	public void setDaysAll(int daysAll) {
		this.daysAll = daysAll;
	}

	public int getDaysWork() {
		return daysWork;
	}

	public void setDaysWork(int daysWork) {
		this.daysWork = daysWork;
	}
	
	@Override
    public String toString() {
        return "<Salary" + super.toString() + ">";
    }
	
}