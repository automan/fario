/**
 * 
 */
package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taobao.fario.server.db.HibernateSessionFactory;
import com.taobao.fario.server.service.LocationInfo;
import com.taobao.fario.server.service.ShopInfo;
import com.taobao.fario.server.service.User;
import com.taobao.fario.server.service.UserHistory;

/**
 * @author taichan
 * 
 */
@WebServlet(name = "RegisterUserServlet", urlPatterns = { "/registerUser" })
public class RegisterUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		PrintWriter writer = resp.getWriter();

		Session session = HibernateSessionFactory.getSession();
		Transaction beginTransaction = session.beginTransaction();
		User user = new User();

		user.setName(name);
		user.setPassword("123456");
		user.setNickName("nickName");
		user.setSex("男");
		user.setTelephone("13666666666");
		Criteria criteria = session.createCriteria(User.class);
		List<User> userlist = criteria.list();

		boolean isExist = false;
		for (User u : userlist) {
			if (u.getName().endsWith(name)) {
				isExist = true;
				writer.write("-1 user exist");
				break;
			}
		}
		if (!isExist) {
			session.save(user);
			writer.write("1 user added");
			beginTransaction.commit();
		}
		session.close();
		writer.flush();
		writer.close();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String time = req.getParameter("time");
		double latitude = Double.parseDouble(req.getParameter("la"));
		double longitude = Double.parseDouble(req.getParameter("lo"));
		double altitude = Double.parseDouble(req.getParameter("al"));
		int accuracy = Integer.parseInt(req.getParameter("acc"));
		String username = req.getParameter("uid");
		String key = req.getParameter("key");

		LocationInfo locationInfo = new LocationInfo(time, latitude, longitude,
				altitude, accuracy, username);

		UserHistory.getInstance().add(locationInfo);
		ShopInfo shopInfo = new ShopInfo(key);
		String result = shopInfo.toJson();

		PrintWriter writer = resp.getWriter();
		if (result != null) {
			writer.write(result);
		} else {
			writer.write("");
		}
		writer.close();
	}
}
