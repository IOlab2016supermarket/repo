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
	
	public Dostawa()
	{
		zamowienia = new ArrayList<Zamowienie>();
		dostawcy = new ArrayList<Dostawca>();
	}
	
	public void dodajDostawce( String nazwa, String adres)
	{
    	int i;
    	if(dostawcy.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = dostawcy.size();
    		while(i <= dostawcy.get(dostawcy.size()-1).pobierzId())
    			i++;
    	}
		dostawcy.add(new Dostawca(i,nazwa,adres));
	}
	
	public void usunDostawce(int indeks)
	{
		dostawcy.remove(indeks);
	}
	
	public void usunDostawce(Dostawca dostawca)
	{
		dostawcy.remove(dostawca);
	}
	
	public List<Dostawca> pobierzListeDostawcy()
	{
		return dostawcy;
	}
	
	public List<Zamowienie> pobierzListeZamowienia()
	{
		return zamowienia;
	}
	
	//produktyZamowienie - nie moga miec referencji do produktow z listy produktow w magazynie
	public void dodajZamowienie(int nr_zamowienia,List<Produkt> produktyZamowienie,int czas_dostawy,Date data_zlozenia, int id_dostawca)
	{
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String txt = simple.format(data_zlozenia);
		try 
		{
			data_zlozenia = simple.parse(txt);
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
    		while(i <= zamowienia.get(zamowienia.size()-1).pobierzIdZamowienia())
    			i++;
    	}
		zamowienia.add(new Zamowienie(i,nr_zamowienia,produktyZamowienie,czas_dostawy,data_zlozenia,id_dostawca));
	}
	
	public void usunZamowienie(Zamowienie zamowienie)
	{
		zamowienia.remove(zamowienie);
	}
	
	
	/*
	 nrZamowienia - numer zamowienia ktoremu odpowiada dostawa
	 produktyDostawa - lista produktow otrzymanych od dostawcy
	 produktyMagazyn - lista produktow przechowywanych w magazynie
	 dataDostawy - podawana w postaci yyyy-mm-dd
	 */
	public boolean sprawdzZgodnoscDostawy(int nrZamowienia,List<Produkt> produktyDostawa,Date dataDostawy,String nazwaDostawcy)
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
				int idDostawcy = -1;
				for(int j = 0;j<dostawcy.size();j++)
				{
					if(dostawcy.get(j).pobierzNazwe().equals(nazwaDostawcy))
						idDostawcy = dostawcy.get(j).pobierzId();
				}
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
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String txt = simple.format(dataDostawy);
		try 
		{
			dataDostawy = simple.parse(txt);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		zamowienia.get(indeks).zamowienieZrealizowane(dataDostawy);
		return true;
	}
	
	public void dodajDoMagazynu(List<Produkt> produktyMagazyn,List<Produkt> produktyDostawa)
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
	}
	
	public void aktualizujDostawce(Dostawca dostawca, Connection connection) throws SQLException
	{
		String query = "";
		query = "UPDATE dostawca SET nazwa = '"+dostawca.pobierzNazwe()+"', adres = '" + dostawca.pobierzAdres()  +"' where idDostawca = "+dostawca.pobierzId()+";";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void aktualizujDostawce(int idDostawcy, String nazwaDostawcy, String adres, Connection connection) throws SQLException
	{
		String query = "";
		query = "UPDATE dostawca SET nazwa = '"+nazwaDostawcy+"', adres = '" + adres  +"' where idDostawca = "+idDostawcy+";";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void wczytajDostawcow(Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM dostawca";
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		while(result.next())
		{
			int id=result.getInt(1);
			String nazwa=result.getString(2);
			String adres=result.getString(3);
			dostawcy.add(new Dostawca(id,nazwa,adres));
		}
        statement.close();
	}
	
	public void wczytajZamowienia(Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM zamowienieDostawa";
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		while(result.next())
		{
			int idZamowienia=result.getInt(1);
			int nrZamowienia=result.getInt(2);
			String status=result.getString(3);
			int czasDostawy=result.getInt(4);
			Date data1=(Date) result.getObject(5);
			Date data2=(Date) result.getObject(6);
			int idSprzedawcy=result.getInt(7);
			List<Produkt> produkty = new ArrayList<Produkt>();
			produkty = wczytajProduktyZamowienia(idZamowienia,connection);
			zamowienia.add(new Zamowienie(idZamowienia,nrZamowienia,produkty,czasDostawy,data1,idSprzedawcy));
			zamowienia.get(idZamowienia-1).ustawStatus(status);
			zamowienia.get(idZamowienia-1).ustawDateDostawy(data2);
		}
        statement.close();
	}
	
	private List<Produkt> wczytajProduktyZamowienia(int idZamowienia,Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM zamowienieDostawa_Produkt where idZamowienie = "+idZamowienia;
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		List<Produkt> produkty = new ArrayList<Produkt>();
		while(result.next())
		{
			int idProduktu = result.getInt(3);
			int ilosc = result.getInt(4);
			Produkt tmp = wczytajProdukty(idProduktu , connection);
			tmp.ustawIlosc(ilosc);
			produkty.add(tmp);
		}
        statement.close();
        return produkty;
	}
	
	/*private Produkt wczytajProdukty(int idProduktu,Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM produkty where id = "+idProduktu;
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		Produkt tmp = new Produkt();
		while(result.next())
		{
			tmp.ustawId(result.getInt(1));
			tmp.ustawNazwe(result.getString(2));
			tmp.ustawIloscPunktow(result.getInt(3));
			tmp.ustawDlugoscGwarancji(result.getInt(4));
			tmp.ustawCeneSprzedazy(result.getFloat(5));
			tmp.ustawCeneZakupu(result.getFloat(6));
			//tmp.ustawIlosc(result.getInt(7));
		}
        statement.close();
        return tmp;
	}*/
	
	private Produkt wczytajProdukty(int idProduktu,Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM produkt where idProdukt = "+idProduktu;
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		Produkt tmp = new Produkt();
		while(result.next())
		{
			tmp.ustawId(result.getInt(1));
			tmp.ustawNazwe(result.getString(2));
			tmp.ustawWage(result.getFloat(3));
			tmp.ustawDlugoscGwarancji(result.getInt(4));
			tmp.ustawDateWaznosci(result.getDate(5));
			tmp.ustawCeneZakupu(result.getFloat(6));
			tmp.ustawCeneSprzedazy(result.getFloat(7));
			tmp.ustawCenePromocyjna(result.getFloat(8));
			tmp.ustawIlosc(result.getInt(9));
			tmp.ustawNrRegalu(result.getInt(10));
			tmp.ustawNrPolki(result.getInt(11));
			tmp.ustawNrMiejsca(result.getInt(12));
			tmp.ustawIloscPunktow(result.getInt(13));
		}
        statement.close();
        return tmp;
	}
	
	public void zapiszZamowienie(Zamowienie zam, Connection connection) throws SQLException
	{
		String query = "";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String data1 = simple.format(zam.pobierzDateZłozenia()) ,data2 = null;
		if(zam.pobierzDateDostawy() != null)
		{	
			data2 = simple.format(zam.pobierzDateDostawy());	
			query = "INSERT INTO zamowienieDostawa VALUES ("+zam.pobierzIdZamowienia()+"," + zam.pobierzNrZamowienia() + ", '" + zam.sprawdzStatus() +"',"+zam.pobierzCzasDostawy()+",'"+data1+"','"+data2+"',"+zam.pobierzIdSprzedawcy()+");";
		}
		else
			query = "INSERT INTO zamowienieDostawa VALUES ("+zam.pobierzIdZamowienia()+"," + zam.pobierzNrZamowienia() + ", '" + zam.sprawdzStatus() +"',"+zam.pobierzCzasDostawy()+",'"+data1+"',"+null+","+zam.pobierzIdSprzedawcy()+");";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        for(int i = 0; i<zam.pobierzListeProduktow().size(); i++)
        {
        	zapiszProduktyZamowienie(zam.pobierzIdZamowienia(),zam.pobierzListeProduktow().get(i),connection);
        }
        statement.close();
	}
	
	private void zapiszProduktyZamowienie(int idZamowienie, Produkt pr, Connection connection) throws SQLException
	{
		String query = "";
		query = "INSERT INTO zamowienieDostawa_Produkt (idZamowienie,idProdukt,ilosc) VALUES ("+ idZamowienie +","+ pr.pobierzId() +"," + pr.pobierzIlosc() +");";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void usunDostawceBazaDanych(Dostawca dostawca, Connection connection) throws SQLException
	{
		String query = "";
		query = "DELETE FROM dostawca WHERE idDostawca = "+dostawca.pobierzId();
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void usunZamowienieBazaDanych(Zamowienie zam, Connection connection) throws SQLException
	{
		String query = "";
		query = "DELETE FROM zamowienieDostawa_Produkt WHERE idZamowienie = "+zam.pobierzIdZamowienia();

		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        
		query = "DELETE FROM zamowienieDostawa WHERE id_zamowienie = "+zam.pobierzIdZamowienia();

		statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
}
