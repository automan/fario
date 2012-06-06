/**
 * 
 */
package com.taobao.fario.server.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taobao.fario.server.service.User;

/**
 * @author taichan
 * 
 */
public class test {
	public static void main(String[] args) throws Exception {

		// Connection connection = getConnection();

		Session session = HibernateSessionFactory.getSession();

		Transaction beginTransaction = session.beginTransaction();
		User user = new User();
		user.setName("你好啊");
		session.save(user);

		beginTransaction.commit();

		Criteria criteria = session.createCriteria(User.class);
		List<User> userlist = criteria.list();

		session.close();

		System.out.println(userlist.toString());
		
		// Statement stmt = connection.createStatement();
		// stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		// stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		// stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		// ResultSet rs = stmt.executeQuery("SELECT name FROM \"user\"");
		// StringBuilder sb = new StringBuilder();
		//
		// while (rs.next()) {
		// sb.append("Read from DB: " + rs.getString("name")).append("\r\n");
		// }
		// System.out.println(sb.toString());
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
