package com.eric.rb.bean;

/**
 * @FileName: EUser.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class EUser {
	private String qqNum;
	private String password;
	
	private String verifyCode;
	private String verifyState;

	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
}
