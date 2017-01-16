package entities;

import superclasses.Entity;
import sprzedaz.Klient;

// Wrapper for class 'Klient' (extends class 'Entity') 
public class RefClient extends Entity {
	private Klient klient;

	public RefClient (int id, String imie, String nazwisko, int iloscPunktow, String kodPocztowy) {
    	super(id);
    	klient = new Klient(id, imie, nazwisko, kodPocztowy);
    	klient.setIloscPunktow(iloscPunktow);
    }
	
	public Klient getKlient() {
		return klient;
	}

	public void setClient(Klient client) {
		this.klient = client;
	}
	
	@Override
    public String toString() {
		return klient.getImie() + " " + klient.getNazwisko();
    }
    
}