package administracja;

import java.util.Date;

public class Pracownik {
	private int id_pracownika;
	private String id_konta;
	private String imie;
	private String nazwisko;
	private String PESEL;
	private String stanowisko;
	private float premia;
	private Date data_zatrudnienia;
	private Date data_zwolnienia;
	private String adres;
	
	public Pracownik(int id_pracownika, String id_konta, String imie, String nazwisko,
			String PESEL, String stanowisko, float premia, Date data_zatrudnienia, Date data_zwolnienia, String adres) {
		this.id_pracownika = id_pracownika;
		this.PESEL = PESEL;
		this.id_konta = id_konta;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.premia = premia;
		this.data_zatrudnienia = data_zatrudnienia;
		this.data_zwolnienia = data_zwolnienia;
		this.adres = adres;
		this.stanowisko = stanowisko;
	}
	public String getStanowisko() {
		return stanowisko;
	}
	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
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
	public int getId_pracownika() {
		return id_pracownika;
	}
	public String getAdres() {
		return adres;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public void setPremia(float premia) {
		this.premia = premia;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
}
