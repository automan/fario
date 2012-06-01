/**
 * 
 */
package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.fario.server.service.LocationInfo;
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

		String time = req.getParameter("time");
		String latitude = req.getParameter("la");
		String longitude = req.getParameter("lo");
		String altitude = req.getParameter("al");
		String accuracy = req.getParameter("acc");
		String username = req.getParameter("uid");

		if (username != null) {
			username = URLDecoder.decode(username, "UTF-8");
		}

		LocationInfo locationInfo = new LocationInfo(time, latitude, longitude,
				altitude, accuracy, username);

		LocationInfo last = UserHistory.getInstance().last(locationInfo);
		UserHistory.getInstance().add(locationInfo);

		if (last != null) {
			resp.getWriter().write(last.toString());
		} else {
			resp.getWriter().write("first time visit!");
		}

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

		LocationInfo locationInfo = new LocationInfo(time, latitude, longitude,
				altitude, accuracy, username);

		LocationInfo last = UserHistory.getInstance().last(locationInfo);
		UserHistory.getInstance().add(locationInfo);

		if (last != null) {
			resp.getWriter().write(last.toString());
		} else {
			resp.getWriter().write("first time visit!");
		}
	}
}
