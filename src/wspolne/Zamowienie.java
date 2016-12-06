/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolne;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojtass
 */
public class Zamowienie {
    
    private List<Produkt> produkty =null;
    private int idZamowienie;
    private  int idProudkt;
    private int idSprzedawca;
    private int iloscProduktow;
    private String status;
    private double czasDostawy;
  //  private Dostawca dostawca;  dopoki nie importuje dostawy to komentuje.    
    private int nrZamowienia;
    
    public Zamowienie(){
        produkty = new ArrayList<>();
    }
    
    
}
