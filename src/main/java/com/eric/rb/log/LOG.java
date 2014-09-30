package com.eric.rb.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: LOG.java
 * @Description:
 * @author Eric
 * @date 2014年9月30日
 */
public class LOG {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	public static void println(Class clazz,String log){
		System.out.println(sdf.format(new Date())+"-->"+log);
	}
	public static void println(String log){
		System.out.println(sdf.format(new Date())+"-->"+log);
	}
}
