package com.eric.rb.msg.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eric.rb.bean.EMessage;
import com.eric.rb.core.EContext;
import com.eric.rb.core.EMsg;

/**
 * @FileName: MsgHandler.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public abstract class MsgHandler implements EMsg{
	
	protected EContext context;
	/**
	 * 字体设置
	 * @return
	 */
	public static JSONArray getFont(){
		JSONObject font = new JSONObject();
		font.put("name", "宋体");
		font.put("size", "10");
		font.put("color", "000000");
		
		JSONArray style = new JSONArray();
		style.add(0);
		style.add(0);
		style.add(0);
		
		font.put("style", style);
		
		JSONArray array = new JSONArray();
//		array.add("font");
		array.add(font);
		return array;
	}
	public final void init(EContext context){
		this.context = context;
	}
	
	public final EMessage autoReply(EContext context, EMessage message){
		message.setFont(getFont());
		return msgHandle(context, message);
	}
	
	// 主动发起QQ消息会话
	public void sendMsg(EMessage message) {
	}
	
	// 可由子类继承覆盖, 进行消息的自定义处理, 如果没有子类继承, 则该消息将被丢弃
	public EMessage msgHandle(EContext context, EMessage message){
		return null;
	};
}
