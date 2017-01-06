package marketing;

import java.util.ArrayList;
import java.util.List;

public class AnkietaPytanie {

	private int numerPytania;
	private String pytanie;
	private boolean czyOdpowiedzDoWyboru;//true - pytanie posiada kilka okreœlonych odpowiedzi do wyboru, false - odpowiedz opisowa
	private boolean czyWielokrotnegoWyboru;//true - mozna wybrac kilka odpowiedzi w ankiecie, false - tylko jedn¹     uwzglêdniany gdy czyOdpowiedzDoWyboru = true
	private List<String> odpowiedziWybor;//zawiera liste odpowiedzi do wyboru na pytanie

	public AnkietaPytanie(int numerPytania,String pytanie,boolean czyOdpowiedzDoWyboru, boolean czyWielokrotnegoWyboru, List<String> odpowiedziWybor)
	{
		this.odpowiedziWybor = new ArrayList<String>();
		this.odpowiedziWybor = odpowiedziWybor;
		this.numerPytania = numerPytania;
		this.pytanie = pytanie;
		this.czyOdpowiedzDoWyboru = czyOdpowiedzDoWyboru;
		this.czyWielokrotnegoWyboru = czyWielokrotnegoWyboru;
	}
	
	public int pobierzNumerPytania()
	{
		return numerPytania;
	}
	
	public void ustawNumerPytania(int numerPytania)
	{
		this.numerPytania = numerPytania;
	}
	
	public String pobierzTrescPytania()
	{
		return pytanie;
	}
	
	public void ustawTrescPytania(String pytanie)
	{
		this.pytanie = pytanie;
	}
	
	public boolean pobierzCzyOdpowiedzDoWyboru()
	{
		return czyOdpowiedzDoWyboru;
	}
	
	public void ustawCzyOdpowiedzDoWyboru(boolean czyOdpowiedzDoWyboru)
	{
		this.czyOdpowiedzDoWyboru = czyOdpowiedzDoWyboru;
	}
	
	public boolean pobierzCzyWielokrotnegoWyboru()
	{
		return czyWielokrotnegoWyboru;
	}
	
	public void ustawCzyWielokrotnegoWyboru(boolean czyWielokrotnegoWyboru)
	{
		this.czyWielokrotnegoWyboru = czyWielokrotnegoWyboru;
	}
	
	public void dodajOdpowiedz(String odpowiedz)
	{
		odpowiedziWybor.add(odpowiedz);
	}
	
	public void usunOdpowiedz(String odpowiedz)
	{
		odpowiedziWybor.remove(odpowiedz);
	}
	
	public List<String> pobierzOdpowiedzi()
	{
		return odpowiedziWybor;
	}
	
	public String toString()
	{
		return numerPytania+","+pytanie+","+czyOdpowiedzDoWyboru+","+czyWielokrotnegoWyboru+"\n"
				+odpowiedziWybor.toString()+",";
	}
}
