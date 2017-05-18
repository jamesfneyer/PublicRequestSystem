package neyer.PRS.requests;

import neyer.PRS.business.Requests;
import neyer.PRS.business.Vendors;
import neyer.PRS.dbutils.DBException;

public interface RequestsWriter {
	void addRequest(Requests request) throws DBException;
	void updateRequest(Requests request) throws DBException;
}
