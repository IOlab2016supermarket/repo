package administracja;
import java.util.ArrayList;
import java.util.Date;
import magazyn.Magazyn;
import wspolne.Produkt;
import java.sql.Connection;
import java.util.Iterator;

public class RaportWydatkow extends RaportZlecenie {

    private Connection polaczenie;
    
	@Override
	public String toString() {
		return "Raport wydatków";
	}

	@Override
	public String getRaport() {
            Magazyn magazyn = new Magazyn();
            magazyn.wczytajProduktyZBazy((com.mysql.jdbc.Connection)polaczenie);
            ArrayList<Produkt> produkty = magazyn.pobierzProdukty();
            float koszty = magazyn.zliczCeneZakupu();
            return "Liczba produktow w magazynie: " + produkty.size() + "\n" + "Laczna cena zakupu produktow w magazynie: " + Float.toString(koszty) + "\n";
	}

	public RaportWydatkow(Date poczatek, Date koniec, Connection polaczenie) {
		super(poczatek, koniec);
                this.polaczenie = polaczenie;
	}

}
