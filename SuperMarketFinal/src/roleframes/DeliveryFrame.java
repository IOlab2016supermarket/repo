package roleframes;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.Common;
import superclasses.RoleFrame;

public class DeliveryFrame extends RoleFrame {
	private static final long serialVersionUID = 1L;
  private static final String CLIENTS = "CLIENTS";
  private static final String SELLERS = "SELLERS";
  private static final String STORES = "STORES";
  private static final String PRODUCTS = "PRODUCTS";
  private static final String INVOICES = "INVOICES";
  private static final String DELIVERIES = "DELIVERIES";
  private static final String SALARIES = "SALARIES";
  private static final String ACCOUNTS = "ACCOUNTS";
  private static final String VIEW_USERS = "VIEW_USERS";
  private static final String VIEW_INVOICES = "VIEW_INVOICES";
  
	public DeliveryFrame() {
		showTitle("Delivery frame / ");
		
		JMenu mnReferences = new JMenu("References");
    menuBar.add(mnReferences);
    
    JMenuItem mntmRefClients = new JMenuItem("Clients list");
    mntmRefClients.setActionCommand(CLIENTS);
    mntmRefClients.addActionListener(this);
    mnReferences.add(mntmRefClients);
    
    JMenuItem mntmRefSellers = new JMenuItem("Sellers list");
    mntmRefSellers.setActionCommand(SELLERS);
    mntmRefSellers.addActionListener(this);
    mnReferences.add(mntmRefSellers);
    
    JMenuItem mntmRefStores = new JMenuItem("Stores list");
    mntmRefStores.setActionCommand(STORES);
    mntmRefStores.addActionListener(this);
    mnReferences.add(mntmRefStores);
    
    mnReferences.addSeparator();
    
    JMenuItem mntmRefProducts = new JMenuItem("Products list");
    mntmRefProducts.setActionCommand(PRODUCTS);
    mntmRefProducts.addActionListener(this);
    mnReferences.add(mntmRefProducts);
    
    JMenu mnDocs  = new JMenu("Documents");
    menuBar.add(mnDocs);
    
    JMenuItem mntmDocInvoices = new JMenuItem("Invoices");
    mntmDocInvoices.setActionCommand(INVOICES);
    mntmDocInvoices.addActionListener(this);
    mnDocs.add(mntmDocInvoices);
    
    JMenuItem mntmDocSales = new JMenuItem("Deliveries");
    mntmDocSales.setActionCommand(DELIVERIES);
    mntmDocSales.addActionListener(this);
    mnDocs.add(mntmDocSales);
    
    mnDocs.addSeparator();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		switch (e.getActionCommand()) {
    case CLIENTS:
      super.showListPanel(new listpanels.RefClientLP());
      break;
    case SELLERS:
      super.showListPanel(new listpanels.RefSellerLP());
      break;
    case STORES:
      super.showListPanel(new listpanels.RefStoreLP());
      break;
    case PRODUCTS:
      super.showListPanel(new listpanels.RefProductLP());
      break;
    case INVOICES:
      super.showListPanel(new listpanels.DocInvoiceLP());
      break;
    case DELIVERIES:
      super.showListPanel(new listpanels.DocSaleLP());
      break;
    case SALARIES:
      super.showListPanel(new listpanels.DocSalaryLP());
      break;
    case ACCOUNTS:
      Common.makeFrame(new listpanels.RefKontoLP(300), "Work with accounts");
      break;
    case VIEW_USERS:
      listpanels.RefUserLP ulp = new listpanels.RefUserLP();
      ulp.setReadOnly(true);
      super.showListPanel(ulp);
      break;
    case VIEW_INVOICES:
      listpanels.DocInvoiceLP dlp = new listpanels.DocInvoiceLP();
      dlp.setReadOnly(true);
      super.showListPanel(dlp);
      break;
    default:
      break;
    }
	}
}
