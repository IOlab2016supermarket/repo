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
    private float cenaZakupu; // to jest cena zakupu do sklepu
	private float cenaSprzedazy; // to cena dla klienta
	private float cenaPromocyjna;
	private float waga;
	private int ilosc;
	private Date dataWaznosci;
                 private int nr_regalu;
                 private int nr_polki;
                 private int nr_miejsca;
                 private int punkty;
	
	public Produkt(int id_produktu,String nazwa,int dlugoscGwarancji,float cenaZakupu,float cenaSprzedazy,float cenaPromocyjna,float waga,int ilosc,Date dataWaznosci,int nrRegalu, int nrPolki, int nrMiejsca)
	{
		this.id_produktu = id_produktu;
		this.nazwa = nazwa;
		this.dlugoscGwarancji = dlugoscGwarancji;
		this.cenaZakupu = cenaZakupu;
		this.cenaSprzedazy = cenaSprzedazy;
		this.cenaPromocyjna = cenaPromocyjna;
		this.waga = waga;
		this.ilosc = ilosc;
                this.dataWaznosci = dataWaznosci;
                this.nr_regalu = nrRegalu;
                this.nr_polki = nrPolki;
                this.nr_miejsca = nrMiejsca;
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
                dataWaznosci = produkt.pobierzDateWaznosci();
	}
	
                    public int pobierzIloscPunktow(){
                        return this.punkty;
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
	
	public void ustawCeneZakupu(float cenaZakupu)
	{
		this.cenaZakupu = cenaZakupu;
	}
	
	public float pobierzCeneZakupu()
	{
		return cenaZakupu;
	}
	
	public void ustawCeneSprzedazy(float cenaSprzedazy)
	{
		this.cenaSprzedazy = cenaSprzedazy;
	}
	
	public float pobierzCeneSprzedazy()
	{
		return cenaSprzedazy;
	}
	
	public void ustawCenePromocyjna(float cenaPromocyjna)
	{
		this.cenaPromocyjna = cenaPromocyjna;
	}
	
	public float pobierzCenePromocyjna()
	{
		return cenaPromocyjna;
	}
	
	public void ustawWage(float waga)
	{
		this.waga = waga;
	}
	
	public float pobierzWage()
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
        
        public void ustawNrRegalu(int nrRegalu)
        {
            this.nr_regalu = nrRegalu;
        }
        
        public int pobierzNrRegalu()
        {
            return nr_regalu;
        }
        
        public void ustawNrPolki(int nrPolki)
        {
            this.nr_polki = nrPolki;
        }
        
        public int pobierzNrPolki()
        {
            return nr_polki;
        }
        
        public void ustawNrMiejsca(int nrMiejsca)
        {
            this.nr_miejsca = nrMiejsca;
        }
        
        public int pobierzNrMiejsca()
        {
            return nr_miejsca;
        }
        
        public void ustawDateWaznosci(Date dataWaznosci)
        {
            this.dataWaznosci = dataWaznosci;
        }
        
        public Date pobierzDateWaznosci()
        {
            return dataWaznosci;
        }
	
	public String toString()
	{
		return nazwa+" cena: "+cenaSprzedazy;
	}
}