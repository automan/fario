package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taobao.fario.server.db.HibernateSessionFactory;
import com.taobao.fario.server.db.test;
import com.taobao.fario.server.service.User;

@WebServlet(name = "MyServlet", urlPatterns = { "/hello" })
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();

		Session session = HibernateSessionFactory.getSession();
		Transaction beginTransaction = session.beginTransaction();
		User user = new User();
		// user.setName(new String("你好啊".getBytes("UTF-8"), "ISO-8859-1"));
		user.setName("你好啊");
		session.save(user);

		beginTransaction.commit();
		Criteria criteria = session.createCriteria(User.class);
		List<User> userlist = criteria.list();
		session.close();

		out.write("hello, welcome to Fario!!!".getBytes());
		for (User u : userlist) {
			out.write((u.getId() + u.getName() + "\r\n").getBytes("UTF-8"));
		}
		out.flush();
		out.close();
	}
}
