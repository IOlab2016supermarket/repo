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
   
   public void dodajFakture(Faktura f){
       Sprzedaz.faktury.add(f);
   }
   
    
}
