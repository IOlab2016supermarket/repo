package administracja;

import java.util.Date;

public class RaportZlecenie {
	private Date poczatek;
	private Date koniec;
	
	public RaportZlecenie(Date poczatek, Date koniec) {
		this.poczatek = poczatek;
		this.koniec = koniec;
	}

	public Date getKoniec() {
		return koniec;
	}
	
	public Date getPoczatek() {
		return poczatek;
	}
	public String getRaport(){
		return "Tutaj powinno siê znajdowaæ cokolwiek o raporcie. To jest klasa bazowa, wiêc tu nic (note really, maybe, it's classified)";
		
	}
}
