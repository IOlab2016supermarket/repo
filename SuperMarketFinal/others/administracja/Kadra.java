package administracja;
import java.util.Date;
import java.util.Vector;
import java.sql.*;
import javax.swing.JOptionPane;

public class Kadra {
	private Vector<Pracownik> pracownicy;
	private Connection polaczenie;
	
	public Kadra(Connection polaczenie) {
		this.pracownicy = new Vector<Pracownik>();
		this.polaczenie = polaczenie;
		wczytajPracownikowZBazy(polaczenie);
	}

	public void dodajPracownika(Pracownik pracownik){
		pracownicy.add(pracownik);
		stworzPracownikaWBazie(pracownik);
	}
	
	public void usunPracownika(Pracownik pracownik){
		pracownik.setData_zwolnienia(new Date());
		pracownicy.remove(pracownik);
		usunPracownikaZBazy(pracownik);
	}
	
	public void edytujPracownika(Pracownik pracownik){
		pracownik.setNazwisko(JOptionPane.showInputDialog("Podaj nowe nazwisko"));
		pracownik.setImie(JOptionPane.showInputDialog("Podaj nowe imie"));
		pracownik.setAdres(JOptionPane.showInputDialog("Podaj nowy adres"));
		pracownik.setStanowisko(JOptionPane.showInputDialog("Podaj nowe stanowisko"));
		modyfikujPracownikaWBazie(pracownik);
		
	}
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, String noweStanowisko){
		pracownik.setStanowisko(noweStanowisko);
		modyfikujPracownikaWBazie(pracownik);
	}
	public Pracownik wezPracownika(int indeks){
		return pracownicy.elementAt(indeks);
	}
	
	private void wczytajPracownikowZBazy(Connection polaczenie) {
		
		try (Statement st = (Statement) polaczenie.createStatement()) {
			st.executeQuery("SELECT * FROM pracownicy");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				int id_pracownika = rs.getInt("id_pracownika");
				String PESEL = rs.getString("PESEL");
				String id_konta = rs.getString("id_konta");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				float premia = rs.getFloat("premia");
				Date data_zatrudnienia = rs.getDate("data_zatrudnienia");
				Date data_zwolnienia = rs.getDate("data_zwolnienia");
				String adres = rs.getString("adres");
				String stanowisko = rs.getString("stanowisko");
				pracownicy.add(new Pracownik(id_pracownika, id_konta, imie, nazwisko, PESEL, stanowisko, premia, data_zatrudnienia, data_zwolnienia, adres));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	private void modyfikujPracownikaWBazie(Pracownik pracownik) {
		try(PreparedStatement ps = polaczenie.prepareStatement("UPDATE pracownicy WHERE pracownicy.id_pracownika=? SET id_konta=? imie=? nazwisko=? PESEL=? stanowisko=? premia=? data_zatrudnienia=? data_zwolnienia=? adres=?")) {
			ps.setInt(1, pracownik.getId_pracownika());
			ps.setString(2, pracownik.getId_konta());
			ps.setString(3, pracownik.getImie());
			ps.setString(4, pracownik.getNazwisko());
			ps.setString(5, pracownik.getPesel());
			ps.setString(6, pracownik.getStanowisko());
			ps.setFloat(7, pracownik.getPremia());
			ps.setDate(8, new java.sql.Date(pracownik.getData_zatrudnienia().getTime()));
			if (pracownik.getData_zatrudnienia() != null) {
				ps.setDate(8, new java.sql.Date(pracownik.getData_zatrudnienia().getTime()));
			} else ps.setDate(8, null);
			if (pracownik.getData_zwolnienia()!= null) {
				ps.setDate(9, new java.sql.Date(pracownik.getData_zwolnienia().getTime()));
			} else ps.setDate(9, null);
			ps.setString(10, pracownik.getAdres());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
	}
	private void usunPracownikaZBazy(Pracownik pracownik) {
		try {
			Statement st = (Statement) polaczenie.createStatement();
			st.executeUpdate("DELETE FROM pracownicy WHERE pracownicy.id_pracownika=" + pracownik.getId_pracownika());
			st.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	private void stworzPracownikaWBazie(Pracownik pracownik) {
		try (PreparedStatement ps = polaczenie.prepareStatement("INSERT INTO pracownicy VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
			ps.setInt(1, pracownik.getId_pracownika());
			ps.setString(2, pracownik.getId_konta());
			ps.setString(3, pracownik.getImie());
			ps.setString(4, pracownik.getNazwisko());
			ps.setString(5, pracownik.getPesel());
			ps.setString(6, pracownik.getStanowisko());
			ps.setFloat(7, pracownik.getPremia());
			if (pracownik.getData_zatrudnienia() != null) {
				ps.setDate(8, new java.sql.Date(pracownik.getData_zatrudnienia().getTime()));
			} else ps.setDate(8, null);
			if (pracownik.getData_zwolnienia()!= null) {
				ps.setDate(9, new java.sql.Date(pracownik.getData_zwolnienia().getTime()));
			} else ps.setDate(9, null);
			ps.setString(10, pracownik.getAdres());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
