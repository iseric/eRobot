package com.eric.rb.http.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: EHttpHeader.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public class EHttpHeader {
	private final static Map<String, String> headers = new HashMap<String, String>();

	static {
		// init header
		headers.put("Connection", "keep-alive");
		headers.put("Accept-Charset", "UTF-8");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Referer",
				/*"http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2"*/
				"http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=3");
		headers.put("Accept-Encoding", "gzip,deflate,sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) "
				+ "AppleWebKit/537.36 (KHTML, like Gecko) "
				+ "Chrome/37.0.2024.2 Safari/537.36");
	}
	
	public static void putHread(String key, String value){
		headers.put(key, value);
	}
	
	public static Map<String, String> getHreads(){
		return headers;
	}
	
}
