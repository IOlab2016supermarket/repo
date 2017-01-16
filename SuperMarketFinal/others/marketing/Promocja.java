package marketing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Promocja {

	private int id_promocji;
	private int id_produkt;
	private Date od_kiedy;
	private Date do_kiedy;
	private float stara_cena;
	private float nowa_cena;
	
	public Promocja(int id_promocji,int id_produkt, float stara_cena)
	{
		this.id_promocji = id_promocji;
		this.id_produkt = id_produkt;
		ustalCene();
		ustalDate();
		this.stara_cena = stara_cena;
	}
	
	public Promocja(int id_promocji,int id_produkt, float stara_cena,Date od_kiedy,Date do_kiedy,float nowa_cena)
	{
		this.id_promocji = id_promocji;
		this.id_produkt = id_produkt;
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		String txt1 = simple.format(od_kiedy);
		String txt2 = simple.format(do_kiedy);
		try 
		{
			this.od_kiedy = simple.parse(txt1);
			this.do_kiedy = simple.parse(txt2);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		this.stara_cena = stara_cena;
		this.nowa_cena = nowa_cena;
	}
	
	private void ustalCene()
	{
		String txt1 = JOptionPane.showInputDialog("Podaj nowa cene");
		nowa_cena = (float) Double.parseDouble(txt1);
	}
	
	private void ustalDate()
	{
		String txt1 = JOptionPane.showInputDialog("Podaj date rozpoczecia (yyyy-mm-dd)");
		String txt2 = JOptionPane.showInputDialog("Podaj date zakonczenia (yyyy-mm-dd)");
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		try 
		{
			od_kiedy = simple.parse(txt1);
			do_kiedy = simple.parse(txt2);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int pobierzId()
	{
		return id_promocji;
	}
	
	public int pobierzIdProduktu()
	{
		return id_produkt;
	}
	
	public float pobierzNowaCene()
	{
		return nowa_cena;
	}
	
	public float pobierzStaraCene()
	{
		return stara_cena;
	}
	
	public Date pobierzOdKiedy()
	{
		return od_kiedy;
	}
	
	public Date pobierzDoKiedy()
	{
		return do_kiedy;
	}
	
	public String toString()
	{
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		String txt1 = simple.format(od_kiedy);
		String txt2 = simple.format(do_kiedy);
		return id_promocji+"  data: "+txt1+"/"+txt2+"       (Id produktu:"+id_produkt+")";
	}
	
}
