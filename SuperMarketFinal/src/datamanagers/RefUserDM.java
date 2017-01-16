package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import administracja.Pracownik;
import superclasses.DataManager;
import superclasses.Entity;
import entities.RefUser;

public class RefUserDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id_pracownika");
		int id_konta = super.getInt("id_konta");
		String login = super.getString("login");	// From INNER JOIN
		String imie = rs.getString("imie");
		String nazwisko = rs.getString("nazwisko");
		String PESEL = rs.getString("PESEL");
		String stanowisko = rs.getString("stanowisko");
		float salary = rs.getFloat("salary");
		float premia = rs.getFloat("premia");
		Date data_zatrudnienia = super.getDate("data_zatrudnienia");
		Date data_zwolnienia = super.getDate("data_zwolnienia");
		String adres = rs.getString("adres");
		
		return new RefUser(id, id_konta, login, imie, nazwisko, PESEL, stanowisko, salary, premia, data_zatrudnienia, data_zwolnienia, adres);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT pracownicy.*, "
				+ "konta.login AS login "
				+ "FROM pracownicy "
				+ "LEFT JOIN konta ON pracownicy.id_konta = konta.id "
				+ "ORDER BY pracownicy.id_pracownika";
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		RefUser user = (RefUser) ent;
		Pracownik worker = user.getWorker();
		
		String sql = "INSERT INTO pracownicy ("
				+ "id_konta, "
				+ "imie, "
				+ "nazwisko, "
				+ "PESEL, "
				+ "stanowisko, "
				+ "salary, "
				+ "premia, "
				+ "data_zatrudnienia, "
				+ "data_zwolnienia, "
				+ "adres"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "%s, "	// without <'>
				+ "%s, "	// without <'>
				+ "'%s'"
				+ ")",
				
				user.getKontoId(),
				worker.getImie(),
				worker.getNazwisko(),
				worker.getPesel(),
				worker.getStanowisko(),
				user.getSalary(),
				worker.getPremia(),
				super.sqlDateFormat(worker.getData_zatrudnienia(), false),
				super.sqlDateFormat(worker.getData_zwolnienia(), true),
				worker.getAdres()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefUser user = (RefUser) ent;
		Pracownik worker = user.getWorker();
		
		String sql = "UPDATE pracownicy SET "
				+ "id_konta='%s', "
				+ "imie='%s', "
				+ "nazwisko='%s', "
				+ "PESEL='%s', "
				+ "stanowisko='%s', "
				+ "salary='%s', "
				+ "premia='%s', "
				+ "data_zatrudnienia=%s, "	// without <'>
				+ "data_zwolnienia=%s, "	// without <'>
				+ "adres='%s' ";
		
		sql = String.format(
				sql, 
				user.getKontoId(),
				worker.getImie(),
				worker.getNazwisko(),
				worker.getPesel(),
				worker.getStanowisko(),
				user.getSalary(),
				worker.getPremia(),
				super.sqlDateFormat(worker.getData_zatrudnienia(), false),
				super.sqlDateFormat(worker.getData_zwolnienia(), true),
				worker.getAdres()
			);
		
		sql += "WHERE id_pracownika=" + user.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM pracownicy WHERE id_pracownika=%d", id);
		super.executeUpdate(sql);
	}
	
	public boolean isKontoInUse(int id, int kontoId) {
		String sql = String.format("SELECT * FROM pracownicy WHERE id_pracownika!=%d AND id_konta=%d", id, kontoId);
		return (super.getResultSetSize(sql) != 0);
	}
	
	public boolean isKontoInUse(int kontoId) {
		String sql = String.format("SELECT * FROM pracownicy WHERE id_konta=%d", kontoId);
		return (super.getResultSetSize(sql) != 0);
	}
	
	public RefUser getUserByKonto(int kontoId) {
		String sql = String.format("SELECT * FROM pracownicy WHERE id_konta=%d", kontoId);
		return (RefUser) super.getFirstFromRS(sql);
	}
	
}