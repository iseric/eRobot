package com.eric.rb.module;

import java.util.Map;

import com.eric.rb.action.ActionFactory;
import com.eric.rb.core.EAction;


/**
 * @FileName: UserModule.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class UserModule extends AbstractModule{
	
	/**
	 * check qqNum state
	 * 		verifyState 0: 正常
	 * 					1: 需验证
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> checkVerify(){
		EAction action = ActionFactory.getAction(EAction.Type.CHECK_VERIFY, context);
		return (Map<String, String>)action.execute();
	}
	
	/**
	 * get verify code iamge
	 * @return image path
	 */
	public String getVerifyImage(){
		EAction action = ActionFactory.getAction(EAction.Type.GET_VERIFY_IMAGE, context);
		return (String)action.execute();
	}
	
	/**
	 * login setp1
	 * get callback url
	 * @return
	 */
	public String login1(){
		EAction action = ActionFactory.getAction(EAction.Type.LOGIN1, context);
		return (String)action.execute();
	}
	
	/**
	 * get kernel cookies
	 */
	public void checkSig(){
		EAction action = ActionFactory.getAction(EAction.Type.CHECK_SIG, context);
		action.execute();
	}
	
	/**
	 * login setp2
	 * last login
	 * @return retcode
	 * 	0		success
	 * 	other	failed
	 */
	public boolean login2(){
		EAction action = ActionFactory.getAction(EAction.Type.LOGIN2, context);
		return (Boolean)action.execute();
	}
	
	
}
