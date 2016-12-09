package administracja;

import java.util.Vector;

public class Administracja {
	private ZarzadzanieBudzetem zarzadzanieBudzetem;
	private Kadra kadra;
	public Administracja() {
		kadra = new Kadra(new Vector<Pracownik>());
		zarzadzanieBudzetem = new ZarzadzanieBudzetem(0);
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
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, int noweStanowisko){
		kadra.zmienStanowiskoPracownika(pracownik, noweStanowisko);
	}
	
	public Pracownik wezPracownika(int indeks){
		return kadra.wezPracownika(indeks);
	}
	
	public String generujRaport(RaportZlecenie raport){
		return zarzadzanieBudzetem.generujRaport(raport);
		
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		zarzadzanieBudzetem.wyplacWynagrodzenie(pracownik);
	}
	
}
