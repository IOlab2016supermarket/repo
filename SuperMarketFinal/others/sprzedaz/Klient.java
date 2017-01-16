/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprzedaz;

/**
 *
 * @author Wojtass
 */
public class Klient {
        private int  idKlient;
        private int iloscPunktow;
        private String imie;
        private String nazwisko;
        private String kodPocztowy;
        
        public Klient(int id,  String imie, String nazwisko, String kodPocztowy){
            
            this.idKlient=id;
            this.imie=imie;
            this.nazwisko = nazwisko;
            this.kodPocztowy=kodPocztowy;
            setIloscPunktow(0);
        }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setIloscPunktow(int iloscPunktow) {
        this.iloscPunktow = iloscPunktow;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public int getIloscPunktow() {
        return iloscPunktow;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }
    
    @Override
    public String toString(){
        return "Imie: " + this.imie + " Nazwisko: " + this.nazwisko + " Kod pocztowy: " + this.kodPocztowy + "  Ilosc punktow: " + this.iloscPunktow;
    }
        
    
}
