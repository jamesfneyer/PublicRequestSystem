package neyer.PRS.users;

import java.util.ArrayList;
import neyer.PRS.business.Users;
import neyer.PRS.dbutils.DBException;

public interface UsersReader {
	ArrayList<Users> getAllUsers() throws DBException;
	Users getUser(String username) throws DBException;
	int getUserID(String username) throws DBException;
	String getUsersFullname(int userID);
}
