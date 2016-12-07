/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolne;

import java.sql.Date;

/**
 *
 * @author Wojtass
 */
public class Produkt {
	
	private int id_produktu;
	private String nazwa;
    private int dlugoscGwarancji;
    private double cenaZakupu;
	private double cenaSprzedazy;
	private double cenaPromocyjna;
	private double waga;
	private int ilosc;
	//private Date dataWaznosci;
    //private int nr_regalu;
    //private int nr_polki;
    //private int nr_miejsca;
    //private int punkty;
	
	public Produkt(int id_produktu,String nazwa,int dlugoscGwarancji,double cenaZakupu,double cenaSprzedazy,double cenaPromocyjna,double waga,int ilosc)
	{
		this.id_produktu = id_produktu;
		this.nazwa = nazwa;
		this.dlugoscGwarancji = dlugoscGwarancji;
		this.cenaZakupu = cenaZakupu;
		this.cenaSprzedazy = cenaSprzedazy;
		this.cenaPromocyjna = cenaPromocyjna;
		this.waga = waga;
		this.ilosc = ilosc;
	}
	
	public Produkt(Produkt produkt)
	{
		id_produktu = produkt.pobierzId();
		nazwa = produkt.pobierzNazwe();
		dlugoscGwarancji = produkt.pobierzDlugoscGwarancji();
		cenaZakupu = produkt.pobierzCeneZakupu();
		cenaSprzedazy = produkt.pobierzCeneSprzedazy();
		cenaPromocyjna = produkt.pobierzCenePromocyjna();
		waga = produkt.pobierzWage();
		ilosc = produkt.pobierzIlosc();
	}
	
	public Produkt()
	{
		
	}
	
	public void ustawDlugoscGwarancji(int dlugoscGwarancji)
	{
		this.dlugoscGwarancji = dlugoscGwarancji;
	}
	
	public int pobierzDlugoscGwarancji()
	{
		return dlugoscGwarancji;
	}
	
	public void ustawId(int id_produktu)
	{
		this.id_produktu = id_produktu;
	}
	
	public int pobierzId()
	{
		return id_produktu;
	}
	
	public int pobierzIlosc()
	{
		return ilosc;
	}
	
	public void zwiekszIlosc(int ilosc)
	{
		this.ilosc += ilosc;
	}
	
	public void ustawIlosc(int ilosc)
	{
		this.ilosc = ilosc;
	}
	
	public void ustawCeneZakupu(double cenaZakupu)
	{
		this.cenaZakupu = cenaZakupu;
	}
	
	public double pobierzCeneZakupu()
	{
		return cenaZakupu;
	}
	
	public void ustawCeneSprzedazy(double cenaSprzedazy)
	{
		this.cenaSprzedazy = cenaSprzedazy;
	}
	
	public double pobierzCeneSprzedazy()
	{
		return cenaSprzedazy;
	}
	
	public void ustawCenePromocyjna(double cenaPromocyjna)
	{
		this.cenaPromocyjna = cenaPromocyjna;
	}
	
	public double pobierzCenePromocyjna()
	{
		return cenaPromocyjna;
	}
	
	public void ustawWage(double waga)
	{
		this.waga = waga;
	}
	
	public double pobierzWage()
	{
		return waga;
	}
	
	public void ustawNazwe(String nazwa)
	{
		this.nazwa = nazwa;
	}
	
	public String pobierzNazwe()
	{
		return nazwa;
	}
	
	public String toString()
	{
		return nazwa+" cena: "+cenaSprzedazy;
	}
}