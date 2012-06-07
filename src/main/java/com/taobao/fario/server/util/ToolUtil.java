/**
 * 
 */
package com.taobao.fario.server.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author taichan
 * 
 */
public class ToolUtil {

	public static String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
