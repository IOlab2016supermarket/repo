package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocSale;
import main.Common;

public class DocSaleDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		Date date = super.getDate("date");
		String number = rs.getString("number");
		int sellerId = rs.getInt("id_seller");
		String sellerFirstName = super.getString("sellerFirstName");	// From INNER JOIN
		String sellerLastName = super.getString("sellerLastName");		// From INNER JOIN
		float summa = Common.Round(super.getFloat("summa"), 2);			// From INNER JOIN
		
		return new DocSale(id, date, number, sellerId, sellerFirstName, sellerLastName, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT sale.*, "
				+ "sprzedawca.imie AS sellerFirstName, sprzedawca.nazwisko AS sellerLastName, "
				+ "SUM(sale_table.summa) AS summa FROM sale "
				+ "INNER JOIN sprzedawca ON sale.id_seller = sprzedawca.idSprzedawca "
				+ "LEFT JOIN sale_table ON sale.id = sale_table.id_sale "
				+ "GROUP BY sale.id";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocSale sale = (DocSale) ent;
		
		String sql = "INSERT INTO sale ("
				+ "date, "
				+ "number, "
				+ "id_seller"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				super.sqlDateFormat(sale.getDate(), false),
				sale.getNumber(),
				sale.getSellerId()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocSale sale = (DocSale) ent;
		
		String sql = "UPDATE sale SET "
				+ "date=%s, "	// without <'>
				+ "number='%s', "
				+ "id_seller='%s' ";
		
		sql = String.format(
				sql, 
				super.sqlDateFormat(sale.getDate(), false),
				sale.getNumber(),
				sale.getSellerId()
			);
		
		sql += "WHERE id=" + sale.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM sale WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}