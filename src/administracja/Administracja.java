package administracja_projekt;

import java.util.Vector;

public class Administracja {
	private ZarzadzanieBudzetem zarzadzanieBudzetem;
	private Kadra kadra;
	public Administracja() {
		kadra = new Kadra(new Vector<Pracownik>());
		zarzadzanieBudzetem = new ZarzadzanieBudzetem(0);
	}
	
}
