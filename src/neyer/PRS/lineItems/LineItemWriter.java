package neyer.PRS.lineItems;

import neyer.PRS.dbutils.DBException;
import neyer.PRS.business.LineItems;

public interface LineItemWriter {
	void addLineItem(LineItems lineItem) throws DBException;
}