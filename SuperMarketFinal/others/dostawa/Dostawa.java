package dostawa;
import wspolne.Zamowienie;
import wspolne.Produkt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


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
	
	//data_zlozenia podawana w postaci yyyy-mm-dd
	public void dodajZamowienie(int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,String data_zlozenia, int id_dostawca)
	{
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		Date data = null;
		try 
		{
			data = simple.parse(data_zlozenia);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
    	int i;
    	if(zamowienia.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = zamowienia.size();
    		while(i == zamowienia.get(zamowienia.size()-1).pobierzIdZamowienia())
    			i++;
    	}
		zamowienia.add(new Zamowienie(i,nr_zamowienia,produkty,czas_dostawy,data,id_dostawca));
	}
	
	
	/*
	 nrZamowienia - numer zamowienia ktoremu odpowiada dostawa
	 produktyMagazyn - lista produktow przechowywanych w magazynie
	 dataDostawy - podawana w postaci yyyy-mm-dd
	 */
	public boolean sprawdzZgodnoscDostawy(int nrZamowienia,List<Produkt> produktyDostawa,String dataDostawy,int idDostawcy, List<Produkt> produktyMagazyn)
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
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		Date data = null;
		try 
		{
			data = simple.parse(dataDostawy);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		zamowienia.get(indeks).zamowienieZrealizowane(data);
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
	
	public void zapiszDostawce(Dostawca dostawca, Connection connection) throws SQLException
	{
		String query = "";
		query = "INSERT INTO dostawca VALUES ("+dostawca.pobierzId()+", '" + dostawca.pobierzNazwe() + "', '" + dostawca.pobierzAdres() +"');";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        //connection.close();
	}
	
	public void wczytajDostawcow(Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM dostawca";
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		int id = 0;
		String nazwa = null,adres = null;
		while(result.next())
		{
			id=result.getInt(1);
			nazwa=result.getString(2);
			adres=result.getString(3);
			dostawcy.add(new Dostawca(id,nazwa,adres));
		}
        statement.close();
        //connection.close();
	}
	
	
}
