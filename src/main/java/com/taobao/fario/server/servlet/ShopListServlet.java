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
import com.taobao.fario.server.service.UserHistory;

/**
 * @author taichan
 * 
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/shoplist" })
public class ShopListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletOutputStream out = resp.getOutputStream();

		Session session = HibernateSessionFactory.getSession();

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
