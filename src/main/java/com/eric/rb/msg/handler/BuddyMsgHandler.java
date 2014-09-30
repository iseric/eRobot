package com.eric.rb.msg.handler;

import com.eric.rb.bean.EMessage;
import com.eric.rb.core.EContext;


/**
 * @FileName: BuddyMsgHandler.java
 * @Description:
 * @author Eric
 * @date 2014年9月29日
 */
public class BuddyMsgHandler extends MsgHandler{
	@Override
	public EMessage msgHandle(EContext context, EMessage message) {
		message.setMsg("然后呢? ");
		return message;
	}

}
