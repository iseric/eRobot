package com.eric.rb.action;

/**
 * @FileName: ActionClassEnum.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public enum ActionClassEnum {
	CHECK_VERIFY("com.eric.rb.action.CheckVerifyAction"),
	GET_VERIFY_IMAGE("com.eric.rb.action.GetVerifyImageAction"),
	LOGIN1("com.eric.rb.action.Login1Action"),
	CHECK_SIG("com.eric.rb.action.CheckSigAction"),
	LOGIN2("com.eric.rb.action.Login2Action"),
	POLL("com.eric.rb.action.PollAction"),
	SEND_MSG("com.eric.rb.action.SendMsgAction"),
	
	
	
	END("");// 加东西很烦,老是要更改最后一个分号- -, 最后给一个假的
	
	private String clazz;
	
	private ActionClassEnum(String clazz){
		this.clazz = clazz;
	}
	public String getClassName(){
		return this.clazz;
	}
}
