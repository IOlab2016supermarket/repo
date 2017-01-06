package administracja;

import java.sql.Connection;

public class Administracja {
	private ZarzadzanieBudzetem zarzadzanieBudzetem;
	private Kadra kadra;
    private Connection polaczenie;
        
    public Connection getPolaczenie() {
        return polaczenie;
    }

	public Administracja(Connection polaczenie) {
        this.polaczenie = polaczenie;
		this.kadra = new Kadra(this);
		this.zarzadzanieBudzetem = new ZarzadzanieBudzetem(0, this);
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
	
	public Pracownik wezPracownika(int nr){
		return kadra.wezPracownika(nr);
	}
	
	public void generujRaport(RaportZlecenie raport){
		zarzadzanieBudzetem.generujRaport(raport);
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		zarzadzanieBudzetem.wyplacWynagrodzenie(pracownik);
	}
	
}
