package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocInvoiceItem;

public class DocInvoiceItemDM extends DataManager {
	private int invoiceId;
	
	public DocInvoiceItemDM() {
		this.invoiceId = 0;
	}
	
	public DocInvoiceItemDM(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		int productId = rs.getInt("id_product");
		String productName = super.getString("productName");	// From INNER JOIN
		String productUnit = super.getString("productUnit");	// From INNER JOIN
		float quantity = rs.getFloat("quantity");
		float price = rs.getFloat("price");
		float summa = rs.getFloat("summa");
		
		return new DocInvoiceItem(id, productId, productName, productUnit, quantity, price, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT invoice_table.*, "
				+ "produkty.nazwa AS productName, produkty.unit AS productUnit "
				+ "FROM invoice_table "
				+ "INNER JOIN produkty ON invoice_table.id_product = produkty.id "
				+ "WHERE invoice_table.id_invoice=%d ORDER BY invoice_table.id";
		
		sql = String.format(sql, invoiceId);
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocInvoiceItem invoiceItem = (DocInvoiceItem) ent;
		
		String sql = "INSERT INTO invoice_table ("
				+ "id_invoice, "
				+ "id_product, "
				+ "quantity, "
				+ "price, "
				+ "summa"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				invoiceId,
				invoiceItem.getProductId(),
				invoiceItem.getQuantity(),
				invoiceItem.getPrice(), 
				invoiceItem.getSumma()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocInvoiceItem invoiceItem = (DocInvoiceItem) ent;
		
		String sql = "UPDATE invoice_table SET "
				+ "id_product='%s', "
				+ "quantity='%s' ,"
				+ "price='%s' ,"
				+ "summa='%s' ";
		
		sql = String.format(
				sql, 
				invoiceItem.getProductId(),
				invoiceItem.getQuantity(),
				invoiceItem.getPrice(), 
				invoiceItem.getSumma()
			);
		
		sql += "WHERE id=" + invoiceItem.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM invoice_table WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}