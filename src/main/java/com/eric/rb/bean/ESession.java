package com.eric.rb.bean;

/**
 * @FileName: ESession.java
 * @Description:
 * @author Eric
 * @date 2014年9月30日
 */
public class ESession {
	private long clientId;
	private String psessionid;
	private String vfwebqq;
	
	private boolean run;

	public String getPsessionid() {
		return psessionid;
	}
	public void setPsessionid(String psessionid) {
		this.psessionid = psessionid;
	}
	public String getVfwebqq() {
		return vfwebqq;
	}
	public void setVfwebqq(String vfwebqq) {
		this.vfwebqq = vfwebqq;
	}
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
}
