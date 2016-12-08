package marketing;
import javax.swing.*;

import wspolne.Produkt;

public class Promocja {

	private int id_promocji;
	private int id_produkt;
	private String od_kiedy;
	private String do_kiedy;
	private double stara_cena;
	private double nowa_cena;
	
	Promocja(int id_promocji,int id_produkt, double stara_cena)
	{
		this.id_promocji = id_promocji;
		this.id_produkt = id_produkt;
		ustalCene();
		ustalDate();
		this.stara_cena = stara_cena;
	}
	
	Promocja(int id_promocji,int id_produkt, double stara_cena,String od_kiedy,String do_kiedy,double nowa_cena)
	{
		this.id_promocji = id_promocji;
		this.id_produkt = id_produkt;
		this.od_kiedy = od_kiedy;
		this.do_kiedy = do_kiedy;
		this.stara_cena = stara_cena;
		this.nowa_cena = nowa_cena;
	}
	
	private void ustalCene()
	{
		String txt1 = JOptionPane.showInputDialog("Podaj cenďż˝");
		nowa_cena = Double.parseDouble(txt1);
	}
	
	private void ustalDate()
	{
		String txt1 = JOptionPane.showInputDialog("Podaj datďż˝ rozpoczďż˝cia (dd-mm-yyyy)");
		od_kiedy = new String(txt1);
		String txt2 = JOptionPane.showInputDialog("Podaj datďż˝ zakoďż˝czenia (dd-mm-yyyy)");
		do_kiedy = new String(txt2);
	}
	
	public int pobierzId()
	{
		return id_promocji;
	}
	
	public int pobierzIdProduktu()
	{
		return id_produkt;
	}
	
	public double pobierzNowaCene()
	{
		return nowa_cena;
	}
	
	public double pobierzStaraCene()
	{
		return stara_cena;
	}
	
	public String toString()
	{
		return id_promocji+"  data: "+od_kiedy+"/"+do_kiedy+"       (Id produktu:"+id_produkt+")";
	}
	
}
