/**
 * 
 */
package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.taobao.fario.server.controller.LocationhistoryController;
import com.taobao.fario.server.db.HibernateSessionFactory;
import com.taobao.fario.server.service.LocationInfo;
import com.taobao.fario.server.service.Locationhistory;
import com.taobao.fario.server.service.LocationhistoryId;
import com.taobao.fario.server.service.ShopInfo;
import com.taobao.fario.server.service.UserHistory;
import com.taobao.fario.server.service.UserInfo;

/**
 * @author taichan
 * 
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date time = new Date();
		try {
			time = df.parse(req.getParameter("time"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double latitude = Double.parseDouble(req.getParameter("la"));
		double longitude = Double.parseDouble(req.getParameter("lo"));
		double altitude = Double.parseDouble(req.getParameter("al"));
		double d = Double.parseDouble(req.getParameter("acc"));
		int accuracy = (int) d;

		int userid = Integer.parseInt(req.getParameter("uid"));
		String key = req.getParameter("key");

		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserInfo.class);

		UserInfo user = (UserInfo) criteria.list().get(0);

		LocationhistoryId locationid = new LocationhistoryId(user.getId(),
				time, latitude, longitude, altitude, accuracy);

		Locationhistory location = new Locationhistory(locationid);

		location.setUserInfo(user);
		session.save(location);
		session.getTransaction().commit();
		session.close();

		ShopInfo shop = LocationhistoryController.getNearbyShopInfo(latitude,
				longitude, 0.01);

		ServletOutputStream out = resp.getOutputStream();

		out.write(shop.toJson().getBytes("UTF-8"));

		out.flush();
		out.close();
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

		Session session = HibernateSessionFactory.getSession();

		Criteria criteria = session.createCriteria(ShopInfo.class);
		criteria.setMaxResults(1);
		List<ShopInfo> shoplist = criteria.list();

		ServletOutputStream out = resp.getOutputStream();
		for (ShopInfo s : shoplist) {
			out.write((s.toJson() + "\r\n").getBytes("UTF-8"));
		}
		session.close();

		out.flush();
		out.close();
	}
}
