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
public class Sprzedawca {
    
    private int idSprzedawca;
    private String imie;
    private String nazwisko;
    

    public Sprzedawca(int id, String imie, String nazwisko){
        
        this.idSprzedawca = id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        
    }

    public void setIdSprzedawca(int idSprzedawca) {
        this.idSprzedawca = idSprzedawca;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getIdSprzedawca() {
        return idSprzedawca;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    
}
