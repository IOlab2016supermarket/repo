package administracja_projekt;

import java.util.Date;

public class Pracownik {
	private int id;
	private String imie;
	private String nazwisko;
	private Date dataZatrudnienia;
	private String stanowisko;
	
	public Pracownik(int id, String imie, String nazwisko, Date dataZatrudnienia, String stanowisko) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.dataZatrudnienia = dataZatrudnienia;
		this.stanowisko = stanowisko;
	}

	public String podajStanowisko(){
		return stanowisko;
	}
	
	public void ustawStanowisko(String noweStanowisko){
		stanowisko = noweStanowisko;
	}
	
	public String podajImie(){
		return imie;
	}
	
	public String podajNazwisko(){
		return nazwisko;
	}
	
	public void ustawImie(String noweImie){
		 imie = noweImie;
	}
	public Date podajDateZatrudnienia(){
		return dataZatrudnienia;
	}
}
