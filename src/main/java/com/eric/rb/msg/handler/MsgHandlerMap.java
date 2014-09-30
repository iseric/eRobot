package com.eric.rb.msg.handler;

import java.util.HashMap;
import java.util.Map;

import com.eric.rb.core.EMsg;


/**
 * @FileName: MsgHandlerMap.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class MsgHandlerMap {
	private static Map<String, EMsg.Type> msgTypes = new HashMap<String, EMsg.Type>();
	
	private static Map<EMsg.Type, EMsg> msgHandlers = new HashMap<EMsg.Type, EMsg>();
	
	static {
		msgTypes.put("message", EMsg.Type.MESSAGE);
		msgTypes.put("group_message", EMsg.Type.GROUP_MESSAGE);
		
		
		msgHandlers.put(EMsg.Type.MESSAGE, new BuddyMsgHandler());
		msgHandlers.put(EMsg.Type.GROUP_MESSAGE, new GroupMsgHandler());
	}
	
	public static EMsg getMsgHandler(EMsg.Type type){
		return msgHandlers.get(type);
	}
	
	public static EMsg.Type getMsgType(String key){
		return msgTypes.get(key);
	}
}
