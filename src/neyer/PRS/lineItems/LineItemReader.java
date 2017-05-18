package neyer.PRS.lineItems;

import java.util.ArrayList;

import neyer.PRS.business.LineItems;
import neyer.PRS.dbutils.DBException;

public interface LineItemReader {
	ArrayList<LineItems> getAllLineItems() throws DBException ;
	ArrayList<LineItems> getUsersLineItems(int requestID) throws DBException;
}
