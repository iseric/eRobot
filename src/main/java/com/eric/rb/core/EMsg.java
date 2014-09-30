package com.eric.rb.core;

import com.eric.rb.bean.EMessage;

/**
 * @FileName: EMsg.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public interface EMsg {
	enum Type{
		MESSAGE,
		GROUP_MESSAGE,
	}
	
	public void sendMsg(EMessage message);
	
	public EMessage autoReply(EContext context, EMessage message);
}
