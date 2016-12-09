package administracja_projekt;

import java.util.Vector;

public class ZarzadzanieBudzetem {
	private int stanKonta; //diagram nie wskazywa³ typu, zak³adam Integer
	private Vector<Wynagrodzenie> wyplaconeWynagrodzenia;
	
	public ZarzadzanieBudzetem(int stanKonta) {
		this.stanKonta = stanKonta;
		wyplaconeWynagrodzenia = new Vector<Wynagrodzenie>();
	}

	public String generujRaport(RaportZlecenie raport){
		return "DEBUG STRING";
		
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		
	}
}
