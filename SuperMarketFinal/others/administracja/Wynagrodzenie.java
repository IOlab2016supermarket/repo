package administracja;

import java.util.Date;

public class Wynagrodzenie {
	private int id_wynagrodzenia;
	private Pracownik pracownik;
	private float kwota;
	private Date czasWyplacenia;
	
	public Pracownik getPracownik() {
		return pracownik;
	}

	public Wynagrodzenie(int id_wynagrodzenia, float wartosc, Date czasWyplacenia, Pracownik pracownik) {
		this.id_wynagrodzenia = id_wynagrodzenia;
		this.kwota = wartosc;
		this.czasWyplacenia = czasWyplacenia;
		this.pracownik = pracownik;
	}

	public int getId_wynagrodzenia() {
		return id_wynagrodzenia;
	}

	public float getKwota() {
		return kwota;
	}
	
	public Date getCzasWyplacenia() {
		return czasWyplacenia;
	}
}
