package com.eric.rb.msg.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.eric.rb.bean.EMessage;
import com.eric.rb.core.EContext;


/**
 * @FileName: GroupMsgHandler.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class GroupMsgHandler extends MsgHandler{
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static String commonText= 
			"about　　#关于\r"+
			"uptime　#服务运行时间\r"+
			"time　　#当前系统时间\r  "+
			"roll　　#返回1到100随机数";

	@Override
	public EMessage msgHandle(EContext context, EMessage message) {
		String msg = message.getContent().trim();
		if("help".equalsIgnoreCase(msg)){
			message.setMsg(commonText);
		}else if("uptime".equalsIgnoreCase(msg)){
			message.setMsg(context.getRunTime());
		}else if("roll".equalsIgnoreCase(msg)){
			Random random = new Random(System.currentTimeMillis());
			message.setMsg(String.valueOf(random.nextInt(100)+1));
		}else if("time".equalsIgnoreCase(msg)){
			message.setMsg(sdf.format(new Date()));
		}else if("about".equals(msg)){
			String s = "版本： 1.0.0\r源码：https://github.com/iseric/eRobot";
			message.setMsg(s);
		}else{
			return null;
		}
		return message;
	}
}
