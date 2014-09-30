package com.eric.rb.http.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;

import com.eric.rb.log.LOG;

/**
 * @FileName: EHttpCookie.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public class EHttpCookie {
	private final static Map<String, String> cookies = new HashMap<String, String>();

	private final static String COOKIE_SEPA = "=";
	private final static String COOKIE_END = ";";

	/**
	 * get all　cookies String
	 * @return key=value;...
	 */
	public static String getCookies() {
		StringBuffer sb = new StringBuffer();
		for(String cookieKey : cookies.keySet()){
			sb.append(cookieKey)
				.append(COOKIE_SEPA)
				.append(cookies.get(cookieKey))
				.append(COOKIE_END);
		}
		return sb.toString();
	}
	
	public static String getCookie(String key){
		return cookies.get(key);
	}

	/**
	 * 
	 * @param headers
	 */
	public static void putCookies(Header[] headers) {
		for (Header header : headers) {
			if ("Set-Cookie".equals(header.getName())) {
				String[] result = formatCookie(header.getValue());
				if (result == null)
					continue;
				cookies.put(result[0], result[1]);
			}
		}
	}

	/**
	 * e.g. uin=10000;
	 * 
	 * @param cookie
	 * @return array[0] = key 
	 * 		   array[1] = value
	 */
	private static String[] formatCookie(String cookie) {
		try {
			cookie = cookie.substring(0, cookie.indexOf(COOKIE_END));
			String[] sCookie = cookie.split(COOKIE_SEPA);
			String[] result = new String[2];
			// valid length of cookie
			// 此处将丢弃服务端返回的value为空的cookie
			if (sCookie.length == 2) {
				result[0] = sCookie[0];
				result[1] = sCookie[1];
			} else {
				// TODO
				LOG.println("invalid cookie: " + cookie);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 调试期间使用
	 */
	@Deprecated
	public static void printAllCookies() {
		for (String key : cookies.keySet()) {
			System.out.println("cookieKey["+key+"], "
					+ "cookieValue["+cookies.get(key)+"]");
		}
	}
}
