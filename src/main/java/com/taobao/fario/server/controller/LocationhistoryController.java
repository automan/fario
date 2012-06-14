package com.taobao.fario.server.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.taobao.fario.server.db.HibernateSessionFactory;
import com.taobao.fario.server.service.ShopInfo;

public class LocationhistoryController {

	/**
	 * 获取距离当前位置最近的一家商店的信息
	 * 
	 * @param latitude
	 *            当前位置经度
	 * @param longitude
	 *            当前位置维度
	 * @param radius
	 *            查找半径
	 * @return 满足条件的、离当前位置直线距离最短的shop
	 */
	public static ShopInfo getNearbyShopInfo(double latitude, double longitude,
			double radius) {

		Session session = HibernateSessionFactory.getSession();

		String hql = "from ShopInfo as shop where shop.latitude between :minlatitude and :maxlatitude and shop.longitude between :minlongitude and :maxlongitude";

		String hql1 = "from ShopInfo as shop where shop.latitude <= :latitude order by shop.latitude desc";
		
		String hql2 = "from ShopInfo order by ACOS(SIN((:latitude * 3.1415) / 180 ) *SIN((latitude * 3.1415) / 180 ) +COS((:latitude * 3.1415) / 180 ) * COS((latitude * 3.1415) / 180 ) *COS((:longitude * 3.1415) / 180 - (longitude * 3.1415) / 180 ) ) * 6380 asc limit 1";
		Query query = session.createQuery(hql2);
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setMaxResults(1); 
		/*
		Query query = session.createQuery(hql);
		query.setDouble("minlatitude", latitude - radius);
		query.setDouble("maxlatitude", latitude + radius);

		query.setDouble("minlongitude", longitude - radius);
		query.setDouble("maxlongitude", longitude + radius);

		@SuppressWarnings("unchecked")
		List<ShopInfo> shoplist = query.list();
		*/
		List<ShopInfo> shoplist = query.list();
		
		session.close();
		return shoplist.get(0);
	}

	private static ShopInfo getNearestShop(List<ShopInfo> shoplist,
			double latitude, double longitude) {

		double z = 10000d;
		int index = 0;

		for (int i = 0; i < shoplist.size(); i++) {

			ShopInfo shop = shoplist.get(i);
			double x = Math.abs(shop.getLatitude() - latitude);
			double y = Math.abs(shop.getLongitude() - longitude);

			if (Math.sqrt(x * x + y * y) < z) {
				z = Math.sqrt(x * x + y * y);
				index = i;
			}
		}
		return shoplist.get(index);
	}
}
