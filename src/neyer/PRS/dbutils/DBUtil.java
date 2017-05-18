package neyer.PRS.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static Connection connection;

	private DBUtil() {
	}

	public static synchronized Connection getConnection() throws DBException {
		if (connection != null) {
			return connection;
		} else {
			try {
				// set the db url, username, and password
				String url = "jdbc:mysql://localhost:3306/prs";
				String username = "prs_user";
				String password = "sesame";

				// get and return connection
				connection = DriverManager.getConnection(url, username, password);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e);
			}
		}
	}

	public static synchronized void closeConnection() throws DBException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException(e);
			} finally {
				connection = null;
			}
		}
	}
}