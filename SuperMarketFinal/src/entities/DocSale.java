package entities;

import java.util.Date;

import superclasses.EntityDoc;

public class DocSale extends EntityDoc {
	private int sellerId;
	private String sellerFirstName;
	private String sellerLastName;
	
	public DocSale(int id, Date date, String number, int sellerId, String sellerFirstName, String sellerLastName, float summa) {
		super(id, date, number, summa);
		
		this.sellerId = sellerId;
		this.sellerFirstName = sellerFirstName;
		this.sellerLastName = sellerLastName;
	}
	
	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerFirstName() {
		return sellerFirstName;
	}

	public void setSellerFirstName(String sellerFirstName) {
		this.sellerFirstName = sellerFirstName;
	}

	public String getSellerLastName() {
		return sellerLastName;
	}

	public void setSellerLastName(String sellerLastName) {
		this.sellerLastName = sellerLastName;
	}
	
	public String getSellerName() {
		return sellerFirstName + " " + sellerLastName;
	}

	@Override
    public String toString() {
        return "<Sale" + super.toString() + ">";
    }
	
}