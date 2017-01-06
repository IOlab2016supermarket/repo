package marketing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.thoughtworks.xstream.XStream;

public class Ankieta {
	
	private int id_ankiety;
	private String tytul;
	private List<AnkietaPytanie> pytania; 

	public Ankieta(int id_ankiety, String tytul, List<AnkietaPytanie> pytania)
	{
		pytania = new ArrayList<AnkietaPytanie>();
		this.pytania = pytania;
		this.id_ankiety = id_ankiety;
		this.tytul = tytul;
	}
	
	
	public Ankieta(int id_ankiety, String tytul)
	{
		pytania = new ArrayList<AnkietaPytanie>();
		this.id_ankiety = id_ankiety;
		this.tytul = tytul;
	}
	
	public void generujPDF(String nazwa)
	{
		 BaseFont czcionka = null;
		 Document document = new Document();

		 try {
		    
		     try 
		    {
				czcionka = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.CACHED);
			} catch (IOException e) {	
				e.printStackTrace();
			}
		    PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(nazwa+".pdf"));
		    document.open();

		      Font font1 = new Font(czcionka, 27);
		      Font font2 = new Font(czcionka, 18);
		      Font font3 = new Font(czcionka, 14);
		      SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		      String txt = simple.format(new Date());
		      
		      Paragraph data = new Paragraph("Data: "+txt,font3);
		      data.setAlignment(2);
		      document.add(data);
		      Paragraph par = new Paragraph("Ankieta",font1);
		      par.setAlignment(1);
		      document.add(par);
		      Paragraph par2 = new Paragraph(this.tytul,font2);
		      par2.setAlignment(1);
	    	  document.add(new Paragraph(" "));

	    	  document.add(par2);
	    	  document.add(new Paragraph(" "));
	    	  document.add(new Paragraph(" "));
		      for(int i=0;i<this.pytania.size();i++)
		      {
		    	  if(this.pytania.get(i).pobierzCzyOdpowiedzDoWyboru())
		    	  {
		    		  document.add(new Paragraph((i+1)+". "+this.pytania.get(i).pobierzTrescPytania(),font3));
		    		  document.add(new Paragraph(" "));
		    		  for(int j = 0; j<this.pytania.get(i).pobierzOdpowiedzi().size();j++)
		    		  {
		    			  Paragraph par5 = new Paragraph("         -   "+this.pytania.get(i).pobierzOdpowiedzi().get(j),font3);
		    			  document.add(par5);
		    			  //Rectangle rect = new Rectangle(50, 602,60, 612);
		    			  //rect.setBorder(Rectangle.BOX);
		    			  //rect.setBorderWidth(1);
		    			  //document.add(rect);
		    		  }
	    			  document.add(new Paragraph(" "));
		    	  }
		    	  else
		    	  {
			    	  document.add(new Paragraph((i+1)+". "+this.pytania.get(i).pobierzTrescPytania(),font3));
			    	  document.add(new Paragraph(".............................................................." +
			    	  		"..................................................................................."));
			    	  document.add(new Paragraph(".............................................................." +
				    	  	"..................................................................................."));
		    	  }

		      }

		      document.close();

		    } catch (DocumentException e) {
		      e.printStackTrace();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
	}
	
	
	//nazwaPliku podawana z rozszerzeniem .html
	//html utworzony w folderze ./xml/ankieta/
	public void generujHTML(String nazwaWyjsciowyHTML) throws TransformerException
	{
		XStream xstream = new XStream();
		String xml = nazwaWyjsciowyHTML.replace(".html", "");
		File file = new File("./xml/ankieta/"+xml+".xml");
		String xmlPlik = xstream.toXML(this);
		try(FileWriter fileWriter = new FileWriter(file))
		{
			String encoding = "<?xml version="+"\"1.0\""+" encoding="+"\"ISO-8859-2\""+"?>";
			file.createNewFile();
			fileWriter.write(encoding);
			fileWriter.write(xmlPlik);
			JOptionPane.showMessageDialog(null, "Ankieta zapisana do XML!");
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Blad zapisu danych do XML:\n" + ioe.getMessage());
		}
		
		String sciezka = "./xml/ankieta/"+nazwaWyjsciowyHTML;
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Source xslt = new StreamSource(new File("./xml/ankieta/doHTML_ankieta.xsl"));
	    Transformer transformer = factory.newTransformer(xslt);
	    Source text = new StreamSource(new File("./xml/ankieta/"+xml+".xml"));
	    transformer.transform( text, new StreamResult(new File(sciezka)));
		
	}
	
	
	public void dodajPytanie(AnkietaPytanie txt)
	{
		pytania.add(txt);
	}
	
	public void usunPytanie(int numer)
	{
		pytania.remove(numer);
	}
	
	public void usunPytanie(AnkietaPytanie txt)
	{
		pytania.remove(txt);
	}
	
	public void ustawId(int id)
	{
		id_ankiety = id;
	}
	
	public int pobierzId()
	{
		return id_ankiety;
	}
	
	public String pobierzTytul()
	{
		return tytul;
	}
	
	public void ustawTytul(String tytul)
	{
		this.tytul = tytul;
	}
	
	public List<AnkietaPytanie> pobierzPytania()
	{
		return pytania;
	}
	
	public String toString()
	{
		return id_ankiety+","+tytul+","+pytania.toString();
	}
	
	
	
}
