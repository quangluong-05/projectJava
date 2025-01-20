package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBCUtil {
	private static final String url="jdbc:sqlserver://Quang;databaseName=estatebasic;encrypt=false;";
	private static final String username= "zane";
	private static final String password = "zanetinodz";
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
