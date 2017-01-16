package entities;

import superclasses.EntityItem;

public class DocSaleItem extends EntityItem {
	private int storeId;
	private String storeFirstName;
	private String storeLastName;
	private int productId;
	private String productName;
	private String productUnit;
	private float quantity;
	private float price;
	
	public DocSaleItem(int id, int storeId, String storeFirstName, String storeLastName, int productId, String productName,	String productUnit, float quantity, float price, float summa) {
		super(id, summa);
		
		this.storeId = storeId;
		this.storeFirstName = storeFirstName;
		this.storeLastName = storeLastName;
		this.productId = productId;
		this.productName = productName;
		this.productUnit = productUnit;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreFirstName() {
		return storeFirstName;
	}

	public void setStoreFirstName(String storeFirstName) {
		this.storeFirstName = storeFirstName;
	}

	public String getStoreLastName() {
		return storeLastName;
	}

	public void setStoreLastName(String storeLastName) {
		this.storeLastName = storeLastName;
	}
	
	public String getStoreName() {
		return storeFirstName + " " + storeLastName;
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

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
    public String toString() {
        return productName + ": " + quantity + " " + productUnit + " * " + price + " = " + super.getSumma();
    }

}