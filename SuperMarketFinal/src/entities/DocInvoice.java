package entities;

import java.util.Date;

import superclasses.EntityDoc;

public class DocInvoice extends EntityDoc {
	private int clientId;
	private String clientFirstName;
	private String clientLastName;
	private int storeId;
	private String storeFirstName;
	private String storeLastName;
	
	public DocInvoice(int id, Date date, String number, int clientId, String clientFirstName, String clientLastName, int storeId, String storeFirstName, String storeLastName, float summa) {
		super(id, date, number, summa);
		
		this.clientId = clientId;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.storeId = storeId;
		this.storeFirstName = storeFirstName;
		this.storeLastName = storeLastName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}
	
	public String getClientName() {
		return clientFirstName + " " + clientLastName;
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
	
	@Override
    public String toString() {
        return "<Invoice " + super.toString() + ">";
    }
	
}