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
@WebServlet(name = "AddShopServlet", urlPatterns = { "/addShop" })
public class AddShopServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletOutputStream out = resp.getOutputStream();

		String shopName = req.getParameter("shopName");
		String address = req.getParameter("address");
		String telephone = req.getParameter("telephone");
		String fetchfrom = req.getParameter("fetchfrom");
		String category = req.getParameter("category");
		String fetchBy = req.getParameter("fetchBy");

		Double latitude = Double.parseDouble(req.getParameter("latitude"));
		Double longitude = Double.parseDouble(req.getParameter("longitude"));
		Double altitude = Double.parseDouble(req.getParameter("altitude"));

		Session session = HibernateSessionFactory.getSession();
		Transaction beginTransaction = session.beginTransaction();

		ShopInfo shop = new ShopInfo("key");

		session.save(shop);
		beginTransaction.commit();

		Criteria criteria = session.createCriteria(ShopInfo.class);
		List<ShopInfo> shoplist = criteria.list();

		for (ShopInfo s : shoplist) {
			out.write((s.toJson() + "\r\n").getBytes("UTF-8"));
		}
		session.close();

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
		Double latitude = Double.parseDouble(req.getParameter("la"));
		Double longitude = Double.parseDouble(req.getParameter("lo"));
		Double altitude = Double.parseDouble(req.getParameter("al"));
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
