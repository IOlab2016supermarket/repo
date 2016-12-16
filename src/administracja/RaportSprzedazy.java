package administracja;

import sprzedaz.Sprzedaz;

public class RaportSprzedazy extends RaportZlecenie {

	@Override
	public String toString() {
		return "RaportSprzedazy";
	}

	@Override
	public String getRaport() {
		Sprzedaz sprzedaz = new Sprzedaz();
		return "Ilo�� sprzedanych produkt�w: "+sprzedaz.pobierzIloscSprzedanych()+'\n'+"Warto�� sprzedanych produkt�w :"+sprzedaz.pobierzWartoscSprzedanych();
	
	}

	public RaportSprzedazy() {
		super();
	}

}
