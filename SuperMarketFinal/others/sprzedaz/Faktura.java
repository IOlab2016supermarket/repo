/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprzedaz;

import wspolne.Produkt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojtass
 */
public class Faktura {

    
   private  List<Produkt> produkty =null;
   private int idFaktura;
   private int idKlient;
   private int idZamowienie;
   private float wartosc;
   private int iloscProduktow;
   
   public Faktura(List<Produkt> produkty, int idFaktura, int IdKlient, int idZamowienie, float wartosc, int ilosc){
      this.produkty  = new ArrayList<Produkt>();
      this.produkty= produkty;
      this.idFaktura = idFaktura;
      this.idKlient = idKlient;
      this.idZamowienie = idZamowienie;
      this.wartosc = wartosc;
      this.iloscProduktow = ilosc;
   }

    Faktura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   public void dodajFakture(Faktura f){
       Sprzedaz.faktury.add(f);
   }
   
    public void setProdukty(List<Produkt> produkty) {
        this.produkty = produkty;
    }

    public void setIdFaktura(int idFaktura) {
        this.idFaktura = idFaktura;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setIdZamowienie(int idZamowienie) {
        this.idZamowienie = idZamowienie;
    }

    public void setWartosc(float wartosc) {
        this.wartosc = wartosc;
    }

    public void setIloscProduktow(int iloscProduktow) {
        this.iloscProduktow = iloscProduktow;
    }
   
    
}
