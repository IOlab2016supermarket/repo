/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Wojtass
 */
public class Zamowienie {

	private int nr_zamowienia;
	private List<Produkt> produkty;
	private String status;
	private int czas_dostawy;
	private Date data_zlozenia; 
	private Date data_dostawy;
	private int id_sprzedawcy;
    //private int idProdukt;
    //private int iloscProduktow;
    
	public Zamowienie(int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,String data_zlozenia, int id_sprzedawcy)
	{
		//String data_zlozenia w formacie dd/mm/yyyy
		this.nr_zamowienia = nr_zamowienia;
		this.produkty = new ArrayList<Produkt>();
		for(int i = 0; i < produkty.size(); i++)
		{
			this.produkty.add(new Produkt(produkty.get(i)));
		}
		this.czas_dostawy = czas_dostawy;
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			this.data_zlozenia = simple.parse(data_zlozenia);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		this.id_sprzedawcy = id_sprzedawcy;
		status = new String("W trakcie realizacji");
	}

	public int pobierzNrZamowienia()
	{
		return nr_zamowienia;
	}
	
	public String sprawdzStatus()
	{
		return status;
	}
	
	public double zliczWartoscZamowienia()
	{
		double wartosc = 0;
		for(int i = 0; i < produkty.size(); i++)
		{
			wartosc += produkty.get(i).pobierzCeneSprzedazy();
		}
		return wartosc;
	}
	
	public int pobierzIdSprzedawcy()
	{
		return id_sprzedawcy;
	}
	
	public List<Produkt> pobierzListeProduktow()
	{
		return produkty;
	}
	
	//String data_dostawy w formacie dd/mm/yyyy
	public void zamowienieZrealizowane(String data_dostawy)
	{
		status = new String("Zrealizowane");
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			this.data_dostawy = simple.parse(data_dostawy);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void dodajProdukt(Produkt produkt)
	{
		produkty.add(produkt);
	}
	
	public void usunProdukt(Produkt produkt)
	{
		int tmp = produkty.indexOf(produkt);
		produkty.remove(tmp);
	}
	
	public String toString()
	{
		
		return nr_zamowienia+","+status+","+czas_dostawy+","+data_zlozenia+","+data_dostawy+
				","+id_sprzedawcy+","+produkty;
	}

}
