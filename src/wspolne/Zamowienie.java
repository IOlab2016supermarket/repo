/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolne;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
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
                  private List<Produkt> produktySprzedaz; // robie dodatkową listę bo lepiej zeby nie mieszaly sie dostawy z tym co klient kupuje.
	private String status;
	private int czas_dostawy;
	private Date data_zlozenia; 
	private Date data_dostawy;
                //  private Date data_sprzedazy; // to tez dodatkowo
	private int id_sprzedawcy;
                  private int id_zamowienie;
                  private int id_klient;
                  
    //private int idProdukt;
    //private int iloscProduktow;
    
	public Zamowienie(int nr_zamowienia,List<Produkt> produkty,int czas_dostawy,Date data_zlozenia, int id_sprzedawcy)
	{
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
                        //no i dalej jakieś opeeracje na bazie, zeby usunac te produkty
                        faktura.dodajFakture(faktura);
                        
                    }
                
                    public void dodajPunktyKlientowi(Klient k){
                       
                        int tmp =0;
                        for(Produkt p : produktySprzedaz)
                        tmp +=  p.pobierzIloscPunktow();
                        
                        k.setIloscPunktow(tmp);
                        
                    }
                
                   public void dodajProduktSprzedaz(Produkt p ){
                       
                       if(p.pobierzIlosc() < 1){
                           JOptionPane.showMessageDialog(null, "Brak produktu!");
                       }
                       else produktySprzedaz.add(p);
                       
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
	
	public List<Produkt> pobierzListeProduktow()
	{
		return produkty;
	}
	
	public void zamowienieZrealizowane(Date data_dostawy)
	{
		status = new String("Zrealizowane");
		this.data_dostawy = data_dostawy;
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
