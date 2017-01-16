package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefClient;
import sprzedaz.Klient;

public class RefClientDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idKlient");
		int iloscPunktow = rs.getInt("iloscPunktow");
		String imie = rs.getString("imie");
		String nazwisko = rs.getString("nazwisko");
		String kodPocztowy = rs.getString("kodPocztowy");
		
		return new RefClient(id, imie, nazwisko, iloscPunktow, kodPocztowy);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM klient");
	}

	@Override
	public void addEntity(Entity ent) {
		RefClient client = (RefClient) ent;
		Klient klient = client.getKlient();
		
		String sql = "INSERT INTO klient ("
				+ "iloscPunktow, "
				+ "imie, "
				+ "nazwisko, "
				+ "kodPocztowy"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				klient.getIloscPunktow(),
				klient.getImie(),
				klient.getNazwisko(),
				klient.getKodPocztowy()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefClient client = (RefClient) ent;
		Klient klient = client.getKlient();
		
		String sql = "UPDATE klient SET "
				+ "iloscPunktow='%s', "
				+ "imie='%s', "
				+ "nazwisko='%s', "
				+ "kodPocztowy='%s' ";
		
		sql = String.format(
				sql, 
				klient.getIloscPunktow(),
				klient.getImie(),
				klient.getNazwisko(),
				klient.getKodPocztowy()
			);
		
		sql += "WHERE idKlient=" + client.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM klient WHERE idKlient=%d", id);
		super.executeUpdate(sql);
	}
	
}