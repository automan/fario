package com.taobao.fario.server.service;

// Generated 2012-6-12 17:01:23 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * LocationhistoryId generated by hbm2java
 */
public class LocationhistoryId implements java.io.Serializable {

	private Integer userid;
	private Date time;
	private Double latitude;
	private Double longitude;
	private Double altitude;
	private Integer accuracy;

	public LocationhistoryId() {
	}

	public LocationhistoryId(Integer userid, Date time, Double latitude,
			Double longitude, Double altitude, Integer accuracy) {
		this.userid = userid;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Integer getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LocationhistoryId))
			return false;
		LocationhistoryId castOther = (LocationhistoryId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getLatitude() == castOther.getLatitude()) || (this
						.getLatitude() != null
						&& castOther.getLatitude() != null && this
						.getLatitude().equals(castOther.getLatitude())))
				&& ((this.getLongitude() == castOther.getLongitude()) || (this
						.getLongitude() != null
						&& castOther.getLongitude() != null && this
						.getLongitude().equals(castOther.getLongitude())))
				&& ((this.getAltitude() == castOther.getAltitude()) || (this
						.getAltitude() != null
						&& castOther.getAltitude() != null && this
						.getAltitude().equals(castOther.getAltitude())))
				&& ((this.getAccuracy() == castOther.getAccuracy()) || (this
						.getAccuracy() != null
						&& castOther.getAccuracy() != null && this
						.getAccuracy().equals(castOther.getAccuracy())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getLatitude() == null ? 0 : this.getLatitude().hashCode());
		result = 37 * result
				+ (getLongitude() == null ? 0 : this.getLongitude().hashCode());
		result = 37 * result
				+ (getAltitude() == null ? 0 : this.getAltitude().hashCode());
		result = 37 * result
				+ (getAccuracy() == null ? 0 : this.getAccuracy().hashCode());
		return result;
	}

}