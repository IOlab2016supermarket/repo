/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprzedaz;

import javax.swing.JOptionPane;

/**
 *
 * @author Wojtass
 */
public class Reklamacja {
    private int idKlient;
    private int idProdukt;
    private int idReklamacja; 
    private String notatka; 
    
    public Reklamacja ( int idReklamacja, int idKlient, int idProduktu){
        
        this.idKlient = idKlient;
        this.idProdukt =idProduktu;
        this.idReklamacja= idReklamacja;
        this.notatka = dodajNotatke();
        
    }
    
     public Reklamacja ( int idReklamacja, int idKlient, int idProduktu, String notatka){
        
        this.idKlient = idKlient;
        this.idProdukt =idProduktu;
        this.idReklamacja= idReklamacja;
        this.notatka = notatka;
        
    }
    
    public static String dodajNotatke(){
        
        String tmp = JOptionPane.showInputDialog("Dodaj notatke odnosnie reklamacji : ");
        if(tmp ==null){
            System.out.println("Anulowano ");
            return null;
        }
        else return tmp;
        
    }
    
    @Override
   public String toString(){
       return "ID Reaklamacji:  " + this.idReklamacja +  " ID Klienta: " +  this.idKlient + " ID Produktu: " + this.idProdukt + " Notatka: " + this.notatka;
   }
    
    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setIdProdukt(int idProdukt) {
        this.idProdukt = idProdukt;
    }

    public void setIdReklamacja(int idReklamacja) {
        this.idReklamacja = idReklamacja;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public int getIdProdukt() {
        return idProdukt;
    }

    public int getIdReklamacja() {
        return idReklamacja;
    }

    public String getNotatka() {
        return notatka;
    }
}
