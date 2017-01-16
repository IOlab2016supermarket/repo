package entities;

import superclasses.EntityItem;

public class DocSalaryItem extends EntityItem {
	private int userId;
	private String userFirstName;
	private String userLastName;
	private float salaryRef;
	private int workDays;
	private float salaryCalc;
	private float premiaRef;
	private float premiaCalc;
	
	public DocSalaryItem(int id, int userId, String userFirstName, String userLastName, float salaryRef, int workDays, float salaryCalc, float premiaRef, float premiaCalc, float summa) {
		super(id, summa);
		
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.salaryRef = salaryRef;
		this.workDays = workDays;
		this.salaryCalc = salaryCalc;
		this.premiaRef = premiaRef;
		this.premiaCalc = premiaCalc;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public String getUserName() {
		return userFirstName + " " + userLastName;
	}

	public float getSalaryRef() {
		return salaryRef;
	}

	public void setSalaryRef(float salaryRef) {
		this.salaryRef = salaryRef;
	}

	public int getWorkDays() {
		return workDays;
	}

	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}

	public float getSalaryCalc() {
		return salaryCalc;
	}

	public void setSalaryCalc(float salaryCalc) {
		this.salaryCalc = salaryCalc;
	}

	public float getPremiaRef() {
		return premiaRef;
	}

	public void setPremiaRef(float premiaRef) {
		this.premiaRef = premiaRef;
	}

	public float getPremiaCalc() {
		return premiaCalc;
	}

	public void setPremiaCalc(float premiaCalc) {
		this.premiaCalc = premiaCalc;
	}
	
	@Override
    public String toString() {
        return getUserName() + ": " + workDays + " day(s) / " + super.getSumma();
    }

}