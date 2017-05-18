package neyer.PRS.users;

import neyer.PRS.business.Users;
import neyer.PRS.dbutils.DBException;

public interface UsersWriter {
	void addUser(Users user) throws DBException;
	void updateUser(Users user) throws DBException;
}
