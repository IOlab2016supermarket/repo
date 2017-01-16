package entities;

import superclasses.Entity;
import magazyn.Magazynier;

// Wrapper for class 'Magazynier' (extends class 'Entity') 
public class RefStore extends Entity {
	private Magazynier magazynier;

	public RefStore (int id, String imie, String nazwisko) {
    	super(id);
    	magazynier = new Magazynier(id, imie, nazwisko);
    }
	
	public Magazynier getMagazynier() {
		return magazynier;
	}

	public void setMagazynier(Magazynier magazynier) {
		this.magazynier = magazynier;
	}
	
	@Override
    public String toString() {
		return magazynier.pobierzImie() + " " + magazynier.pobierzNazwisko();
    }
    
}