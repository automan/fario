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
	public static void main(String[] args) throws Exception {

		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT name FROM \"user\"");
		while (rs.next()) {
			System.out.println("Read from DB: " + rs.getString("name"));
		}
	}

	private static Connection getConnection() throws URISyntaxException,
			SQLException {
		URI dbUri = new URI(
				"postgres://postgres:lee@localhost:5432/fario");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
