package administracja_projekt;

import java.util.Date;

public class Wynagrodzenie {
	private float wartosc;
	private Date czasWyplacenia;
	private Pracownik pracownik;
	
	public Wynagrodzenie(float wartosc, Date czasWyplacenia, Pracownik pracownik) {
		this.wartosc = wartosc;
		this.czasWyplacenia = czasWyplacenia;
		this.pracownik = pracownik;
	}

	public float getWartosc() {
		return wartosc;
	}
	
	public Date getCzasWyplacenia() {
		return czasWyplacenia;
	}
}
