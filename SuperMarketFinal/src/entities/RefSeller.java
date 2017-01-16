package entities;

import superclasses.Entity;
import sprzedaz.Sprzedawca;

// Wrapper for class 'Sprzedawca' (extends class 'Entity') 
public class RefSeller extends Entity {
	private Sprzedawca sprzedawca;

	public RefSeller (int id, String imie, String nazwisko) {
    	super(id);
    	sprzedawca = new Sprzedawca(id, imie, nazwisko);
    }
	
	public Sprzedawca getSprzedawca() {
		return sprzedawca;
	}

	public void setSprzedawca(Sprzedawca sprzedawca) {
		this.sprzedawca = sprzedawca;
	}
	
	@Override
    public String toString() {
		return sprzedawca.getImie() + " " + sprzedawca.getNazwisko();
    }
    
}