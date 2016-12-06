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
    private List<Faktura> faktury = null;
    private List<Reklamacja> reklamacje = null;
    
    public Sprzedaz(){
        this.klienci = null;
        this. zamowienia = new ArrayList<>();
        this.klienci = new ArrayList<>();
        this.faktury = new ArrayList<>();
        this.reklamacje = new ArrayList<>();
    }
    
    
    
    
    //maina ttu nie bedzie
    public static void main(String[] args) throws SQLException {
       BazaDanych.polacz();
       //BazaDanych.getPolaczenie().ping();
    }
    
}
