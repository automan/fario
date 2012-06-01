/**
 * 
 */
package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.fario.server.service.LocationInfo;
import com.taobao.fario.server.service.ShopInfo;
import com.taobao.fario.server.service.UserHistory;

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
		String time = req.getParameter("time");
		String latitude = req.getParameter("la");
		String longitude = req.getParameter("lo");
		String altitude = req.getParameter("al");
		String accuracy = req.getParameter("acc");
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
		String latitude = req.getParameter("la");
		String longitude = req.getParameter("lo");
		String altitude = req.getParameter("al");
		String accuracy = req.getParameter("acc");
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
