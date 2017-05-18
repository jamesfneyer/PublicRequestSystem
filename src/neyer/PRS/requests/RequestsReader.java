package neyer.PRS.requests;

import java.util.ArrayList;
import neyer.PRS.business.Requests;
import neyer.PRS.dbutils.DBException;

public interface RequestsReader {
	ArrayList<Requests> getAllRequests() throws DBException;
	ArrayList<Requests> getUserRequests(int userID) throws DBException;
	int getRequestID(String description) throws DBException;
}
