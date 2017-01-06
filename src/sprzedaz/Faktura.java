/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprzedaz;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import wspolne.Produkt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static sprzedaz.Sprzedaz.faktury;

/**
 *
 * @author Wojtass
 */
public class Faktura {

   @XStreamImplicit(itemFieldName="Produkt") 
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
    
    public void generujFakture(Faktura f ){
        Sprzedaz s  = new Sprzedaz();
        s.wczytajFaktury();
        boolean flaga= true;
//        String  tmp = JOptionPane.showInputDialog("Podaj ID faktury do wygenerowania: ");
//        while (flaga ==true){
//            
//            if(tmp == null) System.out.println("NIe podano nic ");
//            else flaga = false;
//        }
//        int tmp2 = Integer.parseInt(tmp);
        
        Faktura faktura = new Faktura ();
        faktura = faktury.get(f.idFaktura);
        XStream xs = new XStream();
        String xml =  xs.toXML(faktura);   /// koniecznie trzeba sprawdzic czy to bedzie dzialac, wg wszelkich zrodeł internetowych powinno ale wiadomo jak to w życiu bywa...
   
    }
   
    //ta metoda bedzie raczej w sprzedazy.

   
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
   
    public List<Produkt> getProdukty() {
        return produkty;
    }

    public int getIdFaktura() {
        return idFaktura;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public int getIdZamowienie() {
        return idZamowienie;
    }

    public float getWartosc() {
        return wartosc;
    }

    public int getIloscProduktow() {
        return iloscProduktow;
    }
    
}
