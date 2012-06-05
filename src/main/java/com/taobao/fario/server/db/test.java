/**
 * 
 */
package com.taobao.fario.server.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * @author taichan
 * 
 */
public class test {
	public static String testdb() throws Exception {

		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		StringBuilder sb = new StringBuilder();

		while (rs.next()) {
			sb.append("Read from DB: " + rs.getTimestamp("tick"));
		}
		return sb.toString();
	}

	private static Connection getConnection() throws URISyntaxException,
			SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
