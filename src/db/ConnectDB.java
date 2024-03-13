package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	private Connection connection = null;
	private static ConnectDB connectDB;

	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "persistens";
	private static final String serverAddress = "localhost";
	// private static final String serverAddress = "192.168.56.2";
	private static final int serverPort = 1433;
	private static final String userName = "sa";
	private static final String password = "secret";

	private ConnectDB() {
		String connectionString = String.format(
				"jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false", serverAddress, serverPort,
				dbName, userName, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + dbName + "@" + serverAddress + ":" + serverPort + " as user " + userName + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - password.length()) + "....");
			e.printStackTrace();
		}
	}

	public static ConnectDB getInstance() {
		if (connectDB == null) {
			connectDB = new ConnectDB();
		}
		return connectDB;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
