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
		return "Iloœæ sprzedanych produktów: "+sprzedaz.pobierzIloscSprzedanych()+'\n'+"Wartoœæ sprzedanych produktów :"+sprzedaz.pobierzWartoscSprzedanych();
	
	}

	public RaportSprzedazy() {
		super();
	}

}
