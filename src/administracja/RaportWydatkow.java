package administracja;

import sprzedaz.Sprzedaz;

public class RaportWydatkow extends RaportZlecenie {

	@Override
	public String toString() {
		return "RaportWydatkow";
	}

	@Override
	public String getRaport() {
		// TODO Magazyn nie ma interfejsu. Panowie, tak siê nie robi. Wychodzimy.
		return "Magazyn nie ma interfejsu. Panowie, tak siê nie robi. Wychodzimy";
	}

	public RaportWydatkow() {
		super();
	}

}
