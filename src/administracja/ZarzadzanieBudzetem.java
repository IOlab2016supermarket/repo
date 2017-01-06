package administracja;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class ZarzadzanieBudzetem {
	private Administracja administracja;
    private Connection polaczenie;
	private float stanKonta;
    private List<Wynagrodzenie> wyplaconeWynagrodzenia;
        
	public List<Wynagrodzenie> getWyplaconeWynagrodzenia() {
		return wyplaconeWynagrodzenia;
	}
        
	public ZarzadzanieBudzetem(float stanKonta, Administracja administracja) {
		this.polaczenie = administracja.getPolaczenie();
		this.stanKonta = stanKonta;
                this.administracja = administracja;
		wezWynagrodzenieZBazy();
	}

	private void wezWynagrodzenieZBazy(){
		try (Statement st = (Statement) polaczenie.createStatement()) {
			st.executeQuery("SELECT * FROM wynagrodzenia");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				int id_wynagrodzenia = rs.getInt("id_wynagrodzenia");
				int id_pracownika = rs.getInt("id_pracownika");
				float kwota = rs.getFloat("kwota");
				Date dataWyplacenia = rs.getDate("data_wyplacenia");
				
				Pracownik pracownik = administracja.wezPracownika(id_pracownika);
                wyplaconeWynagrodzenia.add(new Wynagrodzenie(id_wynagrodzenia,kwota,dataWyplacenia,pracownik));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void generujRaport(RaportZlecenie raport){
		try(FileOutputStream file = new FileOutputStream(new File("./raport.txt"))){
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(file));
			writer.println(raport+" od dnia "+raport.getPoczatek()+" do "+raport.getKoniec());
			if (raport instanceof RaportWynagrodzen) {
				RaportWynagrodzen r = (RaportWynagrodzen)raport;
				r.ustawWynagrodzenia(this.wyplaconeWynagrodzenia);
			}
			writer.println(raport.getRaport());
			writer.println("Stan konta po operacjach: "+stanKonta);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		float kwota = Integer.parseInt(JOptionPane.showInputDialog("Podaj wysokość wynagrodzenia"));
		stanKonta-=kwota;
		Wynagrodzenie wynagrodzenie = new Wynagrodzenie(wyplaconeWynagrodzenia.size()+1,kwota, new Date(),pracownik);
		try (PreparedStatement ps = polaczenie.prepareStatement("INSERT INTO wynagrodzenia VALUES (?, ?, ?, ?);")) {
			ps.setInt(1, wynagrodzenie.getId_wynagrodzenia());
			ps.setInt(2, pracownik.getId_pracownika());
			ps.setFloat(3, wynagrodzenie.getKwota());
			ps.setDate(4, new java.sql.Date(wynagrodzenie.getCzasWyplacenia().getTime()));
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		wyplaconeWynagrodzenia.add(wynagrodzenie);
	}
	
}