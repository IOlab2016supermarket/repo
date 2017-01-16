package datamanagers;

import java.sql.SQLException;
import java.util.ArrayList;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefKonto;

public class RefKontoDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		int id = rs.getInt("id");
		String login = rs.getString("login");
		String password = rs.getString("haslo");
		String role = rs.getString("uprawnienia");
		
		return new RefKonto(id, login, password, role);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM konta");
	}

	@Override
	public void addEntity(Entity ent) {
		RefKonto konto = (RefKonto) ent;
		
		String sql = "INSERT INTO konta ("
				+ "login, "
				+ "haslo, "
				+ "uprawnienia"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				konto.getLogin(),
				konto.getPassword(),
				konto.getRole()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefKonto konto = (RefKonto) ent;
		
		String sql = "UPDATE konta SET "
				+ "login='%s', "
				+ "haslo='%s', "
				+ "uprawnienia='%s' ";
		
		sql = String.format(
				sql, 
				konto.getLogin(),
				konto.getPassword(),
				konto.getRole()
			);
		
		sql += "WHERE id=" + konto.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM konta WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
	public boolean isKontoExists(int id, String login) {
		String sql = String.format("SELECT * FROM konta WHERE id!=%d AND login LIKE '%s'", id, login);
		return (super.getResultSetSize(sql) != 0);
	}
	
	public RefKonto getKontoByLoginPassword(String login, String password) {
		String sql = String.format("SELECT * FROM konta WHERE login LIKE '%s' AND haslo LIKE '%s'", login, password);
		return (RefKonto) super.getFirstFromRS(sql);
	}

}