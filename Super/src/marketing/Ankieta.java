package marketing;

import java.util.List;
import java.util.ArrayList;

public class Ankieta {
	
	private int id_ankiety;
	private int id_produktu;
	private String tytul;
	private List<String> pytania; 

	public Ankieta(int id_ankiety, int id_produktu, String tytul)
	{
		pytania = new ArrayList<String>();
		this.id_ankiety = id_ankiety;
		this.id_produktu = id_produktu;
		this.tytul = tytul;
	}
	
	public void dodajPytanie(String txt)
	{
		pytania.add(txt);
	}
	
	public void usunPytanie(int numer)
	{
		pytania.remove(numer);
	}
	
	public void ustawId(int id)
	{
		id_ankiety = id;
	}
	
	public int pobierzId()
	{
		return id_ankiety;
	}
	
	public void ustawIdProduktu(int id)
	{
		id_produktu = id;
	}
	
	public String pobierzTytul()
	{
		return tytul;
	}
	
	public void ustawTytul(String tytul)
	{
		this.tytul = tytul;
	}
	
	public int pobierzIdProduktu()
	{
		return id_produktu;
	}
	
	public String toString()
	{
		return id_ankiety+"    "+id_produktu;
	}
}
