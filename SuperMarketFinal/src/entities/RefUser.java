package entities;

import java.util.Date;

import superclasses.Entity;
import administracja.Pracownik;

// Wrapper for class 'Pracownik' (adds fields & extends class 'Entity') 
public class RefUser extends Entity {
	private Pracownik worker;
	private int kontoId;
	private String login;
	private float salary;

	public RefUser(int id, int kontoId, String login, String imie, String nazwisko, String pesel, String stanowisko, float salary, float premia, Date data_zatrudnienia, Date data_zwolnienia, String adres) {
    	super(id);
    	this.login = login;
    	this.kontoId = kontoId;
    	this.salary = salary;
    	worker = new Pracownik(id, String.valueOf(kontoId), imie, nazwisko, pesel, stanowisko, premia, data_zatrudnienia, data_zwolnienia, adres);
    }
    
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getKontoId() {
		return kontoId;
	}

	public void setKontoId(int kontoId) {
		this.kontoId = kontoId;
	}

	public Pracownik getWorker() {
		return worker;
	}

	public void setWorker(Pracownik worker) {
		this.worker = worker;
	}
	
	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override
    public String toString() {
        return worker.getImie() + " " + worker.getNazwisko();
    }

}