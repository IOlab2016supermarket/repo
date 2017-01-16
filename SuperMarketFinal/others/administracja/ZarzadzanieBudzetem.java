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
import java.util.Vector;

import javax.swing.JOptionPane;

public class ZarzadzanieBudzetem {
	private float stanKonta;
	private Vector<Wynagrodzenie> wyplaconeWynagrodzenia;
	public Vector<Wynagrodzenie> getWyplaconeWynagrodzenia() {
		return wyplaconeWynagrodzenia;
	}

	private Connection polaczenie;
	
	public ZarzadzanieBudzetem(float stanKonta, Connection polaczenie) {
		this.polaczenie = polaczenie;
		this.stanKonta = stanKonta;
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
				
				PreparedStatement stprcnk = polaczenie.prepareStatement("SELECT * WHERE id_pracownika = (?) FROM pracownicy");
				stprcnk.setInt(1, id_pracownika);
				//ResultSet pracownikFromId = stprcnk.executeQuery();
				String PESEL = rs.getString("PESEL");
				String id_konta = rs.getString("id_konta");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				float premia = rs.getFloat("premia");
				Date data_zatrudnienia = rs.getDate("data_zatrudnienia");
				Date data_zwolnienia = rs.getDate("data_zwolnienia");
				String adres = rs.getString("adres");
				String stanowisko = rs.getString("stanowisko");
				Pracownik pracownik = new Pracownik(id_pracownika, id_konta, imie, nazwisko, PESEL, stanowisko, premia, data_zatrudnienia, data_zwolnienia, adres);
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
			writer.println(raport.getRaport());
			writer.println("Stan konta po operacjach: "+stanKonta);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void wyplacWynagrodzenie(Pracownik pracownik){
		float kwota = Integer.parseInt(JOptionPane.showInputDialog("Podaj wysokoœæ wynagrodzenia"));
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