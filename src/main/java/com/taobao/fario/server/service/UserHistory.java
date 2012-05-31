/**
 * 
 */
package com.taobao.fario.server.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taichan
 * 
 */
public class UserHistory {
	private static UserHistory instance;
	private static Object lockObj = new Object();

	private List<LocationInfo> locations;

	private UserHistory() {
		locations = new ArrayList<LocationInfo>();
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
		locations.add(location);
	}

	public LocationInfo last() {
		if (locations.size() > 0) {
			return locations.get(locations.size() - 1);
		} else {
			return null;
		}
	}

	public int size() {
		return locations.size();
	}

}
