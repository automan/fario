/**
 * 
 */
package com.taobao.fario.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taichan
 * 
 */
public class UserHistory {
	private static UserHistory instance;
	private static Object lockObj = new Object();

	private Map<String, List<LocationInfo>> locations;

	private UserHistory() {
		locations = new HashMap<String, List<LocationInfo>>();
	}

	public static UserHistory getInstance() {
		synchronized (lockObj) {
			if (instance == null) {
				instance = new UserHistory();
			}
			return instance;
		}
	}

	public void add(LocationInfo location) {
		String user = location.getUsername();
		if (!locations.containsKey(user)) {
			locations.put(user, new ArrayList<LocationInfo>());
		}
		List<LocationInfo> locInfo = locations.get(user);
		locInfo.add(location);
		locations.put(user, locInfo);
	}

	public LocationInfo last(LocationInfo location) {
		String user = location.getUsername();
		if (locations.containsKey(user)) {
			List<LocationInfo> userLocation = locations.get(user);
			if (userLocation.size() > 0) {
				return userLocation.get(userLocation.size() - 1);
			}
		}
		return null;
	}

	public int size() {
		return locations.size();
	}

}
