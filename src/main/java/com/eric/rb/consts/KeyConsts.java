package com.eric.rb.consts;

/**
 * @FileName: KeyConsts.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class KeyConsts {
	/**
	 * login step.1
	 * 查看账号是否需要验证
	 */
	/* 验证状态 key */
	public final static String VERIFY_STATE = "verifyState";
	/* 验证码 key */
	public final static String VERIFY_CODE = "verifyCode";
	
	/* 正常, 不需要验证 */
	public final static String VERIFY_STATE_VALID = "0";
	/* 不正常, 需要验证 */
	public final static String VERIFY_STATE_INVALID = "1";
	
	/* 参数传递 key*/
	public final static String PARAM = "param";
	
}
