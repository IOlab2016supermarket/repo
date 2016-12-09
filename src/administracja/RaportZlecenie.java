package administracja_projekt;

import java.util.Date;

public class RaportZlecenie {
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
}
