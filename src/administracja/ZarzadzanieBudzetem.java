package administracja;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ZarzadzanieBudzetem {
	private float stanKonta; 
	private Vector<Wynagrodzenie> wyplaconeWynagrodzenia;
	private Connection polaczenie;
	
	public ZarzadzanieBudzetem(float stanKonta, Connection polaczenie) {
		this.stanKonta = stanKonta;
		this.wyplaconeWynagrodzenia = new Vector<Wynagrodzenie>();
	}

	public String generujRaport(RaportZlecenie raport){
		return raport.toString()+"Stan konta: "+stanKonta;
		
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		float wartosc = Integer.parseInt(JOptionPane.showInputDialog("Podaj wysokość wynagrodzenia"));
		wyplaconeWynagrodzenia.add(new Wynagrodzenie(wartosc, new Date(),pracownik));
	}
}
