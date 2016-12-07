package marketing;

public class Ulotka {
	
	private int id_ulotki;
	private int id_produktu;
	
	public Ulotka()
	{
		
	}
	
	void ustawIdAnkiety(int id)
	{
		id_ulotki = id;
	}
	
	int pobierzIdAnkiety(int id)
	{
		return id_ulotki;
	}
	
	void ustawIdProduktu(int id)
	{
		id_produktu = id;
	}
	
	int pobierzIdProduktu(int id)
	{
		return id_produktu;
	}

}
