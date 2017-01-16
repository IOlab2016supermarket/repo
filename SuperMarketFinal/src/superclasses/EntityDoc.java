package superclasses;

import java.util.Date;

public abstract class EntityDoc extends Entity {
	private Date date;
	private String number;
	private float summa;
	
	public EntityDoc(int id, Date date, String number, float summa) {
		super(id);
		
		this.date = date;
		this.number = number;
		this.summa = summa;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public float getSumma() {
		return summa;
	}

	public void setSumma(float summa) {
		this.summa = summa;
	}
	
	@Override
    public String toString() {
        return " #" + number + " (" + date + ")"; 
    }
    
}