package administracja_projekt;

import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Kadra {
	private Vector<Pracownik> pracownicy;
	
	public Kadra(Vector<Pracownik> pracownicy) {
		this.pracownicy = pracownicy;
	}

	public void dodajPracownika(Pracownik pracownik){
		pracownicy.add(pracownik);
	}
	
	public void usunPracownika(Pracownik pracownik){
		pracownik.setData_zwolnienia(new Date());
		pracownicy.remove(pracownik);
	}
	
	public void edytujPracownika(Pracownik pracownik){
		pracownik.setNazwisko(JOptionPane.showInputDialog("Podaj nowe nazwisko"));
		pracownik.setUlica(JOptionPane.showInputDialog("Podaj nowy adres (ulica)"));
		pracownik.setNr_budynku(JOptionPane.showInputDialog("Podaj nowy adres (nr budynku)"));
		pracownik.setNr_mieszkania(JOptionPane.showInputDialog("Podaj nowy adres (nr mieszkania)"));
		pracownik.setId_stanowsika(Integer.parseInt(JOptionPane.showInputDialog("Podaj ID nowego stanowiska")));
	}
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, int noweStanowisko){
		pracownik.setId_stanowsika(noweStanowisko);
		
	}
	public Pracownik wezPracownika(int indeks){
		return pracownicy.elementAt(indeks);
	}
}
