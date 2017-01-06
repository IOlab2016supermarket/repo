/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprzedaz;
import wspolne.Zamowienie;
import baza.BazaDanych;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wspolne.Produkt;
/**
 *
 * @author Wojtass
 */
public class Sprzedaz implements IZarzadzajFakturami {

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
    
    
    @Override
    public int pobierzIloscSprzedanych() {
       
        wczytajFaktury();
        int tmp = 0;
        for(Faktura f : faktury){
            
            tmp += f.getIloscProduktow();
        }
        return tmp; 
    }
    
    @Override
      public float pobierzWartoscSprzedanych() {
        
          wczytajFaktury();
          float tmp = 0;
          for(Faktura f : faktury){
              
              tmp += f.getWartosc();
          }
          
          return tmp ;
    }
      
    public void wczytajFaktury(){
        
        faktury.clear(); // nie wiem czy tak  ale wydaję sie rozsądne czyscic za kazdym razem przed wczytaniem z bazy, natomiast 
        try {
            List<Produkt> produktyTmp = new ArrayList<>();
            ResultSet wynikProdukty = BazaDanych.getPolaczenie().createStatement().
                                                                                                executeQuery("Select * from produkt INNER JOIN produktysprzedaz ON produkt.idProdukt = produktysprzedaz.idProduktySpzedaz" );
            while(wynikProdukty.next()){
                
                produktyTmp.add(new Produkt(
                                                                wynikProdukty.getInt("idProdukt"),
                                                                wynikProdukty.getString("nazwaProduktul"),
                                                                wynikProdukty.getInt("dlugoscGwarancji"),
                                                                wynikProdukty.getFloat("cenaSprzedazy")      ));
                        
            }
            
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("Select * from faktura");
            //List<Faktura> tmpList = new ArrayList<>();
            while(wynik.next()){
     
                    faktury.add(new Faktura( produktyTmp,
                                                          wynik.getInt("idFaktura"),
                                                          wynik.getInt("idKlient"),          
                                                          wynik.getInt("idZamowienia"),
                                                          wynik.getFloat("wartosc"),
                                                          wynik.getInt("iloscProduktow")   ));  
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dodajFakture(Faktura f){  ///ta metoda to jeden wielki znak zapytania. Nie wiem czy będą się zgadzać wszystkie ID w fakturze z produktami w osobnej tabeli.
        
        wczytajFaktury();
        
        int idFaktury = faktury.size()+1;
        
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().
            executeQuery("INSERT INTO faktura(idFaktura, idKlient, wartosc, iloscProduktow, idZamowienia) values("+idFaktury+", "+ f.getIdKlient() +" , "+f.getWartosc()+" , "+f.getIloscProduktow()+" , "+f.getIdZamowienie()+")");
             
            
            for(Produkt  p :  f.getProdukty()){ //teoretycznie zadziała dla każdego produktu w fakturze którą przekazujemy.
                
            ResultSet wynikProdukty = BazaDanych.getPolaczenie().createStatement().
                    executeQuery("INSERT INTO produktysprzedaz(idProduktySprzedaz, idFaktury)  values ("+p.pobierzId()+" , "+idFaktury+")") ;
                    
                    wynikProdukty.close();
            }
            
            wynik.close();
            
                    } catch (SQLException ex) {
                              ex.printStackTrace();
        }
        
        
    }
    
    public void usunFakture(Faktura f ){
        
        faktury.remove(f.getIdFaktura());
        
        try {
            
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery(" DELETE FROM  faktura where idFaktura = "+f.getIdFaktura()+" ");
            ResultSet wynikProdukty = BazaDanych.getPolaczenie().createStatement().executeQuery("DELETE FROM produktysprzedaz where idFaktury = "+f.getIdFaktura()+" ");
            
            wynik.close();
            wynikProdukty.close();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public void dodajZamowienie(int idKlient , int idSprzedawca ){
        
        zamowienia = wczytajZamowienia(); //czy to tak zadziałą ? ? czy tylko referencje tu przekazujemy ?
        
        int idZamowienie = zamowienia.size()+1;
        try {
            
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().
                    executeQuery(" INSERTO INTO zamowienie(idZamowienie, idKlient, idSprzedawca) values("+idZamowienie+" , "+idKlient+" ," +idSprzedawca+")");
            
            
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    

    public void usunZamowienie(Zamowienie z){     
        
        zamowienia.remove(z.pobierzIdZamowienia()); // nie wiem czy to jest potrzebne
        
        int id = z.pobierzIdZamowienia();
        try {
            ResultSet wynik =BazaDanych.getPolaczenie().createStatement().executeQuery("DELETE FROM zamowienie where idZamowienie = "+id+" " );
            
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Zamowienie> wczytajZamowienia(){
         
        List<Zamowienie> tmp = new ArrayList<>();
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("Select * from zamowienie");
         
            while(wynik.next()){
                
                tmp.add(new Zamowienie(wynik.getInt("idZamowienie" ),
                                                      wynik.getInt("idKlient"),
                                                      wynik.getInt("idSprzedawca" ) ));
                          }
            
                          wynik.close();
                    } catch (SQLException ex) {
                      ex.printStackTrace();
        }
        
        return tmp;
    }
    
    public void dodajKlienta(String imie, String nazwisko, String kodPocztowy){
        
        wczytajKlientow();
       int idKlient = klienci.size()+1;
       
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().
                    executeQuery("INSERT INTO klient(idKlient, imie, nazwisko, kodPocztowy) values("+ idKlient+" , "+imie+" , "+nazwisko+" , "+kodPocztowy+")");
            
            
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
       
    }
    
    public void usunKlienta(Klient k){
        klienci.remove(k.getIdKlient()); // ?? ??? ? ?? 
        
        int id = k.getIdKlient();
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("DELETE FROM klient where idKlient= "+id+"");
            
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void edytujLiczbePunktow(Klient k ,  int ilosc){
        
        for(Klient klient : klienci){
            
            if(klient.getIdKlient() == k.getIdKlient()){
                k.setIloscPunktow(ilosc);
                
                try {
                    ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("UPDATE klient SET iloscPunktow="+ilosc+" where idKlient="+k.getIdKlient()+"  ");
                    wynik.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }                            
            }
                     
        }      
        
    }
    
    public void wczytajKlientow(){
        
      //  List<Klient> tmp = new ArrayList<>();
        try {
            ResultSet wynik  = BazaDanych.getPolaczenie().createStatement().executeQuery("select * from klient");
            
            while(wynik.next()){
                
                klienci.add(new Klient(wynik.getInt("idKlient"),
                                               wynik.getString("imie"),
                                                    wynik.getString("nazwisko"),
                                                        wynik.getString("kodPocztowy")    ));
            }
            
        } catch (SQLException ex) {
              ex.printStackTrace();
        }
       
    }
    
    public void zlozReklamacje(int idKlient, int idProduktu){
        
        
      //  reklamacje.add(new Reklamacja(reklamacje.size()+1, idKlient,idProduktu ));
        reklamacje = wczytajReklamacje();
        int id = reklamacje.size()+1;
        
        String notatka = Reklamacja.dodajNotatke();
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("INSERT INTO reklamacje(id, id_klienta, id_produktu, opis) values("+ id+". "+idKlient+" ,"+idProduktu+", "+notatka+" ");
            
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public void usunReklamacje(Reklamacja r){
        reklamacje.remove(r.getIdReklamacja()); // ??????
        
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("DELETE from reklamacje where id = "+r.getIdReklamacja()+"  ");
            
            wynik.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
    }
            
   public List<Reklamacja> wczytajReklamacje(){
       
       List<Reklamacja> tmp = new ArrayList<>();
        try {
            ResultSet wynik = BazaDanych.getPolaczenie().createStatement().executeQuery("SELECT * FROM reklamacje");
            while(wynik.next()){
                tmp.add(new Reklamacja (wynik.getInt("id"),
                                                        wynik.getInt("id_klienta"),
                                                            wynik.getInt("id_produktu"),
                                                                wynik.getString("opis")     ));
                            
            }
  
            wynik.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tmp;
       
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

    
}
