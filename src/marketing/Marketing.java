package marketing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import wspolne.Produkt;


public class Marketing {

	private List<Promocja> promocje;
	private List<Ankieta> ankiety;
	private Ulotka ulotka;
	
	public Marketing()
	{
		 promocje = new ArrayList<Promocja>();
		 ankiety = new ArrayList<Ankieta>();
		 ulotka = new Ulotka();
	}
	
	public void utworzAnkiete(String tytul, List<String> pytania)
	{
    	int i;
    	if(ankiety.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = ankiety.size();
    		while(i <= ankiety.get(ankiety.size()-1).pobierzId())
    			i++;
    	}
		ankiety.add(new Ankieta(i, tytul, pytania));
		sortujListe("Ankieta");
	}
	
	public void utworzPromocje(Produkt produkt,Date od_kiedy,Date do_kiedy,float nowa_cena,List<Produkt> magazynProdukty)
	{
    	int i;
    	if(sprawdzPromocje(produkt) == true)
    	{
    		if(promocje.size() == 0)
    		{
    			i = 1;
    		}else
    		{	
    			i = promocje.size();
    			while(i <= promocje.get(promocje.size()-1).pobierzId())
    				i++;
    		}
    		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
    		String txt1 = simple.format(od_kiedy);
    		String txt2 = simple.format(do_kiedy);
			try 
			{
				od_kiedy = simple.parse(txt1);
				do_kiedy = simple.parse(txt2);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
    		Promocja promocja = new Promocja(i,produkt.pobierzId(),produkt.pobierzCeneSprzedazy(),od_kiedy,do_kiedy,nowa_cena);
    		int indeksProdukt = wyszukaj(magazynProdukty, promocja.pobierzIdProduktu());
    		promocje.add(promocja);
    		sortujListe("Promocja");
    		aktualizujCene(indeksProdukt,promocja.pobierzNowaCene(),magazynProdukty);
    		JOptionPane.showMessageDialog(null,"Promocja zostala utworzona pomyslnie");
    	}
    	else
    		JOptionPane.showMessageDialog(null,"Promocja na wybrany produkt juz istnieje");
	}
	
	public void utworzAnkiete()
	{
		String txt;
		int tmp = 1, i;
    	if(ankiety.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = ankiety.size();
    		while(i <= ankiety.get(ankiety.size()-1).pobierzId())
    			i++;
    	}
    	txt = JOptionPane.showInputDialog("Podaj temat ankiety");
		Ankieta ankieta = new Ankieta(i, txt);
		boolean czyKolejnePytanie = true, czyWpisanePytanie = true;
		while(czyKolejnePytanie == true)
		{
			txt = JOptionPane.showInputDialog("Wpisz pytanie");
			if(txt == null)
			{
				czyWpisanePytanie = false;
				JOptionPane.showMessageDialog(null,"Ankieta nie zostala utworzona");
			}
			else
				ankieta.dodajPytanie(txt);
			if(czyWpisanePytanie == true)
				tmp = JOptionPane.showConfirmDialog(null,"Czy chcesz dodac nastepne pytanie", " ", JOptionPane.YES_NO_OPTION);
			if(tmp == 1)
				czyKolejnePytanie = false;
			if(czyKolejnePytanie == false && czyWpisanePytanie == true)
			{
				ankiety.add(ankieta);
				sortujListe("Ankieta");
				JOptionPane.showMessageDialog(null,"Ankieta zostala utworzona pomyslnie");
			}
		}
	}
	
	public void utworzPromocje(List<Produkt> magazynProdukty)
	{
		Produkt produkt;
		produkt = wybierzProdukt(magazynProdukty);
		if(produkt == null)
			JOptionPane.showMessageDialog(null,"Nie mozna utworzyc promocji");
	    if((sprawdzPromocje(produkt) == true) && (produkt != null))
	    {
	    	int i;
	    	if(promocje.size() == 0)
	    	{
	    		i = 1;
	    	}else
	    	{	
	    		i = promocje.size();
	    		while(i <= promocje.get(promocje.size()-1).pobierzId())
	    			i++;
	    	}
	    	Promocja promocja = new Promocja(i,produkt.pobierzId(),produkt.pobierzCeneSprzedazy());
			promocje.add(promocja);
			sortujListe("Promocja");
			int indeks = wyszukaj(magazynProdukty, promocja.pobierzIdProduktu());
			aktualizujCene(indeks,promocja.pobierzNowaCene(),magazynProdukty);
			JOptionPane.showMessageDialog(null,"Promocja zostala utworzona pomyslnie");
	    }
	    else
	    {
	    	if(produkt != null)
	    		JOptionPane.showMessageDialog(null,"Promocja na wybrany produkt juz istnieje");	
	    }
	}
	
	//nazwaPliku podawana z rozszerzeniem .html
	public void utworzUlotke(List<Produkt> magazynProdukty, String nazwaPliku)
	{		
		boolean test = false;
		Map<Produkt,Promocja> mm = new HashMap<Produkt,Promocja>();
		for(int i = 0;i< magazynProdukty.size();i++)
		{
			test = false;
			for(int j = 0;j< promocje.size();j++)
			{
				if(magazynProdukty.get(i).pobierzId() == promocje.get(j).pobierzIdProduktu())
				{
					mm.put(new Produkt(magazynProdukty.get(i)), promocje.get(j));
					test = true;
				}
			}
			if(test == false)
				mm.put(new Produkt(magazynProdukty.get(i)), new Promocja());;
		}
		ulotka.generujUlotke(mm, nazwaPliku);
	}
	
	public void usunAnkiete()
	{
		Ankieta ankieta;
		ankieta = wybierzAnkiete();
		ankiety.remove(ankieta);
	}
	
	public void usunAnkiete(Ankieta ankieta)
	{
		ankiety.remove(ankieta);
	}
	
	public void usunPromocje(Promocja promocja,List<Produkt> magazynProdukty)
	{
			int indeksProdukt = wyszukaj(magazynProdukty, promocja.pobierzIdProduktu());
			aktualizujCene(indeksProdukt,promocja.pobierzStaraCene(),magazynProdukty);
			promocje.remove(promocja);
	}
	
	public void usunPromocje(List<Produkt> magazynProdukty)
	{
		boolean czyZnalezionoProdukt = true;
		int indeksProdukt = -1;
		Promocja promocja;
		promocja = wybierzPromocje(magazynProdukty);
		int indeksPromocja = promocje.indexOf(promocja);
		try
		{
			indeksProdukt = wyszukaj(magazynProdukty, promocja.pobierzIdProduktu());
		}
		catch(NullPointerException npe)
		{
			czyZnalezionoProdukt = false;
		}
		if(czyZnalezionoProdukt == true)
		{
			aktualizujCene(indeksProdukt,promocja.pobierzStaraCene(),magazynProdukty);
			promocje.remove(indeksPromocja);
			JOptionPane.showMessageDialog(null,"Promocja zostala zakonczona");
		}
	}
	
	public String wypiszPromocje()
	{
		return promocje.toString();
	}
	
	public String wypiszAnkiety()
	{
		return ankiety.toString();
	}
	
	private boolean sprawdzPromocje(Produkt produkt)
	{
		int i = 0;
		while(i < promocje.size())
		{
			if(promocje.get(i).pobierzIdProduktu() == produkt.pobierzId())
			{
				return false;
			}
			i++;
		}
		return true;
	}
	
	private int wyszukaj(List<Produkt> produkty, int id_produktu)
	{
		int tmp = 0;
		int i = 0;
		int test = 0;
		while (i < produkty.size())
		{
			if (produkty.get(i).pobierzId() == id_produktu)
			{
				tmp = i;
				System.out.println("Znaleziono element");
				test = 1;
			}
			i++;
		}	
		if(test == 0)
		{
			System.out.println("Nie ma elementu");
			return -1;
		}
		return tmp;
	}
	
	private Promocja wybierzPromocje(List<Produkt> magazynProdukty)
	{
		if(promocje.size() == 0)
		{
			JOptionPane.showMessageDialog(null,"Lista istniejacych promocji jest pusta");
			return null;
		}
		String[] tab = new String[promocje.size()];
		int i = 0;
		while(i < promocje.size())
		{
			int indeks = wyszukaj(magazynProdukty,promocje.get(i).pobierzIdProduktu());
			tab[i] = magazynProdukty.get(indeks).pobierzNazwe()+"  "+promocje.get(i).toString();
			i++;
		}
		String txt = (String) JOptionPane.showInputDialog(null,"Wybierz promocje", "",JOptionPane.PLAIN_MESSAGE,null,tab,tab[0]);
		for(i = 0;i<tab.length;i++)
		{
			if(tab[i] == txt)
				return promocje.get(i);
		}
		return null;
	}
	
	public Produkt wybierzProdukt(List<Produkt> magazynProdukty)
	{
		if(magazynProdukty.size() == 0)
		{
			JOptionPane.showMessageDialog(null,"Lista produktow jest pusta");
			return null;
		}
		String[] tab = new String[magazynProdukty.size()];
		int i = 0, indeks = 0;
		while(i < magazynProdukty.size())
		{
			tab[i] = magazynProdukty.get(i).pobierzNazwe();
			i++;
		}
		String txt = (String)JOptionPane.showInputDialog(null,"Wybierz produkt", "",JOptionPane.PLAIN_MESSAGE,null,tab,tab[0]);
		for(i = 0; i < magazynProdukty.size(); i++)
		{
			if(txt == magazynProdukty.get(i).pobierzNazwe())
				indeks = i;
		}
		return magazynProdukty.get(indeks);
	}
	
	private Ankieta wybierzAnkiete()
	{
		if(ankiety.size() == 0)
		{
			JOptionPane.showMessageDialog(null,"Lista ankiet jest pusta");
			return null;
		}
		String[] tab = new String[ankiety.size()];
		int i = 0;
		while(i < ankiety.size())
		{
			tab[i] = " "+ankiety.get(i).pobierzTytul();
			i++;
		}
		String txt = (String)JOptionPane.showInputDialog(null,"Wybierz ankiete", "",JOptionPane.PLAIN_MESSAGE,null,tab,tab[0]);
		for(i = 0;i<tab.length;i++)
		{
			if(tab[i] == txt)
				return ankiety.get(i);
		}
		return null;
	}

	//Aktualizujemy cenePromocyjna produktu
	private void aktualizujCene(int indeks, float cena,List<Produkt> magazynProdukty)
	{
		magazynProdukty.get(indeks).ustawCenePromocyjna(cena);
		JOptionPane.showMessageDialog(null,"Cena produktu zostala zaktualizowana");
	}
	
	public List<Promocja> pobierzListePromocji()
	{
		return promocje;
	}
	
	public List<Ankieta> pobierzListeAnkiet()
	{
		return ankiety;
	}
	
	public Ulotka pobierzUlotke()
	{
		return ulotka;
	}
    
	private void sortujListe(String nazwaKlasy)
	{
		if(nazwaKlasy == "Ankieta")
		{
			for(int i = 0; i < ankiety.size()-1; i++)
			{
				if(ankiety.get(i).pobierzId() > ankiety.get(i+1).pobierzId())
				{
					Ankieta tmp = ankiety.get(i);
					ankiety.set(i, ankiety.get(i+1));
					ankiety.set(i+1, tmp);
				}
			}
		}
		if(nazwaKlasy == "Promocja")
		{
			for(int i = 0; i < promocje.size()-1; i++)
			{
				if(promocje.get(i).pobierzId() > promocje.get(i+1).pobierzId())
				{
					Promocja tmp = promocje.get(i);
					promocje.set(i, promocje.get(i+1));
					promocje.set(i+1, tmp);
				}
			}
		}
	}
	
	public void wczytajPromocje(Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM promocja";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		while(result.next())
		{
			int idPromocja=result.getInt(1);
			Date data1=(Date) result.getObject(2);
			Date data2=(Date) result.getObject(3);
			String tmp = data1.toString();
			String tmp2 = data2.toString();
			try 
			{
				data1 = simple.parse(tmp);
				data2 = simple.parse(tmp2);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			int idProdukt=result.getInt(4);
			float stara_cena = result.getFloat(5);
			float nowa_cena = result.getFloat(6);
			promocje.add(new Promocja(idPromocja,idProdukt,stara_cena,data1,data2,nowa_cena));
		}
        statement.close();
	}
	
	public void aktualizujPromocje(Promocja promocja, Connection connection) throws SQLException
	{
		String query = "";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String data1 = simple.format(promocja.pobierzOdKiedy());
		String data2 = simple.format(promocja.pobierzDoKiedy());	
		query = "UPDATE promocja SET data_rozpoczecia = '"+data1+"', data_zakonczenia = '"+data2+"',id_produktu = "+ promocja.pobierzIdProduktu()+", stara_cena = "+ promocja.pobierzStaraCene()+" , nowa_cena = "+ promocja.pobierzNowaCene() +" where id = "+promocja.pobierzId()+";";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void aktualizujPromocje(int idPromocji, Date odKiedy, Date doKiedy, int idProduktu, float staraCena, float nowaCena, Connection connection) throws SQLException
	{
		String query = "";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String data1 = simple.format(odKiedy);
		String data2 = simple.format(doKiedy);	
		query = "UPDATE promocja SET data_rozpoczecia = '"+data1+"', data_zakonczenia = '" + data2 +"',id_produktu = "+ idProduktu+" , stara_cena = "+ staraCena+" , nowa_cena = "+ nowaCena +" where id = "+idPromocji+";";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void zapiszPromocje(Promocja promocja, Connection connection) throws SQLException
	{
		String query = "";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String data1 = simple.format(promocja.pobierzOdKiedy());
		String data2 = simple.format(promocja.pobierzDoKiedy());	
		query = "INSERT INTO promocja VALUES ("+promocja.pobierzId() +",'"+data1+"','"+data2+"', "+promocja.pobierzIdProduktu()+", " + promocja.pobierzStaraCene() +", "+promocja.pobierzNowaCene()+");";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
	}
	
	public void wczytajAnkiety(Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM ankiety";
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		while(result.next())
		{
			int idAnkieta = result.getInt(1);
			String tytul = result.getString(2);
			ankiety.add(new Ankieta(idAnkieta,tytul));
		}
        statement.close();
		for(int i =0;i<ankiety.size();i++)
		{
			wczytajPytaniaAnkiety(ankiety.get(i),connection);
		}
	}
	
	private void wczytajPytaniaAnkiety(Ankieta ankieta,Connection connection) throws SQLException
	{
		String query = "";
		query = "SELECT * FROM ankietyPytania where idAnkiety="+ankieta.pobierzId()+"";
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		while(result.next())
		{
			ankieta.dodajPytanie(result.getString(3));
		}
        statement.close();
	}
	
	public void zapiszAnkiete(Ankieta ankieta, Connection connection) throws SQLException
	{
		String query = "";
		query = "INSERT INTO ankiety VALUES ("+ankieta.pobierzId() +",'"+ankieta.pobierzTytul()+"');";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        for(int i=0;i<ankieta.pobierzPytania().size();i++)
        {
        	zapiszPytaniaAnkiety(ankieta.pobierzId(),ankieta.pobierzPytania().get(i),connection);
        }
	}
	
	private void zapiszPytaniaAnkiety(int idAnkiety,String pytanie,Connection connection) throws SQLException
	{
		String query = "";
		query = "INSERT INTO ankietyPytania (idAnkiety,pytanie) VALUES ("+idAnkiety+",'"+pytanie+"');";
		Statement statement = (Statement) connection.createStatement();
		statement.executeUpdate(query);
        statement.close();
	}
	
	
}
