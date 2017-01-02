package marketing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.thoughtworks.xstream.XStream;

import wspolne.Produkt;

public class Ulotka {
	
	private int id_ulotki;
	private int id_produktu;
	
	public Ulotka()
	{
		
	}
	
	void ustawId(int id)
	{
		id_ulotki = id;
	}
	
	int pobierzId(int id)
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
	
	private void generujHTML(String nazwaPliku) throws TransformerException
	{
		String sciezka = "./xml/ulotka/"+nazwaPliku;
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Source xslt = new StreamSource(new File("./xml/ulotka/doHTML.xsl"));
	    Transformer transformer = factory.newTransformer(xslt);
	    Source text = new StreamSource(new File("./xml/ulotka/produkty.xml"));
	    transformer.transform( text, new StreamResult(new File(sciezka)));
	}

	//nazwaPliku podawana z rozszerzeniem .html
	//html utworzony w folderze ./xml/ulotka/
	public void generujUlotke(Map<Produkt,Promocja> map, String nazwaPliku)
	{
		XStream xstream = new XStream();
		File file = new File("./xml/ulotka/produkty.xml");
		String xml = xstream.toXML(map);
		try(FileWriter fileWriter = new FileWriter(file))
		{
			String encoding = "<?xml version="+"\"1.0\""+" encoding="+"\"ISO-8859-2\""+"?>";
			file.createNewFile();
			fileWriter.write(encoding);
			fileWriter.write(xml);
			JOptionPane.showMessageDialog(null, "Produkty zapisane do XML!");
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Blad zapisu danych do XML:\n" + ioe.getMessage());
		}
		
		try {
			generujHTML(nazwaPliku);
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}
	}		
}
