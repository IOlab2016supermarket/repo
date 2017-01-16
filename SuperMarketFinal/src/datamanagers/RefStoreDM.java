package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefStore;
import magazyn.Magazynier;

public class RefStoreDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idMagazynier");
		String imie = rs.getString("imie");
		String nazwisko = rs.getString("nazwisko");
		
		return new RefStore(id, imie, nazwisko);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM magazynier");
	}

	@Override
	public void addEntity(Entity ent) {
		RefStore store = (RefStore) ent;
		Magazynier magazynier = store.getMagazynier();
		
		String sql = "INSERT INTO magazynier ("
				+ "imie, "
				+ "nazwisko"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				magazynier.pobierzImie(),
				magazynier.pobierzNazwisko()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefStore store = (RefStore) ent;
		Magazynier magazynier = store.getMagazynier();
		
		String sql = "UPDATE magazynier SET "
				+ "imie='%s', "
				+ "nazwisko='%s' ";
		
		sql = String.format(
				sql, 
				magazynier.pobierzImie(),
				magazynier.pobierzNazwisko()
			);
		
		sql += "WHERE idMagazynier=" + store.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM magazynier WHERE idMagazynier=%d", id);
		super.executeUpdate(sql);
	}
	
}