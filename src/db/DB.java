package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {

		if (conn == null) {
			try {
				Properties prop = DB.loadProperties();
				String dburl = prop.getProperty("dburl");
				conn = DriverManager.getConnection(dburl);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void closeStatment(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try {
			FileInputStream fs = new FileInputStream("db.properties");
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
		} catch (FileNotFoundException e) {
			throw new DbException(e.getMessage());
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

}
