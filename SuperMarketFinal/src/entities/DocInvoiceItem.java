package entities;

import superclasses.EntityItem;

public class DocInvoiceItem extends EntityItem {
	private int productId;
	private String productName;
	private String productUnit;
	private float quantity;
	private float price;
	
	public DocInvoiceItem(int id, int productId, String productName, String productUnit, float quantity, float price, float summa) {
		super(id, summa);
		
		this.productId = productId;
		this.productName = productName;
		this.productUnit = productUnit;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return productName + ": " + quantity + " " + productUnit + " * " + price + " = " + super.getSumma();
    }

}