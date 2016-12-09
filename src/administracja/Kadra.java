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
		pracownik.setUlica(JOptionPane.showInputDialog("Podaj nowy adres (ulica)"));
		pracownik.setNr_budynku(JOptionPane.showInputDialog("Podaj nowy adres (nr budynku)"));
		pracownik.setNr_mieszkania(JOptionPane.showInputDialog("Podaj nowy adres (nr mieszkania)"));
		pracownik.setId_stanowsika(Integer.parseInt(JOptionPane.showInputDialog("Podaj ID nowego stanowiska")));
	}
	
	public void zmienStanowiskoPracownika(Pracownik pracownik, int noweStanowisko){
		pracownik.setId_stanowsika(noweStanowisko);
		
	}
	public Pracownik wezPracownika(int indeks){
		return pracownicy.elementAt(indeks);
	}
	
	public void zapiszPracownikaWBazie(Pracownik pracownik, Connection connection) throws SQLException {
		String query = "SELECT pracownicy.PESEL FROM pracownicy WHERE pracownicy.PESEL=" + pracownik.getPESEL();
		Statement st = (Statement) connection.createStatement();
		// sprawdz czy jest
		if (st.execute(query)) {
			// mamy ju¿ wpis, uaktualnij go
			
		} else {
			// wstaw nowy wpis
			query = "INSERT INTO pracownicy VALUES (";
			query.concat(pracownik.getPESEL() + ", ");
			query.concat(pracownik.getId_stanowiska() + ", ");
			query.concat(pracownik.getImie() + ", ");
			query.concat(pracownik.getNazwisko() + ", ");
			query.concat(pracownik.getPremia() + ", ");
			query.concat(pracownik.getData_zatrudnienia() + ", ");
			if (pracownik.getData_zwolnienia() == null) {
				query.concat("NULL, ");
			} else {
				query.concat(pracownik.getData_zwolnienia() + ", ");
			}
			query.concat(pracownik.getMiasto() + ", ");
			query.concat(pracownik.getUlica() + ", ");
			query.concat(pracownik.getNr_budynku() + ", ");
			query.concat(pracownik.getNr_mieszkania() + ", ");
			query.concat(pracownik.getKod_pocztowy() + ", ");
			query.concat(pracownik.getPoczta() +");");
			st.executeQuery(query);
		}
		st.close();
	}
	public void wczytajPracownikowZBazy(Connection connection) throws SQLException {
		Statement st = (Statement) connection.createStatement();
		st.executeQuery("SELECT * FROM pracownicy");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			String PESEL = rs.getString("PESEL");
			int id_stanowiska = rs.getInt("id_stanowiska");
			String id_konta = rs.getString("id_konta");
			String imie = rs.getString("imie");
			String nazwisko = rs.getString("nazwisko");
			float premia = rs.getFloat("premia");
			Date data_zatrudnienia = rs.getDate("data_zatrudnienia");
			Date data_zwolnienia = rs.getDate("data_zwolnienia");
			String miasto = rs.getString("miasto");
			String ulica = rs.getString("ulica");
			String nr_budynku = rs.getString("nr_budynku");
			String nr_mieszkania = rs.getString("nr_mieszkania");
			String kod_pocztowy = rs.getString("kod_pocztowy");
			String poczta = rs.getString("poczta");
			pracownicy.add(new Pracownik(PESEL, id_stanowiska, id_konta, imie, nazwisko, premia, data_zatrudnienia, data_zwolnienia, miasto, ulica, nr_budynku, nr_mieszkania, kod_pocztowy, poczta));
		}
	}
}
