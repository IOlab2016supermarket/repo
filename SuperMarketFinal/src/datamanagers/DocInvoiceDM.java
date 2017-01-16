package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocInvoice;
import main.Common;

public class DocInvoiceDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		Date date = super.getDate("date");
		String number = rs.getString("number");
		int clientId = rs.getInt("id_client");
		String clientFirstName = super.getString("clientFirstName");	// From INNER JOIN
		String clientLastName = super.getString("clientLastName");		// From INNER JOIN
		int storeId = rs.getInt("id_store");
		String storeFirstName = super.getString("storeFirstName");		// From INNER JOIN
		String storeLastName = super.getString("storeLastName");		// From INNER JOIN
		float summa = Common.Round(super.getFloat("summa"), 2);			// From INNER JOIN
		
		return new DocInvoice(id, date, number, clientId, clientFirstName, clientLastName, storeId, storeFirstName, storeLastName, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT invoice.*, "
				+ "klient.imie AS clientFirstName, klient.nazwisko AS clientLastName, "
				+ "magazynier.imie AS storeFirstName, magazynier.nazwisko AS storeLastName, "
				+ "SUM(invoice_table.summa) AS summa FROM invoice "
				+ "INNER JOIN klient ON invoice.id_client = klient.idKlient "
				+ "INNER JOIN magazynier ON invoice.id_store = magazynier.idMagazynier "
				+ "LEFT JOIN invoice_table ON invoice.id = invoice_table.id_invoice "
				+ "GROUP BY invoice.id";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocInvoice invoice = (DocInvoice) ent;
		
		String sql = "INSERT INTO invoice ("
				+ "date, "
				+ "number, "
				+ "id_client, "
				+ "id_store"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				super.sqlDateFormat(invoice.getDate(), false),
				invoice.getNumber(),
				invoice.getClientId(),
				invoice.getStoreId()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocInvoice invoice = (DocInvoice) ent;
		
		String sql = "UPDATE invoice SET "
				+ "date=%s, "	// without <'>
				+ "number='%s', "
				+ "id_client='%s', "
				+ "id_store='%s' ";
		
		sql = String.format(
				sql, 
				super.sqlDateFormat(invoice.getDate(), false),
				invoice.getNumber(),
				invoice.getClientId(),
				invoice.getStoreId()
			);
		
		sql += "WHERE id=" + invoice.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM invoice WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}