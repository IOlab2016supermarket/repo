package administracja;

import java.sql.Connection;

public class Administracja {
	private ZarzadzanieBudzetem zarzadzanieBudzetem;
	private Kadra kadra;
	public Administracja(Connection polaczenie) {
		kadra = new Kadra(polaczenie);
		zarzadzanieBudzetem = new ZarzadzanieBudzetem(0, polaczenie);
	}
	public void dodajPracownika(Pracownik pracownik){
		kadra.dodajPracownika(pracownik);
	}
	
	public void usunPracownika(Pracownik pracownik){
		kadra.usunPracownika(pracownik);
	}
	
	public void edytujPracownika(Pracownik pracownik){
		kadra.edytujPracownika(pracownik);
	}
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, String noweStanowisko){
		kadra.zmienStanowiskoPracownika(pracownik, noweStanowisko);
	}
	
	public Pracownik wezPracownika(int indeks){
		return kadra.wezPracownika(indeks);
	}
	
	public void generujRaport(RaportZlecenie raport){
		zarzadzanieBudzetem.generujRaport(raport);
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		zarzadzanieBudzetem.wyplacWynagrodzenie(pracownik);
	}
	
}
