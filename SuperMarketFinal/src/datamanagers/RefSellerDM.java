package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefSeller;
import sprzedaz.Sprzedawca;

public class RefSellerDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idSprzedawca");
		String imie = rs.getString("imie");
		String nazwisko = rs.getString("nazwisko");
		
		return new RefSeller(id, imie, nazwisko);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM sprzedawca");
	}

	@Override
	public void addEntity(Entity ent) {
		RefSeller seller = (RefSeller) ent;
		Sprzedawca sprzedawca = seller.getSprzedawca();
		
		String sql = "INSERT INTO sprzedawca ("
				+ "imie, "
				+ "nazwisko"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				sprzedawca.getImie(),
				sprzedawca.getNazwisko()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefSeller seller = (RefSeller) ent;
		Sprzedawca sprzedawca = seller.getSprzedawca();
		
		String sql = "UPDATE sprzedawca SET "
				+ "imie='%s', "
				+ "nazwisko='%s' ";
		
		sql = String.format(
				sql, 
				sprzedawca.getImie(),
				sprzedawca.getNazwisko()
			);
		
		sql += "WHERE idSprzedawca=" + seller.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM sprzedawca WHERE idSprzedawca=%d", id);
		super.executeUpdate(sql);
	}
	
}