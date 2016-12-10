/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

/**
 *
 * @author mateusz
 */
public class Magazynier {
    
    private int idMagazynier;
    private String imie;
    private String nazwisko;
    
    public Magazynier(int idMagazynier, String imie, String nazwisko)
    {
        this.idMagazynier = idMagazynier;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    
    public int pobierzId()
    {
	return idMagazynier;
    }
	
    public void ustawId(int idMagazynier)
    {
	this.idMagazynier = idMagazynier;
    }
	
    public String pobierzImie()
    {
	return imie;
    }
	
    public void ustawImie(String imie)
    {
	this.imie = imie;
    }
	
    public String pobierzNazwisko()
    {
	return nazwisko;
    }	
	
    public void ustawNazwisko(String nazwisko)
    {
	this.nazwisko = nazwisko;
    }
	
    public String toString()
    {
	return "Magazynier:" + idMagazynier + " " + imie + " " + nazwisko;
    }  
}
