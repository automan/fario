/**
 * 
 */
package com.taobao.fario.server.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.taobao.fario.server.db.HibernateSessionFactory;
import com.taobao.fario.server.service.Locationhistory;
import com.taobao.fario.server.service.LocationhistoryId;
import com.taobao.fario.server.service.UserInfo;

/**
 * @author taichan
 * 
 */
@SuppressWarnings("serial")
@WebServlet(name = "LocationHistoryServlet", urlPatterns = { "/addlocation" })
public class LocationHistoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserInfo.class);

		criteria.add(Restrictions.eq("name", "huang0001"));

		UserInfo user = (UserInfo) criteria.list().get(0);

		LocationhistoryId locationid = new LocationhistoryId(user.getId(),
				new Date(), 123.00, 100.33, 12.0, 10);

		Locationhistory location = new Locationhistory(locationid);

		location.setUserInfo(user);

		session.save(location);

		session.getTransaction().commit();
		session.close();

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
		this.doGet(req, resp);
	}
}
