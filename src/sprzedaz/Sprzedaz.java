/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprzedaz;
import wspolne.Zamowienie;
import baza.BazaDanych;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Wojtass
 */
public class Sprzedaz {

    /**
     * @param args the command line arguments
     */
    private List<Zamowienie> zamowienia = null;
    private List<Klient> klienci =null;
    public static List<Faktura> faktury = null;
    private List<Reklamacja> reklamacje = null;
    
    public Sprzedaz(){
        this.klienci = null;
        this. zamowienia = new ArrayList<>();
        this.klienci = new ArrayList<>();
        Sprzedaz.faktury = new ArrayList<>();
        this.reklamacje = new ArrayList<>();
    }
    
    
    public void dodajZamowienie(int idKlient , int idSprzedawca ){
        
        
        zamowienia.add(new Zamowienie(zamowienia.size()+1, idKlient, idSprzedawca));
        
    }
    
    public void usunZamowienie(Zamowienie z){     
        zamowienia.remove(z.pobierzIdZamowienia());
    }
    
    public void dodajKlienta(String imie, String nazwisko, String kodPocztowy){
        
       klienci.add(new Klient(klienci.size()+1, imie, nazwisko, kodPocztowy));
    }
    
    public void usunKlienta(Klient k){
        klienci.remove(k.getIdKlient());
    }
    
    public void zlozReklamacje(int idKlient, int idProduktu){
        
        reklamacje.add(new Reklamacja(reklamacje.size()+1, idKlient,idProduktu ));
    }
    
    public void usunReklamacje(Reklamacja r){
        reklamacje.remove(r.getIdReklamacja());
    }
            
            
    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public void setFaktury(List<Faktura> faktury) {
        this.faktury = faktury;
    }

    public void setReklamacje(List<Reklamacja> reklamacje) {
        this.reklamacje = reklamacje;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public List<Faktura> getFaktury() {
        return faktury;
    }

    public List<Reklamacja> getReklamacje() {
        return reklamacje;
    }        
    //maina ttu nie bedzie
    public static void main(String[] args) throws SQLException {
       BazaDanych.polacz();
       //BazaDanych.getPolaczenie().ping();
       
      
       
    }
    
}
