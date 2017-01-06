package administracja;

import java.util.Date;
import java.util.List;

public class RaportWynagrodzen extends RaportZlecenie {

	private List<Wynagrodzenie> wyplaconeWynagrodzenia;

	@Override
	public String toString() {
		return "Raport Wynagrodzeń";
	}

	public RaportWynagrodzen(List<Wynagrodzenie> wyplaconeWynagrodzenia, Date poczatek, Date koniec) {
		super(poczatek, koniec);
		this.wyplaconeWynagrodzenia = null;
	}

	public void ustawWynagrodzenia(List<Wynagrodzenie> wynagrodzenia) {
		wyplaconeWynagrodzenia = wynagrodzenia;
	}

	@Override
	public String getRaport() {
		String tabela = "Imię\tNazwisko\tStanowisko\tWypłacona kwota\tData\n";
		String podsumowanie = "Podsumowanie:\nCałkowita suma wypłaconych wynagrodzeń: ";;
		float sumaWynagrodzen = 0f;
		for (Wynagrodzenie w : wyplaconeWynagrodzenia) {
			if (w.getCzasWyplacenia().after(getPoczatek()) && w.getCzasWyplacenia().before(getKoniec())) {
				Pracownik p = w.getPracownik();
				tabela = tabela + p.getImie() + "\t" + p.getNazwisko() + "\t" + p.getStanowisko() + "\t" + w.getKwota() + "\t" + w.getCzasWyplacenia() + "\n";
				sumaWynagrodzen += w.getKwota();
			}
		}
		podsumowanie = podsumowanie + Float.toString(sumaWynagrodzen);
		return tabela + podsumowanie;
	}

}
