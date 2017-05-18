package neyer.PRS.javautil;

import neyer.PRS.Vendors.VendorsDAO;
import neyer.PRS.Vendors.VendorsDB;
import neyer.PRS.lineItems.LineItemDAO;
import neyer.PRS.lineItems.LineItemDB;
import neyer.PRS.products.ProductsDAO;
import neyer.PRS.products.ProductsDB;
import neyer.PRS.requests.RequestsDAO;
import neyer.PRS.requests.RequestsDB;
import neyer.PRS.users.UsersDAO;
import neyer.PRS.users.UsersDB;

public class DAOFactory {

	public static LineItemDAO getLineItemDAO() {
		LineItemDAO mc = new LineItemDB();
		return mc;
	}

	public static ProductsDAO getProductsDAO() {
		ProductsDAO mc = new ProductsDB();
		return mc;
	}

	public static RequestsDAO getRequestsDAO() {
		RequestsDAO mc = new RequestsDB();
		return mc;
	}

	public static UsersDAO getUsersDAO() {
		UsersDAO mc = new UsersDB();
		return mc;
	}

	public static VendorsDAO getVendorsDAO() {
		VendorsDAO mc = new VendorsDB();
		return mc;
	}
}
