package administracja;

import java.util.Date;
import java.util.Vector;

public class RaportWynagrodzen extends RaportZlecenie {

	private Vector<Wynagrodzenie> wyplaconeWynagrodzenia;

	public RaportWynagrodzen(Vector<Wynagrodzenie> wyplaconeWynagrodzenia, Date poczatek, Date koniec) {
		super(poczatek, koniec);
		this.wyplaconeWynagrodzenia = wyplaconeWynagrodzenia;
	}

	@Override
	public String getRaport() {
		String tytul = "Wyp³acone wynagrodzenia od " + getPoczatek().toString() + "do " + getKoniec().toString();
		String tabela = "Imiê\tNazwisko\tStanowisko\tWyp³acona kwota\tData\n";
		String podsumowanie = "Podsumowanie:\nCa³kowita suma wyp³aconych wynagrodzeñ: ";
		float sumaWynagrodzen = 0f;
		for (Wynagrodzenie w : wyplaconeWynagrodzenia) {
			if (w.getCzasWyplacenia().after(getPoczatek()) && w.getCzasWyplacenia().before(getKoniec())) {
				Pracownik p = w.getPracownik();
				tabela = tabela + p.getImie() + "\t" + p.getNazwisko() + "\t" + p.getStanowisko() + "\t" + w.getKwota() + "\t" + w.getCzasWyplacenia() + "\n";
				sumaWynagrodzen += w.getKwota();
			}
		}
		podsumowanie = podsumowanie + Float.toString(sumaWynagrodzen);
		return tytul + "\n" + tabela + podsumowanie;
	}

}
