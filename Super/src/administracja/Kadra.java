package administracja;

import java.util.Date;
import java.util.Vector;
import java.sql.*;
import javax.swing.JOptionPane;

public class Kadra {
	private Vector<Pracownik> pracownicy;
	
	public Kadra(Vector<Pracownik> pracownicy) {
		this.pracownicy = pracownicy;
	}

	public void dodajPracownika(Pracownik pracownik){
		pracownicy.add(pracownik);
	}
	
	public void usunPracownika(Pracownik pracownik){
		pracownik.setData_zwolnienia(new Date());
		pracownicy.remove(pracownik);
	}
	
	public void edytujPracownika(Pracownik pracownik){
		pracownik.setNazwisko(JOptionPane.showInputDialog("Podaj nowe nazwisko"));
		pracownik.setImie(JOptionPane.showInputDialog("Podaj nowe imie"));
		pracownik.setAdres(JOptionPane.showInputDialog("Podaj nowy adres"));
		pracownik.setStanowisko(JOptionPane.showInputDialog("Podaj nowe stanowisko"));
		
	}
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, String noweStanowisko){
		pracownik.setStanowisko(noweStanowisko);
		
	}
	public Pracownik wezPracownika(int indeks){
		return pracownicy.elementAt(indeks);
	}
	
	public void zapiszPracownikaWBazie(Pracownik pracownik, Connection polaczenie) throws SQLException {
		String query = "SELECT pracownicy.id_pracownika FROM pracownicy WHERE pracownicy.id_pracownika=" + pracownik.getId_pracownika();
		Statement st = (Statement) polaczenie.createStatement();
		if (st.execute(query)) {
			//
						/* BLAD tutaj mace porpawne funkcje i co robia..
						 * executeUpdate() -> database UPDATE statements; jak INSERT,UPDATE, DELETE
			executeQuery() -> database QUERY statements; tylko SELECT
			execute() -> anything that comes in  ;; WIECC IF ZAWSZE SIE WYKONA, ELSE NIGDY, do poprawy
						 */
			modyfikujPracownikaWBazie(pracownik, polaczenie);
		} else {
			stworzPracownikaWBazie(pracownik, polaczenie);
		}
		st.close();
	}
	public void wczytajPracownikowZBazy(Connection polaczenie) throws SQLException {
		Statement st = (Statement) polaczenie.createStatement();
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
	}
	public void modyfikujPracownikaWBazie(Pracownik pracownik, Connection polaczenie) throws SQLException {
		PreparedStatement ps = polaczenie.prepareStatement("UPDATE pracownicy WHERE pracownicy.id_pracownika=? SET id_konta=? imie=? nazwisko=? PESEL=? stanowisko=? premia=? data_zatrudnienia=? data_zwolnienia=? adres=?");
		ps.setInt(1, pracownik.getId_pracownika());
		ps.setString(2, pracownik.getId_konta());
		ps.setString(3, pracownik.getImie());
		ps.setString(4, pracownik.getNazwisko());
		ps.setString(5, pracownik.getPESEL());
		ps.setString(6, pracownik.getStanowisko());
		ps.setFloat(7, pracownik.getPremia());
		ps.setDate(8, new java.sql.Date(pracownik.getData_zatrudnienia().getTime()));
		ps.setDate(9, new java.sql.Date(pracownik.getData_zwolnienia().getTime()));
		ps.setString(10, pracownik.getAdres());
		ps.executeUpdate();
		//ps.executeQuery(); //updateQuery jesli juz czytwaj wyzej komentarz
		
	}
	public void usunPracownikaZBazy(Pracownik pracownik, Connection polaczenie) throws SQLException {
		Statement st = (Statement) polaczenie.createStatement();
		st.executeUpdate("DELETE FROM pracownicy WHERE pracownicy.id_pracownika=" + pracownik.getId_pracownika());
		
		//ps.executeQuery(); //updateQuery jesli juz czytwaj wyzej komentarz
	}
	public void stworzPracownikaWBazie(Pracownik pracownik, Connection polaczenie) throws SQLException {
		PreparedStatement ps = polaczenie.prepareStatement("INSERT INTO pracownicy VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(1, pracownik.getId_pracownika());
		ps.setString(2, pracownik.getId_konta());
		ps.setString(3, pracownik.getImie());
		ps.setString(4, pracownik.getNazwisko());
		ps.setString(5, pracownik.getPESEL());
		ps.setString(6, pracownik.getStanowisko());
		ps.setFloat(7, pracownik.getPremia());
		ps.setDate(8, new java.sql.Date(pracownik.getData_zatrudnienia().getTime()));
		ps.setDate(9, new java.sql.Date(pracownik.getData_zwolnienia().getTime()));
		ps.setString(10, pracownik.getAdres());
		ps.executeUpdate();
		//ps.executeQuery(); //updateQuery jesli juz czytwaj wyzej komentarz
	}
}
