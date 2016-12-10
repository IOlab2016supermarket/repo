package dostawa;

public class Dostawca {

	private int id_dostawcy;
	private String nazwa;
	private String adres;
	
	public Dostawca(int id_dostawcy,String nazwa,String adres)
	{
		this.id_dostawcy = id_dostawcy;
		this.nazwa = new String(nazwa);
		this.adres = new String(adres);
	}
	
	public Dostawca(Dostawca dostawca)
	{
		this.id_dostawcy = dostawca.id_dostawcy;
		this.nazwa = new String(dostawca.nazwa);
		this.adres = new String(dostawca.adres);
	}
	
	public int pobierzId()
	{
		return id_dostawcy;
	}
	
	public void ustawId(int id_dostawcy)
	{
		this.id_dostawcy = id_dostawcy;
	}
	
	public String pobierzNazwe()
	{
		return nazwa;
	}
	
	public void ustawNazwe(String nazwa)
	{
		this.nazwa = nazwa;
	}
	
	public String pobierzAdres()
	{
		return adres;
	}
	
	public void ustawAdres(String adres)
	{
		this.adres = adres;
	}
	
	public String toString()
	{
		return nazwa+"  "+adres;
	}
}
