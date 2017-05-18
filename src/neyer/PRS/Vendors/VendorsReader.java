package neyer.PRS.Vendors;

import java.util.ArrayList;

import neyer.PRS.business.Vendors;
import neyer.PRS.dbutils.DBException;

public interface VendorsReader {
	ArrayList<Vendors> getAllVendors() throws DBException;
	Vendors getVendor(String code) throws DBException;
	int getVendorID(String code) throws DBException;
}
