package neyer.PRS.products;

import neyer.PRS.business.Products;
import neyer.PRS.dbutils.DBException;

public interface ProductsWriter {
	void addProduct(Products product) throws DBException;
	void updateProduct(Products product) throws DBException;
}
