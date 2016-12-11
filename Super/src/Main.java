import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sprzedaz.Sprzedaz;

import wspolne.Produkt;
import wspolne.Zamowienie;

import magazyn.Magazyn;
import magazyn.Magazynier;
import marketing.Marketing;
import marketing.Promocja;

import dostawa.Dostawa;
import dostawa.Dostawca;

import administracja.Administracja;
import administracja.Pracownik;
import administracja.RaportZlecenie;

import baza.BazaDanych;


public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws SQLException {
			
		//Administracja @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		/*
				Date dataZatrudnienia = new Date(1111111111);
				
				Date dataZwolnienia = new Date();

				
				Pracownik p =  new Pracownik ( 1, "123", "Macik", "Mily","12312","lolek", (float) 123.00, dataZatrudnienia, dataZwolnienia, "Lodz");
				
				
			Administracja admin = new Administracja();
			
				admin.dodajPracownika(p);
				admin.usunPracownika(p);
				admin.edytujPracownika(p);
				admin.zmienStanowiskoPracownika(p, "bezrobotny jak my");
				admin.wyplacWynagrodzenie(p);
				
				
				RaportZlecenie raport = new RaportZlecenie();
				raport.getPoczatek();
				raport.getKoniec();

				admin.generujRaport(raport);
		
		
		*/
		
		//testy bazy dla administracji @@@@@@@@@@@@@@@@@@@@@@@@@@@
		/*
	       BazaDanych.polacz();
	       Date date = new Date();
	       Pracownik pracownik1 = new Pracownik ( 1, "123", "Macik", "Mily","12312","lolek", (float) 123.00, date, date, "Lodz");  
	      
	       Administracja administracja = new Administracja();
	       administracja.dodajPracownika(pracownik1);
	      
	      administracja.kadra.zapiszPracownikaWBazie(pracownik1, BazaDanych.getPolaczenie());
	     */
		
		//Testy dla Dostawy i marketingu @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		/*   <<usunac tylko ta linijke 
		Date data_zlozenia = new Date(20160507);
		Date data_zlozenia1 = new Date(20160809);

		java.sql.Date dataWaznosci2 = new java.sql.Date(20161220);
		java.sql.Date dataWaznosci3 = new java.sql.Date(20170325);

		
		//public Produkt(int id_produktu,String nazwa,int dlugoscGwarancji,float cenaZakupu,float cenaSprzedazy,float cenaPromocyjna,float waga,int ilosc,Date dataWaznosci,int nrRegalu, int nrPolki, int nrMiejsca)
		
		Produkt pr1 =  new Produkt(2, "Ser", 1, 50 ,100 ,30 , 3, 20, dataWaznosci2, 4, 2, 2);
		Produkt pr2 = new Produkt(3, "Szynka", 2, 60 ,500 ,40 , 5, 10, dataWaznosci3, 6, 4, 8);
		Produkt pr3 =  new Produkt(2, "Czekolada", 1, 50 ,200 ,35 , 3, 20, dataWaznosci2, 4, 5, 2);
	
		
		
		List <Produkt> produkty = new ArrayList<Produkt>();
		produkty.add(pr1);
		produkty.add(pr2);
		
		//public Zamowienie(int id_zamowienie,int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,Date data_zlozenia, int id_sprzedawcy)
		Zamowienie z1 = new Zamowienie(2, 3, produkty, 2015, data_zlozenia, 2 );
		Zamowienie z2 = new Zamowienie(4, 5, produkty, 2015, data_zlozenia1, 2 );

		
		z1.dodajProdukt(pr1);
		z1.usunProdukt(pr1);
		
		z1.dodajProdukt(pr2);
		z1.usunProdukt(pr2);
		
		z2.dodajProdukt(pr3);
		z2.usunProdukt(pr3);
		
		
		Dostawa dostawa  = new Dostawa();
		dostawa.dodajZamowienie(3, produkty, 2015, "2016-04-03", 7);
		
		//Produkt(int id_produktu,String nazwa,int dlugoscGwarancji,float cenaZakupu,float cenaSprzedazy,float cenaPromocyjna,float waga,int ilosc,Date dataWaznosci,int nrRegalu, int nrPolki, int nrMiejsca
		
		List <Produkt> produktyDostawa = new ArrayList<Produkt>();
		Produkt pr4 = new Produkt(3, "Jogurty", 3, 60 ,700 ,70 , 2, 15, dataWaznosci2, 9, 4, 7);
		produktyDostawa.add(pr4); 
		
		List <Produkt> produktyMagazyn = new ArrayList<Produkt>();
		Produkt pr5 = new Produkt(4, "Jogurty", 3, 60 ,700 ,70 , 2, 15, dataWaznosci3, 9, 4, 7);
		produktyMagazyn.add(pr5);
		
		// sprawdzZgodnoscDostawy(int nrZamowienia,List<Produkt> produktyDostawa,String dataDostawy,int idDostawcy, List<Produkt> produktyMagazyn)
		dostawa.sprawdzZgodnoscDostawy(5, produktyDostawa, "2014-04-03", 9, produktyMagazyn);
		
		dostawa.dodajDoMagazynu(produktyMagazyn,produktyDostawa);
		
		
		//Marketing @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
				Marketing marketing = new Marketing();
				

				
				marketing.utworzAnkiete(4, "Obsluga Klienta");
				marketing.utworzPromocje (pr1, "2016-12-16", "2017-01-10", 200, produktyMagazyn);
				marketing.utworzAnkiete(produktyMagazyn);
				marketing.utworzUlotke();
					
				//Date odKiedy = new Date();
				//Date doKiedy = new Date();
				
				//Promocja(int id_promocji,int id_produkt, float stara_cena,Date od_kiedy,Date do_kiedy,float nowa_cena)
				
				marketing.utworzPromocje(produktyMagazyn);
				marketing.usunAnkiete(produktyMagazyn);
				
				Promocja promo1 = new Promocja(3, 4, 250); 
				marketing.usunPromocje(promo1, produktyMagazyn);
				marketing.usunPromocje(produktyMagazyn);
				marketing.wybierzProdukt(produktyMagazyn);
				
			
				/*wywolywanie prywatnych metod??? */
				
				/*Method m = Marketing.class.getDeclaredMethod("sprawdzPromocje");
				m.setAccessible(true);
			    m.invoke(marketing);
			    //marketing.sprawdzPromocje(pr1);
			    
				Method m1 = Marketing.class.getDeclaredMethod("wyszukaj");
				m1.setAccessible(true);
			    m1.invoke(marketing);
				
				
				Method m2 = Marketing.class.getDeclaredMethod("wybierzPromocje");
				m2.setAccessible(true);
			    m2.invoke(marketing);
			    */
				
			   /* Class <Marketing> klasa = Marketing.class;
			    Method met = klasa.getDeclaredMethod("sprawdzPromocje", Produkt.class);
			    met.setAccessible(true);
			    met.invoke(new Marketing()); */
		
		
		
		
		
		
		
		//testy bazy dla Dostawy @@@@@@@@@@@@@@@@@@@@@@@@@
		/*
		BazaDanych.polacz();
		Dostawca dostawca1 = new Dostawca(1,"Zenek", "Pilsudskiego 3");
		Dostawca dostawca2 = new Dostawca(2,"Zbysiek", "Pilsudskiego 3");
		Dostawa dostawa = new Dostawa ();
		
		//dostawa.zapiszDostawce(dostawca2, BazaDanych.getPolaczenie());
		dostawa.wczytajDostawcow(BazaDanych.getPolaczenie());
		System.out.println(dostawa.pobierzListeDostawcy().get(0));
		
		*/
		
		
		
		
		
		//testy bazy dla Magazynu @@@@@@@@@@@@@@@@@@@@@@@@@@
	      /*
		BazaDanych.polacz();
		
		Date date1 = new Date();
		
		Magazynier magazynier1 = new Magazynier(1, "Marcin" , " Elo");
		Magazynier magazynier2 = new Magazynier(2, "Wojciech" , " Elo");
		Produkt produkt1 = new Produkt(1, "Hehe", 2, 3, 4, 5, 6, 7, date1, 9, 10, 11);
		
		Magazyn magazyn = new Magazyn();
		//magazyn.dodajProdukt(produkt1, BazaDanych.getPolaczenie()); // nie dziala
		//magazyn.dodajMagazyniera(magazynier1, BazaDanych.getPolaczenie());
		//magazyn.usunMagazyniera(magazynier1, BazaDanych.getPolaczenie());
		//magazyn.edytujImieMagazyniera(magazynier1, "Robert", BazaDanych.getPolaczenie()); //dziala
		
		*/
		
		// Sprzedaz @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		/*
				Sprzedaz s = new Sprzedaz();
				s.dodajZamowienie(2, 5);
				s.dodajZamowienie(4, 6);
			
				//List<Zamowienie> zamowienia = s.wczytajZamowienia();
				
				//s.usunZamowienie(z1);
				//s.usunZamowienie(z2);
		
		*/
		
		
		
		
	       
	    }
}
