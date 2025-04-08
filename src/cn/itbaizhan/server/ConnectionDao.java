package cn.itbaizhan.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Database connection */
public class ConnectionDao {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("");
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver loading failed");
		}
		try {
			String url = "jdbc:mysql://localhost:3306/test"
					+ "?user=root"
					+ "&password=123456"
					+ "&useUnicode=true"
					+ "&characterEncoding=UTF-8"
					+ "&serverTimezone=UTC"
					+ "&allowPublicKeyRetrieval=true"
					+ "&useSSL=false";
			conn = DriverManager.getConnection(url);
			System.out.print("Connection successful");
			return conn;
		} catch (SQLException ex) {
			System.out.println("Connection failed: " + ex);
			return null;
		}
	}
}
