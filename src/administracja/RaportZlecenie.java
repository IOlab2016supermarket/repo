package administracja;

import java.util.Date;

public class RaportZlecenie {
	@Override
	public String toString() {
		return "RaportZlecenie";
	}

	protected Date poczatek;
	protected Date koniec;
	
	public RaportZlecenie() {
		poczatek = new Date();
	}

	public Date getKoniec() {
		return koniec;
	}
	
	public void setKoniec(Date koniec) {
		this.koniec = koniec;
	}
	
	public Date getPoczatek() {
		return poczatek;
	}
	public String getRaport(){
		return "Tutaj powinno si� znajdowa� cokolwiek o raporcie. To jest klasa bazowa, wi�c tu nic (note really, maybe, it's classified)";
		
	}
}
