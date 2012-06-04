/**
 * 
 */
package com.taobao.fario.server.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.taobao.fario.server.util.ToolUtil;

/**
 * @author taichan
 * 
 */
public class ShopInfo {
	private String shopName;
	private String address;
	private String telephone;
	// 来源
	private String getFrom;
	// 分类
	private String category;

	// 爬网站时用什么关键字
	private String getBy;

	private String latitude;
	private String longitude;
	private String altitude;

	private String gbkToUtf8(String value) {
		try {
			return new String(value.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ShopInfo(String key) {
		shopName = gbkToUtf8("加油站 (康桥中学南)");
		address = gbkToUtf8("拱康路 - 4 公里 北");
		telephone = "0571-80801133";
		getFrom = "mapbar";
		category = gbkToUtf8("汽车服务");
		getBy = gbkToUtf8("加油站");

		latitude = "30.405";
		longitude = "120.26776";
		altitude = "0.0";
	}

	public String toJson() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("shopName", ToolUtil.encode(shopName));
			jsonObject.put("address", ToolUtil.encode(address));
			jsonObject.put("telephone", ToolUtil.encode(telephone));
			jsonObject.put("getFrom", getFrom);
			jsonObject.put("category", ToolUtil.encode(category));
			jsonObject.put("getBy", ToolUtil.encode(getBy));

			jsonObject.put("latitude", latitude);
			jsonObject.put("longitude", longitude);
			jsonObject.put("altitude", altitude);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject.toString();
	}
}
