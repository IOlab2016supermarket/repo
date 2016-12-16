package administracja;
import java.util.Date;

public class RaportWydatkow extends RaportZlecenie {

	@Override
	public String getRaport() {
		// TODO Magazyn nie ma interfejsu. Panowie, tak siê nie robi. Wychodzimy.
		return "Magazyn nie ma interfejsu. Panowie, tak siê nie robi. Wychodzimy";
	}

	public RaportWydatkow(Date poczatek, Date koniec) {
		super(poczatek, koniec);
	}

}
