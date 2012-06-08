/**
 * 
 */
package com.taobao.fario.server.service;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.taobao.fario.server.util.ToolUtil;

/**
 * @author taichan
 * 
 */
public class ShopInfo {
	private String shopName;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String address;
	private String telephone;
	// 来源
	private String fetchfrom;

	// 分类
	private String category;

	public String getFetchfrom() {
		return fetchfrom;
	}

	public void setFetchfrom(String fetchfrom) {
		this.fetchfrom = fetchfrom;
	}

	public String getFetchBy() {
		return fetchBy;
	}

	public void setFetchBy(String fetchBy) {
		this.fetchBy = fetchBy;
	}

	// 爬网站时用什么关键字
	private String fetchBy;

	private Double latitude;
	private Double longitude;
	private Double altitude;
	private Integer id;

	private String gbkToUtf8(String value) {
		try {
			return new String(value.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ShopInfo() {
	}

	public ShopInfo(String key) {
		shopName = "加油站 (康桥中学南)";
		address = "拱康路 - 4 公里 北";
		telephone = "0571-80801133";
		fetchfrom = "mapbar";
		category = "汽车服务";
		fetchBy = "加油站";

		latitude = 30.405;
		longitude = 120.26776;
		altitude = 0.0;
	}

	public String toJson() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
}
