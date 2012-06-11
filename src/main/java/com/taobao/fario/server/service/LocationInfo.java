/**
 * 
 */
package com.taobao.fario.server.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;

/**
 * @author taichan
 * 
 */

public class LocationInfo {

	private static final String TAG = "LocationInfo";

	// private String speed;
	// private String bearing;

	private String username;

	private int userid;
	private String time;

	private double latitude;
	private double longitude;
	private double altitude;
	private double precision;
	private int accuracy;

	public String getUsername() {
		return username;
	}

	/**
	 * @param time
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param accuracy
	 * @param username
	 */
	public LocationInfo(String time, double latitude, double longitude,
			double altitude, int accuracy, String username) {
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public static String getTag() {
		return TAG;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		if (this.time != null) {
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = new Date(Long.parseLong(this.time));
			sb.append("Time: ").append(timeFormat.format(date)).append("\n");
		} else {
			sb.append("Time: ").append(this.time).append("\n");
		}
		sb.append("Latitude: ").append(this.latitude).append("\n")
				.append("Longitude: ").append(this.longitude).append("\n")
				.append("Altitude: ").append(this.altitude).append("\n")
				.append("Accuracy: ").append(this.accuracy).append("\n")
				.append("Username: ").append(this.username).append("\n");

		return sb.toString();
	}
}
