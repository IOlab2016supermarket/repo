package dostawa;
import wspolne.Zamowienie;
import wspolne.Produkt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dostawa {

	private List<Zamowienie> zamowienia;
	private List<Dostawca> dostawcy;
	private Eksport eksport;
	
	public Dostawa()
	{
		zamowienia = new ArrayList<Zamowienie>();
		dostawcy = new ArrayList<Dostawca>();
		eksport = new Eksport();
	}
	
	public void dodajDostawce( String nazwa, String adres)
	{
		dostawcy.add(new Dostawca(dostawcy.size()+1,nazwa,adres));
	}
	
	public void usunDostawce(int indeks)
	{
		dostawcy.remove(indeks);
	}
	
	public List<Dostawca> pobierzListeDostawcy()
	{
		return dostawcy;
	}
	
	public List<Zamowienie> pobierzListeZamowienia()
	{
		return zamowienia;
	}
	
	public void dodajZamowienie(Zamowienie zamowienie)
	{
		zamowienia.add(zamowienie);
	}
	
	public void dodajZamowienie(int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,Date data_zlozenia, int id_dostawca)
	{
		zamowienia.add(new Zamowienie(nr_zamowienia,produkty,czas_dostawy,data_zlozenia,id_dostawca));
	}
	
	
	/*
	 nrZamowienia - numer zamowienia ktoremu odpowiada dostawa
	 produktyMagazyn - lista produktow przechowywanych w magazynie
	 */
	public boolean sprawdzZgodnoscDostawy(int nrZamowienia,List<Produkt> produktyDostawa,Date dataDostawy,int idDostawcy, List<Produkt> produktyMagazyn)
	{
		List<Produkt> brakReferencji = new ArrayList<Produkt>();
		for(int i = 0; i < produktyDostawa.size(); i++)
		{
			brakReferencji.add(new Produkt(produktyDostawa.get(i)));
		}
		int indeks = -1;
		for(int i = 0; i < zamowienia.size(); i++ )
		{
			if(zamowienia.get(i).pobierzNrZamowienia() == nrZamowienia)
			{
				indeks = i;
				if(zamowienia.get(i).pobierzIdSprzedawcy() != idDostawcy)
					return false;
			}
		}
		
		if(indeks == -1)
			return false;
		if(zamowienia.get(indeks).pobierzListeProduktow().size() != brakReferencji.size())
			return false;
		
		int iloscZgodnychProduktow = 0;
		for(int i = 0; i < brakReferencji.size(); i++)
		{
			for(int j = 0; j < brakReferencji.size(); j++)
			{
				if(zamowienia.get(indeks).pobierzListeProduktow().get(i).pobierzNazwe() == brakReferencji.get(j).pobierzNazwe())
				{
					if(zamowienia.get(indeks).pobierzListeProduktow().get(i).pobierzIlosc() != brakReferencji.get(j).pobierzIlosc())
						return false;
					iloscZgodnychProduktow++;
				}
			}	
		}
		if(iloscZgodnychProduktow != brakReferencji.size() || iloscZgodnychProduktow != zamowienia.get(indeks).pobierzListeProduktow().size())
			return false;
		zamowienia.get(indeks).zamowienieZrealizowane(dataDostawy);
		dodajDoMagazynu(produktyMagazyn,brakReferencji);
		return true;
	}
	
	private void dodajDoMagazynu(List<Produkt> produktyMagazyn,List<Produkt> produktyDostawa)
	{
		for(int i = 0; i < produktyDostawa.size(); i++)
		{
			for(int j = 0; j < produktyMagazyn.size(); j++)
			{
				if(produktyDostawa.get(i).pobierzNazwe() == produktyMagazyn.get(j).pobierzNazwe())
					produktyMagazyn.get(j).zwiekszIlosc(produktyDostawa.get(i).pobierzIlosc());
			}
		}
	}
}
