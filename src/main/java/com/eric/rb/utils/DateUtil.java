package com.eric.rb.utils;

/**
 * @FileName: DateUtil.java
 * @Description:
 * @author Eric
 * @date 2014年9月30日
 */
public class DateUtil {

	private final static long DAY = 86400000;
	private final static long HOURS = 3600000;
	private final static long MINUTE = 60000;
	private final static long SECOND = 1000;
	
	/**
	 * 根据传入的启动时间计算当前应用运行时间
	 * @param startTime 启动时间
	 * @return
	 */
	public static String getRunTime(long startTime) {
		long runTime = System.currentTimeMillis() - startTime;
		
		long day = runTime / DAY;
		long hour = (runTime - day * DAY) / HOURS;
		long minute = (runTime- (day * DAY + hour * HOURS)) / MINUTE;
		long second = (runTime - (day * DAY + hour * HOURS + minute * MINUTE)) / SECOND;
		
		StringBuffer sb = new StringBuffer();
		sb.append(day);
		sb.append("天");
		sb.append(hour);
		sb.append("时");
		sb.append(minute);
		sb.append("分");
		sb.append(second);
		sb.append("秒");
		return sb.toString();
	}
}
