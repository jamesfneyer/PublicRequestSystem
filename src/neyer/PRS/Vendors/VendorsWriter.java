package neyer.PRS.Vendors;

import neyer.PRS.business.Vendors;
import neyer.PRS.dbutils.DBException;

public interface VendorsWriter {
	void addVendor(Vendors vendor) throws DBException;
	void updateVendor(Vendors vendor) throws DBException;
}
