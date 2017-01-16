/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolne;


import baza.BazaDanych;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import magazyn.Magazyn;
import sprzedaz.Faktura;
import sprzedaz.Klient;
import sprzedaz.Sprzedaz;

/**
 *
 * @author Wojtass
 */
public class Zamowienie {

	private int nr_zamowienia;
	private List<Produkt> produkty;
                  private List<Produkt> produktySprzedaz; // przechowuje produkty tymczasowo w zamowieniu ( do wyswietlenia w GUI ) / czyszczona po zatwierdzeniu zamowienia . 
           	private String status;
	private int czas_dostawy;//liczba dni
	private Date data_zlozenia; 
	private Date data_dostawy;
                // private Date data_sprzedazy; // to tez dodatkowo
	private int id_sprzedawcy;
                  private int id_zamowienie;
                  private int id_klient;
                  
    //private int idProdukt;
    //private int iloscProduktow;
    
     //konstruktor dla dostawy
	public Zamowienie(int id_zamowienie,int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,Date data_zlozenia, int id_sprzedawcy)
	{
		this.id_zamowienie = id_zamowienie;
		this.nr_zamowienia = nr_zamowienia;
		this.produkty = new ArrayList<Produkt>();
		for(int i = 0; i < produkty.size(); i++)
		{
			this.produkty.add(new Produkt(produkty.get(i)));
		}
		this.czas_dostawy = czas_dostawy;
		this.data_zlozenia = data_zlozenia;
		this.id_sprzedawcy = id_sprzedawcy;
		status = new String("W trakcie realizacji");
	}
        
                //dotyczy sprzedazy
                public Zamowienie(int idZamowienie, int idKlient , int idSprzedawca) {
                    
                                    produktySprzedaz = new ArrayList<>();
                                    this.id_zamowienie = idZamowienie;
                                    this.id_klient = idKlient;
                                    this.id_sprzedawcy = idSprzedawca;
                                    
                    
                }
                //dotyczy sprzedazy
                    public void zatwierdz(Klient k, Zamowienie z){
                        
                        Faktura faktura = new Faktura(produktySprzedaz, Sprzedaz.faktury.size() +1, k.getIdKlient() , z.id_zamowienie, zliczWartoscZamowieniaSprzedaz(), zliczIloscProduktow() );
                        
                        Magazyn m  = new Magazyn();
                        
                        for(Produkt p : produktySprzedaz){
     
                            m.edytujIloscProduktu(p.pobierzId(), p.pobierzIlosc()-1 , BazaDanych.getPolaczenie());
                            
                            if(p.pobierzIlosc() <1) 
                                m.usunProdukt(p, BazaDanych.getPolaczenie());
                                
                        }
                        faktura.dodajFakture(faktura);
                        
                        produktySprzedaz.clear();
                        
                    }
                
                    public void dodajPunktyKlientowi(Klient k){
                        
                        Sprzedaz s = new Sprzedaz();
                        s.wczytajKlientow();
                        int ilosc =0;
                        for(Produkt p : produktySprzedaz)
                        ilosc +=  p.pobierzIloscPunktow();
                        ilosc += k.getIloscPunktow();
                        
                        s.edytujLiczbePunktow(k, ilosc);
                                                
                    }
                
                   public void dodajProduktSprzedaz(Produkt p ){
                       
                       Magazyn m = new Magazyn();
                       //List<Produkt> tmp = new ArrayList<>();
                       Produkt tmp = new Produkt();
                       m.wczytajProduktyZBazy(BazaDanych.getPolaczenie());
                       // tmp = m.pobierzProdukty();
                       tmp = new Produkt(m.pobierzProdukt(p.pobierzId())); //a tu nie powinien byc konstruktor kopiujacy ?
                       
                       if(tmp.pobierzIlosc() <1){
                           JOptionPane.showMessageDialog(null, "Brak produktu!");
                       }
                       else produktySprzedaz.add(tmp);
                       
                   }
                   
                   public void usunProduktSprzedaz(Produkt p ){
                       produktySprzedaz.remove(p.pobierzId());
                   }
                
                   public List<Produkt> pobierzProduktySprzedaz(){
                       return produktySprzedaz;
                   }
                  public int pobierzidKlienta(){
                      return this.id_klient;
                  }
                  public int pobierzIdZamowienia(){
                      return this.id_zamowienie;
                  }
                       
                    public void  ustawIdKlienta(int id){
                      this.id_klient = id;
                  }
                   public void  ustawIdZamowienia(int id){
                      this.id_zamowienie = id;
                  }   
                  

	public int pobierzNrZamowienia()
	{
		return nr_zamowienia;
	}
	
	public String sprawdzStatus()
	{
		return status;
	}
        
                //dotyczy  Zamowienia w sprzedazy
                  public int zliczIloscProduktow(){
                            return produktySprzedaz.size();
                  }
                  
                  public float zliczWartoscZamowieniaSprzedaz(){
                      
                      float wartosc= 0;
                      for(Produkt p : produktySprzedaz)  wartosc +=p.pobierzCeneSprzedazy();
                      return wartosc;
                  }
	
	public float zliczWartoscZamowienia()
	{
		float wartosc = 0;
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
        
                public void  ustawIdSprzedawcy(int id){
                      this.id_sprzedawcy = id;
                  }
	
	//dotyczy modulu dostawy
	public List<Produkt> pobierzListeProduktow()
	{
		return produkty;
	}
	//dotyczy modulu dostawy
	public void zamowienieZrealizowane(Date data_dostawy)
	{
		status = new String("Zrealizowane");
		this.data_dostawy = data_dostawy;
	}
	//dotyczy modulu dostawy
	public void dodajProdukt(Produkt produkt)
	{
		produkty.add(produkt);
	}
	//dotyczy modulu dostawy
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
