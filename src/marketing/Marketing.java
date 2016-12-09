package marketing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import wspolne.Produkt;


public class Marketing {

	private List<Promocja> promocje;
	private List<Ankieta> ankiety;
	private List<Ulotka> ulotki;
	
	public Marketing()
	{
		 promocje = new ArrayList<Promocja>();
		 ankiety = new ArrayList<Ankieta>();
		 ulotki = new ArrayList<Ulotka>();
	}
	
	public void utworzAnkiete( int id_produktu, String tytul)
	{
    	int i;
    	if(ankiety.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = ankiety.size();
    		while(i == ankiety.get(ankiety.size()-1).pobierzId())
    			i++;
    	}
    	sortujListe("Ankieta");
		ankiety.add(new Ankieta(i, id_produktu, tytul));
	}
	
	//od_kiedy,do_kiedy podawane w postaci yyyy-mm-dd
	public void utworzPromocje(Produkt produkt,String od_kiedy,String do_kiedy,float nowa_cena,List<Produkt> magazynProdukty)
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
    			while(i == promocje.get(promocje.size()-1).pobierzId())
    				i++;
    		}
    		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
    		Date data1=null,data2=null;
			try 
			{
				data1 = simple.parse(od_kiedy);
				data2 = simple.parse(do_kiedy);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
    		Promocja promocja = new Promocja(i,produkt.pobierzId(),produkt.pobierzCeneSprzedazy(),data1,data2,nowa_cena);
    		promocje.add(promocja);
    		sortujListe("Promocja");
    		aktualizujCene(promocja.pobierzIdProduktu(),promocja.pobierzNowaCene(),magazynProdukty);
    	}
    	else
    		JOptionPane.showMessageDialog(null,"Promocja na wybrany produkt juz istnieje");
	}
	
	public void utworzAnkiete(List<Produkt> magazynProdukty)
	{
		Produkt produkt = new Produkt();
		String txt;
		int tmp = 1, i;
		produkt = wybierzProdukt(magazynProdukty);
    	if(ankiety.size() == 0)
    	{
    		i = 1;
    	}else
    	{	
    		i = ankiety.size();
    		while(i == ankiety.get(ankiety.size()-1).pobierzId())
    			i++;
    	}
    	txt = JOptionPane.showInputDialog("Podaj temat ankiety");
		Ankieta ankieta = new Ankieta(i, produkt.pobierzId(), txt);
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
	
	public void utworzUlotke()
	{
		ulotki.add(new Ulotka());
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
	    		while(i == promocje.get(promocje.size()-1).pobierzId())
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
	
	public void usunAnkiete(List<Produkt> magazynProdukty)
	{
		Ankieta ankieta;
		ankieta = wybierzAnkiete(magazynProdukty);
		ankiety.remove(ankieta);
	}
	
	public void usunPromocje(Promocja promocja,List<Produkt> magazynProdukty)
	{
		if(promocje.contains(promocja))
		{
			promocje.remove(promocja);
			aktualizujCene(promocja.pobierzIdProduktu(),promocja.pobierzStaraCene(),magazynProdukty);
		}
	}
	
	public void usunPromocje(List<Produkt> magazynProdukty)
	{
		boolean czyZnalezionoProdukt = true;
		int indeksProdukt = -1;
		Promocja promocja;
		promocja = wybierzPromocje();
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
	
	private Promocja wybierzPromocje()
	{
		if(promocje.size() == 0)
		{
			JOptionPane.showMessageDialog(null,"Lista istniejacych promocji jest pusta");
			return null;
		}
		Promocja[] tab = new Promocja[promocje.size()];
		int i = 0;
		while(i < promocje.size())
		{
			tab[i] = promocje.get(i);
			i++;
		}
		Promocja promocja = (Promocja)JOptionPane.showInputDialog(null,"Wybierz promocje", "",JOptionPane.PLAIN_MESSAGE,null,tab,tab[0]);
		return promocja;
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
	
	private Ankieta wybierzAnkiete(List<Produkt> magazynProdukty)
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
			int indeksProdukt = wyszukaj(magazynProdukty,ankiety.get(i).pobierzIdProduktu());
			tab[i] = ankiety.get(i).pobierzId()+"  "+ankiety.get(i).pobierzTytul()+"    Dotyczy produktu: "+magazynProdukty.get(indeksProdukt).pobierzNazwe();
			i++;
		}
		String txt = (String)JOptionPane.showInputDialog(null,"Wybierz ankiete", "",JOptionPane.PLAIN_MESSAGE,null,tab,tab[0]);
		String indeks = new String("");
		i = 0;
		while(txt.charAt(i) != ' ')
		{
			indeks = new String(indeks+txt.charAt(i));
			i++;
		}
		i = Integer.parseInt(indeks) - 1;
		return ankiety.get(i);
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
		String query = "",tmp = "",tmp2 = "";
		query = "SELECT * FROM promocja";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		Statement statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		int idPromocja,idProdukt;
		float stara_cena,nowa_cena;
		while(result.next())
		{
			idPromocja=result.getInt(1);
			Date data1=(Date) result.getObject(2);
			Date data2=(Date) result.getObject(3);
			tmp = data1.toString();
			tmp2 = data2.toString();
			try 
			{
				data1 = simple.parse(tmp);
				data2 = simple.parse(tmp2);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			idProdukt=result.getInt(4);
			stara_cena = result.getFloat(5);
			nowa_cena = result.getFloat(6);
			promocje.add(new Promocja(idPromocja,idProdukt,stara_cena,data1,data2,nowa_cena));
		}
        statement.close();
	}
	
	public void zapiszPromocje(Promocja promocja, Connection connection) throws SQLException
	{
		String query = "";
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		String data1 = simple.format(promocja.pobierzOdKiedy());
		String data2 = simple.format(promocja.pobierzDoKiedy());	
		query = "INSERT INTO promocja VALUES ("+promocja.pobierzId() +",'"+data1+"','"+data2+"', "+promocja.pobierzIdProduktu()+", " + promocja.pobierzStaraCene() +", "+promocja.pobierzNowaCene()+");";
		Statement statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        //connection.close();
	}	
	
}
