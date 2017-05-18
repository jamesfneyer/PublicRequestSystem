package neyer.PRS.products;

import java.util.ArrayList;
import neyer.PRS.dbutils.DBException;
import neyer.PRS.business.Products;

public interface ProductsReader {
	ArrayList<Products> getAllProducts(int vendorID) throws DBException;
	Products getProduct(String partNumber, int vID) throws DBException;
}
