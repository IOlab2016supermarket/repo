package administracja;

import java.util.Date;

public class Pracownik {
	private String PESEL;;
	private int id_stanowsika;
	private String id_konta;
	private String imie;
	private String nazwisko;
	private float premia;
	private Date data_zatrudnienia;
	private Date data_zwolnienia;
	private String miasto;
	private String ulica;
	private String nr_budynku;
	private String nr_mieszkania;
	private String kod_pocztowy;
	private String poczta;
	public Pracownik(String PESEL, int id_stanowsika, String id_konta, String imie, String nazwisko, float premia,
			Date data_zatrudnienia, Date data_zwolnienia, String miasto, String ulica, String nr_budynku,
			String nr_mieszkania, String kod_pocztowy, String poczta) {
		super();
		this.PESEL = PESEL;
		this.id_stanowsika = id_stanowsika;
		this.id_konta = id_konta;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.premia = premia;
		this.data_zatrudnienia = data_zatrudnienia;
		this.data_zwolnienia = data_zwolnienia;
		this.miasto = miasto;
		this.ulica = ulica;
		this.nr_budynku = nr_budynku;
		this.nr_mieszkania = nr_mieszkania;
		this.kod_pocztowy = kod_pocztowy;
		this.poczta = poczta;
	}
	public int getId_stanowsika() {
		return id_stanowsika;
	}
	public void setId_stanowsika(int id_stanowsika) {
		this.id_stanowsika = id_stanowsika;
	}
	public Date getData_zwolnienia() {
		return data_zwolnienia;
	}
	public void setData_zwolnienia(Date data_zwolnienia) {
		this.data_zwolnienia = data_zwolnienia;
	}
	public String getPESEL() {
		return PESEL;
	}
	public String getId_konta() {
		return id_konta;
	}
	public String getImie() {
		return imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public float getPremia() {
		return premia;
	}
	public Date getData_zatrudnienia() {
		return data_zatrudnienia;
	}
	public String getMiasto() {
		return miasto;
	}
	public String getUlica() {
		return ulica;
	}
	public String getNr_budynku() {
		return nr_budynku;
	}
	public String getNr_mieszkania() {
		return nr_mieszkania;
	}
	public String getKod_pocztowy() {
		return kod_pocztowy;
	}
	public String getPoczta() {
		return poczta;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public void setPremia(float premia) {
		this.premia = premia;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public void setNr_budynku(String nr_budynku) {
		this.nr_budynku = nr_budynku;
	}
	public void setNr_mieszkania(String nr_mieszkania) {
		this.nr_mieszkania = nr_mieszkania;
	}
	public void setKod_pocztowy(String kod_pocztowy) {
		this.kod_pocztowy = kod_pocztowy;
	}
	public void setPoczta(String poczta) {
		this.poczta = poczta;
	}
	
	
}
